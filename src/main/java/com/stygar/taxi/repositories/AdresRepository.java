
package com.stygar.taxi.repositories;

import com.stygar.taxi.entities.Adres;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AdresRepository extends JpaRepository<Adres, Long> {
    List<Adres> findByTaksowkarzNazwisko(String nazwisko);

    List<Adres> findByTaksowkarzImieAndTaksowkarzNazwisko(String imie,String nazwisko);
    List<Adres> findById(long ul);
    
    
    List<Adres> findByUlica(String ul);
    

    
    
}
