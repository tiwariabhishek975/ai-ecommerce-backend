package com.example.aiecommerce.controller;
import com.example.aiecommerce.dto.*;
import com.example.aiecommerce.service.AiService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
@RestController @RequestMapping("/api/ai")
public class AiChatController {
    private final AiService service; public AiChatController(AiService service){this.service=service;}
    @PostMapping("/chat") public ChatResponse chat(Principal p,@Valid @RequestBody ChatRequest r){return new ChatResponse(service.chat(p.getName(),r.question()));}
}
