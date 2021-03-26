package controlleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import vue.VueAllumettes;
import vue.VueMenuTicTacToe;
import vue.VuePendu;
import vue.VueTicTacToe;
import vue.VueTicTacToe2;


public class AccueilControleur {
	@FXML
	private Button ButtonPendu;
	@FXML
	private Button ButtonAllumettes;
	@FXML
	private Button ButtonTicTacToe;
	@FXML
	private Button ButtonQuitter;

	public void initialize(URL location, ResourceBundle ressources) {
		try {
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void VersPendu() throws RemoteException {
		new VuePendu().start(new Stage());
		this.Quitter();
	}

	public void VersAllumettes() {
		new VueAllumettes().start(new Stage());
		this.Quitter();
	}

	public void VersTicTacToe() throws Exception {
		//new VueTicTacToe().start(new Stage());
		//new VueTicTacToe2().start(new Stage());
		new VueMenuTicTacToe().start(new Stage());
		this.Quitter();
	}

	public void Quitter() {
		Stage stage=(Stage) ButtonQuitter.getScene().getWindow();
		stage.close();
	}
}