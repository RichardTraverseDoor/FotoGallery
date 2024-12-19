package pk.foto;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Fachobjekt implements CsvExportable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1940315785076019058L;
	// Attribute
	private String name;
	private String id;

	// Konstruktor
	public Fachobjekt(String name) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
	}

	public String getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	// Methoden
	public abstract void drucke(OutputStream stream) throws IOException;

	public abstract String toString();

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fachobjekt other = (Fachobjekt) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String exportiereAlsCsv() {
		return this.getID() + "," + this.getName();
	}

}
