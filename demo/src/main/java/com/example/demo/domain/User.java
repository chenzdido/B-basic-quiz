package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private static Integer count=0;
    private Integer id;
    @NotBlank(message = "user name is not empty")
    @Size(max=128, min=1, message = "user name max length is 128")
    private String name;
    @NotNull(message = "user age is not empty")
    @Min(value=16, message="user age should greater than 16 ")
    private Integer age;
    @NotBlank(message = "imgURL is not empty")
    @Size(max=512, min=8, message = "imgURL length is 8 to 512")
    private String avatar;
    @Size(max=1024,message = "description is up to 128")
    private String description;
    public User(String name, Integer age, String imgURL, String description){
        this.id = ++this.count;
        this.name = name;
        this.age = age;
        this.avatar = imgURL;
        this.description = description;
    }
}
