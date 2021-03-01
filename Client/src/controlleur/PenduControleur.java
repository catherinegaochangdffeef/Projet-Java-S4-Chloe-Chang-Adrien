package controlleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.UUID;

import application.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.interfaceRMI.PenduInterface;

public class PenduControleur implements Initializable {
	String mot;
	UUID numPartie;
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

	private PenduInterface penduInterface;

	public void initialize(URL location, ResourceBundle ressources) {
		try {
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void initialisation (UUID uuid) throws RemoteException {
		this.numPartie = uuid;
	}

	public void DebutJeu() throws RemoteException {
		System.out.println("valeur penduInterface lancement jeu : "+ this.penduInterface);
		mot = penduInterface.ChoixMot();
		motcache.setText(penduInterface.AfficheTirets(mot));
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
		new Client().start(new Stage());
	}
	public void setInterface(PenduInterface controller1) {
		this.penduInterface = controller1;
		System.out.println("valeur penduInterface lors du set Interface : " + this.penduInterface);
	}
}
