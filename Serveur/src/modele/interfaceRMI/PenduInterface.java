package modele.interfaceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface PenduInterface extends Remote {
	// liste des fonctions pour le jeu du pendu
	public boolean RechCharactere(char c, String mot) throws RemoteException;
	// choisi un mot du dico
	public String ChoixMot(UUID numPartie) throws RemoteException;
	// transforme le mot choisi en liste de -
	public String AfficheTirets(String mot) throws RemoteException;
	// affiche une lettre dans le mot � trouver
	public String AfficheLettres(char c, UUID numPartie) throws RemoteException;
	// retire un point lors d'une erreur de lettres
	public int ErreurLettre(UUID id) throws RemoteException;
	// on cree une partie avec le choix du theme
	public UUID creerPartie(int choix) throws RemoteException;
	// permet de retirer la partie de la HashMap lorsque l'on quitte
	public void Effacer(UUID numPartie) throws RemoteException;
}
