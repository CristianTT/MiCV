package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.models.CV;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class CVController implements Initializable {

	@FXML
    private VBox root;
    @FXML
    private Tab personalTab, contactoTab, formacionTab, experienciaTab, conocimientosTab;

    private PersonalController personalController = new PersonalController();
    private ContactoController contactosController = new ContactoController();
    private FormacionController formacionController = new FormacionController();
    private ExperienciaController experienciaController = new ExperienciaController();
    private ConocimientosController conocimientosController = new ConocimientosController();
    
    private static CV cv = new CV();
    
    public CVController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CVView.fxml"));
		loader.setController(this);
		loader.load();
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		personalTab.setContent(personalController.getView());
		contactoTab.setContent(contactosController.getView());
		formacionTab.setContent(formacionController.getView());
		experienciaTab.setContent(experienciaController.getView());
		conocimientosTab.setContent(conocimientosController.getView());
		
		
		
	}
	
	public static CV getCV() {
		return cv;
	}
	
	public VBox getView() {
		return root;
	}
	
}
