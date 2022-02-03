package zms.service;

import zms.pojo.PageBean;
import zms.pojo.Student;

import java.util.List;

public interface StudentService {

    List<Student> selectAll();


    void add(Student student);


    void deleteByIds(int[] ids);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    PageBean<Student> selectByPage(int currentPage,int pageSize);

    Student select(Integer id,String password);


    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param student
     * @return
     */
    PageBean<Student> selectByPageAndCondition(int currentPage,int pageSize,Student student);
}
