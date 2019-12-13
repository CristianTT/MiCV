package dad.javafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.javafx.models.Titulo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorColumn.setCellValueFactory(v -> v.getValue().organizadorProperty());
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
    void onSeleccionarFormacionAction(MouseEvent event) {

    }

}
