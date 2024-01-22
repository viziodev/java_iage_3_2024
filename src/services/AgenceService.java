package services;

import java.util.List;

import entities.Agence;
import repositories.AgenceRepository;

public class AgenceService {
    //Definir tous les Use case(fonction) relatif a agence

    //Definir une dependance 
      //La couche service depend de la couche repository
       //Creer un objet de type repository dans la classe Service
         private AgenceRepository agenceRepository=new AgenceRepository();
        //Ajouter une agence dans une liste a partir du Repo
        //inserer une agence dans la table agence de la BD a partir du Repo
        public void addAgence(Agence agence){
              agenceRepository.insert(agence);
        }
        //Lister les Agence de la Liste a partir du Repo
        //Selectionner les  agence dans la table agence de la BD a partir du Repo

        public List<Agence> listerAgences(){
            return agenceRepository.selectAll();
        }
        //Lister les Agence de la Liste qui le numero passe en parametre
        //Selectionner les  agence dans la table agence de la BD dont le numero es egal au numero 
        //passee en parametre

        public List<Agence> listerAgences(String numero){
            return agenceRepository.selectByNumero(numero);
        }



}
