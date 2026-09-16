package com.example.aiecommerce.controller;
import com.example.aiecommerce.entity.Payment;
import com.example.aiecommerce.service.PaymentService;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
@RestController @RequestMapping("/api/payments") @Validated
public class PaymentController {
    private final PaymentService service; public PaymentController(PaymentService service){this.service=service;}
    @PostMapping("/orders/{orderId}") public Payment pay(@PathVariable Long orderId,@RequestParam @NotNull @Positive BigDecimal amount){return service.create(orderId,amount);}
    @GetMapping("/orders/{orderId}") public Payment get(@PathVariable Long orderId){return service.findByOrder(orderId);}
}
