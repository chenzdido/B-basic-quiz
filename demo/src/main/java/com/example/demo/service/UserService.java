package com.example.demo.service;

import com.example.demo.domain.Education;
import com.example.demo.domain.Message;
import com.example.demo.domain.User;
import com.example.demo.exception.IDNotFoundException;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    UserRepository userRepository = new UserRepository();
    EducationRepository educationRepository = new EducationRepository();
    public User getUserById(Integer id){
        if(userRepository.getUserMap().get(id)==null)
        {
            throw new IDNotFoundException("user id is not exist");
        }
        User user = userRepository.getUserMap().get(id);
        return user;
    }

    public List<Education> getUserEducationByID(Integer id){
       List<Education> educations=educationRepository.getEducationMap().get(id);
       return educations;
    }

    public Message createUser(User user){
        User newUser = new User(user.getName(),user.getAge(),user.getAvatar(),user.getDescription());
        userRepository.getUserMap().put(newUser.getId(),newUser);
        Message createUserMessage = new Message();
        createUserMessage.setCode(201);
        createUserMessage.setId(newUser.getId());
        return createUserMessage;
    }

    public Integer createEducation(Integer id,Education education){
        if(educationRepository.getEducationMap().get(id)==null){
           // education.setId(id);
            List<Education> educations = new ArrayList<>();
            educations.add(education);
            educationRepository.getEducationMap().put(id,educations);
        }else{
            List<Education> educations = educationRepository.getEducationMap().get(id);
           // education.setId(id);
            educations.add(education);
            educationRepository.getEducationMap().put(id,educations);
        }
        Message createEducationMessage = new Message();
        createEducationMessage.setCode(201);
        return createEducationMessage.getCode();
    }
}

