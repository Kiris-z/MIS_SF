package zms.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import zms.mapper.KlassMapper;
import zms.pojo.PageBean;
import zms.pojo.Klass;
import zms.service.KlassService;
import zms.util.SqlSessionFactoryUtils;

import java.util.List;

public class KlassServiceImpl implements KlassService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Klass> selectAll() {
        SqlSession sqlSession = factory.openSession();

        KlassMapper mapper = sqlSession.getMapper(KlassMapper.class);

        List<Klass> klasss = mapper.selectAll();


        sqlSession.close();

        return klasss;
    }



    @Override
    public void add(Klass klass) {
        SqlSession sqlSession = factory.openSession();

        KlassMapper mapper = sqlSession.getMapper(KlassMapper.class);

        mapper.add(klass);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();

        KlassMapper mapper = sqlSession.getMapper(KlassMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public PageBean<Klass> selectByPage(int currentPage, int pageSize) {

        SqlSession sqlSession = factory.openSession();

        KlassMapper mapper = sqlSession.getMapper(KlassMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //查询当前页数据
        List<Klass> rows = mapper.selectByPage(begin, size);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        PageBean<Klass> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Klass> selectByPageAndCondition(int currentPage, int pageSize, Klass klass) {
        SqlSession sqlSession = factory.openSession();

        KlassMapper mapper = sqlSession.getMapper(KlassMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //处理klass条件,模糊表达式
        String name = klass.getKlassName();
        if (name != null && name.length() > 0) {
            klass.setKlassName("%" + name + "%");
        }

        //查询当前页数据
        List<Klass> rows = mapper.selectByPageAndCondition(begin, size, klass);

        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(klass);

        PageBean<Klass> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }
}
