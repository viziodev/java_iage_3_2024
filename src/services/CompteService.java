package services;

import java.util.List;

import entities.Compte;
import repositories.CompteRepository;

public class CompteService {
    
    private CompteRepository compteRepository =new CompteRepository();
    public  List<Compte> listerCompte(){
           return compteRepository.selectAll();
    }
    public  List<Compte> listerCompteUnClient(String tel){
        return compteRepository.selectByClient(tel);
   }

    public  void ajouterCompte(Compte compte){
        compteRepository.insert(compte);
   }
}
