package com.stygar.taxi.controllers;

import com.stygar.taxi.entities.Adres;
import com.stygar.taxi.entities.Klient;
import com.stygar.taxi.entities.Samochod;
import com.stygar.taxi.entities.Taksowkarz;
import com.stygar.taxi.repositories.AdresRepository;
import com.stygar.taxi.repositories.KlientRepository;
import com.stygar.taxi.repositories.SamochodRepository;
import com.stygar.taxi.repositories.TaksowkarzRepository;
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
public class TaksowkarzController {
    
    @Autowired
    TaksowkarzRepository taksowkarzRepository;
    
     @Autowired
    SamochodRepository samochodRepository;
     @Autowired
    AdresRepository adresRepository;
     
     @Autowired
    KlientRepository klientRepository;
    
    
    
    
    @RequestMapping(value = "/taksowkarz/addtaksowkarz", method = RequestMethod.GET)
    public String printlist(Model model)
    {
        
        Taksowkarz taksowkarz = new Taksowkarz(); 
        
        model.addAttribute("taksowkarz", taksowkarz);
        //List<Samochod> samochodList =  samochodRepository.findAll(); 
        
        ArrayList<Samochod>  samochod =  (ArrayList<Samochod>) samochodRepository.findAll();
        ArrayList<Adres>  adres =  (ArrayList<Adres>) adresRepository.findAll();
        ArrayList<Klient>  klient =  (ArrayList<Klient>) klientRepository.findAll();
        
        model.addAttribute("samochodList",samochod); 
        model.addAttribute("adresList",adres);
        model.addAttribute("klientList",klient);
        return "/taksowkarz/addtaksowkarz";  

    }
    
    
    
    //////
    
    @RequestMapping(value = "/taksowkarz/addtaksowkarz", method = RequestMethod.POST)
    public String addTaksowkarz(@ModelAttribute("taksowkarz")@Valid Taksowkarz taksowkarz,BindingResult bindingResult,Adres adres,Samochod samochod,Klient klient,Model model)   
    {
        if (bindingResult.hasErrors()) {
          return "/taksowkarz/addtaksowkarz";
      }
        try{
        String imie = taksowkarz.getImie();
        String nazwisko = taksowkarz.getNazwisko();
        String telefon = taksowkarz.getTelefon();
        String pesel = taksowkarz.getPesel();
        String status = taksowkarz.getStatus();
        Long id = samochod.getId();
        Long ul = adres.getId();
        Long idkon = klient.getId();
      
       
       
        adres = adresRepository.findById(ul).get();
        klient = klientRepository.findById(idkon).get();
       
      
       Samochod sam = samochodRepository.findById(id).get();
      
       
        taksowkarzRepository.save(new Taksowkarz(imie,nazwisko,telefon,pesel,status,adres,sam,klient));
        }catch(Exception e) {    
        e.printStackTrace();
        }
         
        return "redirect:/taksowkarz/printalltaksowkarz";
        
    }
     
    @RequestMapping(value = "/taksowkarz/printalltaksowkarz", method = RequestMethod.GET)
    public String printAllTaksowkarz(Model model)
    {
        List<Taksowkarz> taksowkarzList =  taksowkarzRepository.findAll();
        List<Adres> adresList =  adresRepository.findAll();
        List<Samochod> samochodList =  samochodRepository.findAll();
        List<Klient> klientList =  klientRepository.findAll();
        
        model.addAttribute("header","Lista wszystkich taksówkarzy:"); 
        model.addAttribute("taksowkarzList",taksowkarzList); 
        model.addAttribute("adresList",adresList);
        model.addAttribute("samochodList",samochodList);
        model.addAttribute("klientList",klientList);
        
        return "/taksowkarz/printalltaksowkarz";  

    }
    
    //USUWANIE KLIENTA
    @GetMapping("/taksowkarz/printalltaksowkarz/delete/{id}")
    public String deleteTaksowkarz(@PathVariable("id")long id, Model model,Taksowkarz taksowkarz) {
     
    taksowkarzRepository.deleteById(taksowkarz.getId());
    return "redirect:/taksowkarz/printalltaksowkarz";
    }
    
    //EDYTOWANIE ADRESU
    @RequestMapping("/taksowkarz/printalltaksowkarz/edycja/{id}")
    public String updateTaksowkarz(@PathVariable Long id,Model model)
    {
        ArrayList<Klient>  klient =  (ArrayList<Klient>) klientRepository.findAll();
        ArrayList<Samochod>  samochod =  (ArrayList<Samochod>) samochodRepository.findAll();
        ArrayList<Adres>  adres =  (ArrayList<Adres>) adresRepository.findAll();
        model.addAttribute("taksowkarz", taksowkarzRepository.getOne(id));
        model.addAttribute("samochodList",samochod);
        model.addAttribute("adresList",adres);
        model.addAttribute("klientList",klient);
        
        
        return "/taksowkarz/edittaksowkarz";
    }
    
    @RequestMapping(value = "/taksowkarz/printalltaksowkarz/edycja/{id}", method = RequestMethod.POST)
    public String updateTaksowkarz(@ModelAttribute(name = "taksowkarz")Taksowkarz taksowkarz){
        Long id = taksowkarz.getId();
        String imie = taksowkarz.getImie();
        String nazwisko = taksowkarz.getNazwisko();
        String telefon = taksowkarz.getTelefon();
        String pesel = taksowkarz.getPesel();
        String status = taksowkarz.getStatus();
        Adres adres = taksowkarz.getAdres();
        Klient klient = taksowkarz.getKlient();
        Samochod samochod = taksowkarz.getSamochod();
        
        
        
        
        taksowkarzRepository.save(new Taksowkarz(id,imie,nazwisko,telefon,pesel,status,adres,samochod,klient));
        return "redirect:/taksowkarz/printalltaksowkarz";
    }
    
    
}
