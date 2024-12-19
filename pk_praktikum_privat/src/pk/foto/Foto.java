package pk.foto;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.MetadataException;

import pk.foto.util.FotoUtil;

public class Foto extends Fachobjekt implements CsvExportable, Serializable {

	private static final long serialVersionUID = -69325018256172806L;
	// Attribute
	private String dateiName;
	private FotoMetadaten metadaten;

	// Konstruktor
	public Foto(String name, String dateiName, FotoMetadaten metadaten) {
		super(name);
		this.dateiName = dateiName;
		this.metadaten = metadaten;
	}

	public Foto(String name, String dateiName) {
		super(name);
		this.dateiName = dateiName;
		this.metadaten = metadaten;
	}

	// Funktionen und Methoden
	public void drucke(OutputStream stream) throws IOException {

		OutputStreamWriter writer = new OutputStreamWriter(stream);
		String erg = this.toString();

		writer.write(erg);

		writer.write(metadaten.toString());

		writer.flush();

		// System.out.println(this.toString());
		// System.out.println(metadaten.toString());

	}

	@Override
	public String toString() {
		return "\tFotoname: " + super.getName() + "\n\tDateiname: " + dateiName;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(this.getID(), this.getName(), dateiName);
		return result;

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		return Objects.equals(getID(), other.getID()) && Objects.equals(this.getName(), other.getName())
				&& Objects.equals(dateiName, other.dateiName);

	}

	@Override
	public String exportiereAlsCsv() {
		return super.exportiereAlsCsv() + "," + this.dateiName + "," + this.metadaten.exportiereAlsCsv();
	}

	public String getDateiname() {

		return this.dateiName;
	}

	public void setMetadaten(FotoMetadaten readMetadata) {
		this.metadaten = readMetadata;

	}

	public void setMetadaten(File file)
			throws ImageProcessingException, MetadataException, NullPointerException, IOException {
		this.metadaten = FotoUtil.readMetadata(file);
	}

}
