package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Agence;

public class AgenceRepository {


  //Insere dans la liste
  //Insere dans la BD

  public void insert(Agence agence){

  }


  public ArrayList<Agence>  selectAll(){
      String sql="select * from agence ";
      ArrayList<Agence> agences=new ArrayList<>();
    try {
      //Chargement du Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
      //étape 2: créer l'objet de connexion
         Connection conn = DriverManager.getConnection(
             "jdbc:mysql://localhost:8889/iageb_ism_2024", "root", "root");
       //étape 3: créer l'objet statement,execution des Requetes 
        Statement stmt = conn.createStatement();
        //  ResultSet  , le resultat d'une requete select
        ResultSet rs=stmt.executeQuery(sql);
         while (rs.next()) {
             //rs une ligne de la requete ==> a une agence
             Agence agence=new Agence();
             agence.setId(rs.getInt("id_ag"));
             agence.setNumero(rs.getString("numero_ag"));
             agence.setAdresse(rs.getString("adresse_ag"));
             agences.add(agence);
         }
         rs.close();
         conn.close();
    } catch (Exception e) {
         System.out.println("Erreur de chargement du Driver");
    }
       return agences;
  }

  public ArrayList<Agence>  selectByNumero(String numero){
    String sql="select * from agence where numero_ag like '"+numero+"'";
    ArrayList<Agence> agences=new ArrayList<>();
  try {
    //Chargement du Driver
      Class.forName("com.mysql.cj.jdbc.Driver");
    //étape 2: créer l'objet de connexion
       Connection conn = DriverManager.getConnection(
           "jdbc:mysql://localhost:8889/iageb_ism_2024", "root", "root");
     //étape 3: créer l'objet statement,execution des Requetes 
      Statement stmt = conn.createStatement();
      //  ResultSet  , le resultat d'une requete select
      ResultSet rs=stmt.executeQuery(sql);
       while (rs.next()) {
           //rs une ligne de la requete ==> a une agence
           Agence agence=new Agence();
           agence.setId(rs.getInt("id_ag"));
           agence.setNumero(rs.getString("numero_ag"));
           agence.setAdresse(rs.getString("adresse_ag"));
           agences.add(agence);
       }
       rs.close();
       conn.close();
      
  } catch (Exception e) {
       System.out.println("Erreur de chargement du Driver");
  }
     return agences;
  }
}
