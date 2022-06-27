package com.teama.bioskop.Models;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.teama.bioskop.DTOs.FilmResponseDTO;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Films {
    @Id
    private Integer filmCode;

    @OneToOne
    @JoinColumn(name="seat_id")
    private Seats seatId;

    @Column(length = 100)
    private String filmName;

    private Boolean isPlaying;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public FilmResponseDTO convertToResponse(){
        return FilmResponseDTO.builder()
            .filmCode(this.filmCode)
            .seatId(this.seatId)
            .filmName(this.filmName)
            .isPlaying(this.isPlaying)
            .build();
    }

    @Override
    public String toString() {
        return "Films{" +
                "filmCode=" + filmCode +
                ", seatId=" + seatId.toString() +
                ", filmName='" + filmName + '\'' +
                ", isPlaying=" + isPlaying +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}