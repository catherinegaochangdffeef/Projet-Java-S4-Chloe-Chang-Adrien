package controlleur;

import java.lang.Math;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import application.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modele.interfaceRMI.AccueilInterface;
import modele.interfaceRMI.AllumetteInterface;

public class AllumettesControleur {

	ArrayList<ImageView> listAllumettes;

	@FXML private ImageView allumette1;
	@FXML private ImageView allumette2;
	@FXML private ImageView allumette3;
	@FXML private ImageView allumette4;
	@FXML private ImageView allumette5;
	@FXML private ImageView allumette6;
	@FXML private ImageView allumette7;
	@FXML private ImageView allumette8;
	@FXML private ImageView allumette9;
	@FXML private ImageView allumette10;
	@FXML private ImageView allumette11;
	@FXML private ImageView allumette12;
	@FXML private ImageView allumette13;
	@FXML private ImageView allumette14;
	@FXML private ImageView allumette15;
	
	@FXML
	private Button btnTest, btnUn, btnDeux, btnRejouer, btnQuitter;

	@FXML
	private Label lblScore, lblResultat;

	int nbAllumettesDepart = 15;
	int nbAllumettesJoueur = 0;
	int nbAllumettesIA = 0;

	int max = 2;
	int min = 1;
	int range = max - min +1;
	int tour;

	AllumetteInterface allu; // cette variable permettre d'utiliser les fonctions implementees du cote serveur
	int idAllumette; // variable qui récupère l'id des parties du jeu des allumettes

	public void initialize() {
		try {
			String hote = "127.0.0.1";
			int port = Integer.parseInt("6002");
			allu = (AllumetteInterface) Naming.lookup("rmi://" + hote + ":" + port + "/allumette");
			idAllumette = allu.newAllumette();
		}
		catch (Exception e) {
			System.out.println("Erreur lookup" + e);
		}
		listAllumettes = new ArrayList<ImageView>();
		listAllumettes.add(allumette1);
		listAllumettes.add(allumette2);
		listAllumettes.add(allumette3);
		listAllumettes.add(allumette4);
		listAllumettes.add(allumette5);
		listAllumettes.add(allumette6);
		listAllumettes.add(allumette7);
		listAllumettes.add(allumette8);
		listAllumettes.add(allumette9);
		listAllumettes.add(allumette10);
		listAllumettes.add(allumette11);
		listAllumettes.add(allumette12);
		listAllumettes.add(allumette13);
		listAllumettes.add(allumette14);
		listAllumettes.add(allumette15);
		btnRejouer.setVisible(false);
		tour = (int) (Math.random()*range)+min;
		// generation aleatoire d'un nombre qui determine quel joueur commence
		if (tour == 1) { // si c'est 1, l'ordinateur commence, sinon le programme attend que le joueur realise une action
			tourIA();
		}
		System.out.println(idAllumette);
	}

	// fonction qui gere l'affichage des allumettes au fil du jeu
	private void afficherAllumettes() {
		try {
			for(int i=0; i<nbAllumettesDepart; i++) {
				listAllumettes.get(i).setVisible(false);	// on rend invisibles toutes les allumettes
			}
			for(int i=0; i<allu.getNbAllumettes(idAllumette); i++) {
				listAllumettes.get(i).setVisible(true);		// on affiche les allumettes restantes
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// fonction qui gère le retrait d'1 allumette par le client 
	public void retirerUn() { 
		try {
			allu.retirerAllumettes(1, idAllumette);
			nbAllumettesJoueur += 1;
			afficherAllumettes();
			lblScore.setText("Joueur " + nbAllumettesJoueur + " | " + nbAllumettesIA + " Ordinateur");
			// s'il n'y a plus d'allumettes, on arrete la partie
			if (allu.getNbAllumettes(idAllumette) == 0) {
				finPartie();
			}
			// sinon c'est à l'ordinateur de jouer
			else {
				tourIA();
			}
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	// fonction qui gere le retrait de 2 allumettes par le client
	public void retirerDeux() {  
		try {
			allu.retirerAllumettes(2, idAllumette);
			nbAllumettesJoueur += 2;
			afficherAllumettes();
			lblScore.setText("Joueur " + nbAllumettesJoueur + " | " + nbAllumettesIA + " Ordinateur");
			// s'il n'y a plus d'allumettes, on arrete la partie
			if (allu.getNbAllumettes(idAllumette) == 0) {
				finPartie();
			}
			// sinon c'est à l'ordinateur de jouer
			else {
				tourIA();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	// fonction qui gere le coup de l'ordinateur
	public void tourIA() { 
		try {
			// s'il ne reste qu'1 allumette, l'ordinateur la prend automatiquement
			if (allu.getNbAllumettes(idAllumette) == 1) {
				allu.retirerAllumettes(1, idAllumette);
				nbAllumettesIA += 1;
				afficherAllumettes();
				lblScore.setText("Joueur " + nbAllumettesJoueur + " | " + nbAllumettesIA + " Ordinateur");
				finPartie();
			}
			// sinon il en prend aleatoirement 1 ou 2
			else { 
				int nbARetirer = (int) (Math.random()*range)+min;
				if (nbARetirer == 1) {
					allu.retirerAllumettes(1, idAllumette);
					nbAllumettesIA += 1;
					afficherAllumettes();
					lblScore.setText("Joueur " + nbAllumettesJoueur + " | " + nbAllumettesIA + " Ordinateur");
					if (allu.getNbAllumettes(idAllumette) == 0) {
						finPartie();
					}
					else if (allu.getNbAllumettes(idAllumette) == 1) {
						btnDeux.setVisible(false);
					}
				}
				else {
					allu.retirerAllumettes(2, idAllumette);
					nbAllumettesIA += 2;
					afficherAllumettes();
					lblScore.setText("Joueur " + nbAllumettesJoueur + " | " + nbAllumettesIA + " Ordinateur");
					if (allu.getNbAllumettes(idAllumette) == 0) {
						finPartie();
					}
					else if (allu.getNbAllumettes(idAllumette) == 1) {
						btnDeux.setVisible(false);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// fonction qui gère la fin de la partie, celui qui a un nombre impair d'allumettes gagne
	private void finPartie() {
		btnUn.setVisible(false);
		btnDeux.setVisible(false);
		btnRejouer.setVisible(true);
		if (nbAllumettesJoueur %2 == 0) {
			lblResultat.setText("L'ordinateur a gagné avec un score de " + nbAllumettesIA + " allumettes.");
		}
		else {
			lblResultat.setText("Vous avez gagné avec un score de " + nbAllumettesJoueur + " allumettes.");
		}
	}
	
	// fonction qui permet au joueur de rejouer une partie
	public void Rejouer() {
		try {
			idAllumette = allu.newAllumette();
			afficherAllumettes();
			nbAllumettesJoueur = 0;
			nbAllumettesIA = 0;
			lblScore.setText("Joueur " + nbAllumettesJoueur + " | " + nbAllumettesIA + " Ordinateur");
			lblResultat.setText("");
			btnUn.setVisible(true);
			btnDeux.setVisible(true);
			btnRejouer.setVisible(false);
			tour = (int) (Math.random()*range)+min;
			// generation aleatoire d'un nombre qui determine quel joueur commence
			if (tour == 1) { // si c'est 1, l'ordinateur commence, sinon le programme attend que le joueur realise une action
				tourIA();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// fonction qui permet de quitter la fenetre de jeu et de revenir sur la fenetre d'accueil
	public void Quitter() {
        Stage stage=(Stage) btnQuitter.getScene().getWindow();
        stage.close();
        new Client().start(new Stage());
    }
}