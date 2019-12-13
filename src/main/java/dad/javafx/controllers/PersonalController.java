package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.models.Nacionalidad;
import dad.javafx.utils.LectorCSV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
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
		nacionalidadList.itemsProperty().bindBidirectional(CVController.getCV().getPersonal().nacionalidadesProperty());
		paisCombo.valueProperty().bindBidirectional(CVController.getCV().getPersonal().paisProperty());

		eliminarNacionalidadBtn.disableProperty().bind(nacionalidadList.getSelectionModel().selectedItemProperty().isNull());
	}

	public GridPane getView() {
		return root;
	}

	@FXML
	void onAgregarNacionalidadAction(ActionEvent event) {
		ArrayList<Nacionalidad> choices = LectorCSV.lectorNacionalidades();
		ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<Nacionalidad>(choices.get(0), choices);
		dialog.setTitle("MiCV");
		dialog.setHeaderText("Añadir nacionalidad");
		dialog.setContentText("Selecciona una nacionalidad:");
		Optional<Nacionalidad> result = dialog.showAndWait();
		if (result.isPresent()) {
			boolean yaExiste = false;
			for (Nacionalidad nacionalidad : CVController.getCV().getPersonal().getNacionalidades()) {
				if (nacionalidad.getDenominacion().toLowerCase().equals(result.get().getDenominacion().toLowerCase())) {
					yaExiste = true;
				}
			}
			if (!yaExiste) {
				CVController.getCV().getPersonal().getNacionalidades().add(result.get());
			}
		}
	}

	@FXML
	void onEliminarNacionalidadAction(ActionEvent event) {
		if (!nacionalidadList.getSelectionModel().isEmpty()) {
			CVController.getCV().getPersonal().getNacionalidades()
					.remove(nacionalidadList.getSelectionModel().getSelectedItem());
		}
	}

}
