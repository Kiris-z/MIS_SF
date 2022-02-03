package zms.web.servlet;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import zms.mapper.TeacherMapper;
import zms.pojo.Teacher;
import zms.util.SqlSessionFactoryUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginTeacherServlet", value = "/loginTeacherServlet")
public class LoginTeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受用户id和密码
        String[] values = request.getParameterValues("permission");
        String id = request.getParameter("usernameTeacher");
        String password = request.getParameter("passwordTeacher");

        Integer permission = 1;
        if (values != null && values.length > 0) {
            permission = 0;
        }


        Cookie cookieId = new Cookie("username", id);
        Cookie cookiePassword = new Cookie("password", password);
        Cookie cookiePermission = new Cookie("permission", permission.toString());

        response.addCookie(cookieId);
        response.addCookie(cookiePassword);
        response.addCookie(cookiePermission);

        //调用mybatis完成查询
        //2.1获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.2获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.3获取Mapper
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        //2.4调用方法
        Teacher teacher = teacherMapper.select(Integer.valueOf(id), password, permission);

        //2.5
        sqlSession.close();

        //获取对应地字符输出流,并设置对应的content type
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        //3.判断teacher是否为null
        if (teacher != null) {
            HttpSession session = request.getSession();
            session.setAttribute("teacher", teacher);
            String contextPath = request.getContextPath();
            if (teacher.getPermission() == 1) {
                response.sendRedirect(contextPath + "/NavigationTEACHER.html");
            } else {
                response.sendRedirect(contextPath + "/NavigationADMIN.html");
            }
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
