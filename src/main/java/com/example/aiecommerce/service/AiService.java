package com.example.aiecommerce.service;

import com.example.aiecommerce.entity.ChatHistory;
import com.example.aiecommerce.exception.ResourceNotFoundException;
import com.example.aiecommerce.repository.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AiService {
    private final ChatClient chatClient; private final UserRepository users; private final ChatHistoryRepository history;
    public AiService(ChatClient.Builder builder,UserRepository users,ChatHistoryRepository history){
        this.chatClient=builder.defaultSystem("""
            You are an e-commerce customer support assistant.
            Answer clearly and briefly. Do not invent order, payment, refund, or policy information.
            If the application does not provide enough information, say that you do not have enough information.
            """).build();
        this.users=users;this.history=history;
    }
    @Transactional
    public String chat(String email,String question){
        String answer=chatClient.prompt().user(question).call().content();
        if(answer==null) answer="I could not generate a response right now.";
        ChatHistory h=new ChatHistory();
        h.setUser(users.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found")));
        h.setQuestion(question);h.setAnswer(answer);history.save(h);
        return answer;
    }
}
