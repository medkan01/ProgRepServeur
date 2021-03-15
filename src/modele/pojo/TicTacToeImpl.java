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
            coord[x][y] = "O";
        } else if(joueur == 2) {
            coord[x][y] = "X";
        } else {
            throw new Exception("Joueur inconnu.");
        }

        return coord;
    }
}
