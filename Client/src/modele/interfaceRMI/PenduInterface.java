package modele.interfaceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface PenduInterface extends Remote {
	// liste des fonctions pour le jeu du pendu
	public boolean RechCharactere(char c, String mot) throws RemoteException;
	// choisi un mot du dico
	public String ChoixMot(UUID numPartie) throws RemoteException;
	// transforme le mot choisi en liste de -)
	public String AfficheTirets(String mot) throws RemoteException;
	// affiche une lettre dans le mot à trouver
	public String AfficheLettres(char c, UUID numPartie) throws RemoteException;
	// retire un point lors d'une erreur de lettres
	public int ErreurLettre(UUID numPartie) throws RemoteException;
	public UUID creerPartie() throws RemoteException;
}
