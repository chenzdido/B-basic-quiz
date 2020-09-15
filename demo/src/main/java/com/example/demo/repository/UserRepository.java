package com.example.demo.repository;

import com.example.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@AllArgsConstructor
public class UserRepository {
    private Map<Integer, User> userMap = new HashMap<Integer, User>();

    public UserRepository(){
        User user = new User(
                "KAMIL",
                1,
                "https://inews.gtimg.com/newsapp_match/0/3581582328/0",
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut fuga aliquam ad asperiores voluptatem dolorum! Quasi."
        );
        userMap.put(user.getId(),user);
    }
}
