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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.interfaceRMI.AllumetteInterface;
import modele.interfaceRMI.PenduInterface;

public class PenduControleur implements Initializable {
	String mot;
	PenduInterface pendu;
	UUID numPartie;
	@FXML
	private Label vies_restantes;
	@FXML
	private Label motcache;
	@FXML
	private Label lbl_vies;
	@FXML
	private Label lbl_saisie;
	@FXML	// label indiquant ou sont les lettres deja jouées
	private Label lbl_lettres_saisies;
	@FXML	// lettres qui ont été jouées
	private Label lbl_lettres_jouees;
	@FXML
	private Label lbl_erreur_lettre;
	@FXML
	private TextField saisieLettre;
	@FXML
	private Button btn_valid;
	@FXML
	private Button btn_quitter;
	@FXML
	private Button commencer;

	public void initialize(URL location, ResourceBundle ressources) {
		try {
			String hote = "127.0.0.1";
			int port = Integer.parseInt("6002");
			pendu = (PenduInterface) Naming.lookup("rmi://" + hote + ":" + port + "/pendu");
		}
		catch (Exception e) {
			System.out.println("Erreur lookup" + e);
		}
	}
	
	public void DebutJeu() throws RemoteException {
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
		
		vies_restantes.setText(String.valueOf(10));
		lbl_lettres_jouees.setText("");
		

	}
	public void Envoie_Lettre() throws RemoteException {
		char c = 0;
		try {
			c = saisieLettre.getText().trim().toUpperCase().charAt(0);
		}catch(Exception e) {
			System.out.println("erreur saisie" + e);
		}
		// le regex pour autoriser une seule lettre est  [A-Z]{1,1}
		if (Pattern.matches("[A-Z]{1,1}", saisieLettre.getText().trim().toUpperCase())) {
			if (LettreUtilisee(c) == false) {
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
					Finjeu(Integer.valueOf(vies_restantes.getText()));
				}
			}
			
			else 
				lbl_erreur_lettre.setText("La lettre a déjà été utilisée !");
		}
		else {
			lbl_erreur_lettre.setText("Une seule lettre est attendue !");
		}
		saisieLettre.setText("");
	}
	private void Finjeu(int erreur) {
		if (motcache.getText().indexOf('_') == -1) {
			// on a gagné
			CommunFinJeu();
			lbl_lettres_jouees.setText("Bravo ! Vous avez gagné avec " + String.valueOf(10-erreur) + " erreurs !");
		}
		else if (erreur <= 0) {
			// on a perdu
			CommunFinJeu();
			lbl_lettres_jouees.setText("Perdu ! Le mot a trouver était : " + mot);
		}
	}
	
	private void CommunFinJeu() {
		lbl_lettres_saisies.setVisible(false);
		saisieLettre.setDisable(true);
		btn_valid.setDisable(true);
		commencer.setVisible(true);
		saisieLettre.setVisible(false);
		lbl_saisie.setVisible(false);
		btn_valid.setVisible(false);
		commencer.setText("Recommencer");
		
	}

	public boolean LettreUtilisee (char c) {
		// retourne -1 si le charactere est pas contenu
		if (lbl_lettres_jouees.getText().indexOf(c) == -1)
			return false;
		else return true;
	}
	public void Quitter () {
		Stage stage=(Stage) btn_quitter.getScene().getWindow();
		stage.close();
		new Client().start(new Stage());
	}
}
