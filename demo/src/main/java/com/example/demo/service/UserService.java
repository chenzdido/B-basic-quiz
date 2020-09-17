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
    // GTB: - 注入 UserRepository 和 EducationRepository！
    UserRepository userRepository = new UserRepository();
    EducationRepository educationRepository = new EducationRepository();

    public User getUserById(Integer id){
        // GTB: - if 的格式各种不统一
        if(userRepository.getUserMap().get(id)==null)
        {
            throw new IDNotFoundException("user id is not exist");
        }
        // GTB: - 直接 return userRepository.getUserMap().get(id); 就行
        User user = userRepository.getUserMap().get(id);
        return user;
    }

    public List<Education> getUserEducationByID(Integer id){
        // GTB: - 同理，直接 return 就行
       List<Education> educations=educationRepository.getEducationMap().get(id);
       return educations;
    }

    public Message createUser(User user){
        User newUser = new User(user.getName(),user.getAge(),user.getAvatar(),user.getDescription());
        // GTB: - 这么写，Repository 就没有什么封装性了！
        userRepository.getUserMap().put(newUser.getId(),newUser);
        // GTB: - 这里不需要返回 201 吧
        Message createUserMessage = new Message();
        createUserMessage.setCode(201);
        createUserMessage.setId(newUser.getId());
        return createUserMessage;
    }

    // GTB: - 这个方法里的代码写的不好，跟小组同学好好交流一下，看看可以如何改进！
    public Integer createEducation(Integer id,Education education){
        if(educationRepository.getEducationMap().get(id)==null){
            List<Education> educations = new ArrayList<>();
            educations.add(education);
            educationRepository.getEducationMap().put(id,educations);
        }else{
            List<Education> educations = educationRepository.getEducationMap().get(id);
            educations.add(education);
            educationRepository.getEducationMap().put(id,educations);
        }
        Message createEducationMessage = new Message();
        createEducationMessage.setCode(201);
        return createEducationMessage.getCode();
    }
}

