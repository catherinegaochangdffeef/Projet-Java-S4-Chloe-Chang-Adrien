package modele.interfaceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToeInterface  extends Remote{
	// liste des fonctions pour le jeu du Tic-Tac-Toe (ou morpion en français)
	public abstract void redemarrerMorpion() throws RemoteException;
}
