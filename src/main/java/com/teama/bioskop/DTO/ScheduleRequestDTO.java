package com.teama.bioskop.DTO;

import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Models.Schedule;
import lombok.*;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleRequestDTO {
    private Films films;
    private Date tanggalTayang;
    private Time jamMulai;
    private Time jamSelesai;
    private  Double hargaTiket;
    public Schedule convertToEntity(){
        return Schedule.builder().films(this.films).tanggalTayang(this.tanggalTayang).
        jamMulai(this.jamMulai).jamSelesai(this.jamSelesai).hargaTiket(this.hargaTiket).build();
    }
}