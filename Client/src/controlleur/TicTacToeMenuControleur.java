package controlleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import vue.VueTicTacToe;
import vue.VueTicTacToe2;

public class TicTacToeMenuControleur {

	@FXML
	private Button SoloBtn;
	@FXML
	private Button MulBtn;
	@FXML
	private Button quitterBtn;
	
	public void initialize(URL location, ResourceBundle ressources) {
		try {
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void VersTicTacToe1() {
		new VueTicTacToe().start(new Stage());
		this.Quitter();
	}
	
	public void VersTicTacToe2() throws Exception {
		new VueTicTacToe2().start(new Stage());
		this.Quitter();
	}
	
	public void Quitter() {
		Stage stage=(Stage) quitterBtn.getScene().getWindow();
		stage.close();
	}
	
}
