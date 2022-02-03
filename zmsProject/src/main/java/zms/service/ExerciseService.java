package zms.service;

import zms.pojo.Exercise;
import zms.pojo.PageBean;

import java.util.List;

public interface ExerciseService {

    List<Exercise> selectAll();


    void add(Exercise exercise);


    void deleteByIds(int[] ids);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    PageBean<Exercise> selectByPage(int currentPage,int pageSize);


    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param exercise
     * @return
     */
    PageBean<Exercise> selectByPageAndCondition(int currentPage,int pageSize,Exercise exercise);
}
