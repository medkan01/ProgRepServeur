package modele.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class PartieAllumettes implements Serializable {

	private int[] tabScore = new int[2];
	private int nbAllumettes;
	private int tour;
	private String modeJeu;
	private int nbJoueurs = 0;
	private String nomJoueur1;
	private String nomJoueur2;
	private ArrayList<String> allumettesRetiree = new ArrayList<String>();
	
	public int[] getTabScore() {
		return tabScore;
	}
	
	public void setTabScore(int[] tabScore) {
		this.tabScore = tabScore;
	}
	
	public int getNbAllumettes() {
		return nbAllumettes;
	}
	
	public void setNbAllumettes(int nbAllumettes) {
		this.nbAllumettes = nbAllumettes;
	}
	
	public int getTour() {
		return tour;
	}
	
	public void setTour(int tour) {
		this.tour = tour;
	}
	
	public String getModeJeu() {
		return modeJeu;
	}
	
	public void setModeJeu(String modeJeu) {
		this.modeJeu = modeJeu;
	}
	
	public int getNbJoueurs() {
		return nbJoueurs;
	}
	
	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}
	
	public String getNomJoueur1() {
		return nomJoueur1;
	}
	
	public void setNomJoueur1(String nomJoueur1) {
		this.nomJoueur1 = nomJoueur1;
	}
	
	public String getNomJoueur2() {
		return nomJoueur2;
	}
	
	public void setNomJoueur2(String nomJoueur2) {
		this.nomJoueur2 = nomJoueur2;
	}
	
	public ArrayList<String> getAllumettesRetiree() {
		return allumettesRetiree;
	}
	
	public void setAllumettesRetiree(ArrayList<String> allumettesRetiree) {
		this.allumettesRetiree = allumettesRetiree;
	}
}
