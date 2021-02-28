package controlleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vue.VueAccueil;
import vue.VueAllumettes;
import vue.VuePendu;
import vue.VueTicTacToe;
public class AccueilControleur  {

	@FXML
	private Button ButtonPendu;
	@FXML
	private Button ButtonAllumettes;
	@FXML
	private Button ButtonTicTacToe;
	@FXML
	private Button ButtonQuitter;

	public void VersPendu() {
		new VuePendu().start(new Stage());
		this.Quitter();
	}
	
	public void VersAllumettes() {
		new VueAllumettes().start(new Stage());
		this.Quitter();
	}
	
	public void VersTicTacToe() {
		new VueTicTacToe().start(new Stage());
		new VueTicTacToe().start(new Stage());
		this.Quitter();
	}

	public void Quitter() {
		Stage stage=(Stage) ButtonQuitter.getScene().getWindow();
		stage.close();
	}

}
