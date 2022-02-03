package zms.web.servlet;


import com.alibaba.fastjson.JSON;
import zms.pojo.PageBean;
import zms.pojo.StuWork;
import zms.service.Impl.StuWorkServiceImpl;
import zms.service.StuWorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/stuWork/*")
public class StuWorkServlet extends BaseServlet {

    private StuWorkService stuWorkService = new StuWorkServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<StuWork> stuWorks = stuWorkService.selectAll();

        //2.转为JSON
        String jsonString = JSON.toJSONString(stuWorks);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受学生数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为对应StuWork对象
        StuWork stuWork = JSON.parseObject(params, StuWork.class);

        //2.调用service添加
        stuWorkService.add(stuWork);

        //.响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受学生数据 [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为对应int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //2.调用service添加
        stuWorkService.deleteByIds(ids);

        //.响应成功的标识
        response.getWriter().write("success");
    }


    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受当前页码和每页展示条数 url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        ;

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2.调用service查询
        PageBean<StuWork> pageBean = stuWorkService.selectByPage(currentPage, pageSize);

        //3.转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //4.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    /**
     * 分页条件查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受当前页码和每页展示条数 url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        ;

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取对应的查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为对应StuWork对象
        StuWork stuWork = JSON.parseObject(params, StuWork.class);

        //2.调用service查询
        PageBean<StuWork> pageBean = stuWorkService.selectByPageAndCondition(currentPage, pageSize, stuWork);

        //3.转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //4.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
