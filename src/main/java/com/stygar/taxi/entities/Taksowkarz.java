package com.stygar.taxi.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
public class Taksowkarz {
    @Id
    @Column(name="ID_taksowkarza")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable=false,length=30)
    private String imie;
    
    @NotEmpty
    @Column(nullable=false,length=30)
    private String nazwisko;
    
    
    @Column(nullable=false,length=9)
    private Integer telefon;
    
    @NotEmpty
    @Column(nullable=false,length=11)
    private String pesel;
    
    @NotEmpty
    @Column(nullable=false,length=30)
    private String status;
    
   
    
    @ManyToOne
    @JoinColumn(name="id_samochodu", nullable=true)
    private Samochod samochod;

   
    
    @ManyToOne
    @JoinColumn(name="id_adresu", nullable=true)
    private Adres adres;
    
    @OneToOne
    @JoinColumn(name="id_klienta", nullable=true)
    private Klient klient;

    
    
    @OneToMany(mappedBy = "taksowkarz") 
    private Set<Kurs> kurs;
    
    public Taksowkarz(){}
    
    
    ///
     public Taksowkarz(Long id,String imie, String nazwisko, String telefon, String pesel, String status,Samochod samochod,Klient klient){
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        if(telefon == ""){ this.telefon = null;}
        else{
            this.telefon = Integer.parseInt(telefon);
        }
        this.pesel = pesel;
        this.status = status;
        this.klient = klient;
        this.samochod = samochod;
        
     
        
    }
    
    
    
    public Taksowkarz(String imie, String nazwisko, String telefon, String pesel, String status,Adres adres,Samochod samochod,Klient klient){
        this.imie = imie;
        this.nazwisko = nazwisko;
        if(telefon == ""){ this.telefon = null;}
        else{
            this.telefon = Integer.parseInt(telefon);
        }
        this.pesel = pesel;
        this.status = status;
        this.adres = adres;
        this.samochod = samochod;      
        this.klient = klient;
        
    }
    
    public Taksowkarz(Long id,String imie, String nazwisko, String telefon, String pesel, String status,Adres adres,Samochod samochod,Klient klient){
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        if(telefon == ""){ this.telefon = null;}
        else{
            this.telefon = Integer.parseInt(telefon);
        }
        this.pesel = pesel;
        this.status = status;
        this.adres = adres;
        this.samochod = samochod;      
        this.klient = klient;
        
    }
    
    public Taksowkarz(String imie, String nazwisko, String telefon, String pesel, String status){
        this.imie = imie;
        this.nazwisko = nazwisko;
        if(telefon == ""){ this.telefon = null;}
        else{
            this.telefon = Integer.parseInt(telefon);
        }
        this.pesel = pesel;
        this.status = status;
        
     
        
    }

  

    

   

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getTelefon() {
        if (telefon==null)
            return "";
        else return String.valueOf(telefon);
    }

    public void setTelefon(String telefon) {
        if(telefon == "")
            this.telefon = null;
        else
        this.telefon = Integer.parseInt(telefon);
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
    
    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
    
    public Set<Kurs> getKurs() {
        return kurs;
    }

    public void setKurs(Set<Kurs> kurs) {
        this.kurs = kurs;
    }
    
     public Samochod getSamochod() {
        return samochod;
    }

    public void setSamochod(Samochod samochod) {
        this.samochod = samochod;
    }

    
   
    
    
}

