package com.example.zadanie.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record Facility(
        @Id
        Integer facilityId,
        @NotBlank
        String facilityName,
        @NotNull
        Double facilityPrice,
        @NotNull
        Double facilityArea,
        String facilityDescription
) {

}
