package com.stygar.taxi.controllers;

import com.stygar.taxi.entities.Klient;
import com.stygar.taxi.entities.Samochod;
import com.stygar.taxi.repositories.SamochodRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SamochodController {
    
    @Autowired
    SamochodRepository samochodRepository;
    
    //DODAWANIE SAMOCHODU
    @RequestMapping("/samochod/addsamochod")
    public String addSamochod(Model model)
    {       
        Samochod samochod = new Samochod();                
        model.addAttribute("samochod", samochod);         
        return "/samochod/addsamochod";  
    }
    
    @RequestMapping(value = "/samochod/addsamochod", method = RequestMethod.POST)
    public String addSamochod(@Valid Samochod samochod,BindingResult bindingResult,Model model)
    {
        
        if (bindingResult.hasErrors()) {
          return "/samochod/addsamochod";
      }
        try{
        String marka = samochod.getMarka();
        String modell = samochod.getModell();
        String rok_produkcji = samochod.getRok_produkcji();
        String nr_rejestracyjny = samochod.getNr_rejestracyjny();
        String nr_boczny = samochod.getNrBoczny();
        
        
     
        samochodRepository.save(new Samochod(marka,modell,rok_produkcji,nr_rejestracyjny,nr_boczny));
        }catch(Exception e) {    
        e.printStackTrace();
        }
        return "redirect:/samochod/printallsamochod";
        
           
    }

    //WYSIWETLENIE WSZYSTKICH SAMOCHODOW
    @RequestMapping(value = "/samochod/printallsamochod", method = RequestMethod.GET)
    public String printAllSamochod(Model model)
    {
        
        List<Samochod> samochodList = samochodRepository.findAll();
        model.addAttribute("header","Lista wszystkich samochodów:"); 
        model.addAttribute("samochodList",samochodList); 
        
        return "/samochod/printallsamochod";  

    }
    
     //USUWANIE SAMOCHODOW
    @GetMapping("/samochod/printallsamochod/delete/{id}")
    public String deleteSamochod(@PathVariable("id")long id, Model model,Samochod samochod) {
     
    samochodRepository.deleteById(samochod.getId());
    return "redirect:/samochod/printallsamochod";
    }
    
    //EDYTOWANIE SAMOCHODU
    @RequestMapping("/samochod/printallsamochod/edycja/{id}")
    public String updateSamochod(@PathVariable Long id,Model model)
    {
                       
        model.addAttribute("samochod", samochodRepository.getOne(id));
        
        return "/samochod/editsamochod";
    }
    
    @RequestMapping(value = "/samochod/printallsamochod/edycja/{id}", method = RequestMethod.POST)
    public String updateSamochod(@ModelAttribute(name = "samochod") Samochod samochod){
        Long id = samochod.getId();
        String marka = samochod.getMarka();
        String modell = samochod.getModell();
        String rok_produkcji = samochod.getRok_produkcji();
        String nr_rejestracyjny = samochod.getNr_rejestracyjny();
        String nr_boczny = samochod.getNrBoczny();
        
        
     
        samochodRepository.save(new Samochod(id,marka,modell,rok_produkcji,nr_rejestracyjny,nr_boczny));
        return "redirect:/samochod/printallsamochod";
    }
    
    
}
