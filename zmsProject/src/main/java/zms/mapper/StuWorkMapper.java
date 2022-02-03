package zms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import zms.pojo.StuWork;

import java.util.List;

public interface StuWorkMapper {

    @Select("select id,name from stu_work")
    @ResultMap("stuWorkResultMap")
    List<StuWork> selectAll();

    @Insert("insert into stu_work values(null,#{title},#{teacherId},#{courseId},#{klassId},#{createDate},#{deadLine})")
    void add(StuWork stuWork);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

/*    @Delete("delete from stuWork where ")
    void delete(StuWork stuWork);*/

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from stu_work limit #{begin},#{size}")
    List<StuWork> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from stu_work")
    int selectTotalCount();



    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<StuWork> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("stuWork") StuWork stuWork);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(@Param("stuWork") StuWork stuWork);


}
