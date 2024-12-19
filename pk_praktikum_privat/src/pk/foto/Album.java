package pk.foto;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.MetadataException;

import pk.foto.util.FotoUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Album extends Fachobjekt implements Comparable<Album>, CsvExportable, Serializable {

	private static final long serialVersionUID = 7034563142640856595L;
	// Attribute
	private String besitzer;
	private ArrayList<Foto> fotos;
	private static int anzahlAlben = 0;
	private int albennummer;

	// Konstruktor
	public Album(String name, String besitzer) {
		super(name);
		this.besitzer = besitzer;
		// this.fotos = new Foto[2]; // Kapazität von 2, wie in der Aufgabe verlangt
		this.fotos = new ArrayList<Foto>();
		this.albennummer = ++anzahlAlben; // so wird jedes Album durchnummeriert

	}

	// Methoden und Funktionen
	public void addFoto(Foto foto) throws FotoMetadatenException {

		try {
			File f = new File("images/" + foto.getDateiname());
			foto.setMetadaten(FotoUtil.readMetadata(f));

			// foto.setMetadaten(FotoUtil.readMetadata(new
			// File("images/"+foto.getDateiname())));
		} catch (ImageProcessingException | MetadataException | NullPointerException | IOException e) {
			throw new FotoMetadatenException("Hoppla, die Datei existiert wohl nicht!");
		}

		fotos.add(foto);
	}

	public Foto[] getFotos() {
		Foto[] fot = new Foto[fotos.size()];
		fot = fotos.toArray(fot);
		return fot;
	}

	public void drucke(OutputStream stream) throws IOException {

		OutputStreamWriter writer = new OutputStreamWriter(stream);
		int counter = 1;
		String erg = this.toString();

		writer.write(erg.toCharArray());

		for (Foto f : fotos) {

			String header = "\n=== Foto " + counter++ + " ===\n";
			writer.write(header);
			writer.flush();
			f.drucke(stream);
			writer.flush();
		}
	}

	public static int getAlbenanzahl() // liefert die Gesamtanzahl der in der Fotoverwaltung vorhandenen Alben zurück
	{
		return anzahlAlben;
	}

	public int getAlbennummer() // liefert die Albennummer des spezifischen Albums zurück
	{
		return this.albennummer;
	}

	@Override
	public String toString() {
		return "Name: " + super.getName() + "\nBesitzer: " + this.besitzer;
	}

	@Override
	public int compareTo(Album other) {
		return this.getName().compareTo(other.getName());
	}

	@Override
	public String exportiereAlsCsv() {
		return super.exportiereAlsCsv() + "," + this.besitzer;
	}

}
