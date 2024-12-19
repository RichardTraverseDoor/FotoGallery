package pk.foto.ui;

import javafx.stage.Stage;

public abstract class ErfassungView <T> extends Stage {
	//Fenster
	private Stage stage;
	
    public ErfassungView(Stage stage) {
    	
        // Modalität einstellen
        this.stage.initOwner(stage);
        this.stage.initModality(javafx.stage.Modality.WINDOW_MODAL);
    }

    public boolean showView() {
        stage.showAndWait(); 
        return stage.isShowing(); 
    }

    
	
	

}
