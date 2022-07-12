package com.teama.bioskop.Controllers;


import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Models.Seats;
import com.teama.bioskop.Services.FilmsService;
import com.teama.bioskop.Services.SeatsService;
import com.teama.bioskop.Handlers.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.List;


@Controller
@AllArgsConstructor

public class FilmsController {

    @Autowired
    private final FilmsService filmsService;
    private final SeatsService seatsService;
    private static final Logger logger = LogManager.getLogger(FilmsController.class);

    /**
     * Get all film from Films Table
     * @return List of films
     */
    @GetMapping("/crud/films/{pageNo}")
    public String getAll(Model model, @PathVariable("pageNo") int pageNo) throws DataNotFoundException{
        try{
            int pageSize = 10;
            Page<Films> films = this.filmsService.getAllFilmsPaged(pageNo, pageSize);
            // Collections.reverse(films);
            List<Seats> seats = this.seatsService.getAllSeats();
            List<Films> filmPage = films.getContent();
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", films.getTotalPages());
            model.addAttribute("totalItems", films.getTotalElements());
            model.addAttribute("seats", seats);
            model.addAttribute("films", filmPage);
            model.addAttribute("newFilm", new Films());
            model.addAttribute("updateFilm", new Films());
            logger.info("--------------------------");
            logger.info("Get All Films Data "+films);
            logger.info("--------------------------");
            ResponseHandler.generateResponse("Successfully retrieve all Films!", HttpStatus.OK, films);
            return "films-crud";
        }catch(Throwable e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("errorStatus", HttpStatus.INTERNAL_SERVER_ERROR);
            ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
            return "error-page";
        }
    }

    /**
     * Create new film
     * @param model
     * @param films
     * @return
     */
    @PostMapping("/create/film")
    public String createNew(Model model, @ModelAttribute Films films) {
        try{
            this.filmsService.createFilm(films);
           
            logger.info("--------------------------");
            logger.info("Create films "+films);
            logger.info("--------------------------");
            ResponseHandler.generateResponse("Successfully create Film!", HttpStatus.OK, films);
            return "redirect:/crud/films";
        }catch (Throwable err) {
            model.addAttribute("error", err.getMessage());
            model.addAttribute("errorStatus", HttpStatus.BAD_REQUEST);
            ResponseHandler.generateResponse(err.getMessage(), HttpStatus.BAD_REQUEST, null);
            return "error-page";
        }
    }

    /**
     * Update film
     * @param model
     * @param films
     * @return
     */
    @PutMapping("/update/film/{id}")
    public String updateFilm(Model model, @PathVariable Integer id,@ModelAttribute Films films) throws DataNotFoundException {
        try{
            films.setFilmCode(id);
            this.filmsService.updateFilm(films);
            logger.info("--------------------------");
            logger.info("Update films "+films);
            logger.info("--------------------------");
            ResponseHandler.generateResponse("Successfully update Film!", HttpStatus.OK, films);
            return "redirect:/crud/films";
        }catch (Throwable err){
            model.addAttribute("error", err.getMessage());
            model.addAttribute("errorStatus", HttpStatus.BAD_REQUEST);
            ResponseHandler.generateResponse(err.getMessage(), HttpStatus.BAD_REQUEST, null);
            return "error-page";
        }
    }

    /**
     * Delete film
     * @param model
     * @param id
     * @return
     */

    @DeleteMapping("/delete/film/{id}")
    public String deleteFilm(Model model, @PathVariable("id") Integer id) throws DataNotFoundException {
        try{
            this.filmsService.deleteFilmById(id);
            logger.info("--------------------------");
            ResponseHandler.generateResponse("Successfully delete Film!", HttpStatus.OK, null);
            return "redirect:/crud/films";
        }catch (Throwable err){
            model.addAttribute("error", err.getMessage());
            model.addAttribute("errorStatus", HttpStatus.INTERNAL_SERVER_ERROR);
            ResponseHandler.generateResponse(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
            return "error-page";
        }
    }

    /**
     * Get film by id
     * @param id
     * @return
     */
   @GetMapping("/film/{id}")
    public String getFilmById(Model model, @ModelAttribute Films films,Integer id) throws DataNotFoundException {
        try{
            this.filmsService.getFilmById(id);
            logger.info("--------------------------");
                logger.info("Get film by " + id + " " + films);
                logger.info("--------------------------");
                ResponseHandler.generateResponse("Successfully show data film by id", HttpStatus.OK, films);
            return "redirect:/crud/films";
        }catch (Throwable err){
            model.addAttribute("error", err.getMessage());
            model.addAttribute("errorStatus", HttpStatus.INTERNAL_SERVER_ERROR);
            ResponseHandler.generateResponse(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
            return "error-page";
        }
    }

}
