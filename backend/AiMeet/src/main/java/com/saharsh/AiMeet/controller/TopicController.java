package com.saharsh.AiMeet.controller;

import com.saharsh.AiMeet.Dto.TopicDto;
import com.saharsh.AiMeet.models.Topic;
import com.saharsh.AiMeet.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin(origins = "*") // optional - allow frontend access
public class TopicController {

    @Autowired
    private TopicService topicService;

    // ✅ Create a new topic
    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody TopicDto dto) {
        return topicService.saveTopic(dto);
    }

//    // ✅ Get all topics created by a user
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<?> getTopicsByUser(@PathVariable Long userId) {
//        return topicService.getTopics(userId);
//    }

    // ✅ Access subtopics of a topic via access ID and password
    @PostMapping("/access")
    public ResponseEntity<?> getSubtopicsByAccess(
            @RequestParam String topicAccessId,
            @RequestParam String accessPassword
    ) {
        return topicService.getSubTopics(topicAccessId, accessPassword);
    }
}
