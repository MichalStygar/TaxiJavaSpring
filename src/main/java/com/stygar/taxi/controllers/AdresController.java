
package com.stygar.taxi.controllers;

import com.stygar.taxi.entities.Adres;
import com.stygar.taxi.entities.Klient;
import com.stygar.taxi.repositories.AdresRepository;
import com.stygar.taxi.repositories.KlientRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdresController {
    
    @Autowired
    AdresRepository adresRepository;
    
   
    
    //DODAWANIE ADRESU
    @RequestMapping("/adres/addadres")
    public String addAdres(Model model)
    {       
        Adres adres = new Adres();                
        model.addAttribute("adres", adres);         
        return "/adres/addadres";  
    }
    
    @RequestMapping(value = "/adres/addadres", method = RequestMethod.POST)
    public String addAdres(@Valid Adres adres,BindingResult bindingResult,Model model)
    {
        if (bindingResult.hasErrors()) {
          return "/adres/addadres";
      }
        try{
        String miejscowosc = adres.getMiejscowosc();
        String ulica = adres.getUlica();
        int nr_mieszkania = adres.getNr_mieszkania();
        String kod_pocztowy = adres.getKod_pocztowy();
        
                
        adresRepository.save(new Adres(miejscowosc,ulica,nr_mieszkania,kod_pocztowy));
        
        
        }catch(Exception e) {    
        e.printStackTrace();
        }
        return "redirect:/adres/printalladres";
    }
    
    //WYSIWETLENIE WSZYSTKICH ADRESOW
    @RequestMapping(value = "/adres/printalladres", method = RequestMethod.GET)
    public String printAllAdres(Model model)
    {
        List<Adres> adresList =  adresRepository.findAll();
        
                        
        model.addAttribute("header","Lista wszystkich adresów:"); 
        model.addAttribute("adresList",adresList); 
        
        return "/adres/printalladres";  

    }
    
    //USUWANIE ADRESU
    @GetMapping("/adres/printalladres/delete/{id}")
    public String deleteAdres(@PathVariable("id")long id, Model model,Adres adres) {
     
    adresRepository.deleteById(adres.getId());
    return "redirect:/adres/printalladres";
    }
    
    //EDYTOWANIE ADRESU
    @RequestMapping("/adres/printalladres/edycja/{id}")
    public String updateAdres(@PathVariable Long id,Model model)
    {
                       
        model.addAttribute("adres", adresRepository.getOne(id));
        
        return "/adres/editadres";
    }
    
    @RequestMapping(value = "/adres/printalladres/edycja/{id}", method = RequestMethod.POST)
    public String updateAdres(@ModelAttribute(name = "adres") Adres adres){
        Long id = adres.getId();
        String miejscowosc = adres.getMiejscowosc();
        String ulica = adres.getUlica();
        int nr_mieszkania = adres.getNr_mieszkania();
        String kod_pocztowy = adres.getKod_pocztowy();
        
        adresRepository.save(new Adres(id,miejscowosc,ulica,nr_mieszkania,kod_pocztowy));
        return "redirect:/adres/printalladres";
    }
    
    
}
