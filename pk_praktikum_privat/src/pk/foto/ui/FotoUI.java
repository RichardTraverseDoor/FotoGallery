package pk.foto.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class FotoUI extends Application {
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
        primaryStage.setTitle("Leeres Fenster");

        var l = new Label("");
        Scene scene = new Scene(l, 800, 500); 

        primaryStage.setScene(scene);

        primaryStage.show();
		
	}

}
