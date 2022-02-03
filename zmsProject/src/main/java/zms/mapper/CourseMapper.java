package zms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import zms.pojo.Course;

import java.util.List;

public interface CourseMapper {

    @Select("select id,name from course")
    @ResultMap("courseResultMap")
    List<Course> selectAll();

    @Insert("insert into course values(#{courseId},#{courseName},#{credit})")
    void add(Course course);

    /**3
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

/*    @Delete("delete from course where ")
    void delete(Course course);*/

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from course limit #{begin},#{size}")
    List<Course> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from course")
    int selectTotalCount();



    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Course> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("course") Course course);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(@Param("course") Course course);


}
