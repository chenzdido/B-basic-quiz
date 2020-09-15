package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @NotBlank(message = "description is not empty")
    @Size(max=4096, min=1, message = "description max length is 256")
    private String description;
    @NotNull(message="year is not empty")
    @Min(value=1900, message="year should between 1900 to 2020")
    @Max(value=2020, message="year should between 1900 to 2020")
    private Integer year;
    @NotBlank(message = "title is not empty")
    @Size(max=256, min=1, message = "title max length is 256")
    private String title;
    private Integer userId;
}
