package com.teama.bioskop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teama.bioskop.Models.Films;

public interface FilmsRepository extends JpaRepository<Films,Integer>{

}
