package com.kimulegen.forohub.domain.response;

import com.kimulegen.forohub.domain.topic.Topic;
import com.kimulegen.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name="Response")
@Table(name = "responses")
@Getter
@Setter
@NoArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationDate;
    private String solution;
    @OneToOne
    @JoinColumn(name="author", referencedColumnName="id")
    private User author;
    @OneToOne
    @JoinColumn(name="topico", referencedColumnName="id")
    private Topic topico;
    private boolean active;

    public Response(Long id, String solution, User user, Topic topic, LocalDateTime creationDate) {
        this.id=id;
        this.solution=solution;
        this.author=user;
        this.topico=topic;
        this.creationDate=LocalDateTime.now();
    }

    public void updatedResponse(UpdatedResponseDTO updatedResponseDTO) {
        if (updatedResponseDTO.solution() != null){
            this.solution=updatedResponseDTO.solution();
        }
    }
    public void deactivateResponse(){

        this.active=false;
    }
}