package repositories;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import core.Database;
import entities.Agence;
import entities.Cheque;
import entities.Client;
import entities.Compte;
import entities.ETypeCompte;
import entities.Epargne;

public class CompteRepository extends Database {
     private final String SQL_SELECT_ALL="SELECT * FROM `Compte` c , client cl,agence ag where c.client_id=cl.id_client and c.ag_id=ag.id_ag ";
     private final String SQL_SELECT_BY_TEL="SELECT * FROM `Compte` c,client cl WHERE c.`client_id`=cl.id_client and cl.telephone_client like ? ";
     private final String SQL_INSERT="INSERT INTO `Compte` ( `numero_cpte`, `solde_cpte`, `frais_cpte`, `taux_cpte`, `type_cpte`, `client_id`,ag_id) VALUES (?,?,?,?,?,?,?) ";
  
     public CompteRepository() {
         
    }
    
    public List<Compte> selectAll(){
      
       ArrayList<Compte> comptes=new ArrayList<>();
    try {
         ouvrirConnexion();
         initPrepareStatement(SQL_SELECT_ALL);
         ResultSet rs= executeSelect();
         while (rs.next()) {
             //rs une ligne de la requete ==> a une agence
            //Donnees Client
             Client client=new Client();
             client.setId(rs.getInt("id_client"));
             client.setNomComplet(rs.getString("nom_complet_client"));
             client.setTel(rs.getString("telephone_client"));
             client.setEmail(rs.getString("email_client"));

             //Donnees Agence 
              Agence agence=new Agence();
               agence.setId(rs.getInt("id_ag"));
               agence.setNumero(rs.getString("numero_ag"));
               agence.setAdresse(rs.getString("adresse_ag"));

             String type=rs.getString("type_cpte");
             if(type.compareToIgnoreCase("CHEQUE")==0){
               Cheque compteCheque=new Cheque();
               compteCheque.setId(rs.getInt("id_cpte"));
               compteCheque.setSolde(rs.getDouble("solde_cpte"));
               compteCheque.setNumero(rs.getString("numero_cpte"));
               compteCheque.setType(ETypeCompte.Cheque);
               compteCheque.setFrais(rs.getDouble("frais_cpte"));
               compteCheque.setClient(client);
               compteCheque.setAgence(agence);
               comptes.add(compteCheque);
             }else{
                Epargne comptEpargne=new Epargne();
                comptEpargne.setId(rs.getInt("id_cpte"));
                comptEpargne.setSolde(rs.getDouble("solde_cpte"));
                comptEpargne.setNumero(rs.getString("numero_cpte"));
                comptEpargne.setType(ETypeCompte.Epargne);
                comptEpargne.setTaux(rs.getDouble("taux_cpte"));
                comptEpargne.setClient(client);
                comptEpargne.setAgence(agence);
                comptes.add(comptEpargne);
             }
            
            
         }
         rs.close();
         fermerConnexion();
    } catch (Exception e) {
         System.out.println("Erreur de chargement du Driver");
    }
       return comptes;   
     }
     public List<Compte> selectByClient(String tel){
     
       ArrayList<Compte> comptes=new ArrayList<>();
    try {
         ouvrirConnexion();
         initPrepareStatement(SQL_SELECT_BY_TEL);
         statement.setString(1, tel);
        ResultSet rs= executeSelect();
         while (rs.next()) {
             //rs une ligne de la requete ==> a une agence
             String type=rs.getString("type_cpte");
             if(type.compareToIgnoreCase("CHEQUE")==0){
               Cheque compteCheque=new Cheque();
               compteCheque.setId(rs.getInt("id_cpte"));
               compteCheque.setSolde(rs.getDouble("solde_cpte"));
               compteCheque.setNumero(rs.getString("numero_cpte"));
               compteCheque.setType(ETypeCompte.Cheque);
               compteCheque.setFrais(rs.getDouble("frais_cpte"));
               comptes.add(compteCheque);
             }else{
                Epargne comptEpargne=new Epargne();
                comptEpargne.setId(rs.getInt("id_cpte"));
                comptEpargne.setSolde(rs.getDouble("solde_cpte"));
                comptEpargne.setNumero(rs.getString("numero_cpte"));
                comptEpargne.setType(ETypeCompte.Epargne);
                comptEpargne.setTaux(rs.getDouble("taux_cpte"));
                comptes.add(comptEpargne);
             }
         }
         rs.close();
        fermerConnexion();
    } catch (Exception e) {
         System.out.println("Erreur de chargement du Driver");
    }
       return comptes;  
     }

     public void insert(Compte compte){
          double taux=0,frais=0;
          if (compte.getType()==ETypeCompte.Cheque) {
              Cheque cheque= (Cheque)compte;
              frais=cheque.getFrais();
          } else {
               Epargne epargne= (Epargne)compte;
               taux=epargne.getTaux();
          }
                 
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, compte.getNumero());  
            statement.setDouble(2, compte.getSolde());  
            statement.setDouble(3, frais);  
            statement.setDouble(4, taux);
            statement.setString(5, compte.getType().name()); 
            statement.setInt(6, compte.getClient().getId()); 
            statement.setInt(7, compte.getAgence().getId()); 
            statement.executeUpdate() ;            
           fermerConnexion();
           } catch (Exception e) 
           {
               System.out.println("Erreur de chargement du Driver");
           }
     }

}
