package com.example.demo.repository;

import com.example.demo.domain.Education;
import com.example.demo.dto.EducationDto;
import com.example.demo.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class EducationRepositoryTest {
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_return_education_list_when_id_exists() {
        entityManager.persistAndFlush(UserDto.builder()
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build());
        entityManager.persistAndFlush(EducationDto.builder()
                .description("Eos, explicabo, nam")
                .year(2010L)
                .title( "Secondary school specializing in artistic")
                .user(userRepository.findOneById(1L).get())
                .build());
        List<EducationDto> educationDtoList = educationRepository.findAllByUserId(1L);

        assertThat(educationDtoList.size()).isEqualTo(1);
    }

}