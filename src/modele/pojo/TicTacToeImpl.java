package modele.pojo;

import java.rmi.RemoteException;

import modele.interfaceRMI.InterfaceTicTacToe;

public class TicTacToeImpl implements InterfaceTicTacToe {

    public TicTacToeImpl() throws RemoteException {
        super();
    }

    public String[][] initialiserCoord(int size) throws Exception {
        if(size == 0) {
            throw new Exception("La taille est nulle.");
        } else if (size < 0) {
            throw new Exception("La taille est négative.");
        } else {
            String [][] coord = new String[size][size];

            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++){
                    coord[i][j] = "";
                }
            }

            return coord;
        }
    }

    public String[][] remplirCase(int x, int y, int joueur, String[][] coord) throws Exception {
        if(joueur == 1) {
            coord[x][y] = "X";
        } else if(joueur == 2) {
            coord[x][y] = "O";
        } else {
            throw new Exception("Joueur inconnu.");
        }

        return coord;
    }
	
	private Vue v;
	private int joueur;
	private int nbCase;
	private char[][] tab;
	private String message;

	// le constructeur par defaut
	public TicTacToeImpl() {
		this.tab = new char[3][3];
		this.nbCase = 9;
		this.joueur = 1;
	}
	
	// initialisation de la reference de la classe vue
	public void vue(Vue v) {
		this.v = v;
	}

	//setters et getters
	public int getJoueur() {
		return joueur;
	}

	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}

	public int getNbCase() {
		return nbCase;
	}

	public void setNbCase(int nbCase) {
		this.nbCase = nbCase;
	}

	public char[][] getTab() {
		return tab;
	}

	public void setTab(char[][] tab) {
		this.tab = tab;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// fonction pour modifier le tableau
	public void Position(int x, int y) {
		
		if(getNbCase() > 0){
			// marquer X ou O en fonction du joueur
			if(joueur%2 != 0) 
				tab[x][y] = 'X';
			else 
				tab[x][y] = 'O';
			
			// reduire le nombre de case gauche
			setMovesCount(--movesCount);
			
			// Vérifier si un joueur a gagné ou si le jeu donne une égalité
			// envoyer un message pour notifier chaque cas
			if(gagnant(x, y)) {
				setMessage("Joueur " + joueur + " a gagné!");
				v.gagnant(x, y, tab[x][y], getMessage());
			}
			else if(getNbCase()==0) {
				setMessage("Pas de gagnant! Match nul");
				v.gagnant(x, y, tab[x][y], getMessage());
			}
			else {
				if(joueur%2 != 0) {
					// Changer le joueur
					setJoueur(2);
					setMessage("'O':  Joueur " +getJoueur());
				}
				else {
					setJoueur(1);
					setMessage("'X':  Joueur " +getJoueur());

				}
				// modification du tableau pour le joueur suivant
				v.modifier(x, y, tab[x][y], getMessage());
			}
			
		}
		
	}
	
	// fonction pour vérifier s'il y a un gagnant
	public boolean gagnant(int x, int y) {
		int compteLigne = 0;
		int compteColonne = 0;
		int compteDiagGauche = 0;
		int compteDiagDroit = 0;
		char symbole;
		if(getJoueur() %2 !=0)
			symbole = 'X';
		else
			symbole = 'O';
		
		for(int i=0; i<tab.length;i++) {
			if(tab[x][i] == symbole)
				compteLigne++;
			if(tab[i][y] == symbole)
				compteColonne++;
			if(tab[i][i] == symbole)
				compteDiagDroit++;
			if(tab[tab.length-1-i][i] == symbole)
				compteDiagGauche++;	
		}
		
		if(compteColonne==tab.length || compteLigne==tab.length 
		   || compteDiagGauche == tab.length || compteDiagDroit == tab.length)
			return true;
		
		return false;
	}
}
