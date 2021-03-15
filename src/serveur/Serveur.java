package serveur;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import modele.pojo.AllumettesImpl;
import modele.pojo.PenduImpl;

public class Serveur {
    public static void main(String[] args) {
        try {
            System.out.println("Demarrage du serveur en cours..");
            // Initialisation de l'adresse et du port pour le serveur.
            String hote = args[0];
            int port = Integer.parseInt(args[1]);
            LocateRegistry.createRegistry(port);
            // Implementation des differentes classes que le client va pouvoir utiliser.
            AllumettesImpl allumettesImpl = new AllumettesImpl();
            PenduImpl penduImpl = new PenduImpl();
            // Creation des routes pour que le client puisse utiliser les methodes des classes ci-dessus.
            Naming.rebind("rmi://" + hote + ":" + port + "/Allumettes", allumettesImpl);
            Naming.rebind("rmi://" + hote + ":" + port + "/Pendu", penduImpl);
            // Le serveur est pret à être utilisé.
            System.out.println("Le serveur est prêt.");
        } catch(Exception e) {
            System.out.println("Une erreur est survenue:\n" + e);
        }
    }
}
