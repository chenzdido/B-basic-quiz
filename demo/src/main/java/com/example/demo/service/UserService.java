package com.example.demo.service;

import com.example.demo.domain.Education;
import com.example.demo.domain.User;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository = new UserRepository();
    EducationRepository educationRepository = new EducationRepository();
    public User getUserById(Integer id){
        User user = userRepository.getUserMap().get(id);
        System.out.println(userRepository.getUserMap());
        return user;
    }

    public List<Education> getUserEducationByID(Integer id){
       List<Education> educations=educationRepository.getEducationMap().get(id);
       return educations;
    }
}

