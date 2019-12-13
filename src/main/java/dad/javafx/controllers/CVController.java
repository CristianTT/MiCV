package dad.javafx.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.MiCVApp;
import dad.javafx.models.CV;
import dad.javafx.models.Contacto;
import dad.javafx.models.Personal;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class CVController implements Initializable {

	@FXML
	private VBox root;
	@FXML
	private Tab personalTab, contactoTab, formacionTab, experienciaTab, conocimientosTab;

	private PersonalController personalController;
	private ContactoController contactosController;
	private FormacionController formacionController;
	private ExperienciaController experienciaController;
	private ConocimientosController conocimientosController;

	private static CV cv = new CV();
	private File fileToSave = null;

	public CVController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CVView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			cv.setPersonal(new Personal());
			cv.setContacto(new Contacto());

			personalController = new PersonalController();
			contactosController = new ContactoController();
			formacionController = new FormacionController();
			experienciaController = new ExperienciaController();
			conocimientosController = new ConocimientosController();

			personalTab.setContent(personalController.getView());
			contactoTab.setContent(contactosController.getView());
			formacionTab.setContent(formacionController.getView());
			experienciaTab.setContent(experienciaController.getView());
			conocimientosTab.setContent(conocimientosController.getView());
		} catch (IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("MiCVApp");
			alert.setHeaderText("No se puede iniciar la aplicación.");
			alert.showAndWait();
			Platform.exit();
		}
	}

	public static CV getCV() {
		return cv;
	}

	public VBox getView() {
		return root;
	}

	@FXML
	void onAbrirAction(ActionEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			ExtensionFilter extFilterXML = new ExtensionFilter("XML", "*.xml");
			ExtensionFilter extFilterAll = new ExtensionFilter("Todos los archivos", "*.*");
			fileChooser.getExtensionFilters().add(extFilterXML);
			fileChooser.getExtensionFilters().add(extFilterAll);
			fileToSave = fileChooser.showOpenDialog(MiCVApp.stage);
			CV nuevo = CV.load(fileToSave);
			cv.getPersonal().setIdentificacion(nuevo.getPersonal().getIdentificacion());
			cv.getPersonal().setNombre(nuevo.getPersonal().getNombre());
			cv.getPersonal().setApellidos(nuevo.getPersonal().getApellidos());
			cv.getPersonal().setDireccion(nuevo.getPersonal().getDireccion());
			cv.getPersonal().setCodigoPostal(nuevo.getPersonal().getCodigoPostal());
			cv.getPersonal().setFechaNacimiento(nuevo.getPersonal().getFechaNacimiento());
			cv.getPersonal().setLocalidad(nuevo.getPersonal().getLocalidad());
			cv.getPersonal().setNacionalidades(nuevo.getPersonal().nacionalidadesProperty());
			cv.getPersonal().setPais(nuevo.getPersonal().getPais());
			cv.getContacto().setEmails(nuevo.getContacto().emailsProperty());
			cv.getContacto().setTelefonos(nuevo.getContacto().telefonosProperty());
			cv.getContacto().setWebs(nuevo.getContacto().websProperty());
			cv.setFormacion(nuevo.formacionProperty());
			cv.setExperiencias(nuevo.experienciasProperty());
			cv.setHabilidades(nuevo.habilidadesProperty());
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("MiCVApp");
			alert.setHeaderText("Error al cargar el CV.");
			alert.showAndWait();
		}
	}

	@FXML
	void onAcercaDeAction(ActionEvent event) {

	}

	@FXML
	void onGuardarAction(ActionEvent event) {
		if (fileToSave != null) {
			try {
				cv.save(fileToSave);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("MiCVApp");
				alert.setHeaderText("Error al guardar el CV.");
				alert.showAndWait();
			}
		} else {
			onGuardarComoAction(null);
		}
	}

	@FXML
	void onGuardarComoAction(ActionEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			ExtensionFilter extFilterXML = new ExtensionFilter("XML", "*.xml");
			ExtensionFilter extFilterAll = new ExtensionFilter("Todos los archivos", "*.*");
			fileChooser.getExtensionFilters().add(extFilterXML);
			fileChooser.getExtensionFilters().add(extFilterAll);
			fileToSave = fileChooser.showSaveDialog(MiCVApp.stage);
			cv.save(fileToSave);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("MiCVApp");
			alert.setHeaderText("Error al guardar el CV.");
			alert.showAndWait();
		}
	}

	@FXML
	void onNuevoAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("MiCV");
		alert.setHeaderText("¿Estás seguro de crear un nuevo CV?");
		alert.setContentText("Si tienes cambios sin guardar se perderán.");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			cv.getPersonal().setIdentificacion("");
			cv.getPersonal().setNombre("");
			cv.getPersonal().setApellidos("");
			cv.getPersonal().setPais("");
			cv.getPersonal().setLocalidad("");
			cv.getPersonal().setDireccion("");
			cv.getPersonal().setCodigoPostal("");
			cv.getPersonal().setFechaNacimiento(null);
			cv.getPersonal().nacionalidadesProperty().clear();
			cv.getContacto().telefonosProperty().clear();
			cv.getContacto().emailsProperty().clear();
			cv.getContacto().websProperty().clear();
			cv.formacionProperty().clear();
			cv.experienciasProperty().clear();
			cv.habilidadesProperty().clear();
		}
	}

	@FXML
	void onSalirAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("MiCV");
		alert.setHeaderText("¿Estás seguro de querer salir?");
		alert.setContentText("Si tienes cambios sin guardar se perderán.");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Platform.exit();
		}
	}

}
