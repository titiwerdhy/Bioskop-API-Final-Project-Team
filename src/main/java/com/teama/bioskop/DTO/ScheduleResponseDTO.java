package com.teama.bioskop.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teama.bioskop.Models.Films;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ScheduleResponseDTO {
    private Integer filmsCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalTayang;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime jamMulai;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime jamSelesai;


    private  Double hargaTiket;

    private LocalDateTime createdAt;
}