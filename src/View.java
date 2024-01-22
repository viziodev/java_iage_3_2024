import entities.Agence;

public class View {
    public static void main(String[] args) throws Exception {
         //Declarer un objet de type Agence , l'objet est null
           Agence ag;
          //Creer objet , appel au constructeur
           ag=new Agence();
           //ag. => correspond a l'interface de l'objet ag, tout attribut
           // ou methodes publics est visible sur l'interface
        
        System.out.println("Hello, World!");
    }
}
