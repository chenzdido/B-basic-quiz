package com.example.demo.service;

import com.example.demo.domain.Education;
import com.example.demo.domain.User;
import com.example.demo.dto.EducationDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.IDNotFoundException;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EducationRepository educationRepository;
    private UserDto userDto;
    private EducationDto educationDto;
    private User user;
    private Education education;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository,educationRepository);
        userDto = UserDto.builder()
                .id(1L)
                .name("KAMIL")
                .age(24L)
                .avatar("http://...")
                .description("Lorem ipsum dolor sit amet")
                .build();
        user = User.builder()
                .id(1L)
                .name("KAMIL")
                .age(24L)
                .avatar("http://...")
                .description("Lorem ipsum dolor sit amet")
                .build();
        education = Education.builder()
                .description("Eos, explicabo, nam")
                .year(2010L)
                .title( "Secondary school specializing in artistic")
                .userId(1L)
                .build();
        educationDto = EducationDto.builder()
                .description("Eos, explicabo, nam")
                .year(2010L)
                .title( "Secondary school specializing in artistic")
                .user(userDto)
                .build();
    }

    @Nested
    class FindUserById {

        @Nested
        class WhenIdExists {

            @Test
            public void should_return_user() {
                when(userRepository.findOneById(1L)).thenReturn(Optional.of(userDto));

                User foundUser = userService.getUserById(1L);

                assertThat(foundUser).isEqualTo(User.builder()
                        .id(userDto.getId())
                        .name(userDto.getName())
                        .age(userDto.getAge())
                        .avatar(userDto.getAvatar())
                        .description(userDto.getDescription())
                        .build());
            }
        }

        @Nested
        class WhenUserIdNotExisted {

            @Test
            void should_throw_exception() {
                when(userRepository.findOneById(222L)).thenReturn(Optional.empty());
                IDNotFoundException thrownException = assertThrows(IDNotFoundException.class, () -> {
                    userService.getUserById(222L);
                });
                assertThat(thrownException.getMessage()).containsSequence("user id is not exist");
            }
        }
    }

    @Nested
    class CreateUser{
        private UserDto newUserDtoRequest;
        private User newUserRequest;
        @BeforeEach
        public void beforeEach() {
            newUserRequest = User.builder()
                    .name("Panda")
                    .age(24L)
                    .avatar("http://...")
                    .description("A good guy.")
                    .build();
            newUserDtoRequest = UserDto.builder()
                    .name("Panda")
                    .age(24L)
                    .avatar("http://...")
                    .description("A good guy.")
                    .build();
        }

        @Test
        void should_create_new_user_and_return_its_id () {
            when(userRepository.save(newUserDtoRequest)).thenReturn(newUserDtoRequest);
            Long id = userService.createUser(newUserRequest);
            assertThat(id).isEqualTo(newUserDtoRequest.getId());
        }

    }

    @Nested
    class CreateEducation{
        @Nested
        class WhenRequestIsNotValid {

            @Test
            public void should_not_create_new_education(){
                when(userRepository.findOneById(222L)).thenReturn(Optional.empty());
                IDNotFoundException thrownException = assertThrows(IDNotFoundException.class, () -> {
                    userService.createEducation(222L,education);
                });
                assertThat(thrownException.getMessage()).containsSequence("user id is not exist");
            }
        }
    }

    @Nested
    class GetEducationsById {
        @Nested
        class WhenEducationExists{
            private List<EducationDto> educationDtoList = new ArrayList<>();
            @BeforeEach
            public void beforeEach() {
                educationDtoList.add(educationDto);
            }
            @Test
            public void should_return_educations_by_id(){
                when(educationRepository.findAllByUserId(1L)).thenReturn(educationDtoList);
                List<Education> educations = userService.getEducationsById(1L);
                List<Education> educationList = new ArrayList<>();
                for (EducationDto dto : educationDtoList) {
                    educationList.add(Education.builder()
                            .title(dto.getTitle())
                            .description(dto.getDescription())
                            .year(dto.getYear())
                            .userId(dto.getUser().getId())
                            .build()
                    );
                }
                assertThat(educations).isEqualTo(educationList);
            }
        }
    }

}