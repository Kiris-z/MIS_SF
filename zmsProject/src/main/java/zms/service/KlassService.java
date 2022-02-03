package zms.service;

import zms.pojo.PageBean;
import zms.pojo.Klass;

import java.util.List;

public interface KlassService {

    List<Klass> selectAll();

    void add(Klass klass);

    void deleteByIds(int[] ids);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    PageBean<Klass> selectByPage(int currentPage,int pageSize);


    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param klass
     * @return
     */
    PageBean<Klass> selectByPageAndCondition(int currentPage,int pageSize,Klass klass);
}
