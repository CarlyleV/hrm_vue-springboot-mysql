package com.hrm.spring.hrm_backend_spring.service;

import com.hrm.spring.hrm_backend_spring.enity.*;
import com.hrm.spring.hrm_backend_spring.mapper.hrInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class hrInfoService {
    @Autowired
    hrInfoMapper hrInfoMapper;

    // 本データ取得
    public List<hrInfo> getAll(){
        return hrInfoMapper.getAll();
    }

    // フロントエンドで実現させることにしました
    // public List<hrInfo> search(String search_field,String search_text){
    //     return hrInfoMapper.search(search_field,search_text);
    // }

    // 順番変更
    public List<hrInfo> orderList(String field,String order){
        return hrInfoMapper.orderList(field,order);
    }
    
    // POST取得
    public List<postSection> getPost(){
        return hrInfoMapper.getPost();
    }
    
    // SECTION取得
    public List<postSection> getSection(){
        return hrInfoMapper.getSection();
    }

    // FIELD取得
    public List<field> getField(){
        return hrInfoMapper.getField();
    }
    
    // データ変更
    public int updateHrData(hrInfo hrInfoParams){
        return hrInfoMapper.updateHrData(hrInfoParams);
    }

    // データ削除
    public int deleteHrData(Integer id){
        return hrInfoMapper.deleteHrData(id);
    }
    
    // データ追加
    public int insertHrData(hrInfo hrInfoParams){
        return hrInfoMapper.insertHrData(hrInfoParams);
    }
}
