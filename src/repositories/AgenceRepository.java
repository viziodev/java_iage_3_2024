package repositories;


import java.sql.ResultSet;
import java.util.ArrayList;
import core.Database;
import entities.Agence;

public class AgenceRepository extends Database {
   private final String SQL_SELECT_ALL="select * from agence ";
   private final String SQL_SELECT_BY_NUMERO="select * from agence where numero_ag like ? ";
   private final String SQL_INSERT="INSERT INTO `agence` (`numero_ag`, `adresse_ag`) VALUES (?,?) ";

  //Insere dans la liste
  //Insere dans la BD

  public void insert(Agence agence){
     try {
          ouvrirConnexion();
          initPrepareStatement(SQL_INSERT);
          statement.setString(1, agence.getNumero());
          statement.setString(2, agence.getAdresse());
          executeUpdate();
         fermerConnexion();
      } catch (Exception e) 
      {
         System.out.println("Erreur de chargement du Driver");
      }

  }


  public ArrayList<Agence>  selectAll(){
      ArrayList<Agence> agences=new ArrayList<>();
    try {
         ouvrirConnexion();
         initPrepareStatement(SQL_SELECT_ALL);
         ResultSet rs= executeSelect();
         while (rs.next()) {
             //rs une ligne de la requete ==> a une agence
             Agence agence=new Agence();
             agence.setId(rs.getInt("id_ag"));
             agence.setNumero(rs.getString("numero_ag"));
             agence.setAdresse(rs.getString("adresse_ag"));
             agences.add(agence);
         }
         rs.close();
         fermerConnexion();
    } catch (Exception e) {
         System.out.println("Erreur de chargement du Driver");
    }
       return agences;
  }

  public ArrayList<Agence>  selectByNumero(String numero){
     ArrayList<Agence> agences=new ArrayList<>();
  try {
        ouvrirConnexion();
        initPrepareStatement(SQL_SELECT_BY_NUMERO);
        statement.setString(1, numero);
        ResultSet rs= executeSelect();
     
       while (rs.next()) {
           //rs une ligne de la requete ==> a une agence
           Agence agence=new Agence();
           agence.setId(rs.getInt("id_ag"));
           agence.setNumero(rs.getString("numero_ag"));
           agence.setAdresse(rs.getString("adresse_ag"));
           agences.add(agence);
       }
       rs.close();
       fermerConnexion();
      
  } catch (Exception e) {
       System.out.println("Erreur de chargement du Driver");
  }
     return agences;
  }
}
