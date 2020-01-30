
package com.stygar.taxi.controllers;

import com.stygar.taxi.entities.Klient;
import com.stygar.taxi.entities.Kurs;
import com.stygar.taxi.entities.Paragon;
import com.stygar.taxi.entities.Taksowkarz;
import com.stygar.taxi.repositories.KlientRepository;
import com.stygar.taxi.repositories.KursRepository;
import com.stygar.taxi.repositories.ParagonRepository;
import com.stygar.taxi.repositories.TaksowkarzRepository;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class ParagonController {
    @Autowired
    ParagonRepository paragonRepository;
    
    @Autowired
    KursRepository kursRepository;
    
    @Autowired
    KlientRepository klientRepository;
    
    @Autowired
    TaksowkarzRepository taksowkarzRepository;
    
    
    //DODAWANIE PARAGONU
    @RequestMapping(value = "/paragon/addparagon", method = RequestMethod.GET)
    public String addParagon(Model model)
    {       
        Paragon paragon = new Paragon(); 
        ArrayList<Kurs>  kurs =  (ArrayList<Kurs>) kursRepository.findAll();
        model.addAttribute("paragon", paragon);
        model.addAttribute("kursList", kurs);
        
        return "/paragon/addparagon";  
    }
    
    @RequestMapping(value = "/paragon/addparagon", method = RequestMethod.POST)
    public String addParagon(@ModelAttribute("paragon")@Valid  Paragon paragon,BindingResult bindingResult,Model model)
    {
        if (bindingResult.hasErrors()) {
          return "/paragon/addparagon";
      }
        try{
        double odleglosc = paragon.getOdleglosc();
        String dataGodzinaKoncowa = paragon.getDataGodzinaKoncowa();
        double suma = paragon.getSuma();
        Kurs kurs = paragon.getKurs();
        Taksowkarz taksowkarz = kurs.getTaksowkarz();
        Long id = taksowkarz.getId();
        
        if(taksowkarz!=null){
            Taksowkarz asd = taksowkarzRepository.findById(id).get();
           asd.setStatus("wolny");
        }
       
     
     
        paragonRepository.save(new Paragon(odleglosc,dataGodzinaKoncowa,suma,kurs));
        }catch(Exception e) {    
        e.printStackTrace();
        }
        return "redirect:/paragon/printallparagon";
        
           
    }

    //WYSIWETLENIE WSZYSTKICH PARAGONOW
    @RequestMapping(value = "/paragon/printallparagon", method = RequestMethod.GET)
    public String printAllParagon(Principal principal,Model model)
    {
        String login = principal.getName();
        Klient id = klientRepository.getByLogin(login);
        
        if(id.getUpraw().equals("user"))
        {
        
           
            List<Paragon> paragonList = paragonRepository.findByKursKlient(id);
           //List<Kurs> kursList = kursRepository.findAll();
           List<Kurs> kursList =  kursRepository.findByKlient(id);
           
          
          
        model.addAttribute("header","Lista wszystkich paragonów:"); 
        model.addAttribute("paragonList",paragonList);
        model.addAttribute("kursList",kursList);
        
        }else{
            List<Paragon> paragonList = paragonRepository.findAll();
           List<Kurs> kursList = kursRepository.findAll();
           
          
        model.addAttribute("header","Lista wszystkich paragonów:"); 
        model.addAttribute("paragonList",paragonList);
        model.addAttribute("kursList",kursList);
        } 
        
        return "/paragon/printallparagon";  

    }
    
    //USUWANIE PARAGONU
    @GetMapping("/paragon/printallparagon/delete/{id}")
    public String deleteParagon(@PathVariable("id")long id, Model model,Paragon paragon) {
     
    paragonRepository.deleteById(paragon.getId());
    return "redirect:/paragon/printallparagon";
    }
    
    //EDYTOWANIE PARAGONU
    @RequestMapping("/paragon/printallparagon/edycja/{id}")
    //@RequestMapping(value = "/klient/printallklient/edycja/{id}", method = RequestMethod.GET)
    public String updateParagon(@PathVariable Long id,Model model)
    {
        
        ArrayList<Kurs>  kurs =  (ArrayList<Kurs>) kursRepository.findAll();              
        model.addAttribute("paragon", paragonRepository.getOne(id));
        model.addAttribute("kursList",kurs);
        return "/paragon/editparagon";
    }
    
    @RequestMapping(value = "/paragon/printallparagon/edycja/{id}", method = RequestMethod.POST)
    public String updateParagon(@ModelAttribute("paragon")Paragon paragon,Model model)
    {
       
        Long id = paragon.getId();
        double odleglosc = paragon.getOdleglosc();
        String dataGodzinaKoncowa = paragon.getDataGodzinaKoncowa();
        double suma = paragon.getSuma();
        Kurs kurs = paragon.getKurs();
        
        
         paragonRepository.save(new Paragon(id,odleglosc,dataGodzinaKoncowa,suma,kurs));
        return "redirect:/paragon/printallparagon";
    }
   
}
