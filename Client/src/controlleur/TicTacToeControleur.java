package controlleur;

import java.rmi.Naming;
import java.rmi.RemoteException;

import application.Client;
import controlleur.TicTacToeControleur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modele.interfaceRMI.TicTacToeInterface;
import vue.VueTicTacToe;
import javafx.scene.image.Image;

public class TicTacToeControleur {
	public Image imageX;
	public Image imageO;

	
	public int tourCompteur = 0;

	public boolean tour = true;
	public  boolean signe = true; // X - true, O - false
	public boolean fin = false;
	TicTacToeInterface tic;// cette variable permettre d'utiliser les fonctions implementees du cote serveur
	
	
	@FXML
    private GridPane gridPane;
	@FXML
    private Label tour_lb;
	@FXML
    private Label signe_lb;
	@FXML
	private Button btn_quitter;
	@FXML
	private Button btn_rec;
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
	
	
	 public void initialize(){
		 try {
			 //vider toutes les case 
			    img9.setImage(null);
				img8.setImage(null);
				img7.setImage(null);
				img5.setImage(null);
				img6.setImage(null);
				img4.setImage(null);
				img3.setImage(null);
				img2.setImage(null);
				img1.setImage(null);
				fin =false;
				signe = true;
				tour = true;
				signe_lb.setText("X");
				tour_lb.setText("c'est le tour de: ");
			 
			 String hote = "127.0.0.1";
				int port = Integer.parseInt("6005");
				tic = (TicTacToeInterface) Naming.lookup("rmi://" + hote + ":" + port + "/tictactoe");
			imageO = new Image(TicTacToeControleur.class.getResource("/vue/O.png").toString());
	        imageX = new Image(TicTacToeControleur.class.getResource("/vue/X.png").toString());
	        btn_rec.setVisible(false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Erreur lookup" + e);
			}
	        
	    } 
	
	public void imageClique(MouseEvent event) throws RemoteException {
		ImageView img = (ImageView) event.getSource();
		Image temp = img.getImage();
		if(!fin) {
		//if(!tic.finMorpion()) {
			if(temp == null) 
				//if(tour) img.setImage(imageX);
				//else img.setImage(imageO);
				if (tic.setImage(tour)) img.setImage(imageX);
			    else img.setImage(imageO);
				//img.setImage(tic.setImage(tour, imageX, imageO));
				endTour();
				if(!fin) {
				//if(!tic.finMorpion()) {
				//tour=!tour;
					tour=tic.changerTour(tour);
				//signe=!signe;
					signe=tic.changerSigne(signe);
				//if (signe) signe_lb.setText("X");
		        //else signe_lb.setText("O");
					// si signe est true, afficher "X", sinon afficher "O"
					signe_lb.setText(tic.afficherSigne(signe));
				}
				
			}	
		}
		
	
	public boolean isTour() {
		return tour;
	}
	public void setTour(boolean tour) {
		this.tour = tour;
	}
	public boolean isSigne() {
		return signe;
	}
	public void endTour() throws RemoteException {
		tour_lb.setText("c'est le tour de: ");
		++tourCompteur;
		
		if(tourCompteur >=5) {
			int gagnant = verifierGagnant();
			if(gagnant >=0) {
				finJeu(gagnant);
			}
			
		}
	}
	public void startTour() {
		tour_lb.setText("C'est le tour de : ");
		
	}
	//fonction qui permets de affihcer la gagnant et permet de recommencer le jeu 
	public void finJeu(int gagnant) throws RemoteException  {
		String gagne = "Le gagnant est: ";
		signe_lb.setText(this.tic.finJeu(gagnant));
		//fin = true;
		fin= tic.finMorpion(gagnant);
		tour_lb.setText(gagne);
		btn_rec.setVisible(true);
		
	}
	// -1 pas de gagnant
	// 0 - O gagne
	// 1 - X gagne
	public int verifierGagnant() throws RemoteException {
		// premier horizontal
		int gagnant = verifierColonne(img1,img2,img3);
		
		// deuxieme horizontal
		if(gagnant<0) {
			gagnant = verifierColonne(img4,img5,img6);
			
		}
		//troisieme horizontal
		if(gagnant < 0) {
			gagnant =verifierColonne(img7,img8,img9);
		}
		
		// premiere verticale
		if(gagnant < 0) {
			gagnant = verifierColonne(img1,img4,img7);
		}
		// deuxieme verticale
		if(gagnant < 0) {
			gagnant = verifierColonne(img2,img5,img8);
		}
		// troisieme verticale
		if(gagnant < 0) {
			gagnant =verifierColonne(img3,img6,img9);
		}
		// premiere diagonale
		if(gagnant < 0) {
			gagnant = verifierColonne(img1,img5,img9);
		}
		//deuxieme diagonale
		if(gagnant < 0) {
			gagnant = verifierColonne(img3,img5,img7);
		}
		return gagnant;	
	}
	
	private int verifierColonne(ImageView a, ImageView b, ImageView c) throws RemoteException {
		if(a!=null && (a.getImage() == b.getImage()) && c!=null && (b.getImage() == c.getImage())){
            if(a.getImage() == imageO) return 0;
            else if(a.getImage() == imageX) return 1;
        } else {
            return -1;
        }
        return -1;
	}
public void Quitter() {
		Stage stage=(Stage) btn_quitter.getScene().getWindow();
		stage.close();
		new Client().start(new Stage());
	}
public void Redemarrer() throws RemoteException {
	//fin = tic.redemarrer();
	initialize();
	
}
}
