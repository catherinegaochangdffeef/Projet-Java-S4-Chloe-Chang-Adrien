package modele.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.interfaceRMI.TicTacToeInterface;

public class TicTacToeImpl extends UnicastRemoteObject implements TicTacToeInterface{

	
	private boolean finMorpion;
	private boolean tour;
	public Image imageX;
	public Image imageO; 

	public TicTacToeImpl()throws RemoteException  {
		super();
		this.demarrerMorpion();
	}
	public void demarrerMorpion() throws RemoteException{ 
		this.finMorpion = false;
	}
	
	/*public boolean finMorpion() throws RemoteException{ 
		this.finMorpion=true;
		this.finMorpion= false;
		return false;
	}
	*/
	public boolean setImage(boolean tour) throws RemoteException{
		if(tour) return true;
		else return false;
	}
	
	/*public Image setImage(boolean tour,Image imageX,Image imageO) throws RemoteException{
		if(tour) return imageX;
		else return imageO;
	}*/
	// la fonction qui permet d'afficher X quand signe est vraie, O quand signe est fausse
	public String afficherSigne(boolean signe) throws RemoteException{
		if(signe) return ("X");
		else return("O");
	}
	public boolean finMorpion(int gagnant) throws RemoteException{
		if((finJeu(gagnant)!=null)) {
			return true;
		}
		return false;
	}
	public boolean changerTour(boolean tour) throws RemoteException{
		return (!tour);
	}
	public boolean changerSigne(boolean signe) throws RemoteException{
		return (!signe);
	}
	
	public String finJeu(int gagnant) throws RemoteException{
		String signe=null;
		if(gagnant ==0) signe="O";
		else signe="X";
		this.finMorpion=true;
		return signe;
	}

}
