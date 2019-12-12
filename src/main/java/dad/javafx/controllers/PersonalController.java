package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {
	
	@FXML
    private GridPane root;
    @FXML
    private TextField dniText, nombreText, apellidosText, localidadText, codigoPostalText;
    @FXML
    private DatePicker nacimientoDate;
    @FXML
    private TextArea direccionText;
    @FXML
    private ListView<?> nacionalidadList;
    @FXML
    private ComboBox<?> paisCombo;

    public PersonalController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public GridPane getView() {
		return root;
	}
    
}
