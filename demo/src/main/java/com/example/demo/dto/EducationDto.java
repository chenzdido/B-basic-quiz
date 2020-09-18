package com.example.demo.dto;

import com.example.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long eduid;
    Long year;
    String title;
    String description;
    @ManyToOne
    @JoinColumn(name = "id")
    UserDto user;
}
