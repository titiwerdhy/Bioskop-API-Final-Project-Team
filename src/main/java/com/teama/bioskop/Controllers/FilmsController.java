package com.teama.bioskop.Controllers;

import com.teama.bioskop.DTOs.FilmRequestDTO;
import com.teama.bioskop.DTOs.FilmResponseDTO;
import com.teama.bioskop.Handlers.ResponseHandler;
import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Services.FilmsService;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class FilmsController {
    private final FilmsService filmsService;

    private static final Logger logger = LogManager.getLogger(FilmsController.class);

    @GetMapping("/films")
    public ResponseEntity<Object> getAll(){
        try{
            List<Films> films = filmsService.getAllFilms();
            logger.info("--------------------------");
            logger.info("Get All Films Data "+films);
            logger.info("--------------------------");
            return ResponseHandler.generateResponse("Successfully retrieve all Films!", HttpStatus.OK, films);
        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<Object> getFilmById(@PathVariable Integer id) throws DataNotFoundException{
        try {
            Films films = this.filmsService.getOneFilms(id);
            FilmResponseDTO responseDTO = films.convertToResponse();

            logger.info("--------------------------");
            logger.info("GET FILMS BY ID "+ films);
            logger.info("--------------------------");

//            return ResponseEntity.ok(responseDTO);
            return ResponseHandler.generateResponse("Successfully get film by id!",HttpStatus.OK, films);
        } catch (DataNotFoundException e){
            logger.error("--------------------------");
            logger.error("GET FILMS BY ID "+ id + " NOT FOUND");
            logger.error("--------------------------");

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/films")
    public ResponseEntity<Object> createFilm(@RequestBody FilmRequestDTO filmRequestDTO){
        try{
            Films newFilm = filmRequestDTO.convertToEntity();

            Films films = this.filmsService.createFilm(newFilm);
            FilmResponseDTO responseDTO = films.convertToResponse();

            logger.info("--------------------------");
            logger.info("FILM SUCCESSFULLY RECORDED");
            logger.info("--------------------------");

            return ResponseHandler.generateResponse("Films Successfully Recorded", HttpStatus.OK, responseDTO);
        } catch (Exception e) {
            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<Object> updateFilms(@PathVariable Integer id, @RequestBody FilmRequestDTO filmRequestDTO) throws DataNotFoundException {
        try{
            Films films = filmRequestDTO.convertToEntity();
            films.setFilmCode(id);
            Films updateFilms = this.filmsService.updateFilm(films);

            logger.info("--------------------------");
            logger.info("FILM SUCCESSFULLY UPDATED"+updateFilms);
            logger.info("--------------------------");

            return ResponseHandler.generateResponse("Films Updated!",HttpStatus.OK, updateFilms);
        } catch (Exception e){

            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<Object> deleteFilm(@PathVariable Integer id) throws DataNotFoundException{
        try{
            Films films = new Films();
            films.setFilmCode(id);

            this.filmsService.deleteFilm(films);

            logger.info("--------------------------");
            logger.info("SUCCESS DELETE BY ID "+id);
            logger.info("--------------------------");

            return ResponseHandler.generateResponse("Films Deleted!", HttpStatus.OK, films);
        } catch(DataNotFoundException e){
            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/films/isplaying")
    public ResponseEntity<Object> findFilmsByIsPlaying(@RequestBody Films films) {
        try {
            List<Films> filmsl = this.filmsService.getByIsPlaying(films.getIsPlaying());

            logger.info("--------------------------");
            logger.info("GET DATA BY AVAILABLE FILMS " + filmsl);
            logger.info("--------------------------");

            return ResponseHandler.generateResponse("Success Get Data By Playing", HttpStatus.OK, filmsl);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/films/studioname")
    public ResponseEntity<Object> findFilmsByStudioName(@RequestBody Films films) throws DataNotFoundException {
        try{
            List<Films> filmsl = this.filmsService.getByStudioName(films.getSeatId().getStudioName());

            logger.info("--------------------------");
            logger.info("GET DATA BY STUDIO NAME "+filmsl);
            logger.info("--------------------------");

            return ResponseHandler.generateResponse("Success Get All Data By Studio Name", HttpStatus.OK, filmsl);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}