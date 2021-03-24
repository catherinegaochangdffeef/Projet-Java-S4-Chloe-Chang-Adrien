package modele.interfaceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AllumetteInterface extends Remote{
	// liste des fonctions pour le jeu des allumettes
	
	// fonction qui retourne le nombre d'allumettes correspondant à une partie
	public int getNbAllumettes(int idPartie) throws RemoteException;
	
	// fonction qui permet d'enlever 1 ou 2 allumettes au nombre d'allumettes
	public void retirerAllumettes(int nbAllu, int idPartie) throws RemoteException;
	
	// fonction qui retourne "vrai" s'il n'y a plus d'allumettes ou "faux" s'il en reste
	public boolean partieTerminee(int idPartie) throws RemoteException;
	
	// fonction qui permet de generer un ID de partie et associe un nombre d'allumettes
	int newAllumette() throws RemoteException;
}