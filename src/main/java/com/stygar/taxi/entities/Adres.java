package com.stygar.taxi.entities;



import java.util.HashSet;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
public class Adres {
    @Id
    @Column(name="ID_adresu")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable=false,length=30)
    @NotEmpty
    private String miejscowosc;
    
    @Column(nullable=false,length=30)
    @NotEmpty
    private String ulica;
    
    @Min(1)
    @Column(nullable=false,length=4)
    private int nr_mieszkania;
    
    @Column(nullable=false,length=6)
    @Pattern(regexp="\\d{2}-\\d{3}")
    private String kod_pocztowy;
    
    @OneToMany(mappedBy = "adres") 
    private Set<Taksowkarz> taksowkarz;
    
  
    
    public Adres(){}
    
     public Adres(Long id,String miejscowosc,String ulica,int nr_mieszkania,String kod_pocztowy ){
        this.id = id;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nr_mieszkania = nr_mieszkania;
        this.kod_pocztowy = kod_pocztowy;
        
    }
    
    public Adres(String miejscowosc,String ulica,int nr_mieszkania,String kod_pocztowy ){
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nr_mieszkania = nr_mieszkania;
        this.kod_pocztowy = kod_pocztowy;
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNr_mieszkania() {
        return nr_mieszkania;
    }

    public void setNr_mieszkania(int nr_mieszkania) {
        this.nr_mieszkania = nr_mieszkania;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }
    
    public Set<Taksowkarz> getTaksowkarz() {
        return taksowkarz;
    }

    public void setTaksowkarz(Set<Taksowkarz> taksowkarz) {
        this.taksowkarz = taksowkarz;
    }
    
   
    
  
}

