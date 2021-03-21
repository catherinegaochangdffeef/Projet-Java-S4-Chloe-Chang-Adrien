package modele.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.interfaceRMI.TicTacToeInterface;

public class TicTacToeImpl extends UnicastRemoteObject implements TicTacToeInterface{

	public TicTacToeImpl()throws RemoteException  {
		super();
	
	}
	public String finJeu(int gagnant) throws RemoteException{
		String signe=null;
		if(gagnant ==0) signe="O";
		else signe="X";
		return signe;
	}

}
