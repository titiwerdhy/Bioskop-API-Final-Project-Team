package com.teama.bioskop.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Models.Schedule;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleRequestDTO {
    private Films films;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate tanggalTayang;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss[.SSS][.SS][.S]")
    private LocalTime jamMulai;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss[.SSS][.SS][.S]")
    private LocalTime jamSelesai;
    private  Double hargaTiket;
    public Schedule convertToEntity(){
        return Schedule.builder().films(this.films).tanggalTayang(this.tanggalTayang).
        jamMulai(this.jamMulai).jamSelesai(this.jamSelesai).hargaTiket(this.hargaTiket).build();
    }
}