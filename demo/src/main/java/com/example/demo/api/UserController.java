package com.example.demo.api;

import com.example.demo.domain.Education;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

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
    public void creteUser(@RequestBody User user){
        userService.createUser(user);
    }

    @PostMapping("/users/{id}/educations")
    public void createEducation(@RequestBody Education education){
        userService.createEducation(education);
    }

    @GetMapping("/users/{id}/educations")
    public List<Education> getEducation(@PathVariable Long id){
        return userService.getEducationsById(id);
    }
}
