package zms.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import zms.mapper.ExerciseMapper;
import zms.pojo.Exercise;
import zms.pojo.PageBean;
import zms.service.ExerciseService;
import zms.util.SqlSessionFactoryUtils;

import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Exercise> selectAll() {
        SqlSession sqlSession = factory.openSession();

        ExerciseMapper mapper = sqlSession.getMapper(ExerciseMapper.class);

        List<Exercise> exercises = mapper.selectAll();


        sqlSession.close();

        return exercises;
    }



    @Override
    public void add(Exercise exercise) {
        SqlSession sqlSession = factory.openSession();

        ExerciseMapper mapper = sqlSession.getMapper(ExerciseMapper.class);

        mapper.add(exercise);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();

        ExerciseMapper mapper = sqlSession.getMapper(ExerciseMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public PageBean<Exercise> selectByPage(int currentPage, int pageSize) {

        SqlSession sqlSession = factory.openSession();

        ExerciseMapper mapper = sqlSession.getMapper(ExerciseMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //查询当前页数据
        List<Exercise> rows = mapper.selectByPage(begin, size);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        PageBean<Exercise> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Exercise> selectByPageAndCondition(int currentPage, int pageSize, Exercise exercise) {
        SqlSession sqlSession = factory.openSession();

        ExerciseMapper mapper = sqlSession.getMapper(ExerciseMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

/*        //处理exercise条件,模糊表达式
        String content = exercise.getContent();
        if (content != null && content.length() > 0) {
            exercise.setContent("%" + content + "%");
        }*/

        //查询当前页数据
        List<Exercise> rows = mapper.selectByPageAndCondition(begin, size, exercise);

        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(exercise);

        PageBean<Exercise> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }
}
