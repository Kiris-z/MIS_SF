package zms.web.old;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import zms.pojo.Student;
import zms.service.Impl.StudentServiceImpl;
import zms.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "addServlet", value = "/addServlet")
public class AddServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受学生数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为对应Student对象
        Student student = JSON.parseObject(params,Student.class);

        //2.调用service添加
        studentService.add(student);

        //.响应成功的标识
        response.getWriter().write("success");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
