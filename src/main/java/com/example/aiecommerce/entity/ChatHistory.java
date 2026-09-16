package com.example.aiecommerce.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="chat_history")
public class ChatHistory {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="user_id",nullable=false) private User user;
    @Column(nullable=false,columnDefinition="TEXT") private String question;
    @Column(nullable=false,columnDefinition="TEXT") private String answer;
    @Column(name="created_at",nullable=false) private LocalDateTime createdAt;
    @PrePersist void prePersist(){if(createdAt==null)createdAt=LocalDateTime.now();}
    public ChatHistory(){}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public User getUser(){return user;} public void setUser(User v){user=v;}
    public String getQuestion(){return question;} public void setQuestion(String v){question=v;}
    public String getAnswer(){return answer;} public void setAnswer(String v){answer=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
