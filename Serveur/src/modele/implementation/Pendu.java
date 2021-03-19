package modele.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import modele.interfaceRMI.PenduInterface;

public class Pendu extends UnicastRemoteObject implements PenduInterface {

	private static final long serialVersionUID = 1L;
	
	// liste des mots du pendu
	String[] dico = new String[]{"complexe","analyse","depiter","cuillere","nuage",
			"analphabete","granite","rhyolite","gabbro","subduction","gaz","lait",""
					+ "table","algorithme","biscuit","parfum","volcan","arbre","séquoia","film",
					"australopitheque","tyranosaure","spinosaurus","licorne","archeopteryx","ornythorinque",
					"globuline","appartenir","mot","boeuf","heritage","personne","constructeur","interface",
					"trop","subliminal","vingt","note","gentil"};
	HashMap<UUID, Pendu> tableauPendu = new HashMap<UUID,Pendu>();
	int nb_erreur;
	String mot;
	String motcache;
	
	// constructeurs
	public Pendu() throws RemoteException {
		super();
	}
	
	public Pendu(String mot, String motcache, int i) throws RemoteException {
		super();
		this.mot = mot;
		this.motcache = motcache;
		this.nb_erreur=i;
	}

	@Override
	public UUID creerPartie() throws RemoteException {
		UUID id = UUID.randomUUID();
		int index = new Random().nextInt(dico.length);
		String mot = dico[index].toUpperCase();
		tableauPendu.put(id, new Pendu(mot, AfficheTirets(mot), 10));
		return id;
	}

	@Override
	public boolean RechCharactere(char c, String mot) {
		// on renvoie vrai si la lettre est trouvée, sinon faux
		return  (mot.indexOf(c) != -1);
	}

	@Override
	public String ChoixMot(UUID id) {
		System.out.println(tableauPendu.get(id));
		Pendu bob = tableauPendu.get(id);
		return bob.getMot();
	}
	@Override
	public String AfficheTirets(String mot) {
		return mot.replaceAll("[A-Z]", "_ ");
	}

	@Override
	public String AfficheLettres(char c, UUID id) {
		Pendu bob = tableauPendu.get(id);
		mot = bob.getMot();
		motcache = bob.getMotcache();
		for (int position = 0; position < mot.length(); position++) { 
			if (mot.charAt(position) == c) { 

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
		System.out.println(motcache);
		bob.setMotcache(motcache);
		return motcache;
	}

	@Override
	public int ErreurLettre(UUID id) throws RemoteException {
		Pendu p =tableauPendu.get(id);
		nb_erreur = p.getNb_erreur()-1;
		p.setNb_erreur(nb_erreur);
		return nb_erreur;
	}

	public int getNb_erreur() {
		return nb_erreur;
	}

	public void setNb_erreur(int nb_erreur) {
		this.nb_erreur = nb_erreur;
	}

	public String getMot() {
		return mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	public String getMotcache() {
		return motcache;
	}

	public void setMotcache(String motcache) {
		this.motcache = motcache;
	}

	@Override
	public String toString() {
		return "Le mot est : " + mot + " sous forme cachée " +motcache + " nb erreurs : " + nb_erreur;
	}
}