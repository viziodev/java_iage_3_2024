package repositories;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Client;

public class ClientRepository {
   
      public void insert(Client client){
           String sql=String.format("INSERT INTO `client` (`nom_complet_client`, `telephone_client`, `email_client`)"+
            "VALUES ('%s', '%s', '%s')",client.getNomComplet(),client.getTel(),client.getEmail());
          try {
              //Chargement du Driver
              Class.forName("com.mysql.cj.jdbc.Driver");                  
              Connection conn = DriverManager.getConnection(
                                        "jdbc:mysql://localhost:8889/iageb_ism_2024", "root", "root");
              Statement stmt = conn.createStatement();
              stmt.executeUpdate(sql);               
              conn.close();
          } catch (Exception e) 
           {
            System.out.println("Erreur de chargement du Driver");
           }
          }




      public ArrayList<Client>  selectAll(){
        String sql="select * from client ";
        ArrayList<Client> clients=new ArrayList<>();
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
         Connection conn = DriverManager.getConnection(
             "jdbc:mysql://localhost:8889/iageb_ism_2024", "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
         while (rs.next()) {
             //rs une ligne de la requete ==> a une agence
             Client client=new Client();
             client.setId(rs.getInt("id_client"));
             client.setNomComplet(rs.getString("nom_complet_client"));
             client.setTel(rs.getString("telephone_client"));
             client.setEmail(rs.getString("email_client"));
             clients.add(client);
         }
         rs.close();
         conn.close();
    } catch (Exception e) {
         System.out.println("Erreur de chargement du Driver");
    }
       return clients;
     }

     public Client  selectByTelephone(String telephone){
      String sql=String.format("select * from client where telephone_client like '%s' ",telephone);
      Client client=null;
  try {
      Class.forName("com.mysql.cj.jdbc.Driver");
       Connection conn = DriverManager.getConnection(
           "jdbc:mysql://localhost:8889/iageb_ism_2024", "root", "root");
      Statement stmt = conn.createStatement();
      ResultSet rs=stmt.executeQuery(sql);
       if (rs.next()) {
           //rs une ligne de la requete ==> a une agence
            client=new Client();
            client.setId(rs.getInt("id_client"));
            client.setNomComplet(rs.getString("nom_complet_client"));
            client.setTel(rs.getString("telephone_client"));
            client.setEmail(rs.getString("email_client"));
       }
       rs.close();
       conn.close();
  } catch (Exception e) {
       System.out.println("Erreur de chargement du Driver");
  }
     return client;
   }
}
