package com.saharsh.AiMeet.controller;

import com.saharsh.AiMeet.models.Subtopic;
import com.saharsh.AiMeet.services.SubtopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtopics")
@CrossOrigin(origins = "*") // allow requests from frontend
public class SubtopicController {

    @Autowired
    private SubtopicService subtopicService;

    // ✅ Create a new Subtopic under a Topic
    @PostMapping
    public ResponseEntity<?> createSubtopic(
            @RequestParam String name,
            @RequestParam Long topicId
    ) {
        return subtopicService.saveTopic(name, topicId);
    }

    // ✅ Get all Subtopics for a specific Topic
    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<Subtopic>> getSubtopicsByTopic(@PathVariable Long topicId) {
        List<Subtopic> subtopics = subtopicService.findByParentTopic_Id(topicId);
        return ResponseEntity.ok(subtopics);
    }

    // ✅ Search Subtopics by name (case-insensitive)
    @GetMapping("/search")
    public ResponseEntity<List<Subtopic>> searchSubtopicsByName(@RequestParam String name) {
        List<Subtopic> results = subtopicService.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(results);
    }
}

