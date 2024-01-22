package repositories;

import java.util.ArrayList;

import entities.Agence;

public class AgenceRepository {
  //Gerer Acces au donnes  
  //Enregistrement, recuperation ,... des donnees de type Agence
  private ArrayList<Agence> tableAgence=new ArrayList<>();

  //Insere dans la liste
  //Insere dans la BD

  public void insert(Agence agence){
      tableAgence.add(agence);
  }

  //recuper les donnees de la liste
  //recuper les donnees de la BD
    //Select * from agence'
  public ArrayList<Agence>  selectAll(){
      return tableAgence;
  }

  //recuper les donnees de la liste a travers le numero
  //recuper les donnees de la BD
 //Select * from agence where numero like 'XXXX'
  public ArrayList<Agence>  selectByNumero(String numero){
      ArrayList   listAgenceByNumero=new ArrayList<>();
     for (Agence agence : tableAgence) {
           if(agence.getNumero().compareTo(numero)==0){
             listAgenceByNumero.add(agence);
           }
     }
       return listAgenceByNumero;
  }
}
