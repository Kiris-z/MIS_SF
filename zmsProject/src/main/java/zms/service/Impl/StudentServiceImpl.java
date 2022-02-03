package zms.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import zms.mapper.StudentMapper;
import zms.pojo.PageBean;
import zms.pojo.Student;
import zms.service.StudentService;
import zms.util.SqlSessionFactoryUtils;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Student> selectAll() {
        SqlSession sqlSession = factory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = mapper.selectAll();


        sqlSession.close();

        return students;
    }

    @Override
    public Student select(Integer id, String password) {
        SqlSession sqlSession = factory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        Student student = mapper.select(id, password);

        sqlSession.commit();

        sqlSession.close();

        return student;
    }


    @Override
    public void add(Student student) {
        SqlSession sqlSession = factory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        mapper.add(student);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public PageBean<Student> selectByPage(int currentPage, int pageSize) {

        SqlSession sqlSession = factory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //查询当前页数据
        List<Student> rows = mapper.selectByPage(begin, size);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        PageBean<Student> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Student> selectByPageAndCondition(int currentPage, int pageSize, Student student) {
        SqlSession sqlSession = factory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //处理student条件,模糊表达式
        String name = student.getName();
        if (name != null && name.length() > 0) {
            student.setName("%" + name + "%");
        }

        //查询当前页数据
        List<Student> rows = mapper.selectByPageAndCondition(begin, size, student);

        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(student);

        PageBean<Student> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }
}
