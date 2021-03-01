package controlleur;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modele.interfaceRMI.AccueilInterface;
import modele.interfaceRMI.PenduInterface;
import vue.VueAllumettes;
import vue.VuePendu;
import vue.VueTicTacToe;


public class AccueilControleur implements AccueilInterface  {
	@FXML
	private Button ButtonPendu;
	@FXML
	private Button ButtonAllumettes;
	@FXML
	private Button ButtonTicTacToe;
	@FXML
	private Button ButtonQuitter;
	

	private PenduInterface controller1;
	//private InterfaceAllumettes obj1;
	//private InterfaceTicTacToe obj3;

	public void initialize(URL location, ResourceBundle ressources) {
		try {
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void VersPendu() throws RemoteException {
		try {
			String hote = "127.0.0.1";
			int port = Integer.parseInt("6002");

			controller1= (PenduInterface) Naming.lookup("rmi://" + hote + ":" + port + "/pendu");
			UUID uuid = this.controller1.creerPartie();
			
			new VuePendu().start(new Stage());
			
			URL fxmlUrl = getClass().getClassLoader().getResource("vue/FenetrePendu.fxml");
			FXMLLoader fxml = new FXMLLoader(fxmlUrl);
			Node root = null;
			try {
				root= fxml.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			PenduControleur pc = fxml.getController();
			pc.setInterface(controller1);
			pc.initialisation(uuid);
			this.Quitter();
			
		} catch (Exception e) {
			System.out.println("Client exception: " + e);
		}
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