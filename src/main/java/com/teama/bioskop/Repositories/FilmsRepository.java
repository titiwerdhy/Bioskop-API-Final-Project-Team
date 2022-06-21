package com.teama.bioskop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Films extends JpaRepository<Films,Integer>{

}