package zms.web.servlet;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import zms.mapper.StudentMapper;
import zms.mapper.TeacherMapper;
import zms.pojo.Student;
import zms.pojo.Teacher;
import zms.util.SqlSessionFactoryUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginStudentServlet", value = "/loginStudentServlet")
public class LoginStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受用户id和密码
        String id = request.getParameter("username");
        String password = request.getParameter("password");

        Cookie cookieId = new Cookie("username",id);
        Cookie cookiePassword = new Cookie("password",password);

        response.addCookie(cookieId);
        response.addCookie(cookiePassword);

        //调用mybatis完成查询
        //2.1获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.2获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.3获取Mapper
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        //2.4调用方法
        Student student = studentMapper.select(Integer.valueOf(id), password);

        //2.5
        sqlSession.close();




        //获取对应地字符输出流,并设置对应的content type
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        //3.判断student是否为null
        if (student != null) {
            //登陆成功
            HttpSession session = request.getSession();
            session.setAttribute("student",student);

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath+"/NavigationSTUDENT.html");
        } else {
            //登陆失败
            writer.write("登陆失败");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
