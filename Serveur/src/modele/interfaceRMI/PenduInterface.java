package modele.interfaceRMI;

import java.rmi.Remote;

public interface PenduInterface extends Remote {
	// liste des fonctions pour le jeu du pendu
	public boolean RechCharactere(char c);
}
