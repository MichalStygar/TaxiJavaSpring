
package com.stygar.taxi.controllers;

import com.stygar.taxi.entities.Klient;

import com.stygar.taxi.repositories.KlientRepository;
import java.security.Principal;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KlientController {
   
 
     @Autowired
    KlientRepository klientRepository;
    
    @RequestMapping("/")
    public String home(){
        
        return "/page";
    //   return "home";
       
    }
    //DODAWANIE KONTA
    @RequestMapping("/klient/addklient")
    public String addKlient(Model model)
    {       
        Klient klient = new Klient();                
        model.addAttribute("klient", klient);         
        return "/klient/addklient";  
    }
    
    @RequestMapping(value = "/klient/addklient", method = RequestMethod.POST)
    public String addKlient(@Valid Klient klient,BindingResult bindingResult,Model model)
    {
      
        
        if (bindingResult.hasErrors()) {
            
                return "/rejestracja/add";
            
        }
        try{
        String login = klient.getLogin();
        String haslo = klient.getHaslo();
        String email = klient.getEmail();
       String encoded = new BCryptPasswordEncoder().encode(haslo);
     
        klientRepository.save(new Klient(login,encoded,email));
        
        }catch(Exception e) {    
        e.printStackTrace();
        }
        return "redirect:/klient/printallklient";
        
           
    }

    //WYSIWETLENIE WSZYSTKICH KONT
    @RequestMapping(value = "/klient/printallklient", method = RequestMethod.GET)
    public String printAllKlient(Model model)
    {
        
        List<Klient> klientList = klientRepository.findAll();
        model.addAttribute("header","Lista wszystkich klientow:"); 
        model.addAttribute("klientList",klientList); 
        
        return "/klient/printallklient";  

    }
    
    
    //USUWANIE KLIENTA
    @GetMapping("/klient/printallklient/delete/{id}")
    public String deleteKlient(@PathVariable("id")long id, Model model,Klient klient) {
     
    klientRepository.deleteById(klient.getId());
    return "redirect:/klient/printallklient";
    }
    
    //EDYTOWANIE KLIENTA
    @RequestMapping("/klient/printallklient/edycja/{id}")
    public String updateKlient(@PathVariable Long id,Model model)
    {
                       
        model.addAttribute("klient", klientRepository.getOne(id));
        
        return "/klient/editklient";
    }
    
    @RequestMapping(value = "/klient/printallklient/edycja/{id}", method = RequestMethod.POST)
    public String updateKlient(@ModelAttribute(name = "klient") Klient klient){
        
        Long id = klient.getId();
        String login = klient.getLogin();
        String haslo = klient.getHaslo();
        String email = klient.getEmail();
        
        
     
        klientRepository.save(new Klient(id,login,haslo,email));
        return "redirect:/klient/printallklient";
    }

   

    

        

        
    
    
}
