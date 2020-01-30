package com.stygar.taxi.repositories;

import com.stygar.taxi.entities.Klient;
import com.stygar.taxi.entities.Paragon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParagonRepository extends JpaRepository<Paragon, Long>{

    public List<Paragon> findByKursKlient(Klient id);

    
    
}