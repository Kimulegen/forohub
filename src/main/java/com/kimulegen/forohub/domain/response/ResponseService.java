package com.kimulegen.forohub.domain.response;

import com.kimulegen.forohub.domain.topic.TopicRepository;
import com.kimulegen.forohub.domain.user.UserRepository;
import com.kimulegen.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResponseRepository repository;

    public CreatedResponseDTO createdResponseDTO(ResponseDTO responseDTO) {
        if (userRepository.findById(responseDTO.user_id()).isEmpty()){
            throw new IntegrityValidation("Este ID de usuario no está registrado en la base de datos. ");
        }
        if (topicRepository.findById(responseDTO.topic_id()).isEmpty()){
            throw new IntegrityValidation("Este id de tópico no está registrado en la base de datos. ");
        }
        var user = userRepository.findById(responseDTO.user_id()).get();
        var topic =topicRepository.findById(responseDTO.topic_id()).get();

        var rVerified= new Response(null,responseDTO.solution(),user,topic,responseDTO.creationDate());
        repository.save(rVerified);
        return new CreatedResponseDTO(rVerified);
    }

}