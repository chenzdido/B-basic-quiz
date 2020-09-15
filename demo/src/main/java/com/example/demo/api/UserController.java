package com.example.demo.api;


import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

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
}
