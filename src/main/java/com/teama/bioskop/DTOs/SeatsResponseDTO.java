package com.teama.bioskop.DTOs;

import java.time.LocalDateTime;

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
public class SeatsResponseDTO {
    private Integer seatId;

    private String studioName;

    private String barisKursi;

    private Boolean isAvailable;

    private Integer nomorKursi;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
