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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;


@Entity
public class Kurs {
    @Id
    @Column(name="ID_kursu")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false,length=30)
    @NotEmpty
    private String adresPocztowy;
    
    @Column(nullable=false,length=30)
    @NotEmpty
    private String adresKoncowy;

    @Column(nullable=false,length=30)
    private LocalDateTime dataGodzinaPoczatkowa;

    
    
    @ManyToOne 
    @JoinColumn(name="id_taksowkarza", nullable=false)
    private Taksowkarz taksowkarz;
    
    @ManyToOne
    @JoinColumn(name="id_klienta", nullable=false)
    private Klient klient;
    
    @ManyToOne 
    @JoinColumn(name="id_taryfy", nullable=false)
    private Taryfa taryfa;    

   
    
    @OneToMany(mappedBy = "kurs") 
    private Set<Paragon> paragon;

    
    
    public Kurs(){}
    
    public Kurs(String adresPocztowy,String adresKoncowy,String dataGodzinaPoczatkowa ,Taksowkarz taksowkarz,Klient klient,Taryfa taryfa){
        this.adresPocztowy = adresPocztowy;
        this.adresKoncowy = adresKoncowy;
        if(dataGodzinaPoczatkowa == ""){ this.dataGodzinaPoczatkowa = null;}
        else{
            this.dataGodzinaPoczatkowa = LocalDateTime.parse(dataGodzinaPoczatkowa,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        this.taksowkarz = taksowkarz;
        this.klient = klient;
        this.taryfa = taryfa;
        
    }
    
    public Kurs(Long id,String adresPocztowy,String adresKoncowy,String dataGodzinaPoczatkowa ,Taksowkarz taksowkarz,Klient klient,Taryfa taryfa){
        this.id = id;
        this.adresPocztowy = adresPocztowy;
        this.adresKoncowy = adresKoncowy;
        if(dataGodzinaPoczatkowa == ""){ this.dataGodzinaPoczatkowa = null;}
        else{
            this.dataGodzinaPoczatkowa = LocalDateTime.parse(dataGodzinaPoczatkowa,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        this.taksowkarz = taksowkarz;
        this.klient = klient;
        this.taryfa = taryfa;
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     public String getAdresPocztowy() {
        return adresPocztowy;
    }

    public void setAdresPocztowy(String adresPocztowy) {
        this.adresPocztowy = adresPocztowy;
    }

    public String getAdresKoncowy() {
        return adresKoncowy;
    }

    public void setAdresKoncowy(String adresKoncowy) {
        this.adresKoncowy = adresKoncowy;
    }

    public String getDataGodzinaPoczatkowa() {
        
        if(dataGodzinaPoczatkowa !=null){
            return dataGodzinaPoczatkowa.toString();
        }else{
            return "";
        }
    }

    public void setDataGodzinaPoczatkowa(String dataGodzinaPoczatkowa) {
        
        LocalDateTime dataTime = LocalDateTime.parse(dataGodzinaPoczatkowa,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.dataGodzinaPoczatkowa = dataTime;
    }
    
     public Taksowkarz getTaksowkarz() {
        return taksowkarz;
    }

    public void setTaksowkarz(Taksowkarz taksowkarz) {
        this.taksowkarz = taksowkarz;
    }
    
    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
    
    public Taryfa getTaryfa() {
        return taryfa;
    }

    public void setTaryfa(Taryfa taryfa) {
        this.taryfa = taryfa;
    }
    
    public Set<Paragon> getParagon() {
        return paragon;
    }

    public void setParagon(Set<Paragon> paragon) {
        this.paragon = paragon;
    }
    
    
    
}

