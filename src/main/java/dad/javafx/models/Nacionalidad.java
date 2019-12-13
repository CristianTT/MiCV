package dad.javafx.models;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nacionalidad {

	private StringProperty denominacion = new SimpleStringProperty();
	
	public Nacionalidad(String nacionalidad) {
		this.denominacion.set(nacionalidad);
	}
	
	public Nacionalidad() {
	}

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

	@Override
	public String toString() {
		return denominacion.get();
	}

}
