package com.teama.bioskop.Controllers;

import com.teama.bioskop.DTO.FilmRequestDTO;
import com.teama.bioskop.DTO.FilmResponseDTO;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Services.FilmsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FilmsController {
    private final FilmsService filmsService;

    @GetMapping("/films")
    public List<Films> getAll() {
        return this.filmsService.getAllFilms();

    }

    @PostMapping("/films")
    public ResponseEntity<FilmResponseDTO> createFilm(@RequestBody FilmRequestDTO filmRequestDTO){
        Films newFilm = filmRequestDTO.convertToEntity();

        Films films = this.filmsService.createFilm(newFilm);
        FilmResponseDTO responseDTO = films.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}