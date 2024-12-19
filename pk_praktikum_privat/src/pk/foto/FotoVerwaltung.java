package pk.foto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import java.util.*;
import java.util.Iterator;

public class FotoVerwaltung implements Serializable {

	/**
	 * 
	 */

	//Menu menue;
	private static final long serialVersionUID = 5270575059913949411L;
	// Attribute
	private TreeSet<Album> alben;

	// Konstruktor
	public FotoVerwaltung() {
		this.alben = new TreeSet<>(Comparator.comparing(Album::getName));

	}

	// Methoden und Funktionen
	public void addAlbum(Album album) throws AlbumVorhandenException {
		if (findeAlbumMitName(album.getName()) != null) {
			throw new AlbumVorhandenException("Der Albumname " + album.getName() + " ist schon vorhanden!");
		}
		alben.add(album);

	}

	public void druckeAlleAlben(OutputStream stream) throws IOException {

		OutputStreamWriter writer = new OutputStreamWriter(stream);

		int i = 1;
		Iterator<Album> it = alben.iterator();

		/*
		 * while(it.hasNext()) { Album a = it.next(); String header =
		 * "=== Album "+(i)+" ===\n"; writer.write(header.toCharArray());
		 * writer.flush(); a.drucke(stream); i++; System.out.println("Letzte Schleife");
		 * }
		 */

		for (Album a : alben) {

			String header = "\n=== Album " + (i) + " ===\n";
			writer.write(header);
			writer.flush();
			a.drucke(stream);
			i++;
			writer.flush();

		}

	}

	public int gibAnzahlAlben() {
		return Album.getAlbenanzahl();
	}

	public Album[] gibAlleAlben() {
		Album[] alb = new Album[alben.size()];
		alb = alben.toArray(alb);
		return alb;

	}

	public Album findeAlbumMitName(String name) {
		Album a = null;

		for (Album alb : alben) {
			if (alb.getName().equals(name)) {
				a = alb;
				return alb;
			}
		}

		return null;
	}

	public void exportiereEintraegeAlsCsv(File datei) throws IOException {

		try (FileWriter fWriter = new FileWriter(datei); PrintWriter pWriter = new PrintWriter(fWriter)) {
			String kopfzeile = "Album-ID,Name,Besitzer,Foto-ID,Name,Dateiname,Breite,Höhe,Kameramarke,Kameramodell,Erstellungszeitpunkt";
			pWriter.println(kopfzeile);
			Foto[] hilfsarray;
			for (Album a : alben) {
				hilfsarray = a.getFotos();
				for (int i = 0; i < hilfsarray.length; i++) {
					pWriter.println((a.exportiereAlsCsv() + "," + hilfsarray[i].exportiereAlsCsv()));
				}
			}

		}

	}

	public void exportiereEintraegeAlsCsvNio(File datei) throws IOException {

		String kopfzeile = "Album-ID,Name,Besitzer,Foto-ID,Name,Dateiname,Breite,Höhe,Kameramarke,Kameramodell,Erstellungszeitpunkt";

		List<String> csvZeilen = new ArrayList<>();
		csvZeilen.add(kopfzeile);
		Foto[] hilfsarray;
		for (Album a : alben) {
			// csvZeilen.add(a.exportiereAlsCsv());
			hilfsarray = a.getFotos();
			// for(int i = 0; i < hilfsarray.length; i++) {
			// csvZeilen.add(a.exportiereAlsCsv()+","+hilfsarray[i].exportiereAlsCsv());
			// }
			for (Foto foto : hilfsarray) {
				csvZeilen.add(a.exportiereAlsCsv() + "," + foto.exportiereAlsCsv());
			}
		}
		Path path = datei.toPath();

		Files.write(path, csvZeilen);

	}

	public void ladenAusDatei(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis);) {
			
			alben.add( (Album) ois.readObject());
		}
		
	}

	public void speichernInDatei(File file) throws IOException, ClassNotFoundException {

		
		try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos);) {

			for (Album a : alben) {
				oos.writeObject(a);
			}

		}
		
		
		
		
	}

}
