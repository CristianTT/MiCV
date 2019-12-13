package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.models.Experiencia;
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

public class ExperienciaController implements Initializable {
	
	@FXML
	private HBox root;
	@FXML
	private TableView<Experiencia> experienciaTable;
	@FXML
	private TableColumn<Experiencia, LocalDate> desdeColumn, hastaColumn;
	@FXML
	private TableColumn<Experiencia, String> denominacionColumn, empleadorColumn;
	@FXML
	private Button eliminarBtn;

	public ExperienciaController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		experienciaTable.itemsProperty().bind(CVController.getCV().experienciasProperty());
		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		desdeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		hastaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		empleadorColumn.setCellValueFactory(v -> v.getValue().empleadorProperty());
		empleadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		eliminarBtn.disableProperty().bind(experienciaTable.getSelectionModel().selectedItemProperty().isNull());
	}

	public HBox getView() {
		return root;
	}
	
	@FXML
	void onAgregarAction(ActionEvent event) {
		NewExperienciaController newExperienciaController = new NewExperienciaController();
		Optional<Experiencia> result = newExperienciaController.showAndWait();
		if (result.isPresent()){
			CVController.getCV().getExperiencias().add(result.get());
		}
	}

	@FXML
	void onEliminarAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("MiCV");
		alert.setHeaderText("¿Estás seguro de querer eliminarlo?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			CVController.getCV().getExperiencias().remove(experienciaTable.getSelectionModel().getSelectedItem());
		}
	}
	
}
