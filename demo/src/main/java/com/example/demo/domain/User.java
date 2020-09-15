package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private static Integer count=0;
    private Integer id;
    @NotBlank(message = "user name is not empty")
    @Size(max=16, message = "user name max length is 16")
    private String name;
    @Min(value=16, message="user age should greater than 16 ")
    private Integer age;
    @Size(max=64, min=1, message = "imgURL length is 1 to 64")
    @JsonProperty("avatar")
    private String imgURL;
    @Size(max=128,message = "description is up to 128")
    private String description;
    public User(String name, Integer age, String imgURL, String description){
        this.id = ++this.count;
        this.name = name;
        this.age = age;
        this.imgURL = imgURL;
        this.description = description;
    }
}
