package com.teama.bioskop.Repositories;

import com.teama.bioskop.Models.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmsRepository extends JpaRepository<Films,Integer>{

}
