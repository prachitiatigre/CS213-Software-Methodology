package cafe;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
This class takes care of formatting the JavaFX window and also running the application
@author Prachiti Atigre, Ujani Patel
*/
public class Main extends Application {

	/**
	In this method, we are able to set the dimension of the JavaFX primary window and also set the title
	@param primaryStage the Stage object represents the primary window of a JavaFX application
	*/
	@Override
	public void start(Stage primaryStage) {
	
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainView.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("RU Cafe");
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	This method runs the program
	@param args Arguments from the GUI
	*/
	public static void main(String[] args) {
		launch(args);
	}
}
