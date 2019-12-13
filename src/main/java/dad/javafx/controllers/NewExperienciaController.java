package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.models.Experiencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;

public class NewExperienciaController extends Dialog<Experiencia> implements Initializable {

	@FXML
    private TextField denominacionText, empleadorText;
    @FXML
    private DatePicker desdeDate, hastaDate;

	private ButtonType AGREGAR_BUTTON_TYPE = new ButtonType("Crear", ButtonData.OK_DONE);

	private Experiencia experiencia = new Experiencia();

	public NewExperienciaController() {
		super();
		setTitle("MiCV");
		getDialogPane().getButtonTypes().addAll(AGREGAR_BUTTON_TYPE, ButtonType.CANCEL);
		getDialogPane().setContent(loadContent("/fxml/NewExperienciaDialog.fxml"));
		setResultConverter(btn -> {
			if (btn.getButtonData() == ButtonData.OK_DONE) {
				return experiencia;
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
		experiencia.denominacionProperty().bindBidirectional(denominacionText.textProperty());
		experiencia.empleadorProperty().bindBidirectional(empleadorText.textProperty());
		experiencia.desdeProperty().bindBidirectional(desdeDate.valueProperty());
		experiencia.hastaProperty().bindBidirectional(hastaDate.valueProperty());

		Node agregarBtn = getDialogPane().lookupButton(AGREGAR_BUTTON_TYPE);
		agregarBtn.disableProperty().bind(experiencia.denominacionProperty().isEmpty()
				.or(experiencia.empleadorProperty().isNull())
				.or(experiencia.desdeProperty().isNull())
				.or(experiencia.hastaProperty().isNull()));
	}
}
