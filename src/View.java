import java.util.List;
import java.util.Scanner;

import entities.Agence;
import entities.Cheque;
import entities.Client;
import entities.Compte;
import entities.ETypeCompte;
import entities.Epargne;
import services.AgenceService;
import services.ClientService;
import services.CompteService;

public class View {
   

    public static void main(String[] args) throws Exception {
        //Definir une dependance 
         //La couche vue depend de la couche service
         //Creer un objet de type service dans la classe View
           AgenceService agenceService=new AgenceService();
           CompteService compteService=new CompteService();
           ClientService clientService=new ClientService();
           Scanner scanner=new Scanner(System.in);
           int choix;
           do{
              System.out.println("1-Ajouter une Agence");
              System.out.println("2-Lister les  Agences");
              System.out.println("3-Lister les  Agences Par numero");
              System.out.println("4-Lister les  Comptes");
              System.out.println("5-Lister les  Comptes d'un client");
              System.out.println("6-Creer un Client");
              System.out.println("7-Lister les clients");
              System.out.println("8-Quitter");
              choix=scanner.nextInt();
              scanner.nextLine();
              switch (choix) {
                case 1:
                       Agence agence=new Agence();
                         System.out.println("Entrer le numero");
                        agence.setNumero(scanner.nextLine());
                        System.out.println("Entrer le Adresse");
                        agence.setAdresse(scanner.nextLine());
                        agenceService.addAgence(agence);
                    break;
                case 2:
                  List<Agence>  listerAgences = agenceService.listerAgences();
                    for (Agence ag: listerAgences) {
                       System.out.println("Numero: "+ ag.getNumero());
                       System.out.println("Adresse: "+ ag.getAdresse());
                    }
                    break;
                case 3:
                      System.out.println("Entrer le numero");
                       String numero= scanner.nextLine();
                      listerAgences = agenceService.listerAgences(numero);
                      for (Agence ag: listerAgences) {
                        System.out.println("Numero: "+ ag.getNumero());
                        System.out.println("Adresse: "+ ag.getAdresse());
                      }
                    break;

                    case 4:
                       List<Compte>  comptesList=compteService.listerCompte();
                      for (Compte cp: comptesList) {
                          System.out.println("Type: "+cp.getType());
                          System.out.println("Numero: "+ cp.getNumero());
                          System.out.println("Solde: "+ cp.getSolde());
                         if (cp.getType()==ETypeCompte.Cheque) {
                              //Conversion le Compte en Cheque  
                               Cheque cmpteCheque=(Cheque)cp;
                               System.out.println("Frais: "+ cmpteCheque.getFrais());
                            } else {
                                Epargne ctEpargne=(Epargne)cp;
                                System.out.println("Taux: "+ ctEpargne.getTaux());
                           }
                          System.out.println("=========================");
                      }
                    break;
                    case 5:
                         System.out.println("Entrer le numero de Telephone");
                         String tel= scanner.nextLine();
                        comptesList=compteService.listerCompteUnClient(tel);
                        for (Compte cp: comptesList) {
                            System.out.println("Numero: "+ cp.getNumero());
                            System.out.println("Solde: "+ cp.getSolde());
                            System.out.println("===============");
                        }
                    break;

                    case 6:
                         Client client=new Client();
                         System.out.println("Entrer le Nom et Prenom");
                         client.setNomComplet(scanner.nextLine());
                         System.out.println("Entrer le numero de Telephone");
                         client.setTel(scanner.nextLine());
                         System.out.println("Entrer Email");
                         client.setEmail(scanner.nextLine());
                         clientService.addClient(client);
                    break;
                    case 7:
                    List<Client> clients = clientService.listerClients();
                    for (Client cl: clients) {
                      System.out.println("Nom et Prenom: "+ cl.getNomComplet());
                      System.out.println("Telephone : "+ cl.getTel());
                      System.out.println("Email : "+ cl.getEmail());
                      System.out.println("==================================");
                  }
                  break;
                default:
                    break;
              }
           }while(choix!=87);
       
    }
}
