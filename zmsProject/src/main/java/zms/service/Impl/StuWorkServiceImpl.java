package zms.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import zms.mapper.StuWorkMapper;
import zms.pojo.PageBean;
import zms.pojo.StuWork;
import zms.service.StuWorkService;
import zms.util.SqlSessionFactoryUtils;

import java.util.List;

public class StuWorkServiceImpl implements StuWorkService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<StuWork> selectAll() {
        SqlSession sqlSession = factory.openSession();

        StuWorkMapper mapper = sqlSession.getMapper(StuWorkMapper.class);

        List<StuWork> stuWorks = mapper.selectAll();


        sqlSession.close();

        return stuWorks;
    }



    @Override
    public void add(StuWork stuWork) {
        SqlSession sqlSession = factory.openSession();

        StuWorkMapper mapper = sqlSession.getMapper(StuWorkMapper.class);

        mapper.add(stuWork);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();

        StuWorkMapper mapper = sqlSession.getMapper(StuWorkMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public PageBean<StuWork> selectByPage(int currentPage, int pageSize) {

        SqlSession sqlSession = factory.openSession();

        StuWorkMapper mapper = sqlSession.getMapper(StuWorkMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //查询当前页数据
        List<StuWork> rows = mapper.selectByPage(begin, size);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        PageBean<StuWork> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<StuWork> selectByPageAndCondition(int currentPage, int pageSize, StuWork stuWork) {
        SqlSession sqlSession = factory.openSession();

        StuWorkMapper mapper = sqlSession.getMapper(StuWorkMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;


        //查询当前页数据
        List<StuWork> rows = mapper.selectByPageAndCondition(begin, size, stuWork);


        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(stuWork);

        PageBean<StuWork> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }
}
