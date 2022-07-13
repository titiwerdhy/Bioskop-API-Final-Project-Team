package com.teama.bioskop.Services;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Repositories.FilmsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class FilmsService {
    private final FilmsRepository filmsRepository;

    public List<Films> getAllFilms(){
        return this.filmsRepository.findAll();
    }

    public Page<Films> getAllFilmsPaged(String filmName, int pageNo, int pageSize, String sort, String order){
        Pageable pageable;
        if (sort == null) {
            if (filmName == null) {
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("updatedAt").descending());
            }else{
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("updated_at").descending());
            }
        }else{
            if (order.equals("ascending")) {
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sort).ascending());
            }else{
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sort).descending());
            }
        }
        if (filmName == null) {
            return this.filmsRepository.findAll(pageable);
        }else{
            return this.filmsRepository.searchFilmsByFilmNamePaged(filmName, pageable);
        }
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

    public void deleteFilmById(Integer id) throws DataNotFoundException{
        this.getOneFilms(id);
        this.filmsRepository.deleteById(id);
    }

    public Films getFilmById(Integer id) throws DataNotFoundException{
        return this.filmsRepository.findById(id).get();
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

    public Page<Films> getAllFilmsPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.filmsRepository.findAll(pageable);
    }
}