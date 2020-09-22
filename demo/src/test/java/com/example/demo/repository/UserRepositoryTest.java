package com.example.demo.repository;

import com.example.demo.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_return_user_when_id_exists() {
        entityManager.persistAndFlush(UserDto.builder()
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build());

        Optional<UserDto> found = userRepository.findOneById(1L);

        assertThat(found.isPresent()).isTrue();
        assertThat(found.get()).isEqualTo(UserDto.builder()
                .id(1L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build());
    }
}