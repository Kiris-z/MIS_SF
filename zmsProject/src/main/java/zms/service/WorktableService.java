package zms.service;

import zms.pojo.Worktable;
import zms.pojo.PageBean;

import java.util.List;

public interface WorktableService {

    List<Worktable> selectAll();


    void add(Worktable worktable);


    void deleteByIds(int[] ids);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    PageBean<Worktable> selectByPage(int currentPage,int pageSize);


    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param worktable
     * @return
     */
    PageBean<Worktable> selectByPageAndCondition(int currentPage,int pageSize,Worktable worktable);
}
