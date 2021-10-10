package com.hrm.spring.hrm_backend_spring.mapper;

import com.hrm.spring.hrm_backend_spring.enity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface hrInfoMapper {

    // 本データ取得
    @Select("SELECT * from hr_data")
    public List<hrInfo> getAll();

    // フロントエンドで実現させることにしました
    // @Select("select * from hr_data where ${search_field} like '%${search_text}%'")
    // public List<hrInfo> search(String search_field,String search_text);

    // 順番変更
    @Select("select * from hr_data order by ${field} ${order}")
    public List<hrInfo> orderList(String field,String order);

    // POST取得
    @Select("SELECT * from post")
    public List<postSection> getPost();

    // SECTION取得
    @Select("SELECT * from section")
    public List<postSection> getSection();

    // FIELD取得
    @Select("SELECT * from field")
    public List<field> getField();

    // データ変更
    @Update("update hr_data set name = #{name} , gender = #{gender} , birthday = #{birthday} , other = #{other} , section_id = #{section_id} , post_id =#{post_id} where id = #{id}")
    public int updateHrData(hrInfo hrInfoParams);

    // データ削除
    @Delete("delete from hr_data where id = #{id}")
    public int deleteHrData(Integer id);

    // データ追加
    @Insert("insert into hr_data values(#{id}, #{name}, #{gender}, #{birthday}, #{other}, #{section_id}, #{post_id})")
    public int insertHrData(hrInfo hrInfoParams);
}


