package zms.mapper;

import org.apache.ibatis.annotations.*;
import zms.pojo.Student;
import zms.service.StudentService;

import java.util.List;

public interface StudentMapper {

    @Select("select id,name,klass_id from student")
    @ResultMap("studentResultMap")
    List<Student> selectAll();

    @Select("select * from student where id = #{id} and password = #{password}")
    Student select(@Param("id") Integer id,@Param("password") String password);


    @Insert("insert into student values(null,#{studentName},#{klassId},12345)")
    void add(Student student);

    /**3
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

/*    @Delete("delete from student where ")
    void delete(Student student);*/

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from student limit #{begin},#{size}")
    List<Student> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from student")
    int selectTotalCount();



    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Student> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("student") Student student);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(@Param("student") Student student);


}
