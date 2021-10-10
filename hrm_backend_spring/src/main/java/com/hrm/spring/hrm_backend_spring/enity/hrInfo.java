package com.hrm.spring.hrm_backend_spring.enity;

import lombok.Data;

//本データ
@Data
public class hrInfo {
    private int id;
    private String name;
    private String gender; 
    private int birthday;
    private String other;
    private int section_id;
    private int post_id;
}
