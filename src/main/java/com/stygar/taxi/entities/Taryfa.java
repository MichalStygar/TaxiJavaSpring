package com.stygar.taxi.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Taryfa {
    @Id
    @Column(name="ID_taryfy")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    
    @Column(nullable=false,length=30)
    @NotEmpty
    private String nazwa;
    
    
    @Column(nullable=false,length=10)
    @NotNull
    private Double cenaTaryfy;
    
    
    @Column(nullable=false,length=10)
    @NotNull
    private Double oplata_poczatkowa;
    
    
    @Column(nullable=false,length=50)
    @NotEmpty
    private String opis;
    
    @OneToMany(mappedBy = "taryfa") 
    private Set<Kurs> kurs;
    
    public Taryfa(){}
    
    public  Taryfa(Long id,String nazwa,String cenaTaryfy,String oplata_poczatkowa,String opis){
        this.id = id;
        this.nazwa = nazwa;  
        if(cenaTaryfy == "") this.cenaTaryfy = null;
        else{
            this.cenaTaryfy = Double.parseDouble(cenaTaryfy);
        }
        if(oplata_poczatkowa == "") this.oplata_poczatkowa = null;
        else{
            this.oplata_poczatkowa = Double.parseDouble(oplata_poczatkowa);
        }
        
        this.opis = opis;
       
    }
    
    public  Taryfa(String nazwa,String cenaTaryfy,String oplata_poczatkowa,String opis){
        this.nazwa = nazwa;  
        if(cenaTaryfy == "") this.cenaTaryfy = null;
        else{
            this.cenaTaryfy = Double.parseDouble(cenaTaryfy);
        }
        if(oplata_poczatkowa == "") this.oplata_poczatkowa = null;
        else{
            this.oplata_poczatkowa = Double.parseDouble(oplata_poczatkowa);
        }
        
        this.opis = opis;
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getCenaTaryfy() {
        if (cenaTaryfy==null)
            return "";
        else return String.valueOf(cenaTaryfy);
    }

    public void setCenaTaryfy(String cenaTaryfy) {
        if(cenaTaryfy == "")
            this.cenaTaryfy = null;
        else
        this.cenaTaryfy = Double.parseDouble(cenaTaryfy);
    }

    public String getOplata_poczatkowa() {
        if (oplata_poczatkowa==null)
            return "";
        else return String.valueOf(oplata_poczatkowa);
    }

    public void setOplata_poczatkowa(String oplata_poczatkowa) {
        if(oplata_poczatkowa == "")
            this.oplata_poczatkowa = null;
        else
        this.oplata_poczatkowa = Double.parseDouble(oplata_poczatkowa);
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
    public Set<Kurs> getKurs() {
        return kurs;
    }

    public void setKurs(Set<Kurs> kurs) {
        this.kurs = kurs;
    }
    
    
            
            
}

