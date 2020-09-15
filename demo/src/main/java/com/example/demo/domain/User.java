package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private static Integer count=0;
    private Integer id;
    private String name;
    private Integer age;
    private String imgURL;
    private String description;
    public User(String name, Integer age, String imgURL, String description){
        this.id = ++this.count;
        this.name = name;
        this.age = age;
        this.imgURL = imgURL;
        this.description = description;
    }
}
