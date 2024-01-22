package entities;

import java.util.ArrayList;

//Classe Type de Donnee
//Objet est une Variable d'une classe
public class Agence {
    //Attributs
      private int id;
      private String numero;
      private String adresse;
 //Methodes ou Fonctions
    //1-Constructeur 
    //2-Getters

    //Attributs Navigationnels => Attributs issus des relations
     //OneToMany => Une Agence a plusieurs comptes
     ArrayList<Compte> compteList;

    public ArrayList<Compte> getCompteList() {
        return compteList;
    }
    public void setCompteList(ArrayList<Compte> compteList) {
        this.compteList = compteList;
    }
    public int getId() {
        return id;
    }
    public String getNumero() {
        return numero;
    }
    public String getAdresse() {
        return adresse;
    }
    //3-Setters 
    public void setId(int id) {
        this.id = id;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

   
      
     
}
