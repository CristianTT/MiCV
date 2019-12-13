package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.models.Telefono;
import dad.javafx.models.TipoTelefono;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;

public class ContactoNewTelefonoController extends Dialog<Telefono> implements Initializable {
	
	@FXML
	private TextField numeroText;
	@FXML
	private ComboBox<TipoTelefono> tipoCombo;
	
	private ButtonType AGREGAR_BUTTON_TYPE = new ButtonType("Añadir", ButtonData.OK_DONE);
	
	private Telefono telefono = new Telefono();
	
	public ContactoNewTelefonoController() {
		super();
		setTitle("MiCV");
		setHeaderText("Introduzca el nuevo número de teléfono");
		getDialogPane().getButtonTypes().addAll(AGREGAR_BUTTON_TYPE, ButtonType.CANCEL);
		getDialogPane().setContent(loadContent("/fxml/ContactoNewTelefonoDialog.fxml"));
		setResultConverter(btn -> {
			if (btn.getButtonData() == ButtonData.OK_DONE) {
				return telefono;
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
		tipoCombo.getItems().addAll(TipoTelefono.values());
		
		telefono.numeroProperty().bindBidirectional(numeroText.textProperty());
		telefono.tipoProperty().bindBidirectional(tipoCombo.valueProperty());
		
		Node agregarBtn = getDialogPane().lookupButton(AGREGAR_BUTTON_TYPE);
		agregarBtn.disableProperty().bind(telefono.numeroProperty().isEmpty().or(telefono.tipoProperty().isNull()));
	}

}
