package com.example.aiecommerce.dto;
import jakarta.validation.constraints.NotBlank;
public record ChatRequest(@NotBlank String question) {}
