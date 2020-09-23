package com.example.demo.api;

import com.example.demo.domain.Education;
import com.example.demo.domain.User;
import com.example.demo.exception.IDNotFoundException;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<User> userJson;
    @Autowired
    private JacksonTester<Education> educationJson;

    private User firstUser;
    private Education firstEducation;

    @BeforeEach
    public void beforeEach() {
        firstUser = User.builder()
                .id(1L)
                .name("KAMIL")
                .age(24L)
                .avatar("http://...")
                .description("Lorem ipsum dolor sit amet")
                .build();
        firstEducation = Education.builder()
                .description("Eos, explicabo, nam")
                .year(2010L)
                .title( "Secondary school specializing in artistic")
                .userId(1L)
                .build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(userService);
    }

    @Nested
    class GetUserById {
        @Nested
        class WhenUserExists{
            @Test
            public void should_return_user_by_id_with_jsonPath() throws Exception {
                when(userService.getUserById(1L)).thenReturn(firstUser);
                mockMvc.perform(get("/users/{id}", 1L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.name", is("KAMIL")))
                        .andExpect(jsonPath("$.age", is(24)))
                        .andExpect(jsonPath("$.avatar", is("http://...")))
                        .andExpect(jsonPath("$.description", is("Lorem ipsum dolor sit amet")));
                verify(userService).getUserById(1L);
            }
        }
        @Nested
        class WhenUserIdNotExisted {

            @Test
            public void should_return_NOT_FOUND() throws Exception {
                when(userService.getUserById(1L)).thenThrow(new IDNotFoundException("user id is not exist"));
                mockMvc.perform(get("/users/{id}", 1L))
                        .andExpect(status().isNotFound())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.message", containsString("user id is not exist")));

                verify(userService).getUserById(1L);
            }
        }
    }

    @Nested
    class CreateUser {

        private User newUserRequest;

        @BeforeEach
        public void beforeEach() {
            newUserRequest = User.builder()
                    .name("Panda")
                    .age(24L)
                    .avatar("http://...")
                    .description("A good guy.")
                    .build();
        }

        @Nested
        class WhenRequestIsValid {

            @Test
            public void should_create_new_user_and_return_its_id() throws Exception {
                when(userService.createUser(newUserRequest)).thenReturn(2L);
                MockHttpServletRequestBuilder requestBuilder = post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson.write(newUserRequest).getJson());
                MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                        .andReturn()
                        .getResponse();
                assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
                verify(userService).createUser(newUserRequest);
            }
        }
    }

    @Nested
    class CreateEduction {
        private Education newEducation;

        @BeforeEach
        public void beforeEach() {
            newEducation = Education.builder()
                    .description("Eos, explicabo, nam")
                    .year(2010L)
                    .title( "Secondary school specializing in artistic")
                    .userId(2L)
                    .build();
        }

        @Nested
        class WhenRequestIsValid {

            @Test
            public void should_create_new_education() throws Exception {
                MockHttpServletRequestBuilder requestBuilder = post("/users/{id}/educations",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(educationJson.write(newEducation).getJson());
                MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                        .andReturn()
                        .getResponse();
                assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
                verify(userService).createEducation(1L, newEducation);
            }
        }

    }

    @Nested
    class GetEducations{
        @Nested
        class WhenEducationExists{
            private List<Education> educationList = new ArrayList<>();
            @BeforeEach
            public void beforeEach() {
                educationList.add(firstEducation);
            }
            @Test
            public void should_return_educations_by_id_with_jsonPath() throws Exception {
                when(userService.getEducationsById(1L)).thenReturn(educationList);
                mockMvc.perform(get("/users/{id}/educations", 1L))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$", hasSize(1)));
                verify(userService).getEducationsById(1L);
            }
        }
    }

}