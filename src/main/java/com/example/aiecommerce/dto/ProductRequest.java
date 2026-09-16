package com.example.aiecommerce.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ProductRequest(@NotBlank String name,String description,@NotNull @PositiveOrZero BigDecimal price,@NotBlank String category,@NotNull @PositiveOrZero Integer stock,String image) {}
