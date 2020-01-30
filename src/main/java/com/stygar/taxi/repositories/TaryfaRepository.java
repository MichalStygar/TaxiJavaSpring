package com.stygar.taxi.repositories;

import com.stygar.taxi.entities.Taryfa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaryfaRepository extends JpaRepository<Taryfa, Long>{

    public Taryfa findById(int i);

    public Taryfa getByCenaTaryfy(Double cena);

    

    
    
}