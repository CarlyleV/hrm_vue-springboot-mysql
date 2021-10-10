package com.hrm.spring.hrm_backend_spring.enity;

import lombok.Data;


//Field
@Data
public class field {
    private String id;
    private String name;
    private String gender; 
    private String birthday;
    private String other;
    private String section_id;
    private String post_id;
}

