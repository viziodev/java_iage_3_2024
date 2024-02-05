package repositories;
import java.util.ArrayList;


import entities.Client;

public class ClientRepository {
    ArrayList<Client> tableClient=new ArrayList<>();

    public void insert(Client client){
        tableClient.add(client);
     }


    public ArrayList<Client>  selectAll(){
      return tableClient;
     }
}
