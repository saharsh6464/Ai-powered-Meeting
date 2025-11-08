package com.saharsh.AiMeet.services;
import com.saharsh.AiMeet.models.Subtopic;
import com.saharsh.AiMeet.models.Topic;
import com.saharsh.AiMeet.repos.SubtopicRepository;
import com.saharsh.AiMeet.repos.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubtopicService {
    @Autowired
    private SubtopicRepository repo;

    @Autowired
    private TopicRepository TopicRepo;

    public List<Subtopic> findByParentTopic_Id(Long topicId){
        return repo.findByParentTopic_Id(topicId);
    }

    public List<Subtopic> findByNameContainingIgnoreCase(String name){
        return repo.findByNameContainingIgnoreCase(name);
    }

    public ResponseEntity<?> saveTopic(String name,Long parentId){
        Optional<Topic> topic = TopicRepo.findById(parentId);
        if(topic.isPresent()){
            Subtopic obj = new Subtopic();
            obj.setName(name);
            obj.setParentTopic(topic.get());
            return ResponseEntity.ok(repo.save(obj));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Not Found Topic for TopicId");
        }
    }


}
