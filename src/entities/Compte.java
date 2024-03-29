package entities;

import java.util.ArrayList;

public class Compte {
    private int id;
    private String numero;
    private ETypeCompte type;
    private double solde;

    //Attributs Navigationnels => Attributs issus des relations
     //ManyTOne => Plusieurs Comptes sont crees dans une Agence
     private Agence Agence;
      //ManyTOne => Plusieurs Comptes appartiennent a un client
     private Client client;
     private  ArrayList<CarteGab> CarteGabList;

    public ArrayList<CarteGab> getCarteGabList() {
        return CarteGabList;
    }
    public void setCarteGabList(ArrayList<CarteGab> carteGabList) {
        CarteGabList = carteGabList;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Agence getAgence() {
        return Agence;
    }
    public void setAgence(Agence agence) {
        Agence = agence;
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
    public double getSolde() {
        return solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    } 
    public ETypeCompte getType() {
        return type;
    }
    public void setType(ETypeCompte type) {
        this.type = type;
    }
}
