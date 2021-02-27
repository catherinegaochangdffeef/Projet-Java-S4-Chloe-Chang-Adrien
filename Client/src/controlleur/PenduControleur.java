package controlleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vue.VueAccueil;
import vue.VueAllumettes;

public class PenduControleur {
	@FXML
	private Label vies_restantes;
	@FXML
	private Label motcache;
	@FXML
	private Label lbl_vies;
	@FXML
	private Label lbl_saisie;
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void DebutJeu() {
		commencer.setVisible(false);
		btn_valid.setVisible(true);
		vies_restantes.setVisible(true);
		motcache.setVisible(true);
		lbl_vies.setVisible(true);
		lbl_saisie.setVisible(true);
		saisieLettre.setVisible(true);
		
	}
	public void Envoie_Lettre() {

	}
	public void Quitter () {
		Stage stage=(Stage) btn_quitter.getScene().getWindow();
		stage.close();
		new VueAccueil().start(new Stage());
	}	
	

}
