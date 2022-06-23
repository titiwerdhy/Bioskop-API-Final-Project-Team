package com.teama.bioskop.Controllers;

import com.teama.bioskop.DTO.FilmRequestDTO;
import com.teama.bioskop.DTO.FilmResponseDTO;
import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Services.FilmsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FilmsController {
    private final FilmsService filmsService;

    @GetMapping("/films")
    public List<Films> getAll() {
        return this.filmsService.getAllFilms();

    }

    @GetMapping("/films/{id}")
    public ResponseEntity<FilmResponseDTO> getFilmById(@PathVariable Integer id) throws DataNotFoundException{
        Films films = this.filmsService.getOneFilms(id);

        FilmResponseDTO responseDTO = films.convertToResponse();

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/films")
    public ResponseEntity<FilmResponseDTO> createFilm(@RequestBody FilmRequestDTO filmRequestDTO){
        Films newFilm = filmRequestDTO.convertToEntity();

        Films films = this.filmsService.createFilm(newFilm);
        FilmResponseDTO responseDTO = films.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<FilmResponseDTO> updateFilms(@PathVariable Integer id, @RequestBody FilmRequestDTO filmRequestDTO) throws DataNotFoundException {
        Films films = filmRequestDTO.convertToEntity();

        films.setFilmCode(id);

        Films updateFilms = this.filmsService.updateFilm(films);

        return ResponseEntity.ok(updateFilms.convertToResponse());
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable Integer id) throws DataNotFoundException{
        Films films = new Films();
        films.setFilmCode(id);

        this.filmsService.deleteFilm(films);
    }

    @PostMapping("/films/isplaying")
    public List<Films> findFilmsByIsPlaying(@RequestBody Films films){
        return this.filmsService.getByIsPlaying(films.getIsPlaying());
    }

    @PostMapping("/films/studioname")
    public List<Films> findFilmsByStudioName(@RequestBody Films films) throws DataNotFoundException {
        return this.filmsService.getByStudioName(films.getSeatId().getStudioName());
    }
}