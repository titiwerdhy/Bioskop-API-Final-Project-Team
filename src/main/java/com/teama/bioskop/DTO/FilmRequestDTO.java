package com.teama.bioskop.DTO;

import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Models.Seats;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmRequestDTO {

    private Integer filmCode;
    private Seats seatId;
    private String filmName;
    private Boolean isPlaying;

    public Films convertToEntity(){
        return Films.builder().filmCode(this.filmCode).seatId(this.seatId).filmName(this.filmName).isPlaying(this.isPlaying).build();
    }

}
