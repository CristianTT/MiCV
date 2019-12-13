package dad.javafx.models;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Conocimiento extends Idioma {

	private StringProperty denominacion = new SimpleStringProperty();
	private ObjectProperty<Nivel> nivel = new SimpleObjectProperty<Nivel>();

	public final StringProperty denominacionProperty() {
		return this.denominacion;
	}

	@XmlAttribute
	public final String getDenominacion() {
		return this.denominacionProperty().get();
	}

	public final void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}

	public final ObjectProperty<Nivel> nivelProperty() {
		return this.nivel;
	}

	@XmlAttribute
	public final Nivel getNivel() {
		return this.nivelProperty().get();
	}

	public final void setNivel(final Nivel nivel) {
		this.nivelProperty().set(nivel);
	}

}
