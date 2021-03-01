package application;

import java.rmi.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.interfaceRMI.AccueilInterface;

public class Client extends Application {
	public void start(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/vue/FenetreAccueil.fxml"));
			Scene scene = new Scene((VBox) root, 600, 400);		
			primaryStage.setScene(scene);
			primaryStage.setTitle("AccueilControleur");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	
//	public static void main(String[] args) {

//		String hote = "127.0.0.1";
//		int port = Integer.parseInt("6002");
//		try {
//			// lookup est une méthode de Naming qui permet de rechercher un objet dans le
//			// service de nommage
//			AccueilInterface controller = (AccueilInterface) Naming.lookup("rmi://" + hote + ":" + port + "/projetJava");
//			System.out.println("Client est pret !");
//			VueAccueil.main(args);
//			
//		} catch (Exception e) {
//			System.out.println("Client exception: " + e);
//		}
//	}
}