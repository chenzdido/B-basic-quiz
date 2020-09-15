package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository = new UserRepository();
    public User getUserById(Integer id){
        User user = userRepository.getUserMap().get(id);
        return user;
    }
}

