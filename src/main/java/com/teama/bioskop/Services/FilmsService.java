package com.teama.bioskop.Services;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Repositories.FilmsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class FilmsService {
    private final FilmsRepository filmsRepository;

    public List<Films> getAllFilms(){
        return this.filmsRepository.findAll();
    }

    public Films getOneFilms(Integer id) throws DataNotFoundException{
        Optional<Films> optionalFilms = this.filmsRepository.findById(id);

        if(optionalFilms.isEmpty()){
            throw new DataNotFoundException("Film is Not Available");
        }

        return optionalFilms.get();

    }

    public Films createFilm (Films films){
        return this.filmsRepository.save(films);
    }

    public Films updateFilm(Films films) throws DataNotFoundException{
        this.getOneFilms(films.getFilmCode());

        return this.filmsRepository.save(films);
    }

    public void deleteFilm(Films films) throws DataNotFoundException{
        Optional<Films> deletedFilms = this.filmsRepository.findById(films.getFilmCode());

        if(deletedFilms.isEmpty()){
            throw new DataNotFoundException("Film is Not Found");
        }

        this.filmsRepository.delete(deletedFilms.get());
    }

    public List<Films> getByIsPlaying(Boolean isPlaying){
        return this.filmsRepository.getFilmByIsPlaying(isPlaying);
    }

    public List<Films> getByStudioName(String studioName) throws DataNotFoundException {

        List<Films> filmsList = this.filmsRepository.getFilmByStudioName(studioName);

        if(filmsList.isEmpty()){
            throw new DataNotFoundException("There is no film playing in "+ studioName);
        }

        return filmsList;
    }
}