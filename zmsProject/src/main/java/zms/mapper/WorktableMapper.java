package zms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import zms.pojo.Worktable;

import java.util.List;

public interface WorktableMapper {

    @Select("select id,name from worktable")
    @ResultMap("worktableResultMap")
    List<Worktable> selectAll();

    @Insert("insert into worktable values(null,#{title},#{teacherId},#{courseId},#{klassId},#{createDate},#{deadLine})")
    void add(Worktable worktable);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

/*    @Delete("delete from worktable where ")
    void delete(Worktable worktable);*/

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from worktable limit #{begin},#{size}")
    List<Worktable> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from worktable")
    int selectTotalCount();



    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Worktable> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("worktable") Worktable worktable);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(@Param("worktable") Worktable worktable);


}
