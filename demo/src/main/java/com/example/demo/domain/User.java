package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    Long id;
    @NotBlank(message = "user name is not empty")
    @Length(max=128, min=1, message = "user name max length is 128")
    String name;
    @NotNull(message = "user age is not empty")
    @Min(value=16, message="user age should greater than 16 ")
    Long age;
    @NotBlank(message = "imgURL is not empty")
    @Length(max=512, min=8, message = "imgURL length is 8 to 512")
    String avatar;
    @Length(max=1024,message = "description is up to 128")
    String description;
}
