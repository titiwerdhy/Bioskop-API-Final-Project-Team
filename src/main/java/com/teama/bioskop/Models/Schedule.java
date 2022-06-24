package com.teama.bioskop.Models;

import com.teama.bioskop.DTO.ScheduleResponseDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
    public Films films;
    private LocalDate tanggalTayang;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;
    private Double hargaTiket;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public ScheduleResponseDTO convertToResponse(){
        return ScheduleResponseDTO.builder().
                filmsCode(this.films.getFilmCode()).tanggalTayang(this.tanggalTayang).jamMulai(this.jamMulai).
                jamSelesai(this.jamSelesai).hargaTiket(this.hargaTiket).createdAt(this.createdAt).build();
    }

}