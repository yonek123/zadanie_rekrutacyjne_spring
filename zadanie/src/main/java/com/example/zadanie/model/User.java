package com.example.zadanie.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record User(
        @Id
        Integer userId,
        @NotBlank
        String userName
) {
}
