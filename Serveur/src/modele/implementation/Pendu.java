package modele.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import modele.interfaceRMI.PenduInterface;

public class Pendu extends UnicastRemoteObject implements PenduInterface {
	
	private static final long serialVersionUID = 1L;
	public static int NB_ERREURS_MAX=10;
    // liste des mots du pendu
    String[] dico = new String[]{"complexe","analyse","depiter","cuillere","interface"};
    String mot;
    String motcache;

	// constructeur
	public Pendu() throws RemoteException {
		super();
		this.mot = ChoixMot();
		this.motcache = AfficheTirets(mot);
	}
	
    @Override
    public boolean RechCharactere(char c) {
        return (mot.indexOf(c) != -1);
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
               if (mot.charAt(position) == c && motcache.charAt(position) != c) { 

                   motcache = motcache.replaceAll("_ ", "_ "); 
                   String motcache2; 
                   motcache2 = motcache.substring(0, position) 
                           + c 
                           + motcache.substring(position 
                                             + 1); 
                   motcache2 = motcache2.replaceAll("_", "_ "); 
                   this.motcache = motcache2; 
               } 
           } 
		return motcache;
	}
	@Override
	public int ErreurLettre(int nbErreur) {
		return nbErreur-1;
	}
	
	
	@Override
	public String toString() {
		return mot +" " +motcache;
	}
}