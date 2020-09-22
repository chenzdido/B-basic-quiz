package com.example.demo.service;

import com.example.demo.domain.Education;
import com.example.demo.domain.User;
import com.example.demo.dto.EducationDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.IDNotFoundException;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EducationRepository educationRepository;
    public UserService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }

    public User getUserById(Long id){
        Optional<UserDto> userDto = userRepository.findOneById(id);
        if(userDto.equals(Optional.empty())){
            throw new IDNotFoundException("user id is not exist");
        }
        return User.builder()
                .id(userDto.get().getId())
                .name(userDto.get().getName())
                .age(userDto.get().getAge())
                .avatar(userDto.get().getAvatar())
                .description(userDto.get().getDescription())
                .build();
    }

    public Long createUser(User user){
        UserDto userDto=UserDto.builder().name(user.getName())
                .age(user.getAge())
                .avatar(user.getAvatar())
                .description(user.getDescription())
                .build();
        userRepository.save(userDto);
        return userDto.getId();
    }

    public void createEducation (Long id, Education education){
        Optional<UserDto> userDto = userRepository.findOneById(id);
        if(userDto.equals(Optional.empty())){
            throw new IDNotFoundException("user id is not exist");
        }
        EducationDto educationDto = EducationDto.builder()
                .description(education.getDescription())
                .title(education.getTitle())
                .year(education.getYear())
                .user(userDto.get())
                .build();
        educationRepository.save(educationDto);
    }

    public List<Education> getEducationsById(Long id){
        List<EducationDto> educationDtoList = educationRepository.findAllByUserId(id);
        List<Education> educationsList = new ArrayList<>();
        for(int i = 0; i < educationDtoList.size(); i++){
            EducationDto educationDto = educationDtoList.get(i);
            educationsList.add(Education.builder()
                                .title(educationDto.getTitle())
                                .description(educationDto.getDescription())
                                .year(educationDto.getYear())
                                .userId(educationDto.getUser().getId())
                                .build()
                                );
        }
        return educationsList;
    }
}
