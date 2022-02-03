package zms.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import zms.mapper.CourseMapper;
import zms.pojo.Course;
import zms.pojo.PageBean;
import zms.service.CourseService;
import zms.util.SqlSessionFactoryUtils;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Course> selectAll() {
        SqlSession sqlSession = factory.openSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);

        List<Course> courses = mapper.selectAll();


        sqlSession.close();

        return courses;
    }



    @Override
    public void add(Course course) {
        SqlSession sqlSession = factory.openSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);

        mapper.add(course);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public PageBean<Course> selectByPage(int currentPage, int pageSize) {

        SqlSession sqlSession = factory.openSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //查询当前页数据
        List<Course> rows = mapper.selectByPage(begin, size);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        PageBean<Course> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Course> selectByPageAndCondition(int currentPage, int pageSize, Course course) {
        SqlSession sqlSession = factory.openSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //处理course条件,模糊表达式
        String name = course.getCourseName();
        if (name != null && name.length() > 0) {
            course.setCourseName("%" + name + "%");
        }

        //查询当前页数据
        List<Course> rows = mapper.selectByPageAndCondition(begin, size, course);

        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(course);

        PageBean<Course> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }
}
