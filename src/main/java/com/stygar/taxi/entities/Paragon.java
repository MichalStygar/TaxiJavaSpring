package com.stygar.taxi.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;



@Entity
public class Paragon {
    @Id
    @Column(name="ID_paragonu")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    
    @Column(nullable=false,length=30)
    @Min(1)
    private double odleglosc;
  
    
    
    @Column(nullable=false,length=30)
    private LocalDateTime dataGodzinaKoncowa;

    
    @Column(nullable=false,length=10)
    @Min(1)
    private double suma;
    
    @ManyToOne
    @JoinColumn(name="id_kursu", nullable=false)
    private Kurs kurs;

    
    
    public Paragon(){}
    
    public Paragon(double odleglosc,String dataGodzinaKoncowa,double suma,Kurs kurs){
        this.odleglosc = odleglosc;      
        if(dataGodzinaKoncowa == ""){ this.dataGodzinaKoncowa = null;}
        else{
            this.dataGodzinaKoncowa = LocalDateTime.parse(dataGodzinaKoncowa,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        this.suma = suma;
        this.kurs = kurs;
    }
    
    public Paragon(Long id,double odleglosc,String dataGodzinaKoncowa,double suma,Kurs kurs){
        this.id = id;
        this.odleglosc = odleglosc;
        if(dataGodzinaKoncowa == ""){ this.dataGodzinaKoncowa = null;}
        else{
            this.dataGodzinaKoncowa = LocalDateTime.parse(dataGodzinaKoncowa,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        this.suma = suma;
        this.kurs = kurs;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }

 
    public String getDataGodzinaKoncowa() {
        if(dataGodzinaKoncowa !=null){
            return dataGodzinaKoncowa.toString();
        }else{
            return "";
        }
    }

    public void setDataGodzinaKoncowa(String dataGodzinaKoncowa) {
        LocalDateTime dataTime = LocalDateTime.parse(dataGodzinaKoncowa,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.dataGodzinaKoncowa = dataTime;
    }
    
    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }
    
    
    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }
    
    
}

