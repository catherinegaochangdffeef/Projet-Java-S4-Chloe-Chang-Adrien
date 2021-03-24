package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application {
	public void start(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/vue/FenetreAccueil.fxml"));
			Scene scene = new Scene((VBox) root, 600, 400);		
			primaryStage.setScene(scene);
			primaryStage.setTitle("Serveur de jeux en réseau");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/vue/img_accueil.jpg")));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}