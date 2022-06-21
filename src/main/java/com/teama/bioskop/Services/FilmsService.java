package com.teama.bioskop.Services;

import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Repositories.FilmsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class FilmsService {
    private final FilmsRepository filmsRepository;

    public List<Films> getAllFilms(){
        return this.filmsRepository.findAll();
    }

    public Films createFilm (Films films){
        return this.filmsRepository.save(films);
    }
}
