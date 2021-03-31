package controlleur;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tictactoe.Cellule;
import tictactoe.TicTacToeClient;
import tictactoe.TicTacToeServeur;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import application.Client;


public class TicTacToeControleur2 {
	@FXML
	public TextField port;
	@FXML
	public Button serveurBtn;
	@FXML
	public Button btn_quitter;
	@FXML
	public Button clientBtn;
	@FXML
	public AnchorPane panel;
	@FXML
	public Label finJeu;
	@FXML
	public Label gagnant;

	public List<Rectangle> rectangles = new ArrayList<>(9);
	public List<Cellule> cellListe = new ArrayList<>(9);
	public boolean tonTour;

	public Image imgX = new Image("/vue/X2.png");
	public Image imgO = new Image("/vue/O2.png");

	public TicTacToeServeur serveur = null;
	public TicTacToeClient client = null;

	@FXML
	public void initialize() throws MalformedURLException, RemoteException, NotBoundException {

		createTable();
		finJeu.setVisible(false);
		gagnant.setVisible(false);

		
		// vérifier si le client et le serveur sont connecté
		for (Rectangle rectangle : rectangles) {
			rectangle.setOnMouseClicked(event -> {
				if (cellListe.get(rectangles.indexOf(rectangle)).getStatus() == 0 && client != null && tonTour) {
					cellListe.get(rectangles.indexOf(rectangle)).setStatus(1);
					rectangle.setFill(new ImagePattern(imgX));
					rectangle.setDisable(true);
					client.envoyerAServeur(1, rectangles.indexOf(rectangle));
					tonTour = false;
				} else if (cellListe.get(rectangles.indexOf(rectangle)).getStatus() == 0 && serveur != null && tonTour) {
					cellListe.get(rectangles.indexOf(rectangle)).setStatus(2);
					rectangle.setFill(new ImagePattern(imgO));
					rectangle.setDisable(true);
					serveur.envoyerAClient(2, rectangles.indexOf(rectangle));
					tonTour = false;
				}
				// si X est gagné
				if (verifierFinJeu(1) == 1) {
					finJeu.setVisible(true);
					gagnant.setVisible(true);
					gagnant.setText("Gagnant!");
					for (Rectangle rect : rectangles) {
						rect.setDisable(true);
					}
				} 
				// si O est gagné
				else if (verifierFinJeu(2) == 2) {
					finJeu.setVisible(true);
					gagnant.setVisible(true);
					gagnant.setText("Gagnant!");
					for (Rectangle rect : rectangles) {
						rect.setDisable(true);
					}
				}
			});
		}
	}

	@FXML
	private void cliqueServerBouton() {
		try {
			serveur = new TicTacToeServeur();
			clientBtn.setDisable(true);
			serveurBtn.setDisable(true);
			port.setDisable(true);
			tonTour = true;
			serveur.setCelluleListener(event -> {
				rectangles.get(event.getIndex()).setFill(new ImagePattern(imgX));
				rectangles.get(event.getIndex()).setDisable(true);
				cellListe.get(event.getIndex()).setStatus(event.getStatus());
				tonTour = true;
				if (verifierFinJeu(1) == 1) {
					for (Rectangle rect : rectangles) {
						rect.setDisable(true);
					}
					finJeu.setVisible(true);
					gagnant.setVisible(true);
				}
			});
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void cliqueClientBouton() {
		try {
			client = new TicTacToeClient(port);
			clientBtn.setDisable(true);
			serveurBtn.setDisable(true);
			port.setDisable(true);
			tonTour = false;
			client.setCelluleListener(event -> {
				rectangles.get(event.getIndex()).setFill(new ImagePattern(imgO));
				rectangles.get(event.getIndex()).setDisable(true);
				cellListe.get(event.getIndex()).setStatus(event.getStatus());
				tonTour = true;
				if (verifierFinJeu(2) == 2) {
					for (Rectangle rect : rectangles) {
						rect.setDisable(true);
					}
					finJeu.setVisible(true);
					gagnant.setVisible(true);
				}
			});
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void createTable() {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				Rectangle rect = new Rectangle(145, 145);
				rect.setLayoutX(14 + 160 * j);
				rect.setLayoutY(14 + 156 * i);
				rect.setFill(Color.GREEN);
				rectangles.add(rect);
			}
		}
		for (int i = 0; i < 9; ++i) {
			cellListe.add(new Cellule(this, 0, i));
		}
		panel.getChildren().addAll(rectangles);
	}

	// La stratégie: Le gagnant est celui qui arrive à aligner trois symboles identiques
	private int verifierFinJeu(int status) {
		int compteur = 0;
		// verifier horizontalement
		for (int i = 0; i < 3; ++i) {    
			for (int j = 3 * i; j < 3 + 3 * i; ++j) {
				if (cellListe.get(j).getStatus() == status) {
					compteur++;
				} else {
					compteur = 0;
					break;
				}
				if (compteur == 3) {
					return status;
				}
			}
		}

		compteur = 0;
		// verifier verticalement
		for (int i = 0; i < 3; ++i) {   
			for (int j = i; j < i + 7; j += 3) {
				if (cellListe.get(j).getStatus() == status) {
					compteur++;
				} else {
					compteur = 0;
					break;
				}
				if (compteur == 3) {
					return status;
				}
			}
		}
		//verifier en diagonale.
		if (cellListe.get(0).getStatus() == status && cellListe.get(4).getStatus() == status && cellListe.get(8).getStatus() == status) {  
			return status;
		}
		if (cellListe.get(2).getStatus() == status && cellListe.get(4).getStatus() == status && cellListe.get(6).getStatus() == status) {   
			return status;
		}

		for (int i = 0; i < 9; ++i) {
			if (cellListe.get(i).getStatus() == 0) return 0;
		}

		Platform.runLater(() -> {
			finJeu.setVisible(true);
			gagnant.setVisible(true);
			//gagnant.setText("REMIS!");
		});
		return 0;
	}
	public void Quitter() {
		Stage stage=(Stage) btn_quitter.getScene().getWindow();
		stage.close();
		new Client().start(new Stage());
	}
}
