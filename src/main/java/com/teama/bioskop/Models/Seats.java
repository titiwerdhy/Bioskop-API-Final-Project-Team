package com.teama.bioskop.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.teama.bioskop.DTOs.SeatsResponseDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Seats  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;

    private String studioName;

    private String barisKursi;

    private Boolean isAvailable;

    private Integer nomorKursi;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public SeatsResponseDTO convertToResponse(){
        return SeatsResponseDTO.builder()
            .seatId(this.seatId)
            .studioName(this.studioName)
            .barisKursi(this.barisKursi)
            .isAvailable(this.isAvailable)
            .nomorKursi(this.nomorKursi)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }

    @Override
    public String toString() {
        return "Seats{" +
                "seatId=" + seatId +
                ", studioName='" + studioName + '\'' +
                ", barisKursi='" + barisKursi + '\'' +
                ", isAvailable=" + isAvailable +
                ", nomorKursi=" + nomorKursi +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}