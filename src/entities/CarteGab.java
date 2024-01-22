package entities;

import java.time.LocalDate;

public class CarteGab {
    private int id;
    private String numero;
    private LocalDate dateExpiration;
    private Compte compte;
    
    public Compte getCompte() {
        return compte;
    }
    public void setCompte(Compte compte) {
        this.compte = compte;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public LocalDate getDateExpiration() {
        return dateExpiration;
    }
    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
}
