package cn.xdl.ydma.dao;

import cn.xdl.ydma.entity.Video;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface VideoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbg.generated Mon Sep 02 19:04:13 GMT+08:00 2019
     */
    @Delete({
        "delete from video",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbg.generated Mon Sep 02 19:04:13 GMT+08:00 2019
     */
    @Insert({
        "insert into video (name, url, ",
        "chapter_id)",
        "values (#{name,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, ",
        "#{chapterId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Video record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbg.generated Mon Sep 02 19:04:13 GMT+08:00 2019
     */
    @InsertProvider(type=VideoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Video record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbg.generated Mon Sep 02 19:04:13 GMT+08:00 2019
     */
    @Select({
        "select",
        "id, name, url, chapter_id",
        "from video",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="chapter_id", property="chapterId", jdbcType=JdbcType.INTEGER)
    })
    Video selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, name, url, chapter_id",
        "from video",
        "where chapter_id = #{chapter_id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="chapter_id", property="chapterId", jdbcType=JdbcType.INTEGER)
    })
    List<Video> selectBycourseid(Integer id);

    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbg.generated Mon Sep 02 19:04:13 GMT+08:00 2019
     */
    @UpdateProvider(type=VideoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Video record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbg.generated Mon Sep 02 19:04:13 GMT+08:00 2019
     */
    @Update({
        "update video",
        "set name = #{name,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "chapter_id = #{chapterId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Video record);
}