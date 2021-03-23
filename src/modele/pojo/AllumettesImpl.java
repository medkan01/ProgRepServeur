package modele.pojo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import modele.interfaceRMI.*;
import java.util.UUID;

public class AllumettesImpl extends UnicastRemoteObject implements InterfaceAllumettes {
	
	private Hashtable<UUID, PartieAllumettes> listeParties = new Hashtable<UUID, PartieAllumettes>();

	public AllumettesImpl() throws RemoteException {
		super();
	}
	
	@Override
	public UUID creerPartie(String mode) {
		UUID uuid = UUID.randomUUID();
		if (mode.equals("solo")) {
			listeParties.put(uuid, new PartieAllumettes());
			listeParties.get(uuid).setNomJoueur2("ordinateur");
		}
		else {
			for(Map.Entry entry: listeParties.entrySet()) {
	            PartieAllumettes pa = (PartieAllumettes) entry.getValue();
	            if (pa.getModeJeu().equals(mode) && pa.getNbJoueurs() != 2)
	            	return (UUID) entry.getKey();
	        }
			listeParties.put(uuid, new PartieAllumettes());
		}
		
		return uuid;
	}
	
	@Override
	public void initialise(UUID uuid, String mode, String nomJoueur) {
		Random rand = new Random();
		int n = 0;
        while ( n<3 || (n%2==0) ) {
        	n = rand.nextInt(22);
        }
        
        listeParties.get(uuid).setNbAllumettes(n);
        listeParties.get(uuid).setTabScore(new int[] {0,0});
        listeParties.get(uuid).setTour(rand.nextInt(2));
        listeParties.get(uuid).setModeJeu(mode);
        listeParties.get(uuid).setNbJoueurs(1);
        listeParties.get(uuid).setNomJoueur1(nomJoueur);
	}
	
	@Override
	public void action(UUID uuid, int nbAllChoisies) {
		listeParties.get(uuid).setNbAllumettes(listeParties.get(uuid).getNbAllumettes()-nbAllChoisies);
		listeParties.get(uuid).getTabScore()[listeParties.get(uuid).getTour()%2] += nbAllChoisies;
		listeParties.get(uuid).setTour(listeParties.get(uuid).getTour()+1);
	}
	
	@Override
	public void rejoindrePartie(UUID uuid, String nomJoueur) {
		listeParties.get(uuid).setNbJoueurs(listeParties.get(uuid).getNbJoueurs()+1);
		listeParties.get(uuid).setNomJoueur2(nomJoueur);
	}
	
	@Override
	public int maxAllumettes(UUID uuid) {
		return Math.min(2, listeParties.get(uuid).getNbAllumettes());
	}
	
	@Override
	public void finPartie(UUID uuid) {
		listeParties.get(uuid).setNbJoueurs(listeParties.get(uuid).getNbJoueurs()-1);
		
		if (listeParties.get(uuid).getNbJoueurs() == 0)
			listeParties.remove(uuid);
	}
	
	@Override
	public String nomGagnant(UUID uuid) {
		PartieAllumettes pa = listeParties.get(uuid);
		
		return (pa.getTabScore()[0]%2 == 0 ? pa.getNomJoueur2() : pa.getNomJoueur1());
	}
	
	@Override
	public int scoreGagnant(UUID uuid) {
		PartieAllumettes pa = listeParties.get(uuid);
		
		return (pa.getTabScore()[0]%2 == 0 ? pa.getTabScore()[1] : pa.getTabScore()[0]);
	}
	
	@Override
	public String nomJoueurTour(UUID uuid) {
		PartieAllumettes pa = listeParties.get(uuid);
		
		return (pa.getTour()%2 == 0 ? pa.getNomJoueur1() : pa.getNomJoueur2());
	}
	
	@Override
	public int[] getTabScore(UUID uuid) throws RemoteException {
		return listeParties.get(uuid).getTabScore();
	}
	
	@Override
	public int getNbAllumettes(UUID uuid) throws RemoteException {
		return listeParties.get(uuid).getNbAllumettes();
	}
	
	@Override
	public int getTour(UUID uuid) throws RemoteException {
		return listeParties.get(uuid).getTour();
	}
	
	@Override
	public int getNbJoueurs(UUID uuid) throws RemoteException {
		return listeParties.get(uuid).getNbJoueurs();
	}
	
	@Override
	public String getMode(UUID uuid) throws RemoteException {
		return listeParties.get(uuid).getModeJeu();
	}
	
	@Override
	public int coupIA(UUID uuid) {
		Random random = new Random();
		
		return random.nextInt(maxAllumettes(uuid))+1;
	}

	@Override
	public String getNJ1(UUID uuid) throws RemoteException {
		return listeParties.get(uuid).getNomJoueur1();
	}

	@Override
	public String getNJ2(UUID uuid) throws RemoteException {
		return listeParties.get(uuid).getNomJoueur2();
	}

	@Override
	public ArrayList<String> getAllRetiree(UUID uuid) throws RemoteException {
		return listeParties.get(uuid).getAllumettesRetiree();
	}

	@Override
	public void setAllRetiree(UUID uuid, ArrayList<String> tab) throws RemoteException {
		listeParties.get(uuid).setAllumettesRetiree(tab);
	}

	@Override
	public Hashtable<UUID, String> getJoueursParties() throws RemoteException {
		Hashtable<UUID, String> tab = new Hashtable<UUID, String>();
		
		listeParties.forEach((k,v) -> {
			if (v.getModeJeu().equals("duo") && v.getNbJoueurs() == 1)
				tab.put(k, v.getNomJoueur1());
		});
		
		return tab;
	}
}
