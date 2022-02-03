package zms.web.servlet;


import com.alibaba.fastjson.JSON;
import zms.pojo.PageBean;
import zms.pojo.Klass;
import zms.service.Impl.KlassServiceImpl;
import zms.service.KlassService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/klass/*")
public class KlassServlet extends BaseServlet {

    private KlassService klassService = new KlassServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<Klass> klasss = klassService.selectAll();

        //2.转为JSON
        String jsonString = JSON.toJSONString(klasss);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受学生数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为对应Klass对象
        Klass klass = JSON.parseObject(params, Klass.class);

        //2.调用service添加
        klassService.add(klass);

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
        klassService.deleteByIds(ids);

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
        PageBean<Klass> pageBean = klassService.selectByPage(currentPage, pageSize);

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

        //转为对应Klass对象
        Klass klass = JSON.parseObject(params, Klass.class);

        //2.调用service查询
        PageBean<Klass> pageBean = klassService.selectByPageAndCondition(currentPage, pageSize, klass);

        //3.转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //4.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
