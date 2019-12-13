package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.models.Email;
import dad.javafx.models.Telefono;
import dad.javafx.models.TipoTelefono;
import dad.javafx.models.Web;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Alert.AlertType;
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
		telefonosTable.itemsProperty().bindBidirectional(CVController.getCV().getContacto().telefonosProperty());
		numeroColumn.setCellValueFactory(v -> v.getValue().numeroProperty());
		numeroColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		tipoColumn.setCellValueFactory(v -> v.getValue().tipoProperty());
		tipoColumn.setCellFactory(ComboBoxTableCell.forTableColumn(TipoTelefono.values()));
		emailsTable.itemsProperty().bindBidirectional(CVController.getCV().getContacto().emailsProperty());
		emailsColumn.setCellValueFactory(v -> v.getValue().direccionProperty());
		emailsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		websTable.itemsProperty().bindBidirectional(CVController.getCV().getContacto().websProperty());
		urlColumn.setCellValueFactory(v -> v.getValue().urlProperty());
		urlColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		eliminarTelefonoBtn.disableProperty().bind(telefonosTable.getSelectionModel().selectedItemProperty().isNull());
		eliminarEmailBtn.disableProperty().bind(emailsTable.getSelectionModel().selectedItemProperty().isNull());
		eliminarWebBtn.disableProperty().bind(websTable.getSelectionModel().selectedItemProperty().isNull());
	}

	public VBox getView() {
		return root;
	}

	@FXML
	void onAgregarEmailAction(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("MiCV");
		dialog.setHeaderText("Crea una nueva dirección de correo");
		dialog.setContentText("Email:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			CVController.getCV().getContacto().getEmails().add(new Email(result.get()));
		}
	}

	@FXML
	void onAgregarTelefonoAction(ActionEvent event) {
		ContactoNewTelefonoController contactoNewTelefonoController = new ContactoNewTelefonoController();
		Optional<Telefono> result = contactoNewTelefonoController.showAndWait();
		if (result.isPresent()){
			CVController.getCV().getContacto().getTelefonos().add(result.get());
		}
	}

	@FXML
	void onAgregarWebAction(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog("https://");
		dialog.setTitle("MiCV");
		dialog.setHeaderText("Crea una nueva dirección Web");
		dialog.setContentText("URL:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			CVController.getCV().getContacto().getWebs().add(new Web(result.get()));
		}
	}

	@FXML
	void onEliminarEmailAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("MiCV");
		alert.setHeaderText("¿Estás seguro de querer eliminar el email " + emailsTable.getSelectionModel().getSelectedItem() + "?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			CVController.getCV().getContacto().getEmails().remove(emailsTable.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	void onEliminarTelefonoAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("MiCV");
		alert.setHeaderText("¿Estás seguro de querer eliminar el teléfono " + telefonosTable.getSelectionModel().getSelectedItem() + "?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			CVController.getCV().getContacto().getTelefonos().remove(telefonosTable.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	void onEliminarWebAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("MiCV");
		alert.setHeaderText("¿Estás seguro de querer eliminar la web " + websTable.getSelectionModel().getSelectedItem() + "?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			CVController.getCV().getContacto().getWebs().remove(websTable.getSelectionModel().getSelectedItem());
		}
	}

}
