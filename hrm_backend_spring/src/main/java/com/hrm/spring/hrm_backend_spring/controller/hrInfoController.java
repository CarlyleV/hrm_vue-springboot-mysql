package com.hrm.spring.hrm_backend_spring.controller;

import com.hrm.spring.hrm_backend_spring.enity.*;
import com.hrm.spring.hrm_backend_spring.service.hrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hrData")
public class hrInfoController {
    @Autowired
    hrInfoService hrInfoService;

    // 本データ取得
    @GetMapping("/getAll")
    public List<hrInfo> getAll(){
        return hrInfoService.getAll();
    }

    // フロントエンドで実現させることにしました
    // @GetMapping("/search/{search_field}/{search_text}")
    // public List<hrInfo> search(@PathVariable("search_field") String search_field,@PathVariable("search_text") String search_text){
    //     return hrInfoService.search(search_field,search_text);
    // }

    // 順番変更
    @GetMapping("/orderList/{field}/{order}")
    public List<hrInfo> orderList(@PathVariable("field") String field,@PathVariable("order") String order){
        return hrInfoService.orderList(field,order);
    }

    // POST取得
    @GetMapping("/getPost")
    public List<postSection> getPost(){
        return hrInfoService.getPost();
    }

    // SECTION取得
    @GetMapping("/getSection")
    public List<postSection> getSection(){
        return hrInfoService.getSection();
    }

    // Field取得
    @GetMapping("/getField")
    public List<field> getField(){
        return hrInfoService.getField();
    }
    
    // データ変更
    @PutMapping("/update")
    public int updateHrData(@RequestBody hrInfo hrInfoParams){
        return hrInfoService.updateHrData(hrInfoParams);
    }

    // データ削除
    @DeleteMapping("/delete/{id}")
    public int deleteBook(@PathVariable("id") Integer id){
        return hrInfoService.deleteHrData(id);
    }

    // データ追加
    @PostMapping("/add")
    public int insertHrData(@RequestBody hrInfo hrInfoParams){
        return hrInfoService.insertHrData(hrInfoParams);
    }
}
