package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.models.Titulo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.converter.LocalDateStringConverter;

public class FormacionController implements Initializable {

	@FXML
	private HBox root;
	@FXML
	private TableView<Titulo> formacionTable;
	@FXML
	private TableColumn<Titulo, LocalDate> desdeColumn, hastaColumn;
	@FXML
	private TableColumn<Titulo, String> denominacionColumn, organizadorColumn;
	@FXML
	private Button eliminarBtn;

	public FormacionController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		formacionTable.itemsProperty().bind(CVController.getCV().formacionProperty());
		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		desdeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		hastaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		organizadorColumn.setCellValueFactory(v -> v.getValue().organizadorProperty());
		organizadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		eliminarBtn.disableProperty().bind(formacionTable.getSelectionModel().selectedItemProperty().isNull());
	}

	public HBox getView() {
		return root;
	}
	
	@FXML
	void onAgregarAction(ActionEvent event) {
		NewFormacionController newFormacionController = new NewFormacionController();
		Optional<Titulo> result = newFormacionController.showAndWait();
		if (result.isPresent()){
			CVController.getCV().getFormacion().add(result.get());
		}
	}

	@FXML
	void onEliminarAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("MiCV");
		alert.setHeaderText("¿Estás seguro de querer eliminarlo?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			CVController.getCV().getFormacion().remove(formacionTable.getSelectionModel().getSelectedItem());
		}
	}

}
