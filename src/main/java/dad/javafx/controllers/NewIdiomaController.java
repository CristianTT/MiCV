package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.models.Conocimiento;
import dad.javafx.models.Nivel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;

public class NewIdiomaController extends Dialog<Conocimiento> implements Initializable {

	@FXML
    private TextField denominacionText, certificacionText;
    @FXML
    private ComboBox<Nivel> nivelCombo;

	private ButtonType AGREGAR_BUTTON_TYPE = new ButtonType("Crear", ButtonData.OK_DONE);

	private Conocimiento conocimiento = new Conocimiento();

	public NewIdiomaController() {
		super();
		setTitle("MiCV");
		getDialogPane().getButtonTypes().addAll(AGREGAR_BUTTON_TYPE, ButtonType.CANCEL);
		getDialogPane().setContent(loadContent("/fxml/NewIdiomaDialog.fxml"));
		setResultConverter(btn -> {
			if (btn.getButtonData() == ButtonData.OK_DONE) {
				return conocimiento;
			}
			return null;
		});
	}

	private Node loadContent(String fxml) {
		Node node = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			loader.setController(this);
			node = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nivelCombo.getItems().addAll(Nivel.values());
		
		conocimiento.denominacionProperty().bind(Bindings.concat(denominacionText.textProperty(), " (", certificacionText.textProperty(), ")"));
		conocimiento.nivelProperty().bindBidirectional(nivelCombo.valueProperty());

		Node agregarBtn = getDialogPane().lookupButton(AGREGAR_BUTTON_TYPE);
		agregarBtn.disableProperty().bind(conocimiento.denominacionProperty().isEmpty()
				.or(conocimiento.nivelProperty().isNull())
				.or(certificacionText.textProperty().isEmpty()));
	}
	
	@FXML
    void onDeseleccionarAction(ActionEvent event) {
		nivelCombo.getSelectionModel().clearSelection();
    }
	
}
