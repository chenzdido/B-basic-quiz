package com.example.demo.service;

import com.example.demo.domain.Education;
import com.example.demo.domain.User;
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
        User user = userRepository.getUserMap().get(id);
        System.out.println(userRepository.getUserMap());
        return user;
    }

    public List<Education> getUserEducationByID(Integer id){
       List<Education> educations=educationRepository.getEducationMap().get(id);
       return educations;
    }

    public Integer createUser(User user){
        User newUser = new User(user.getName(),user.getAge(),user.getImgURL(),user.getDescription());
        userRepository.getUserMap().put(newUser.getId(),newUser);
        return newUser.getId();
    }

    public void createEducation(Integer id,Education education){
        if(educationRepository.getEducationMap().get(id)==null){
            List<Education> educations = new ArrayList<>();
            educations.add(education);
            educationRepository.getEducationMap().put(id,educations);
        }else{
            List<Education> educations = educationRepository.getEducationMap().get(id);
            educations.add(education);
            educationRepository.getEducationMap().put(id,educations);
        }
    }
}

