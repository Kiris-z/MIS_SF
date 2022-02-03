package zms.service;

import zms.pojo.Course;
import zms.pojo.PageBean;

import java.util.List;

public interface CourseService {

    List<Course> selectAll();


    void add(Course course);


    void deleteByIds(int[] ids);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    PageBean<Course> selectByPage(int currentPage,int pageSize);


    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param course
     * @return
     */
    PageBean<Course> selectByPageAndCondition(int currentPage,int pageSize,Course course);
}
