package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Agence;
import entities.Cheque;
import entities.Client;
import entities.Compte;
import entities.ETypeCompte;
import entities.Epargne;

public class CompteRepository {

     public CompteRepository() {
         
    }
    
    public List<Compte> selectAll(){
       String sql="SELECT * FROM `Compte` c , client cl,agence ag where c.client_id=cl.id_client and c.ag_id=ag.id_ag";
       ArrayList<Compte> comptes=new ArrayList<>();
    try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection conn = DriverManager.getConnection(
             "jdbc:mysql://localhost:8889/iageb_ism_2024", "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
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
         conn.close();
    } catch (Exception e) {
         System.out.println("Erreur de chargement du Driver");
    }
       return comptes;   
     }
     public List<Compte> selectByClient(String tel){
        String sql=String.format("SELECT * FROM `Compte` c,client cl WHERE c.`client_id`=cl.id_client and cl.telephone_client like '%s'", tel);
       ArrayList<Compte> comptes=new ArrayList<>();
    try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection conn = DriverManager.getConnection(
             "jdbc:mysql://localhost:8889/iageb_ism_2024", "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
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
         conn.close();
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
          String sql="INSERT INTO `Compte` ( `numero_cpte`, `solde_cpte`, `frais_cpte`, `taux_cpte`, `type_cpte`, `client_id`,ag_id)"
                              +" VALUES (?,?,?,?,?,?,?)";
                 
        try {
            //Chargement du Driver
            Class.forName("com.mysql.cj.jdbc.Driver");                  
            Connection conn = DriverManager.getConnection(
                                      "jdbc:mysql://localhost:8889/iageb_ism_2024", "root", "root");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, compte.getNumero());  
            stmt.setDouble(2, compte.getSolde());  
            stmt.setDouble(3, frais);  
            stmt.setDouble(4, taux);
            stmt.setString(5, compte.getType().name()); 
            stmt.setInt(6, compte.getClient().getId()); 
            stmt.setInt(7, compte.getAgence().getId()); 
            stmt.executeUpdate() ;            
            conn.close();
           } catch (Exception e) 
           {
               System.out.println("Erreur de chargement du Driver");
           }
     }

}
