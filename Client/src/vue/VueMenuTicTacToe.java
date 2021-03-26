package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VueMenuTicTacToe extends Application{

	public void start(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("vue/FenetreMenuTicTacToe.fxml"));
			Scene scene = new Scene((AnchorPane) root, 700,479);		
			primaryStage.setScene(scene);
			primaryStage.setTitle("TicTacToe");
			 primaryStage.getIcons().add(new Image("https://cdn3.iconfinder.com/data/icons/games/154/game-tic-tac-toe-label-128.png"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
