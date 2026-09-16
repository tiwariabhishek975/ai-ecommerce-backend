package com.example.aiecommerce.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
public record CreateOrderRequest(@NotEmpty List<@Valid OrderItemRequest> items) {}
