package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ExperienciaController implements Initializable {
	
	@FXML
	private HBox root;
	@FXML
	private TableView<?> experienciaTable;
	@FXML
	private TableColumn<?, ?> desdeColumn, hastaColumn, denominacionColumn, empleadorColumn;
	@FXML
	private Button eliminarBtn;

	public ExperienciaController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public HBox getView() {
		return root;
	}
	
	@FXML
	void onAgregarAction(ActionEvent event) {

	}

	@FXML
	void onEliminarAction(ActionEvent event) {

	}

    @FXML
    void onSeleccionarExperienciaAction(MouseEvent event) {

    }
	
}
