package cn.xdl.ydma.dao;

import cn.xdl.ydma.entity.Direction;
import org.apache.ibatis.jdbc.SQL;

public class DirectionSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table direction
     *
     * @mbg.generated Mon Sep 02 19:04:13 GMT+08:00 2019
     */
    public String insertSelective(Direction record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("direction");
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table direction
     *
     * @mbg.generated Mon Sep 02 19:04:13 GMT+08:00 2019
     */
    public String updateByPrimaryKeySelective(Direction record) {
        SQL sql = new SQL();
        sql.UPDATE("direction");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}