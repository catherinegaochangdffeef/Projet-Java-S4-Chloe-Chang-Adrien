package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage; 

public class VuePendu  extends Application{

	@Override
	public void start(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/vue/FenetrePendu.fxml"));
			Scene scene = new Scene((VBox) root, 600,400);		
			primaryStage.setScene(scene);
			primaryStage.setTitle("Pendu");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
