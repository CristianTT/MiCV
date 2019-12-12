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
import javafx.scene.layout.VBox;

public class ContactoController implements Initializable {

	@FXML
    private VBox root;
    @FXML
    private TableView<?> telefonosTable;
    @FXML
    private TableColumn<?, ?> numeroColumn, tipoColumn;
    @FXML
    private TableView<?> emailsTable;
    @FXML
    private TableColumn<?, ?> emailsColumn;
    @FXML
    private TableView<?> websTable;
    @FXML
    private TableColumn<?, ?> urlColumn;
    @FXML
    private Button eliminarTelefonoBtn, eliminarEmailBtn, eliminarWebBtn;

	public ContactoController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public VBox getView() {
		return root;
	}

	@FXML
	void onAgregarEmailAction(ActionEvent event) {

	}

	@FXML
	void onAgregarTelefonoAction(ActionEvent event) {

	}

	@FXML
	void onAgregarWebAction(ActionEvent event) {

	}

	@FXML
	void onEliminarEmailAction(ActionEvent event) {

	}

	@FXML
	void onEliminarTelefonoAction(ActionEvent event) {

	}

	@FXML
	void onEliminarWebAction(ActionEvent event) {

	}

    @FXML
    void onSeleccionarTelefonoAction(MouseEvent event) {

    }

    @FXML
    void onSeleccionarEmailAction(MouseEvent event) {

    }

    @FXML
    void onSeleccionarWebAction(MouseEvent event) {

    }
	
}
