import java.util.List;
import java.util.Scanner;

import entities.Agence;
import services.AgenceService;

public class View {
   

    public static void main(String[] args) throws Exception {
        //Definir une dependance 
         //La couche vue depend de la couche service
         //Creer un objet de type service dans la classe View
           AgenceService agenceService=new AgenceService();
           Scanner scanner=new Scanner(System.in);
           int choix;
           do{
              System.out.println("1-Ajouter une Agence");
              System.out.println("2-Lister les  Agences");
              System.out.println("3-Lister les  Agences Par numero");
              System.out.println("4-Quitter");
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
              
                default:
                    break;
              }
           }while(choix!=4);
       
    }
}
