package com.teama.bioskop.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.teama.bioskop.DTO.ScheduleResponseDTO;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name ="film_code")
    private Films films;
    private LocalDate tanggalTayang;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;
    private Double hargaTiket;


    public ScheduleResponseDTO convertToResponse(){
        return ScheduleResponseDTO.builder().
                films(this.films).tanggalTayang(this.tanggalTayang).jamMulai(this.jamMulai).
                jamSelesai(this.jamSelesai).hargaTiket(this.hargaTiket).build();
    }

}