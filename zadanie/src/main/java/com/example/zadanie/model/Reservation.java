package com.example.zadanie.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record Reservation(
        @Id
        Integer reservationId,
        @FutureOrPresent
        LocalDate reservationStart,
        @FutureOrPresent
        LocalDate reservationEnd,
        @NotNull
        Integer ownerId,
        @NotNull
        Integer lesseeId,
        @NotNull
        Integer reservedFacilityId
) {
}
