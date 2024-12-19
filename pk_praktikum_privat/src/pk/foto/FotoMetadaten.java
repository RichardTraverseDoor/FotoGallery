package pk.foto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FotoMetadaten implements CsvExportable, Serializable {

	private static final long serialVersionUID = -9035073457788041250L;
	// Attribute
	private int breite;
	private int hoehe;
	private String kameraMarke;
	private String kameraModell;
	private LocalDateTime erstellungszeitpunkt;

	// Konstruktor
	public FotoMetadaten(int breite, int hoehe, String kameraMarke, String kameraModell,
			LocalDateTime erstellungszeitpunkt) {
		this.breite = breite;
		this.hoehe = hoehe;
		this.kameraMarke = kameraMarke;
		this.kameraModell = kameraModell;
		this.erstellungszeitpunkt = erstellungszeitpunkt;
	}

	// Methoden und Funktionen
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		return "\tGröße: " + breite + " x " + hoehe + "\n\tKamera: " + kameraMarke + " " + kameraModell
				+ "\n\tErstellungsdatum: " + erstellungszeitpunkt.format(formatter);
	}

	@Override
	public int hashCode() {
		return Objects.hash(breite, erstellungszeitpunkt, hoehe, kameraMarke, kameraModell);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FotoMetadaten other = (FotoMetadaten) obj;
		return breite == other.breite && Objects.equals(erstellungszeitpunkt, other.erstellungszeitpunkt)
				&& hoehe == other.hoehe && Objects.equals(kameraMarke, other.kameraMarke)
				&& Objects.equals(kameraModell, other.kameraModell);
	}

	@Override
	public String exportiereAlsCsv() {
		return this.breite + "," + this.hoehe + "," + this.kameraMarke + "," + this.kameraModell + ","
				+ this.erstellungszeitpunkt;
	}

}
