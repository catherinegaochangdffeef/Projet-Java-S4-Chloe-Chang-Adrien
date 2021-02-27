package controlleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TicTacToeControleur {

	@FXML
	private Button btn_quitter;
	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;
	@FXML
	private ImageView img3;
	@FXML
	private ImageView img4;
	@FXML
	private ImageView img5;
	@FXML
	private ImageView img6;
	@FXML
	private ImageView img7;
	@FXML
	private ImageView img8;
	@FXML
	private ImageView img9;
	
	public void imageClique() {
		
	}
	
	
	public void Quitter() {
		Stage stage=(Stage) btn_quitter.getScene().getWindow();
		stage.close();
	}
}
