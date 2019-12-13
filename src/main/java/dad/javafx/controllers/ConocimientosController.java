package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.models.Conocimiento;
import dad.javafx.models.Nivel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
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
		nivelColumn.setCellValueFactory(v -> v.getValue().nivelProperty());
	}

	public HBox getView() {
		return root;
	}

    @FXML
    void onAgregarConocimientoAction(ActionEvent event) {

    }

    @FXML
    void onAgregarIdiomaAction(ActionEvent event) {

    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }

    @FXML
    void onSeleccionarConociminetoAction(MouseEvent event) {

    }

}
