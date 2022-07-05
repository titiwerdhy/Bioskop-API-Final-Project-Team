package com.teama.bioskop.DTOs;

import java.time.LocalDateTime;

import com.teama.bioskop.Models.Reservations;
import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Models.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationsRequestDTO {
    private Integer reservationId;

    private Users users;

    private Schedule schedule;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Reservations convertToReservations(){
        return Reservations.builder()
                .reservationId(this.reservationId)
                .users(this.users)
                .schedule(this.schedule)
                .isActive(this.isActive)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}