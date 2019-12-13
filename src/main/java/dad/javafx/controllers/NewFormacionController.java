package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.models.Titulo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;

public class NewFormacionController extends Dialog<Titulo> implements Initializable {

	@FXML
    private TextField denominacionText, organizadorText;
    @FXML
    private DatePicker desdeDate, hastaDate;

	private ButtonType AGREGAR_BUTTON_TYPE = new ButtonType("Crear", ButtonData.OK_DONE);

	private Titulo titulo = new Titulo();

	public NewFormacionController() {
		super();
		setTitle("MiCV");
		getDialogPane().getButtonTypes().addAll(AGREGAR_BUTTON_TYPE, ButtonType.CANCEL);
		getDialogPane().setContent(loadContent("/fxml/NewFormacionDialog.fxml"));
		setResultConverter(btn -> {
			if (btn.getButtonData() == ButtonData.OK_DONE) {
				return titulo;
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
		titulo.denominacionProperty().bindBidirectional(denominacionText.textProperty());
		titulo.organizadorProperty().bindBidirectional(organizadorText.textProperty());
		titulo.desdeProperty().bindBidirectional(desdeDate.valueProperty());
		titulo.hastaProperty().bindBidirectional(hastaDate.valueProperty());

		Node agregarBtn = getDialogPane().lookupButton(AGREGAR_BUTTON_TYPE);
		agregarBtn.disableProperty().bind(titulo.denominacionProperty().isEmpty()
				.or(titulo.organizadorProperty().isNull())
				.or(titulo.desdeProperty().isNull())
				.or(titulo.hastaProperty().isNull()));
	}
}
