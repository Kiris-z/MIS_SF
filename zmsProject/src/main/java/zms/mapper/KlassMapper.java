package zms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import zms.pojo.Klass;

import java.util.List;

public interface KlassMapper {

    @Select("select id,name from klass")
    @ResultMap("klassResultMap")
    List<Klass> selectAll();

    @Insert("insert into klass values(#{klassId},#{klassName})")
    void add(Klass klass);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

/*    @Delete("delete from klass where ")
    void delete(Klass klass);*/

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from klass limit #{begin},#{size}")
    List<Klass> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from klass")
    int selectTotalCount();



    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Klass> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("klass") Klass klass);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(@Param("klass") Klass klass);


}
