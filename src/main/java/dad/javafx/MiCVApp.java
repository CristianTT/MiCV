package dad.javafx;

import dad.javafx.controllers.CVController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MiCVApp extends Application {

	private CVController controller;
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new CVController();
		stage = primaryStage;
		
		Scene scene = new Scene(controller.getView());

		primaryStage.setTitle("MiCV");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("/images/cv64x64.png"));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
