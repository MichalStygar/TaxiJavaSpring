package com.stygar.taxi.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
public class Samochod {
    @Id
    @Column(name="ID_samochodu")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable=false,length=30)
    private String marka;
    
    @NotEmpty
    @Column(nullable=false,length=30)
    private String modell;
    
    
    @Column(nullable=false,length=30)
    private Integer rok_produkcji;
    
    @NotEmpty
    @Column(nullable=false,length=30)
    private String nr_rejestracyjny;
    
    
    @Column(nullable=false,length=30)
    private Integer nrBoczny;
    
  
    
    @OneToMany(mappedBy = "samochod") 
    private Set<Taksowkarz> taksowkarz;
    
     public Samochod() {}
     
     

     public Samochod(Long id,String marka, String modell, String rok_produkcji, String nr_rejestracyjny, String nrBoczny ) {
        
         this.id = id;
        this.marka = marka;
        this.modell = modell;
        if(rok_produkcji == "") this.rok_produkcji = null;
        else{
            this.rok_produkcji = Integer.parseInt(rok_produkcji);
        }
        this.nr_rejestracyjny = nr_rejestracyjny;
        if(nrBoczny == "") this.nrBoczny = null;
        else{
            this.nrBoczny = Integer.parseInt(nrBoczny);
        }
       
       
    }
     
     
     
     public Samochod(String marka, String modell, String rok_produkcji, String nr_rejestracyjny, String nrBoczny ) {
        this.marka = marka;
        this.modell = modell;
        if(rok_produkcji == "") this.rok_produkcji = null;
        else{
            this.rok_produkcji = Integer.parseInt(rok_produkcji);
        }
        this.nr_rejestracyjny = nr_rejestracyjny;
        if(nrBoczny == "") this.nrBoczny = null;
        else{
            this.nrBoczny = Integer.parseInt(nrBoczny);
        }
      
       
    }

   

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getRok_produkcji() {
        if (rok_produkcji==null)
            return "";
        else return String.valueOf(rok_produkcji);
    }

    public void setRok_produkcji(String rok_produkcji) {
        if(rok_produkcji == "")
            this.rok_produkcji = null;
        else
        this.rok_produkcji = Integer.parseInt(rok_produkcji);
    }

    public String getNr_rejestracyjny() {
        return nr_rejestracyjny;
    }

    public void setNr_rejestracyjny(String nr_rejestracyjny) {
        this.nr_rejestracyjny = nr_rejestracyjny;
    }

    public String getNrBoczny() {
        if (nrBoczny==null)
            return "";
        else return String.valueOf(nrBoczny);
    }

    public void setNrBoczny(String nrBoczny) {
        if(nrBoczny == "")
            this.nrBoczny = null;
        else
        this.nrBoczny = Integer.parseInt(nrBoczny);
    }
    
    public Set<Taksowkarz> getTaksowkarz() {
        return taksowkarz;
    }

    public void setTaksowkarz(Set<Taksowkarz> taksowkarz) {
        this.taksowkarz = taksowkarz;
    }
    
    
}

