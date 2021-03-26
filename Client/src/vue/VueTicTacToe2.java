package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class VueTicTacToe2 extends Application {
    private Stage primaryStage;
    private AnchorPane layout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.getIcons().add(new Image("https://cdn3.iconfinder.com/data/icons/games/154/game-tic-tac-toe-label-128.png"));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        initialize();
    }

    private void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VueTicTacToe2.class.getResource("FenetreTicTacToe2.fxml"));
            layout = (AnchorPane) loader.load();
            Scene scene = new Scene(layout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) {
		launch(args);
	}
}
