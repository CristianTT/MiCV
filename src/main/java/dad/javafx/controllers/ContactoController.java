package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.models.Email;
import dad.javafx.models.Telefono;
import dad.javafx.models.TipoTelefono;
import dad.javafx.models.Web;
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
	private TableView<Telefono> telefonosTable;
	@FXML
	private TableColumn<Telefono, String> numeroColumn;
	@FXML
	private TableColumn<Telefono, TipoTelefono> tipoColumn;
	@FXML
	private TableView<Email> emailsTable;
	@FXML
	private TableColumn<Email, String> emailsColumn;
	@FXML
	private TableView<Web> websTable;
	@FXML
	private TableColumn<Web, String> urlColumn;
	@FXML
	private Button eliminarTelefonoBtn, eliminarEmailBtn, eliminarWebBtn;

	public ContactoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		telefonosTable.itemsProperty().bind(CVController.getCV().getContacto().telefonosProperty());
		numeroColumn.setCellValueFactory(v -> v.getValue().numeroProperty());
		tipoColumn.setCellValueFactory(v -> v.getValue().tipoProperty());
		emailsTable.itemsProperty().bind(CVController.getCV().getContacto().emailsProperty());
		emailsColumn.setCellValueFactory(v -> v.getValue().direccionProperty());
		websTable.itemsProperty().bind(CVController.getCV().getContacto().websProperty());
		urlColumn.setCellValueFactory(v -> v.getValue().urlProperty());
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
