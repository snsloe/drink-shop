package com.example.drink_shop.model.dto;

import java.time.LocalDateTime;

public record ErrorDTO(
        String errorMessage,
        String descriptionError,
        LocalDateTime errorDateTime
) {}