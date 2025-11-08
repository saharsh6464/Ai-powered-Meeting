package com.saharsh.AiMeet.services;
import com.saharsh.AiMeet.Dto.TopicDto;
import com.saharsh.AiMeet.models.Topic;
import com.saharsh.AiMeet.models.User;
import com.saharsh.AiMeet.repos.TopicRepository;
import com.saharsh.AiMeet.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopicService {
    @Autowired
    private TopicRepository repo;

    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<?> getSubTopics(String topicAccessId, String accessPassword){
        Optional<Topic> obj =  repo.findByTopicAccessId( topicAccessId);
        if(obj.isPresent()){
            Topic cur = obj.get();
            if(cur.getAccessPassword().equals(accessPassword)){
                return  ResponseEntity.ok(cur.getSubtopics());
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of("error","Invalid Accesss"));
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Not Found for the toopicAccessId");
        }
    }

    public ResponseEntity<?> saveTopic(TopicDto dto){
        Optional<User> user = userRepo.findById(dto.getUserId());
        if(user.isPresent()){
            Topic obj = new Topic();
            User curUser = user.get();
            obj.setName(dto.getName());
            obj.setTopicAccessId(dto.getTopicAccessId());
            obj.setAccessPassword(dto.getAccessPassword());
            obj.setCreatedBy(curUser);
            return ResponseEntity.ok(repo.save(obj));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No User Found with this ID");
        }
    }

    public ResponseEntity<?> GetTopics(Long id){
        List<Topic> list =  repo.findAllBycreatedBy_Id(id);
        for(Topic x : list) {
            TopicDto dto = new TopicDto();
            dto.setId(x.getId());
            dto.setName(x.getName());
        }
        return ResponseEntity.ok(list);
    }
}
