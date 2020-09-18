package com.example.demo.repository;

import com.example.demo.dto.EducationDto;
import com.example.demo.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<EducationDto, Long> {

    @Transactional
    List<EducationDto> findAllByUserId(Long userId);
}
