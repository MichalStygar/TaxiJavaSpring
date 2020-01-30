package com.stygar.taxi.repositories;

import com.stygar.taxi.entities.Samochod;
import com.stygar.taxi.entities.Taksowkarz;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SamochodRepository extends JpaRepository<Samochod, Long> {

List<Samochod> findByMarka(String marka);

List<Samochod> findByTaksowkarzImieAndTaksowkarzNazwisko(String imie,String nazwisko);



    List<Samochod> findByNrBoczny(Integer numer);
    

List<Samochod> findById(long id);

    
}
