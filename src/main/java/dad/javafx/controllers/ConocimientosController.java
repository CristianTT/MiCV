package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.models.Conocimiento;
import dad.javafx.models.Nivel;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;

public class ConocimientosController implements Initializable {
	
	@FXML
    private HBox root;
    @FXML
    private TableView<Conocimiento> conocimientosTable;
    @FXML
    private TableColumn<Conocimiento, String> denominacionColumn;
    @FXML
    private TableColumn<Conocimiento, Nivel> nivelColumn;
    @FXML
    private Button eliminarBtn;
    
    public ConocimientosController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conocimientosTable.itemsProperty().bind(CVController.getCV().habilidadesProperty());
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelColumn.setCellValueFactory(v -> v.getValue().nivelProperty());
		nivelColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));

		eliminarBtn.disableProperty().bind(conocimientosTable.getSelectionModel().selectedItemProperty().isNull());
	}

	public HBox getView() {
		return root;
	}

    @FXML
    void onAgregarConocimientoAction(ActionEvent event) {
		NewConocimientoController newConocimientoController = new NewConocimientoController();
		Optional<Conocimiento> result = newConocimientoController.showAndWait();
		if (result.isPresent()){
			CVController.getCV().getHabilidades().add(result.get());
		}
    }

    @FXML
    void onAgregarIdiomaAction(ActionEvent event) {
		NewIdiomaController newIdiomaController = new NewIdiomaController();
		Optional<Conocimiento> result = newIdiomaController.showAndWait();
		if (result.isPresent()){
			CVController.getCV().getHabilidades().add(result.get());
		}
    }

    @FXML
    void onEliminarAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("MiCV");
		alert.setHeaderText("¿Estás seguro de querer eliminarlo?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			CVController.getCV().getHabilidades().remove(conocimientosTable.getSelectionModel().getSelectedItem());
		}
    }

}
