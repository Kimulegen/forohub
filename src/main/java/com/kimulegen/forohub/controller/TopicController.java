package com.kimulegen.forohub.controller;

import com.kimulegen.forohub.domain.topic.*;
import com.kimulegen.forohub.domain.user.UserRepository;
import com.kimulegen.forohub.infra.errors.IntegrityValidation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/topic")
@SecurityRequirement(name="bearer-key")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicService topicService;


    @PostMapping("/topic")
    @Transactional
    public ResponseEntity registeredTopic(@RequestBody @Valid TopicDTO topicDTO) throws IntegrityValidation {
        var registeredTopic = topicService.TopicCreated(topicDTO);
        return ResponseEntity.ok(registeredTopic);
    }

    @GetMapping("/topics")
    public ResponseEntity<Page<ListTopicsDTO>>  listTopics(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(topicRepository.findByActiveTrue(paged).map(ListTopicsDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updatedTopic (@RequestBody @Valid UpdatedTopicDTO updatedTopicDTO){
        Topic topic= topicRepository.getReferenceById(updatedTopicDTO.id());
        topic.setUpdatedTopicDTO(updatedTopicDTO);
        return ResponseEntity.ok(new ResponseTopicDTO(topic.getId(),
                topic.getTitle(),topic.getMessage(),
                topic.getStatus(),topic.getAuthor().getId(),
                topic.getCourse(),topic.getDate()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id){
        Topic topic= topicRepository.getReferenceById(id);
        topic.deactivateTopic();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity <ResponseTopicDTO> responseTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        var topicId = new ResponseTopicDTO(topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getAuthor().getId(),
                topic.getCourse(),
                topic.getDate());
        return ResponseEntity.ok(topicId);
    }
}