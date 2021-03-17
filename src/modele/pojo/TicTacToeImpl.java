package modele.pojo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import modele.interfaceRMI.InterfaceTicTacToe;

public class TicTacToeImpl extends UnicastRemoteObject implements InterfaceTicTacToe {

    private static final long serialVersionUID = 1L;

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
        if(verifierCase(x, y, coord)){
            if(joueur == 0) {
                coord[x][y] = "O";
            } else if(joueur == 1) {
                coord[x][y] = "X";
            } else {
                throw new Exception("Joueur inconnu.");
            }
        }
        return coord;
    }

    public boolean verifierCase(int x, int y, String[][] coord) throws RemoteException{
        return coord[x][y].isEmpty();
    }

    public boolean verifierLignes(int x, String[][] coord, String symbole) throws RemoteException {
        boolean ligneOk = false;
        for(int i = 0; i < coord.length; i++) {
            if(coord[x][i].equals(symbole)){
                ligneOk = true;
            } else {
                ligneOk = false;
                break;
            }
        }
        if(ligneOk){
            return true;
        }
        return false;
    }

    public boolean verifierColones(int y, String[][] coord, String symbole) throws RemoteException{
        boolean coloneOk = false;
        for(int i = 0; i < coord.length; i++) {
            if(coord[i][y].equals(symbole)){
                coloneOk = true;
            } else {
                coloneOk = false;
                break;
            }
        }
        if(coloneOk){
            return true;
        }

        return false;
    }

    public boolean verifierDiagonale1(String[][] coord, String symbole) {
        boolean diagonaleOk = false;;
        for(int i = 0; i < coord.length; i++) {
            diagonaleOk = false;
            if(coord[i][i].equals(symbole)){
                diagonaleOk = true;
            } else {
                diagonaleOk = false;
                break;
            }
        }
        if(diagonaleOk){
            return true;
        }
        return false;
    }

    public boolean verifierDiagonale2(String[][] coord, String symbole) {
        boolean diagonaleOk = false;
        for(int i = 0; i < coord.length; i++) {
            diagonaleOk = false;
            if(coord[i][coord.length - 1 - i].equals(symbole)){
                diagonaleOk = true;
            } else {
                diagonaleOk = false;
                break;
            }
        }
        if(diagonaleOk){
            return true;
        }
        return false;
    }

    public boolean verifierDiagonales(String[][] coord, String symbole){
        return verifierDiagonale1(coord, symbole) || verifierDiagonale2(coord, symbole);
    }

    public boolean verifierTour(int x, int y, String[][] coord, String symbole) throws RemoteException{
        return verifierLignes(x, coord, symbole) || verifierColones(y, coord, symbole) || verifierDiagonales(coord, symbole);
    }

    public boolean grilleRemplie(String[][] coord) throws RemoteException{
        boolean remplieOk = true;
        for(int i = 0; i < coord.length; i++){
            for(int j = 0; j < coord.length; j++){
                if(coord[i][j].isEmpty()){
                    remplieOk = false;
                    break;
                }
            }
            if(!remplieOk){
                break;
            }
        }
        return remplieOk;
    }
}
