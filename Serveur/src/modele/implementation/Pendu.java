package modele.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.UUID;

import modele.interfaceRMI.PenduInterface;

public class Pendu extends UnicastRemoteObject implements PenduInterface {

	private static final long serialVersionUID = 1L;
	public int nb_erreur=10;
	// liste des mots du pendu
	String[] dico = new String[]{"complexe","analyse","depiter","cuillere","interface",};
	String mot;
	String motcache;

	// constructeur
	public Pendu() throws RemoteException {
		super();
		this.nb_erreur = 10;
		this.mot = ChoixMot();
		this.motcache = AfficheTirets(mot);
	}
	
	@Override
	public UUID creerPartie() throws RemoteException {
		Pendu p1 = new Pendu();
		return UUID.randomUUID();
	}

	@Override
	public boolean RechCharactere(char c) {
		// on renvoie vrai si la lettre est trouvée, sinon faux
		System.out.println(mot.indexOf(c) != -1);
		return  (mot.indexOf(c) != -1);
	}

	@Override
	public String ChoixMot() {
		int index = new Random().nextInt(dico.length);
		return dico[index].toUpperCase();
	}
	@Override
	public String AfficheTirets(String mot) {
		return mot.replaceAll("[A-Z]", "_ ");
	}
	@Override
	public String SuppressionLettreListe(String listeLettres, char l) {
		// a voir comment faire
		return null;
	}

	@Override
	public String AfficheLettres(char c) {
		for (int position = 0; position < mot.length(); position++) { 
			if (mot.charAt(position) == c 
					&& motcache.charAt(position) 
					!= c) { 

				motcache = motcache.replaceAll("_ ", "_"); 
				String word2; 
				word2 = motcache.substring(0, position) 
						+ c 
						+ motcache.substring(position 
								+ 1); 
				word2 = word2.replaceAll("_", "_ "); 
				motcache = word2; 
			} 
		} 
		return motcache;
	}

	@Override
	public int ErreurLettre() throws RemoteException {
		this.nb_erreur = nb_erreur-1;
		if (nb_erreur < 0) {
			FinJeu(false);
		}
		return nb_erreur;
	}
	private void FinJeu(boolean b) {
		if (b == true) {
			// on a perdu
		}
		else {
			// bravo, vous avez gagné
		}
		
	}

	@Override
	public String toString() {
		return "Le mot est : " + mot + " sous forme cachée " +motcache + " nb erreurs : " + nb_erreur;
	}
}