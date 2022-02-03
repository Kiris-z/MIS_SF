package zms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import zms.pojo.Exercise;

import java.util.List;

public interface ExerciseMapper {

    @Select("select id,name from exercise")
    @ResultMap("exerciseResultMap")
    List<Exercise> selectAll();

    @Insert("insert into exercise values(null,#{exerciseScore},#{kind},#{content},#{answer},#{courseId},#{teacherId})")
    @ResultMap("exerciseResultMap")
    void add(Exercise exercise);

    /**3
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

/*    @Delete("delete from exercise where ")
    void delete(Exercise exercise);*/

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from exercise limit #{begin},#{size}")
    List<Exercise> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from exercise")
    int selectTotalCount();


    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Exercise> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("exercise") Exercise exercise);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(@Param("exercise") Exercise exercise);


}
