package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VueAllumettes extends Application {

	@Override
	public void start(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("vue/FenetreAllumettes.fxml"));
			Scene scene = new Scene((VBox) root, 700,500);		
			primaryStage.setScene(scene);
			primaryStage.setTitle("Allumettes");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img_allumette.jpg")));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}


