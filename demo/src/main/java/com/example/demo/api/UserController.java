package com.example.demo.api;


import com.example.demo.domain.Education;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:1234")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getStudentById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/educations")
    public List<Education> getStudentEducationsById(@PathVariable Integer id){
        return userService.getUserEducationByID(id);
    }

    @PostMapping("")
    public Integer createUser(@RequestBody User user){
        return userService.createUser(user);
    }
}
