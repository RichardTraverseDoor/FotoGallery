package pk.foto.ui;

import javafx.stage.Stage;

public class ErfassungView<T> {
	//Fenster
	private Stage stage;
	
    public ErfassungView(Stage stage) {
        this.stage = stage;
        // Modalität einstellen
        this.stage.initOwner(stage);
        this.stage.initModality(javafx.stage.Modality.WINDOW_MODAL);
    }

    public boolean showView() {
        stage.showAndWait(); 
        return stage.isShowing(); 
    }

    //public abstract T gibNeuesObjekt();
	
	

}
