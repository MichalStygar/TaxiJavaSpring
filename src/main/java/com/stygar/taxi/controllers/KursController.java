package com.stygar.taxi.controllers;

import com.stygar.taxi.entities.Klient;
import com.stygar.taxi.entities.Kurs;
import com.stygar.taxi.entities.Taksowkarz;
import com.stygar.taxi.entities.Taryfa;
import com.stygar.taxi.repositories.KlientRepository;
import com.stygar.taxi.repositories.KursRepository;
import com.stygar.taxi.repositories.TaksowkarzRepository;
import com.stygar.taxi.repositories.TaryfaRepository;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class KursController {
    @Autowired
    KursRepository kursRepository;
    
    @Autowired
    KlientRepository klientRepository;
    
    @Autowired
    TaksowkarzRepository taksowkarzRepository;
    
    @Autowired
    TaryfaRepository taryfaRepository;
    
    
        //DODAWANIE KURS
    @RequestMapping(value = "/kurs/addkurs", method = RequestMethod.GET)
    public String addKurs(Model model)
    {                   
        Kurs kurs = new Kurs(); 
        ArrayList<Klient>  klient =  (ArrayList<Klient>) klientRepository.findAll();
        ArrayList<Taksowkarz>  taksowkarz =  (ArrayList<Taksowkarz>) taksowkarzRepository.findAll();
        ArrayList<Taryfa>  taryfa =  (ArrayList<Taryfa>) taryfaRepository.findAll();
        model.addAttribute("klientList",klient);
        model.addAttribute("taksowkarzList",taksowkarz);
        model.addAttribute("taryfaList",taryfa);
        model.addAttribute("kurs", kurs);
       
       

        return "/kurs/addkurs"; 
        
    }
    
    @RequestMapping(value = "/kurs/addkurs", method = RequestMethod.POST)
    public String addKurs(@ModelAttribute("kurs")@Valid Kurs kurs,BindingResult bindingResult,Principal principal,Model model)
    {
        if (bindingResult.hasErrors()) {
          return "/kurs/addkurs";
      }
        try{
        ArrayList<Taksowkarz>  taksowkarzlist =  (ArrayList<Taksowkarz>) taksowkarzRepository.findByStatus("wolny");
        
        String adresPocztowy = kurs.getAdresPocztowy();
        String adresKoncowy = kurs.getAdresKoncowy();
        String dataGodzinaPoczatkowa = kurs.getDataGodzinaPoczatkowa();      
        Taksowkarz taksowkarz = taksowkarzlist.get(0); 
        Long sdf = taksowkarz.getId();
        
        
        if(taksowkarz!=null){
            Taksowkarz asd = taksowkarzRepository.findById(sdf).get();
           asd.setStatus("zajety");
        }
           
           
        
        
        String login = principal.getName();
        Klient klient = klientRepository.getByLogin(login);
        
   
        LocalDateTime data = LocalDateTime.parse(dataGodzinaPoczatkowa,DateTimeFormatter.ISO_LOCAL_DATE_TIME);     
        int godzina = data.getHour();
  
        if(godzina >=6 && godzina <22){
        
            Taryfa taryfa = taryfaRepository.getByCenaTaryfy(2.0);
            kursRepository.save(new Kurs(adresPocztowy,adresKoncowy,dataGodzinaPoczatkowa,taksowkarz,klient,taryfa));
        }else{
            Taryfa taryfa = taryfaRepository.getByCenaTaryfy(2.30);
            kursRepository.save(new Kurs(adresPocztowy,adresKoncowy,dataGodzinaPoczatkowa,taksowkarz,klient,taryfa));
        }
        }catch(Exception e) {    
        e.printStackTrace();
        }
        
        return "redirect:/kurs/printallkurs";
        
           
    }

    //WYSIWETLENIE WSZYSTKICH KURSOW
    @RequestMapping(value = "/kurs/printallkurs", method = RequestMethod.GET)
    public String printAllKurs(Principal principal,Model model)
    {
        String login = principal.getName();
        Klient id = klientRepository.getByLogin(login);
           
        if(id.getUpraw().equals("user"))
        {
           List<Kurs> kursList =  kursRepository.findByKlient(id);
           
           List<Klient> klientList = klientRepository.findAll();
           

           List<Taksowkarz> taksowkarzList =  taksowkarzRepository.findAll();
           List<Taryfa> taryfaList =  taryfaRepository.findAll();
           
          
          
        model.addAttribute("header","Lista wszystkich kursów:"); 
        model.addAttribute("kursList",kursList);
        model.addAttribute("klientList",klientList);
        model.addAttribute("taksowkarzList",taksowkarzList);
        model.addAttribute("taryfaList",taryfaList);
        
        }else{
            List<Kurs> kursList =  kursRepository.findAll();
           
           
           List<Klient> klientList = klientRepository.findAll();
           

           List<Taksowkarz> taksowkarzList =  taksowkarzRepository.findAll();
           List<Taryfa> taryfaList =  taryfaRepository.findAll();
           
          
          
        model.addAttribute("header","Lista wszystkich kursów:"); 
        model.addAttribute("kursList",kursList);
        model.addAttribute("klientList",klientList);
        model.addAttribute("taksowkarzList",taksowkarzList);
        model.addAttribute("taryfaList",taryfaList);
        }
        return "/kurs/printallkurs";  

    }
    
    //USUWANIE KURSU
    @GetMapping("/kurs/printallkurs/delete/{id}")
    public String deleteKurs(@PathVariable("id")long id, Model model,Kurs kurs) {
     
    kursRepository.deleteById(kurs.getId());
    return "redirect:/kurs/printallkurs";
    }
    
    //EDYTOWANIE KURSU
    @RequestMapping("/kurs/printallkurs/edycja/{id}")
    public String updateKurs(@PathVariable Long id,Model model)
    {
        
        
        ArrayList<Taksowkarz>  taksowkarz =  (ArrayList<Taksowkarz>) taksowkarzRepository.findAll();
        ArrayList<Taryfa>  taryfa =  (ArrayList<Taryfa>) taryfaRepository.findAll();
        ArrayList<Klient>  klient =  (ArrayList<Klient>) klientRepository.findAll();
        model.addAttribute("kurs", kursRepository.getOne(id));
        model.addAttribute("taksowkarzList",taksowkarz);
        model.addAttribute("taryfaList",taryfa);
        model.addAttribute("klientList",klient);
        return "/kurs/editkurs";
    }
    
    @RequestMapping(value = "/kurs/printallkurs/edycja/{id}", method = RequestMethod.POST)
    public String updateKlient(@ModelAttribute("kurs")Kurs kurs,Model model)
    {
       
        Long id = kurs.getId();
        String adresPocztowy = kurs.getAdresPocztowy();
        String adresKoncowy = kurs.getAdresKoncowy();
        String dataGodzinaPoczatkowa = kurs.getDataGodzinaPoczatkowa();
        Klient klient = kurs.getKlient();
        Taksowkarz taksowkarz = kurs.getTaksowkarz();
        Taryfa taryfa = kurs.getTaryfa();
        
       
        
        kursRepository.save(new Kurs(id,adresPocztowy,adresKoncowy,dataGodzinaPoczatkowa,taksowkarz,klient,taryfa));
        return "redirect:/kurs/printallkurs";
    }
    
}
