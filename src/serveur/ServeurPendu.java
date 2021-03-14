package serveur;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import modele.pojo.PenduImpl;

public class ServeurPendu {
    public static void lancementServeur(String[] args) {
        try {
            System.out.println("Demarrage du serveur du pendu ..");

            // Initialisation de l'adresse et du port pour le serveur.
            String hote = args[0];
            int port = Integer.parseInt(args[1]);
            LocateRegistry.createRegistry(port);
            // Implementation des differentes classes que le client va pouvoir utiliser.
            PenduImpl pendu = new PenduImpl();
    
            // Creation des routes pour que le client puisse utiliser les methodes des classes ci-dessus.
            Naming.rebind("rmi://" + hote + ":" + port + "/Pendu", pendu);
            // Le serveur est pret a etre utilise.
            System.out.println("Serveur du pendu pret a etre utilise.");
        } catch(Exception e) {
            System.out.println("Une erreur est survenue:\n" + e);
        }
    }
}
