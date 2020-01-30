package com.stygar.taxi.entities;






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
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Klient {

   @Id
    @Column(name="ID_klienta",columnDefinition = "serial",insertable = false)       
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    
    @Column(nullable=false,length=30)
    @NotEmpty
    private String login;
    
    @Column(nullable=false,length=60)
    @NotEmpty
    private String haslo;
    
    
    @Column(length=30)
    @Email
    @NotEmpty
    private String email;
    
    @Column(nullable=false,length=30)
    private boolean enabled =true;
    
    @Column(nullable=false,length=30)
    private String  upraw="user";

    
  
    @OneToOne(mappedBy="klient")
    @JoinColumn(name="ID_taksowkarza")
    private Taksowkarz taksowkarz;
    
    @OneToMany(mappedBy = "klient") 
    private Set<Kurs> kurs;
    
    

    
    
    public Klient(){}
    
    
    public Klient(Long id,String login,String haslo,String email){
        this.id = id;
        this.login = login;
        this.haslo = haslo;
        this.email = email;
        
        
        
       
    }
    
    public Klient(String login,String haslo,String email){
        this.login = login;
        this.haslo = haslo;
        this.email = email;
        
        
       
        
       
    }
    
    
    public Klient(String login,String haslo,String email, boolean enabled){
        this.login = login;
        this.haslo = haslo;
        this.email = email;
        this.enabled = enabled;
        
        
       
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

     public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
     public Taksowkarz getTaksowkarz() {
        return taksowkarz;
    }

    public void setTaksowkarz(Taksowkarz taksowkarz) {
        this.taksowkarz = taksowkarz;
    }
    
     
    
    public Set<Kurs> getKurs() {
        return kurs;
    }

    public void setKurs(Set<Kurs> kurs) {
        this.kurs = kurs;
    }
    
    public String getUpraw() {
        return upraw;
    }

    public void setUpraw(String upraw) {
        this.upraw = upraw;
    }
}


