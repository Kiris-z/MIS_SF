package zms.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import zms.mapper.TeacherMapper;
import zms.pojo.PageBean;
import zms.pojo.Teacher;
import zms.service.TeacherService;
import zms.util.SqlSessionFactoryUtils;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Teacher> selectAll() {
        SqlSession sqlSession = factory.openSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        List<Teacher> teachers = mapper.selectAll();


        sqlSession.close();

        return teachers;
    }

    @Override
    public Teacher select(Integer id, String password, Integer permission) {
        SqlSession sqlSession = factory.openSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        Teacher teacher = mapper.select(id, password, permission);

        sqlSession.commit();

        sqlSession.close();

        return teacher;

    }


    @Override
    public void add(Teacher teacher) {
        SqlSession sqlSession = factory.openSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        mapper.add(teacher);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        mapper.deleteByIds(ids);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public PageBean<Teacher> selectByPage(int currentPage, int pageSize) {

        SqlSession sqlSession = factory.openSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //查询当前页数据
        List<Teacher> rows = mapper.selectByPage(begin, size);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        PageBean<Teacher> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Teacher> selectByPageAndCondition(int currentPage, int pageSize, Teacher teacher) {
        SqlSession sqlSession = factory.openSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //处理teacher条件,模糊表达式
        String name = teacher.getTeacherName();
        if (name != null && name.length() > 0) {
            teacher.setTeacherName("%" + name + "%");
        }

        String department = teacher.getDepartment();
        if (department != null && department.length() > 0) {
            teacher.setDepartment("%" + department + "%");
        }
        //查询当前页数据
        List<Teacher> rows = mapper.selectByPageAndCondition(begin, size, teacher);

        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(teacher);

        PageBean<Teacher> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);


        //释放资源
        sqlSession.close();

        return pageBean;
    }
}
