package com.stygar.taxi.controllers;

import com.stygar.taxi.entities.Klient;
import com.stygar.taxi.entities.Taryfa;
import com.stygar.taxi.repositories.TaryfaRepository;
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
public class TaryfaController {
    
    @Autowired
    TaryfaRepository taryfaRepository;
    
    //DODAWANIE TARYFY
    @RequestMapping("/taryfa/addtaryfa")
    public String addTaryfa(Model model)
    {       
        Taryfa taryfa = new Taryfa();                
        model.addAttribute("taryfa", taryfa);         
        return "/taryfa/addtaryfa";  
    }
    
    @RequestMapping(value = "/taryfa/addtaryfa", method = RequestMethod.POST)
    public String addTaryfa(@Valid Taryfa taryfa,BindingResult bindingResult,Model model)
    {
        if (bindingResult.hasErrors()) {
          return "/taryfa/addtaryfa";
      }
        try{
        String nazwa = taryfa.getNazwa();
        String cena_taryfy = taryfa.getCenaTaryfy();
        String oplata_poczatkowa = taryfa.getOplata_poczatkowa();
        String opis = taryfa.getOpis();
        
     
        taryfaRepository.save(new Taryfa(nazwa,cena_taryfy,oplata_poczatkowa,opis));
        }catch(Exception e) {    
        e.printStackTrace();
        }
        return "redirect:/taryfa/printalltaryfa";
        
           
    }

    //WYSIWETLENIE WSZYSTKICH TARYF
    @RequestMapping(value = "/taryfa/printalltaryfa", method = RequestMethod.GET)
    public String printAllTaryfa(Model model)
    {
       
        List<Taryfa> taryfaList = taryfaRepository.findAll();
        model.addAttribute("header","Lista wszystkich taryf:"); 
        model.addAttribute("taryfaList",taryfaList); 
        
        return "/taryfa/printalltaryfa";  

    }
    
    //USUWANIE TARYFY
    @GetMapping("/taryfa/printalltaryfa/delete/{id}")
    public String deleteTaryfa(@PathVariable("id")long id, Model model,Taryfa taryfa) {
     
    taryfaRepository.deleteById(taryfa.getId());
    return "redirect:/taryfa/printalltaryfa";
    }
    
    //EDYTOWANIE TARYFY
    @RequestMapping("/taryfa/printalltaryfa/edycja/{id}")
    public String updateTaryfa(@PathVariable Long id,Model model)
    {
                       
        model.addAttribute("taryfa", taryfaRepository.getOne(id));
        
        return "/taryfa/edittaryfa";
    }
    
    @RequestMapping(value = "/taryfa/printalltaryfa/edycja/{id}", method = RequestMethod.POST)
    public String updateKlient(@ModelAttribute(name = "taryfa") Taryfa taryfa){
        Long id = taryfa.getId();
        String nazwa = taryfa.getNazwa();
        String cena_taryfy = taryfa.getCenaTaryfy();
        String oplata_poczatkowa = taryfa.getOplata_poczatkowa();
        String opis = taryfa.getOpis();
        
     
        taryfaRepository.save(new Taryfa(id,nazwa,cena_taryfy,oplata_poczatkowa,opis));
        return "redirect:/taryfa/printalltaryfa";
    }
    
    
    
    
    
}
