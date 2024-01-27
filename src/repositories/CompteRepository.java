package repositories;

import java.util.ArrayList;
import java.util.List;

import entities.Cheque;
import entities.Client;
import entities.Compte;
import entities.ETypeCompte;
import entities.Epargne;

public class CompteRepository {

     private List<Compte>  tableComptes=new ArrayList<>();
     public CompteRepository() {
          initializeCompte(); 
    }
    private void initializeCompte(){
        for (int index = 1; index <= 5; index++) {
            if (index%2==0) {
                Epargne  epargne=new Epargne();
                epargne.setNumero("CPTE00"+index);
                epargne.setSolde(100000);
                epargne.setTaux(5);
                epargne.setType(ETypeCompte.Epargne);

                Client client=new Client();
                client.setNomComplet("Moussa Diop");
                client.setTel("777001010");
               //Association entre le client et le compte
                 epargne.setClient(client);
                 tableComptes.add(epargne);
            } else{
                Cheque cheque=new Cheque();
                cheque.setNumero("CPTC00"+index);
                cheque.setSolde(100000);
                cheque.setFrais(200);
                tableComptes.add(cheque);
                Client client=new Client();
                client.setNomComplet("Astou Niang");
                client.setTel("777001011");

                 cheque.setClient(client);

                cheque.setType(ETypeCompte.Cheque);
            }
        }
    }
    public List<Compte> selectAll(){
        return tableComptes;
     }
     public List<Compte> selectByClient(String tel){
        List<Compte> comptesUnClient=new ArrayList<>();
        for (Compte compte: tableComptes) {
            if(compte.getClient().getTel().compareTo(tel)==0) {
                comptesUnClient.add(compte);
            }  
        }
        return comptesUnClient;
     }

     public void insert(Compte compte){
          tableComptes.add(compte);
     }

}
