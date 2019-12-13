package dad.javafx.models;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Email {

	private StringProperty direccion = new SimpleStringProperty();

	public Email(String direccion) {
		this.direccion.set(direccion);
	}
	
	public Email() {
	}

	public final StringProperty direccionProperty() {
		return this.direccion;
	}

	@XmlAttribute
	public final String getDireccion() {
		return this.direccionProperty().get();
	}

	public final void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}

	@Override
	public String toString() {
		return direccion.get();
	}
	
	

}
