package com.example.aiecommerce.dto;
import jakarta.validation.constraints.NotBlank;
public record StatusRequest(@NotBlank String status) {}
