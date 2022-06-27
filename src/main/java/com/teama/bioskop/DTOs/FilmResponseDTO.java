package com.teama.bioskop.DTOs;

import com.teama.bioskop.Models.Seats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FilmResponseDTO {

    private Integer filmCode;

    private Seats seatId;

    private String filmName;

    private Boolean isPlaying;
}
