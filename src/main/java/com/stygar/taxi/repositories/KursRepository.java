package com.stygar.taxi.repositories;

import com.stygar.taxi.entities.Klient;
import com.stygar.taxi.entities.Kurs;
import com.stygar.taxi.entities.Taksowkarz;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;



public interface KursRepository extends JpaRepository<Kurs, Long>{

    public List<Kurs> findByKlient(Klient id);

    

 
    
    
            
    
}

