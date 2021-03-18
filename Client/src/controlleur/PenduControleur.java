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

	private PenduInterface penduInterface;

	public void initialize(URL location, ResourceBundle ressources) {
		try {
			String hote = "127.0.0.1";
			int port = Integer.parseInt("6002");
			pendu = (PenduInterface) Naming.lookup("rmi://" + hote + ":" + port + "/pendu");
			numPartie = pendu.creerPartie();
		}
		catch (Exception e) {
			System.out.println("Erreur lookup" + e);
		}
	}
	public void DebutJeu() throws RemoteException {
		mot = pendu.ChoixMot();
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
		lbl_erreur_lettre.setVisible(false);

	}
	public void Envoie_Lettre() throws RemoteException {
		char c = saisieLettre.getText().trim().toUpperCase().charAt(0);
		if (Pattern.matches("[A-Z]{1}", saisieLettre.getText().trim().toUpperCase())) {
			
			if (pendu.RechCharactere(c) == true) {
				motcache.setText(pendu.AfficheLettres(c));
				lbl_erreur_lettre.setText("");
				lbl_lettres_jouees.setText(lbl_lettres_jouees.getText() + " " + c);
			}
			else {
				vies_restantes.setText(String.valueOf(pendu.ErreurLettre()));
				lbl_erreur_lettre.setText("");
				lbl_lettres_jouees.setText(lbl_lettres_jouees.getText() + " " + c);
			}
		}
		else {
			lbl_erreur_lettre.setText("Une seule lettre est attendue");
		}
		saisieLettre.setText("");
	}
	
	public void Quitter () {
		Stage stage=(Stage) btn_quitter.getScene().getWindow();
		stage.close();
		new Client().start(new Stage());
	}
	public void setInterface(PenduInterface controller1) {
		this.penduInterface = controller1;
		System.out.println("valeur penduInterface lors du set Interface : " + this.penduInterface);
	}
}
