package com.teama.bioskop.Models;

import javax.persistence.*;

import com.teama.bioskop.DTO.FilmResponseDTO;
import lombok.*;

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

    public FilmResponseDTO convertToResponse(){
        return FilmResponseDTO.builder().
                filmCode(this.filmCode).seatId(this.seatId).filmName(this.filmName).isPlaying(this.isPlaying).build();
    }
}
