package entities;

import java.util.ArrayList;

public class Client {
    private int id;
    private String nomComplet;
    private String tel;
    private String email;

    //OneToMany Un Client peut avoir plusieurs Compte
    private ArrayList<Compte> compteList;
    
    public ArrayList<Compte> getCompteList() {
        return compteList;
    }
    public void setCompteList(ArrayList<Compte> compteList) {
        this.compteList = compteList;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
