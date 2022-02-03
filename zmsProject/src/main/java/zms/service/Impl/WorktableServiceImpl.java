package zms.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import zms.mapper.WorktableMapper;
import zms.pojo.Worktable;
import zms.pojo.PageBean;
import zms.service.WorktableService;
import zms.util.SqlSessionFactoryUtils;

import java.util.List;

public class WorktableServiceImpl implements WorktableService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Worktable> selectAll() {
        SqlSession sqlSession = factory.openSession();

        WorktableMapper mapper = sqlSession.getMapper(WorktableMapper.class);

        List<Worktable> worktables = mapper.selectAll();


        sqlSession.close();

        return worktables;
    }



    @Override
    public void add(Worktable worktable) {
        SqlSession sqlSession = factory.openSession();

        WorktableMapper mapper = sqlSession.getMapper(WorktableMapper.class);

        mapper.add(worktable);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();

        WorktableMapper mapper = sqlSession.getMapper(WorktableMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public PageBean<Worktable> selectByPage(int currentPage, int pageSize) {

        SqlSession sqlSession = factory.openSession();

        WorktableMapper mapper = sqlSession.getMapper(WorktableMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //查询当前页数据
        List<Worktable> rows = mapper.selectByPage(begin, size);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        PageBean<Worktable> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Worktable> selectByPageAndCondition(int currentPage, int pageSize, Worktable worktable) {
        SqlSession sqlSession = factory.openSession();

        WorktableMapper mapper = sqlSession.getMapper(WorktableMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //处理worktable条件,模糊表达式
        String title = worktable.getTitle();
        if (title != null && title.length() > 0) {
            worktable.setTitle("%" + title + "%");
        }

        //查询当前页数据
        List<Worktable> rows = mapper.selectByPageAndCondition(begin, size, worktable);


        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(worktable);

        PageBean<Worktable> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }
}
