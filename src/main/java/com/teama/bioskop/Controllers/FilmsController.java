package com.teama.bioskop.Controllers;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Services.FilmsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
public class FilmsController {

    @Autowired
    private final FilmsService filmsService;

    /**
     * Get all film from Films Table
     * @return List of films
     */
    @GetMapping("/crud/films")
    public String getAll(Model model) {
        List<Films> films = this.filmsService.getAllFilms();
        model.addAttribute("films", films);
        model.addAttribute("newFilm", new Films());
        model.addAttribute("updateFilm", new Films());
        return "films-crud";
    }

    /**
     * Create new film
     * @param model
     * @param films
     * @return
     */
    @PostMapping("/create/film")
    public String createNew(Model model, @ModelAttribute Films films) {
        this.filmsService.createFilm(films);
        return "redirect:/crud/films";
    }

    /**
     * Update film
     * @param model
     * @param films
     * @return
     */
    @PutMapping("/update/film")
    public String updateFilm(Model model, @ModelAttribute Films films) throws DataNotFoundException {
        this.filmsService.updateFilm(films);
        return "redirect:/crud/films";
    }

    /**
     * Delete film
     * @param model
     * @param id
     * @return
     */

    @PostMapping("/delete/film/{id}")
    public String deleteFilm(Model model, @PathVariable("id") Integer id) throws DataNotFoundException {
        this.filmsService.deleteFilmById(id);
        return "redirect:/crud/films";
    }

    /**
     * Get film by id
     * @param id
     * @return
     */
   @GetMapping("/film/{id}")
    public String getFilmById(Model model, @ModelAttribute Films films,Integer id) throws DataNotFoundException {
        this.filmsService.getFilmById(id);
        return "redirect:/crud/films";
    }

}
