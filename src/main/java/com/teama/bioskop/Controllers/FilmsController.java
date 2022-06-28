package com.teama.bioskop.Controllers;

import com.teama.bioskop.DTOs.FilmRequestDTO;
import com.teama.bioskop.DTOs.FilmResponseDTO;
import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Services.FilmsService;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FilmsController {
    private final FilmsService filmsService;
    private static final Logger logger = LogManager.getLogger(FilmsController.class);

    @GetMapping("/films")
    public List<Films> getAll() {
        List<Films> films = filmsService.getAllFilms();
        logger.info("--------------------------");
        logger.info("Get All Films Data "+films);
        logger.info("--------------------------");
        return this.filmsService.getAllFilms();

    }

    @GetMapping("/films/{id}")
    public ResponseEntity<FilmResponseDTO> getFilmById(@PathVariable Integer id) throws DataNotFoundException{
        try {
            Films films = this.filmsService.getOneFilms(id);
            FilmResponseDTO responseDTO = films.convertToResponse();

            logger.info("--------------------------");
            logger.info("GET FILMS BY ID "+ films);
            logger.info("--------------------------");

            return ResponseEntity.ok(responseDTO);

        } catch (DataNotFoundException e){
            logger.info("--------------------------");
            logger.info("GET FILMS BY ID "+ id + " NOT FOUND");
            logger.info("--------------------------");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/films")
    public ResponseEntity<FilmResponseDTO> createFilm(@RequestBody FilmRequestDTO filmRequestDTO){
        try{
            Films newFilm = filmRequestDTO.convertToEntity();

            Films films = this.filmsService.createFilm(newFilm);
            FilmResponseDTO responseDTO = films.convertToResponse();

            logger.info("--------------------------");
            logger.info("FILM SUCCESSFULLY RECORDED");
            logger.info("--------------------------");

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            logger.info("--------------------------");
            logger.info(e.getMessage());
            logger.info("--------------------------");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<FilmResponseDTO> updateFilms(@PathVariable Integer id, @RequestBody FilmRequestDTO filmRequestDTO) throws DataNotFoundException {
        try{
            Films films = filmRequestDTO.convertToEntity();
            films.setFilmCode(id);
            Films updateFilms = this.filmsService.updateFilm(films);

            logger.info("--------------------------");
            logger.info("FILM SUCCESSFULLY UPDATED"+updateFilms);
            logger.info("--------------------------");

            return ResponseEntity.ok(updateFilms.convertToResponse());
        } catch (Exception e){

            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable Integer id) throws DataNotFoundException{
        try{
            Films films = new Films();
            films.setFilmCode(id);

            this.filmsService.deleteFilm(films);

            logger.info("--------------------------");
            logger.info("SUCCESS DELETE BY ID "+id);
            logger.info("--------------------------");
        } catch(DataNotFoundException e){
            logger.info("--------------------------");
            logger.info(e.getMessage());
            logger.info("--------------------------");
        }

    }

    @PostMapping("/films/isplaying")
    public List<Films> findFilmsByIsPlaying(@RequestBody Films films) {
            List<Films> filmsl = this.filmsService.getByIsPlaying(films.getIsPlaying());

            logger.info("--------------------------");
            logger.info("GET DATA BY AVAILABLE FILMS "+filmsl);
            logger.info("--------------------------");

            return this.filmsService.getByIsPlaying(films.getIsPlaying());
    }

    @PostMapping("/films/studioname")
    public List<Films> findFilmsByStudioName(@RequestBody Films films) throws DataNotFoundException {
            List<Films> filmsl = this.filmsService.getByIsPlaying(films.getIsPlaying());

            logger.info("--------------------------");
            logger.info("GET DATA BY STUDIO NAME "+filmsl);
            logger.info("--------------------------");

            return this.filmsService.getByStudioName(films.getSeatId().getStudioName());
    }

}