package com.example.zadanie;
import com.example.zadanie.controller.ReservationController;
import com.example.zadanie.model.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ZadanieApplication.class)
public class ZadanieApplicationTests {
    @MockBean
    private ReservationController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getReservationByLesseeName_Success() throws Exception {
        Reservation reservation = new Reservation(99, LocalDate.parse("2030-01-01"), LocalDate.parse("2030-01-31"),0,0,0);
        List<Reservation> rList = new ArrayList<Reservation>();
        rList.add(reservation);

        when(controller.findByLesseeName("bob")).thenReturn(rList);
        when(controller.findByLesseeName("fred")).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found!"));
        mockMvc.perform(get("/api/reservation/lessee/{name}", "bob")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].reservationStart").value(reservation.reservationStart().toString()))
                .andDo(print());
    }

    @Test
    void getReservationByLesseeName_FailureUserNotFound() throws Exception {
        Reservation reservation = new Reservation(99, LocalDate.parse("2030-01-01"), LocalDate.parse("2030-01-31"),0,0,0);
        List<Reservation> rList = new ArrayList<Reservation>();
        rList.add(reservation);

        when(controller.findByLesseeName("bob")).thenReturn(rList);
        when(controller.findByLesseeName("fred")).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found!"));
        mockMvc.perform(get("/api/reservation/lessee/{name}", "fred")).andExpect(status().isNotFound())
                .andDo(print());
    }
}