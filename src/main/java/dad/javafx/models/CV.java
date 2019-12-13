package dad.javafx.models;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement
public class CV {

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>();
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>();
	private ListProperty<Titulo> formacion = new SimpleListProperty<Titulo>(FXCollections.observableArrayList());
	private ListProperty<Experiencia> experiencias = new SimpleListProperty<Experiencia>(
			FXCollections.observableArrayList());
	private ListProperty<Conocimiento> habilidades = new SimpleListProperty<Conocimiento>(
			FXCollections.observableArrayList());

	public void save(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(CV.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(this, file);
	}

	public static CV load(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(CV.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (CV) unmarshaller.unmarshal(file);
	}

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}
	
	@XmlElement
	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}

	@XmlElement
	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}

	public final ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}

	@XmlElement
	public final ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}

	public final void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
	}

	public final ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}

	@XmlElement
	public final ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}

	public final void setExperiencias(final ObservableList<Experiencia> experiencias) {
		this.experienciasProperty().set(experiencias);
	}

	public final ListProperty<Conocimiento> habilidadesProperty() {
		return this.habilidades;
	}

	@XmlElement
	public final ObservableList<Conocimiento> getHabilidades() {
		return this.habilidadesProperty().get();
	}

	public final void setHabilidades(final ObservableList<Conocimiento> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}

}
