package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.models.Nacionalidad;
import dad.javafx.utils.LectorCSV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private ListView<Nacionalidad> nacionalidadList;
    @FXML
    private ComboBox<String> paisCombo;
    @FXML
    private Button eliminarNacionalidadBtn;

    public PersonalController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		paisCombo.getItems().addAll(LectorCSV.lectorPaises());
		
		dniText.textProperty().bindBidirectional(CVController.getCV().getPersonal().identificacionProperty());
		nombreText.textProperty().bindBidirectional(CVController.getCV().getPersonal().nombreProperty());
		apellidosText.textProperty().bindBidirectional(CVController.getCV().getPersonal().apellidosProperty());
		localidadText.textProperty().bindBidirectional(CVController.getCV().getPersonal().localidadProperty());
		codigoPostalText.textProperty().bindBidirectional(CVController.getCV().getPersonal().codigoPostalProperty());
		direccionText.textProperty().bindBidirectional(CVController.getCV().getPersonal().direccionProperty());
		nacimientoDate.valueProperty().bindBidirectional(CVController.getCV().getPersonal().fechaNacimientoProperty());
		nacionalidadList.itemsProperty().bind(CVController.getCV().getPersonal().nacionalidadesProperty());
		paisCombo.valueProperty().bind(CVController.getCV().getPersonal().paisProperty());
	}
	
	public GridPane getView() {
		return root;
	}

    @FXML
    void onAgregarNacionalidadAction(ActionEvent event) {

    }

    @FXML
    void onEliminarNacionalidadAction(ActionEvent event) {

    }
    
}
