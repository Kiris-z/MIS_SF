package zms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import zms.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {

    @Select("select id,name,department from teacher")
    @ResultMap("teacherResultMap")
    List<Teacher> selectAll();

    @Select("select * from teacher where id = #{id} and password = #{password} and permission = #{permission}")
    Teacher select(@Param("id") Integer id,@Param("password") String password,@Param("permission") Integer permission);


    @Insert("insert into teacher values(null,#{teacherName},#{department},1,123456)")
    void add(Teacher teacher);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

/*    @Delete("delete from teacher where ")
    void delete(Teacher teacher);*/

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from teacher limit #{begin},#{size}")
    List<Teacher> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from teacher")
    int selectTotalCount();



    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Teacher> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("teacher") Teacher teacher);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(@Param("teacher") Teacher teacher);


}
