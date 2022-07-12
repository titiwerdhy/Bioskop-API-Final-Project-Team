package com.teama.bioskop.RestControllers;

import com.teama.bioskop.DTOs.FilmRequestDTO;
import com.teama.bioskop.DTOs.FilmResponseDTO;
import com.teama.bioskop.Handlers.ResponseHandler;
import com.teama.bioskop.Handlers.ResponseHandlers;
import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Services.FilmsService;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class FilmsRestController {
    private final FilmsService filmsService;
    private static final Logger logger = LogManager.getLogger(FilmsRestController.class);

    /**
     * Get all film from Films Table
     * @return List of films
     */
    @GetMapping("/films")
    public ResponseEntity<?> getAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK B");
        try{
            List<Films> films = this.filmsService.getAllFilms();
            List<FilmResponseDTO> filmResponseDTOs = new ArrayList<FilmResponseDTO>();
            for(Films film : films) {
                filmResponseDTOs.add(film.convertToResponse());
            }
            logger.info("--------------------------");
            logger.info("Get All Films Data "+films);
            logger.info("--------------------------");
            ResponseEntity<?> body = ResponseHandlers.generateResponse("Successfully retrieved data!", 
            HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), filmResponseDTOs);
            return ResponseEntity.ok().headers(headers).body(body);
        } catch (Exception e){
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.ok().headers(headers).body(body);
        }
    }

    /**
     * Get A Film by filmCode(id)
     * @param id is a filmCode
     * @return Selected Film Data
     * @throws DataNotFoundException
     */
    @GetMapping("/films/{id}")
    public ResponseEntity<Object> getFilmById(@PathVariable Integer id) throws DataNotFoundException{
        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK B");
        try {
            Films films = this.filmsService.getOneFilms(id);
            FilmResponseDTO responseDTO = films.convertToResponse();

            logger.info("--------------------------");
            logger.info("GET FILMS BY ID "+ films);
            logger.info("--------------------------");
            ResponseEntity<?> body = ResponseHandlers.generateResponse("", HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), responseDTO);
            return ResponseEntity.ok().headers(headers).body(body);
        } catch (DataNotFoundException e){
            logger.error("--------------------------");
            logger.error("GET FILMS BY ID "+ id + " NOT FOUND");
            logger.error("--------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.ok().headers(headers).body(body);
        }
    }

    /**
     * Input new film data into Films Table
     * @param filmRequestDTO new Film Data
     * @return insert New FIlm to Films Database
     */
    @PostMapping("/films")
    public ResponseEntity<?> createFilm(@RequestBody FilmRequestDTO filmRequestDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK B");

        try{
            Films newFilm = filmRequestDTO.convertToEntity();

            Films films = this.filmsService.createFilm(newFilm);
            FilmResponseDTO responseDTO = films.convertToResponse();

            logger.info("--------------------------");
            logger.info("FILM SUCCESSFULLY CREATE "+films);
            logger.info("--------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse("Success create Film", HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), responseDTO);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        } catch (Exception e) {
            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        }
    }

    /**
     * Updated Film Data, Selected By filmCode(id)
     * @param id is filmCode
     * @param filmRequestDTO Film which is going to be updated
     * @return updated new Film
     * @throws DataNotFoundException
     */
    @PutMapping("/films/{id}")
    public ResponseEntity<?> updateFilms(@PathVariable Integer id, @RequestBody FilmRequestDTO filmRequestDTO) throws DataNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK B");
        try{
            Films films = filmRequestDTO.convertToEntity();
            films.setFilmCode(id);
            Films updateFilms = this.filmsService.updateFilm(films);
            FilmResponseDTO responseDTO = updateFilms.convertToResponse();

            logger.info("--------------------------");
            logger.info("FILM SUCCESSFULLY UPDATED"+updateFilms);
            logger.info("--------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse("Success update Film", HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), responseDTO);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        } catch (Exception e){

            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        }
    }

    /**
     * Delete One Film by filmCode(id)
     * @param id is a filmCode
     * @return Deleted one film
     * @throws DataNotFoundException
     */
    @DeleteMapping("/films/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK B");
        try{
            Films films = new Films();
            films.setFilmCode(id);

            this.filmsService.deleteFilm(films);
            logger.info("--------------------------");
            logger.info("SUCCESS DELETE BY ID "+id);
            logger.info("--------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse("", HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), "Data is deleted");
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        } catch(Throwable e){
            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");
            ResponseEntity<?> body = ResponseHandlers.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        }
    }

    /**
     * Find Films Data by its status isPlaying(true/flase)
     * @param films
     * @return Get Films Data By its Status
     */
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

    /**
     * Find Films By Studi Name
     * @return Get All Data By Studio Name
     * @throws DataNotFoundException
     */
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