package com.stygar.taxi.repositories;


import com.stygar.taxi.entities.Kurs;
import com.stygar.taxi.entities.Taksowkarz;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TaksowkarzRepository extends JpaRepository<Taksowkarz, Long> {


List<Taksowkarz> findById(long id);
List<Taksowkarz> findByNazwisko(String nazwisko);
List<Taksowkarz> findBySamochodMarka(String marka);
List<Taksowkarz> findByAdresMiejscowosc(String miejscowosc);
List<Taksowkarz> findByAdresMiejscowoscAndAdresUlica(String miejscowosc,String ulica);

List<Taksowkarz> findByStatus(String status);

    public String findByImie(Taksowkarz taksowkarz);

   

    
    
   
    
}
