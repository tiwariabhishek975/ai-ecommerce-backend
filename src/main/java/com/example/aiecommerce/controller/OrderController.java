package com.example.aiecommerce.controller;
import com.example.aiecommerce.dto.*;
import com.example.aiecommerce.entity.Order;
import com.example.aiecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
@RestController @RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service; public OrderController(OrderService service){this.service=service;}
    @PostMapping public Order create(Principal principal,@Valid @RequestBody CreateOrderRequest r){return service.create(principal.getName(),r);}
    @GetMapping("/my") public List<Order> mine(Principal p){return service.myOrders(p.getName());}
    @GetMapping("/{id}") public Order one(@PathVariable Long id){return service.find(id);}
    @PutMapping("/{id}/status") public Order status(@PathVariable Long id,@Valid @RequestBody StatusRequest r){return service.updateStatus(id,r.status());}
}
