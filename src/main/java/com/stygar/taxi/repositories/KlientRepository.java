package com.stygar.taxi.repositories;

import com.stygar.taxi.entities.Klient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
@Component
public interface KlientRepository extends JpaRepository<Klient, Long> {


List<Klient> findById(long id);

    public Klient getByLogin(String login);

    List<Klient> findByUpraw(Klient login);

    public List<Klient> findByLogin(String login);

    

    

    

   

    

    

    




}
