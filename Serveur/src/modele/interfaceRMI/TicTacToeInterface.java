package modele.interfaceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public interface TicTacToeInterface  extends Remote{
	// liste des fonctions pour le jeu du Tic-Tac-Toe (ou morpion en français)
	public abstract void demarrerTicTacToe() throws RemoteException;
	public abstract void redemarrerTicTacToe() throws RemoteException;
	public abstract boolean finMorpion() throws RemoteException;
	public abstract String finJeu(int gagnant) throws RemoteException;
	public int verifierColonne(ImageView a,ImageView b, ImageView c) throws RemoteException;
}
