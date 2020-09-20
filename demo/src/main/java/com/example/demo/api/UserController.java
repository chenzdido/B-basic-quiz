package com.example.demo.api;

import com.example.demo.domain.Education;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Long creteUser(@RequestBody @Valid User user){
        return userService.createUser(user);
    }

    @PostMapping("/users/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEducation(@PathVariable Long id, @RequestBody @Valid Education education){
        userService.createEducation(id, education);
    }

    @GetMapping("/users/{id}/educations")
    public List<Education> getEducation(@PathVariable Long id){
        return userService.getEducationsById(id);
    }
}
