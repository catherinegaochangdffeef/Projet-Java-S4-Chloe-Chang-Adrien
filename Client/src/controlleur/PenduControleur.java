package controlleur;

import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.regex.Pattern;

import application.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.interfaceRMI.PenduInterface;

public class PenduControleur implements Initializable {
	String mot;
	PenduInterface pendu;
	UUID numPartie;
	// le booléen recommence permet de dire quand il faut supprimer l'ancien id de partie
	boolean recommence = false;
	@FXML private Label vies_restantes;
	@FXML private Label motcache;
	@FXML private Label lbl_vies;
	@FXML private Label lbl_saisie;
	// label indiquant ou sont les lettres deja jouees
	@FXML private Label lbl_lettres_saisies;
	// lettres qui ont ete jouees
	@FXML private Label lbl_lettres_jouees;
	@FXML private Label lbl_erreur_lettre;
	@FXML private TextField saisieLettre;
	@FXML private Button btn_valid;
	@FXML private Button btn_quitter;
	@FXML private Button commencer;

	// le pendu sous forme d'un canvas
	@FXML private Canvas canvas;
	GraphicsContext gc;


	public void initialize(URL location, ResourceBundle ressources) {
		try {
			String hote = "127.0.0.1";
			int port = Integer.parseInt("6005");
			pendu = (PenduInterface) Naming.lookup("rmi://" + hote + ":" + port + "/pendu");
		}
		catch (Exception e) {
			System.out.println("Erreur lookup" + e);
		}
	}

	public void DebutJeu() throws RemoteException {
		// si on recommence la partie, on efface l'ancienne partie avant de lui en ré-attribuer une nouvelle
		if (recommence == true) {
			pendu.Effacer(numPartie);
			recommence = false;
		}
		// la partie récupère un id unique qui est cree dans le serveur
		numPartie = pendu.creerPartie();
		mot = pendu.ChoixMot(numPartie);
		motcache.setText(pendu.AfficheTirets(mot));
		commencer.setVisible(false);
		btn_valid.setVisible(true);
		vies_restantes.setVisible(true);
		motcache.setVisible(true);
		lbl_vies.setVisible(true);
		lbl_saisie.setVisible(true);
		saisieLettre.setVisible(true);
		lbl_lettres_saisies.setVisible(true);
		lbl_lettres_jouees.setVisible(true);
		lbl_erreur_lettre.setVisible(true);

		btn_valid.setDisable(false);
		saisieLettre.setDisable(false);

		vies_restantes.setText(String.valueOf(11));
		lbl_lettres_jouees.setText("");

		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// on efface le canvas
		gc.clearRect(0, 0, 150, 150);
		// on dessine en blanc les traits du pendu
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(2);
		gc.strokeLine(20,140,130,140);
		gc.strokeLine(20,140, 20, 20);
		gc.strokeLine(20, 20, 100, 20);
		gc.strokeLine(50, 20, 20, 50);
		gc.strokeLine(100,20,100,40);
		gc.strokeOval(90, 40, 20, 20);
		gc.strokeOval(90, 60, 20, 40);
		gc.strokeLine(91, 70, 80, 90);
		gc.strokeLine(109, 70, 120, 90);
		gc.strokeLine(95,98,95,120);
		gc.strokeLine(105,98,105,120);
	}

	// lors de la validation sur le bouton ou avec la touche entrée dans la zone de saisie
	public void Envoie_Lettre() throws RemoteException {
		char c = 0;
		try {
			c = saisieLettre.getText().trim().toUpperCase().charAt(0);
			// le regex pour autoriser une seule lettre est  [A-Z]{1,1}
			if (Pattern.matches("[A-Z]{1,1}", saisieLettre.getText().trim().toUpperCase())) {
				// si la lettre n'est pas déjà utilisée
				if (LettreUtilisee(c) == false) {
					// si le caractère demandé est dans le mot
					if (pendu.RechCharactere(c,mot) == true) {
						motcache.setText(pendu.AfficheLettres(c,numPartie));
						lbl_erreur_lettre.setText("");
						lbl_lettres_jouees.setText(lbl_lettres_jouees.getText() + " " + c);
						Finjeu(Integer.valueOf(vies_restantes.getText()));
					}
					else {
						vies_restantes.setText(String.valueOf(pendu.ErreurLettre(numPartie)));
						lbl_erreur_lettre.setText("");
						lbl_lettres_jouees.setText(lbl_lettres_jouees.getText() + " " + c);
						DessinePendu(Integer.valueOf(vies_restantes.getText()));
						Finjeu(Integer.valueOf(vies_restantes.getText()));
					}
				}
				else lbl_erreur_lettre.setText("La lettre a deja ete utilisee !");
			}
			else lbl_erreur_lettre.setText("Une seule lettre est attendue !");
		} catch(Exception e) {
			System.out.println("erreur saisie" + e);
			lbl_erreur_lettre.setText("Veuillez entrer une lettre");			
		}
		saisieLettre.setText("");
		saisieLettre.requestFocus();
	}

	// permet de dessiner le pendu en fonction du nombre d'erreur
	private void DessinePendu(int nb) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(2);
		gc.setStroke(Color.RED);
		switch (11-nb) {
		case 1: {
			gc.strokeLine(20,140,130,140);
			break;
		}
		case 2 : {
			gc.strokeLine(20,140, 20, 20);
			break;
		}
		case 3 : {
			gc.strokeLine(20, 20, 100, 20);
			break;
		}
		case 4 : {
			gc.strokeLine(50, 20, 20, 50);
			break;
		}
		case 5 : {
			gc.strokeLine(100,20,100,40);
			break;
		}
		case 6 : {
			gc.strokeOval(90, 40, 20, 20);
			break;
		}
		case 7 : {
			gc.strokeOval(90, 60, 20, 40);
			break;
		}
		case 8 : {
			gc.strokeLine(91, 70, 80, 90);
			break;
		}
		case 9 : {
			gc.strokeLine(109, 70, 120, 90);
			break;
		}
		case 10: {
			gc.strokeLine(95,98,95,120);
			break;
		}
		case 11: {
			gc.strokeLine(105,98,105,120);
			break;
		}
		}
	}

	public boolean LettreUtilisee (char c) {
		// retourne -1 si le charactere est pas contenu
		if (lbl_lettres_jouees.getText().indexOf(c) == -1)
			return false;
		else return true;
	}

	private void Finjeu(int erreur) {
		if (motcache.getText().indexOf('_') == -1) {
			// on a gagne
			CommunFinJeu();
			lbl_lettres_jouees.setText("Bravo ! Vous avez gagne avec " + String.valueOf(11-erreur) + " erreurs !");
		}
		else if (erreur <= 0) {
			// on a perdu
			CommunFinJeu();
			lbl_lettres_jouees.setText("Perdu ! Le mot a trouver etait : " + mot);
		}
	}
	
	// permet de faire l'affichage commun (peu importe si il y a victoire ou defaite, on aura toujours ceci)
	private void CommunFinJeu() {
		lbl_lettres_saisies.setVisible(false);
		saisieLettre.setDisable(true);
		btn_valid.setDisable(true);
		commencer.setVisible(true);
		saisieLettre.setVisible(false);
		lbl_saisie.setVisible(false);
		btn_valid.setVisible(false);
		recommence = true;
		// on change juste le titre du bouton de départ pour pouvoir relancer une partie
		commencer.setText("Recommencer");	
	}

	public void Quitter () throws RemoteException {
		Stage stage=(Stage) btn_quitter.getScene().getWindow();
		pendu.Effacer(numPartie);
		stage.close();
		new Client().start(new Stage());
	}
}
