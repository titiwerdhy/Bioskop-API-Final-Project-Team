package com.teama.bioskop.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Films {
    @Id
    private Integer filmCode;

    private Integer seatId;

    @Column(length = 100)
    private String filmName;

    private Boolean isPlaying;
}
