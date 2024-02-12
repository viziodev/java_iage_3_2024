package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Cheque;
import entities.Compte;
import entities.ETypeCompte;
import entities.Epargne;

public class CompteRepository {

     public CompteRepository() {
         
    }
    
    public List<Compte> selectAll(){
       String sql="select * from Compte ";
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
       
     }

}
