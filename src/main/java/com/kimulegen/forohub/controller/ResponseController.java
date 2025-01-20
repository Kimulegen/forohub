package com.kimulegen.forohub.controller;

import com.kimulegen.forohub.domain.response.*;
import com.kimulegen.forohub.domain.topic.TopicRepository;
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
@RequestMapping("/response")
@SecurityRequirement(name="bearer-key")
public class ResponseController
{
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private ResponseRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registeredResponse (@RequestBody @Valid ResponseDTO responseDTO) throws IntegrityValidation {
        var registeredResponse = responseService.createdResponseDTO(responseDTO);
        return ResponseEntity.ok(registeredResponse);
    }

    @GetMapping("/responses")
    public ResponseEntity<Page<ListResponsesDTO>>  listResponses(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(repository.findByActiveTrue(paged).map(ListResponsesDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updatedResponse(@RequestBody @Valid UpdatedResponseDTO updatedResponseDTO){
        Response response=repository.getReferenceById(updatedResponseDTO.id());
        response.updatedResponse(updatedResponseDTO);
        return ResponseEntity.ok(new UpdatedResponseDTO(response.getId(),response.getSolution(),
                response.getAuthor().getId(),
                response.getTopic().getId(),
                response.getCreationDate()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteResponse(@PathVariable Long id){
        Response response = repository.getReferenceById(id);
        response.deactivateResponse();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity <CreatedResponseDTO> createResponse(@PathVariable Long id){
        Response response=repository.getReferenceById(id);
        var registeredResponse = new CreatedResponseDTO(response.getId(),
                response.getSolution(),
                response.getAuthor().getId(),
                response.getTopic().getId(),
                response.getCreationDate());
        return ResponseEntity.ok(registeredResponse);
    }
}