package modele.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.interfaceRMI.TicTacToeInterface;

public class TicTacToeImpl extends UnicastRemoteObject implements TicTacToeInterface{
private boolean finMorpion;
Image imageO ;
Image imageX ;

	public Image getImageO() {
	return imageO;
}

public void setImageO(Image imageO) {
	this.imageO = imageO;
}

public Image getImageX() {
	return imageX;
}

public void setImageX(Image imageX) {
	this.imageX = imageX;
}

	public TicTacToeImpl()throws RemoteException  {
		super();
		this.demarrerTicTacToe();
	}

	public void demarrerTicTacToe() throws RemoteException{
		this.finMorpion=false;
	}
	public void redemarrerTicTacToe() throws RemoteException{
		this.demarrerTicTacToe();
	}
	
	public boolean finMorpion()throws RemoteException{
		this.finMorpion = true;
		if(signe != null) return true;
		
		return false;
		
	}
	String signe=null;
	public String finJeu(int gagnant) throws RemoteException{
		
		if(gagnant ==0) signe="O";
		else signe="X";
		this.finMorpion=true;
		return signe;
	}
	public int verifierColonne(ImageView a,ImageView b, ImageView c) {
		
	if(a!=null && (a.getImage() == b.getImage()) && c!=null && (b.getImage() == c.getImage())){
        
		if(a.getImage() == imageO) return 0;
        else if(a.getImage() == imageX) return 1;
    } else {
        return -1;
    }
    return -1;
	}
}
