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
	String[] dicoAlea = new String[]{"complexe","analyse","arc-en-ciel","depiter","cuillere","nuage",
			"analphabete","granite","marsouin","fade","orchidee","subduction","gaz","lait",
			"table","algorithme","biscuit","parfum","volcan","arbre","sequoia","film",
			"australopitheque","tyranosaure","haie","pique-nique","spinosaurus","licorne","archeopteryx","ornythorinque",
			"acupuncture","appartenir","mot","boeuf","heritage","personne","constructeur","interface",
			"trop","subliminal","vingt","note","gentil","etymologie","eclispe","logiciel","mots-cles"
	};
	String[] dicoMinecraft = {
			"quartz", "pioche", "epee", "pelle","sorciere", "hache", "houe","arc","arbalete", "diamant", "or","netherite",
			"lapis-lazuli","boule de slime", "emeraude", "fer", "charbon", "obsidienne", "nether", "verrue du nether", "glowstone", 
			"redstone", "donjon", "creeper", "enderman", "ghast", "trident", "établi", "coffre", "mule","coffre","escalier","verre",
			"bois de chene","squelette", "bois de sapin","zombie", "bois de bouleau", "sable des ames","eponge", "gardien", 
			"monument sous-marin", "enchantement", "potion", "ragout de lapin","carte", "chair putrefiee",
			"shulker", "ender dragon","wither","diorite","granite","andesite","laine", "argile","villageois","illageois","minecart","mineshaft","elytres"
	};
	String[] dicoGeole = {"geologie","gabbro","granite","rhyolite","subduction","fosse oceanique","montagne"
			,"quartz","peridot","argile","manteau","lithosphere","asthenosphere","noyau","dorsale","volcan",
			"roche","metamorphique","sédiments","basalte","seisme","schistes","calcaire","poreux","faille"
	};
	String[] dicoEspace = {"planete","satellite","trou noir","espace","vide,","astre","galaxie","nebuleuse",
			"comete","etoile","asteroide","orbite","constellation","mercure","gravite", "venus", "terre", "mars",
			"jupiter", "saturne", "uranus", "neptune", "pluton", "soleil", "lune","trou de ver","eclipse"
	};
	HashMap<UUID, Pendu> tableauPendu = new HashMap<UUID,Pendu>();
	int nb_erreur;
	String mot;
	String motcache;
	// on lance la clock pour générer un nombre aléatoire
	private static Random random = new Random(); 

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
	public UUID creerPartie(int choix) throws RemoteException {
		// on genere un id de parties, on choisi un mot et on stocke toute les données dans un tableau qui a pour cle l'id
		UUID id = UUID.randomUUID();
		int index;
		String mot ="";
		switch (choix) {
		case 0 : {
			index = random.nextInt(dicoAlea.length);
			mot = dicoAlea[index].toUpperCase();
			break;
		}
		case 1 : {
			index = random.nextInt(dicoMinecraft.length);
			mot = dicoMinecraft[index].toUpperCase();
			break;
		}
		case 2 : {
			index = random.nextInt(dicoGeole.length);
			mot = dicoGeole[index].toUpperCase();
			break;
		}
		case 3 :{
			index = random.nextInt(dicoEspace.length);
			mot = dicoEspace[index].toUpperCase();
			break;
		}
		}

		tableauPendu.put(id, new Pendu(mot, AfficheTirets(mot), 11));
		return id;
	}

	@Override
	public boolean RechCharactere(char c, String mot) {
		// on renvoie vrai si la lettre est trouvée, sinon faux
		return  (mot.indexOf(c) != -1);
	}

	@Override
	public String ChoixMot(UUID id) {
		// on recupere l'instance du pendu associé au joueur afin de lui retourner le mot
		Pendu p = tableauPendu.get(id);
		return p.getMot();
	}
	@Override
	public String AfficheTirets(String mot) {
		return mot.replaceAll("[A-Z]", "_ ");
	}

	@Override
	public String AfficheLettres(char c, UUID id) {
		Pendu p = tableauPendu.get(id);
		mot = p.getMot();
		motcache = p.getMotcache();

		for (int position = 0; position < mot.length(); position++) { 
			if (mot.charAt(position) == c) { 
				// on retire tout les espaces pour faire correspondre mot et motcaché (mot caché ya des espaces en plus)
				motcache = motcache.replaceAll("_ ", "_"); 
				String mot2; 				
				// on prend tout ce qui est avant le mot trouvé, on affiche la lettre et on affiche tout ce qui vient après + 1
				mot2 = motcache.substring(0, position) + c + motcache.substring(position + 1); 
				// on remplace les _ par des "_ " pour rajouter des espaces
				mot2 = mot2.replaceAll("_", "_ ");
				motcache = mot2; 
			} 
		} 

		p.setMotcache(motcache);
		return motcache;
	}

	@Override
	public int ErreurLettre(UUID id) throws RemoteException {
		// pareil que choixMot, mais la on decremente le nombre d'erreurs
		Pendu p =tableauPendu.get(id);
		nb_erreur = p.getNb_erreur()-1;
		p.setNb_erreur(nb_erreur);
		return nb_erreur;
	}

	@Override
	public void Effacer(UUID id) throws RemoteException {
		// permet de vider la HashMap au fur et a mesure que les gens quittent et recommencent des parties
		tableauPendu.remove(id);
	}

	// getter et setters
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
		return "Le mot est : " + mot + " sous forme cachée " +motcache + " nb erreurs : " + nb_erreur + "\n";
	}
}