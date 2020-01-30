
package com.stygar.taxi.controllers;

import com.stygar.taxi.entities.Klient;
import com.stygar.taxi.repositories.KlientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RejestracjaController {
 
    @Autowired
    KlientRepository klientRepository;
    
    //DODAWANIE KONTA
    @RequestMapping("/rejestracja/add")
    public String addKlient(Model model)
    {       
        Klient klient = new Klient();                
        model.addAttribute("klient", klient);         
        return "/rejestracja/add";  
    }
    
    @RequestMapping(value = "/rejestracja/add", method = RequestMethod.POST)
    public String addKlient(Model model,Klient klient)
    {
        
        String login = klient.getLogin();
        String haslo = klient.getHaslo();
        String email = klient.getEmail();
       String encoded = new BCryptPasswordEncoder().encode(haslo);
     
        klientRepository.save(new Klient(login,encoded,email));
        
        
        return "/login";
        
           
    }
    
    
    
    
    
    
    
    
    
}
