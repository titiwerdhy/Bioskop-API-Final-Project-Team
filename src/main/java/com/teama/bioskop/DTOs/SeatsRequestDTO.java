package com.teama.bioskop.DTOs;

import java.time.LocalDateTime;

import com.teama.bioskop.Models.Seats;

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
public class SeatsRequestDTO {
    private Integer seatId;

    private String studioName;

    private String barisKursi;

    private Boolean isAvailable;

    private Integer nomorKursi;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Seats converToSeat() {
        return Seats.builder()
                .seatId(this.seatId)
                .studioName(this.studioName)
                .barisKursi(this.barisKursi)
                .isAvailable(this.isAvailable)
                .nomorKursi(this.nomorKursi)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

}