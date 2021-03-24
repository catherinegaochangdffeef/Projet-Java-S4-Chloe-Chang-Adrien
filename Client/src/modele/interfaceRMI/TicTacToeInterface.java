package modele.interfaceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public interface TicTacToeInterface  extends Remote{
	// liste des fonctions pour le jeu du Tic-Tac-Toe (ou morpion en français)
	public abstract String finJeu(int gagnant) throws RemoteException;
	public abstract void demarrerMorpion() throws RemoteException;
	//public abstract boolean finMorpion() throws RemoteException;
	public abstract boolean setImage( boolean tour) throws RemoteException;
	public abstract String afficherSigne(boolean signe) throws RemoteException;
	
	//public abstract Image setImage( boolean tour,Image imageX, Image imageO) throws RemoteException;
	public abstract boolean changerTour(boolean tour) throws RemoteException;
	public abstract boolean changerSigne(boolean signe) throws RemoteException;
	public abstract boolean finMorpion(int gagnant) throws RemoteException;
	
}
