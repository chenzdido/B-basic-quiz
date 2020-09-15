package com.example.demo.repository;

import com.example.demo.domain.Education;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
@AllArgsConstructor
public class EducationRepository {
    Map<Integer,List<Education>> educationMap=new HashMap<Integer,List<Education>>();
    public EducationRepository(){
        educationMap.put(1,new ArrayList<Education>(){{
            add(new Education(
                    "I was born in Katowice",
                    1990,
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente, exercitationem, totam, dolores iste dolore est aut modi.",
                    1));
            add(new Education(
                    "Secondary school specializing in artistic",
                    2005,
                    "Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus.",
                    1));
        }});
    }
}
