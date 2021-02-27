package controlleur;

import controlleur.TicTacToeControleur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class TicTacToeControleur {
	private Image imageX;
	private Image imageO;
	private int tourCompteur = 0;

	private boolean tour = true;
	private boolean signe = false; // X - true, O - false
	private boolean fin = false;
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
			
			imageO = new Image(TicTacToeControleur.class.getResource("/vue/O.png").toString());
	        imageX = new Image(TicTacToeControleur.class.getResource("/vue/X.png").toString());
	        btn_rec.setVisible(false);
	        if(signe) signe_lb.setText("X");
	        else signe_lb.setText("O");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	        
	    } 
	public void imageClique(MouseEvent event) {
		ImageView img = (ImageView) event.getSource();
		Image temp = img.getImage();
		if(!fin) {
			if(temp == null) {
				if(tour) img.setImage(imageX);
				else img.setImage(imageO);
				endTour();
				tour=!tour;
				signe=!signe;
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
	public void endTour() {
		tour_lb.setText("c'est au tour de l'adversaire !");
		++tourCompteur;
		if(tourCompteur >=5) {
			int gagnant = verifierGagnant();
			if(gagnant >=0) {
				finJeu(gagnant);
			}
			
		}
		
		
	}
	public void startTour() {
		tour_lb.setText("C'est ton tour !");
		
	}
	
	public void finJeu(int gagnant) {
		String gagne = "Le gagnant est: ";
		if(gagnant == 0) gagne +="O";
		else gagne += "X";
	
		tour_lb.setText(gagne);
		 btn_rec.setVisible(true);
		 fin =true;
	}
	// -1 pas de gagnant
	// 0 - O gagne
	// 1 - X gagne
	public int verifierGagnant() {
		// premier horizontal
		int gagnant = verifierColonne(img1,img2,img3);
		
		// deuxieme horizontal
		if(gagnant<0) {
			gagnant = verifierColonne(img4,img5,img6);
			
		}
		//troisieme horizontal
		if(gagnant < 0) {
			gagnant = verifierColonne(img7,img8,img9);
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
			gagnant = verifierColonne(img3,img6,img9);
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
	private int verifierColonne(ImageView a, ImageView b, ImageView c) {
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
	}
}
