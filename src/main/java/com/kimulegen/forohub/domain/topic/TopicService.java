package com.kimulegen.forohub.domain.topic;

import com.kimulegen.forohub.domain.user.UserRepository;
import com.kimulegen.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public TopicResponseDTO TopicCreated(TopicDTO topicDTO){
        if(!userRepository.findById(topicDTO.user_id()).isPresent()){
            throw new IntegrityValidation("Usario no registrado");
        }

        var title = topicDTO.title();
        if(title != null && topicRepository.existsByTitleIgnoreCase(title)){
            throw new IntegrityValidation("El titulo ya existe");
        }

        String message = topicDTO.message();
        if(message != null && topicRepository.existsByMessageIgnoreCase(message)){
            throw new IntegrityValidation("El mensaje ya existe");
        }

        var user = userRepository.findById(topicDTO.user_id()).get();
        var topicID = new Topic(null, title, message, topicDTO.date(), topicDTO.status(), user, topicDTO.course());
        topicRepository.save(topicID);
        return new TopicResponseDTO(topicID);
    }
}
