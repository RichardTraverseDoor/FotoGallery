package pk.foto;

import java.time.LocalDateTime;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.MetadataException;

import pk.foto.util.FotoUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Tester {
	public static void main(String[] args) {

		Menü einMenue = new Menü();

		einMenue.starteMenue();
		// testeProgramm() {
		/*
		 * 
		 * 
		 * // Album 1 Album album1 = new Album("Naturbilder", "Sonja"); try {
		 * album1.addFoto(new Foto("Wiese", "images/wiese.jpg", new FotoMetadaten(1024,
		 * 1024, "NIKON", "D750", LocalDateTime.now()))); } catch
		 * (FotoMetadatenException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } try { album1.addFoto(new Foto("Wald",
		 * "images/wald.jpg", new FotoMetadaten(1024, 1024, "NIKON", "D750",
		 * LocalDateTime.now()))); } catch (FotoMetadatenException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * // Album 2 System.out.println("Teste addFoto"); Album album2 = new
		 * Album("Menschen", "Aurel"); try { album2.addFoto(new Foto("Meer",
		 * "images/meer.jpg", new FotoMetadaten(1024, 1024, "NIKON", "D750",
		 * LocalDateTime.now()))); } catch (FotoMetadatenException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } System.out.println("");
		 * 
		 * System.out.println("Teste addAlbum"); try { verwaltung.addAlbum(album1);
		 * verwaltung.addAlbum(album2); } catch(AlbumVorhandenException e) {
		 * System.out.println(e.getMessage()); }
		 * 
		 * 
		 * 
		 * System.out.println("Teste Funktion gibAlleAlben()");
		 * verwaltung.gibAlleAlben(); System.out.println("");
		 * 
		 * System.out.println("Teste Funktion gibAnzahlAlben()");
		 * System.out.println("Anzahl Alben: " + verwaltung.gibAnzahlAlben());
		 * System.out.println("");
		 * 
		 * System.out.println("Teste Funktion getID()");
		 * System.out.println(album1.getID()); System.out.println("");
		 * 
		 * System.out.println("Teste Funktion findeAlbumMitName('Naturbilder')");
		 * System.out.println(verwaltung.findeAlbumMitName("Naturbilder"));
		 * System.out.println("");
		 * 
		 * System.out.println("Teste Funktion findeAlbumMitName('Menschen')");
		 * System.out.println(verwaltung.findeAlbumMitName("Menschen"));
		 * System.out.println("");
		 * 
		 * System.out.println("Teste Funktion findeAlbumMitName('Naturbilder')");
		 * System.out.println(verwaltung.findeAlbumMitName("Naturbilder"));
		 * System.out.println("");
		 * 
		 * System.out.println("Teste Funktion druckeAlleAlben()"); //
		 * verwaltung.druckeAlleAlben(); System.out.println("");
		 * 
		 * System.out.println("Teste Funktion gibAnzahlAlben()");
		 * System.out.println(verwaltung.gibAnzahlAlben()); System.out.println("");
		 * 
		 * System.out.println("Teste Funktion album.drucke()"); // album1.drucke();
		 * System.out.println("");
		 * 
		 * 
		 * System.out.println("Teste Funktion f1.drucke()"); Foto f1 = new Foto("Wüste",
		 * "C:/", new FotoMetadaten(2, 2, "LOL", "XD", LocalDateTime.now())); //
		 * f1.drucke(); System.out.println("");
		 * 
		 * System.out.println("TEST FÜR DRUCKE ALLE ALBEN"); //
		 * verwaltung.druckeAlleAlben();
		 * 
		 * 
		 * 
		 * System.out.println("-------"); // album1.drucke(); System.out.println(); //
		 * album2.drucke();
		 * 
		 * System.out.println("-------------------------------------");
		 * System.out.println("Test compareTo(Album other) für dasselbe Objekt:");
		 * System.out.println(album1.compareTo(album1));
		 * 
		 * System.out.
		 * println("Test compareTo(Album other)für ein Objekt derselben Instanz:");
		 * System.out.println(album1.compareTo(album2));
		 * 
		 * System.out.println("Test equals(Album other) für dasselbe Objekt:");
		 * System.out.println(album1.equals(album1));
		 * 
		 * System.out.
		 * println("Test equals(Album other) für ein Objekt derselben Instanz:");
		 * System.out.println(album1.equals(album2));
		 * 
		 * System.out.println("Test getFotos()"); Foto [] fotoTest = album1.getFotos();
		 * 
		 * for(Foto f: fotoTest) { // f.drucke(); }
		 * System.out.println("Test compareTo(Album other) für dasselbe Objekt:");
		 * 
		 * String input = "35835723323_b3ed4bf5d1_o.jpg";
		 * 
		 * File f = new File("images/"+input);
		 * 
		 * Foto lel; try { lel = new Foto("Strand", "Banane", FotoUtil.readMetadata(f));
		 * album1.addFoto(lel); } catch (ImageProcessingException | MetadataException |
		 * NullPointerException | IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (FotoMetadatenException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 
		FotoVerwaltung fv = new FotoVerwaltung();
		Album a1 = new Album("Albumname1", "Besitzer1");
		try {
			fv.addAlbum(a1);
		} catch (AlbumVorhandenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Foto f1;
		try {
			File f = new File("images/35835723323_b3ed4bf5d1_o.jpg");
			f1 = new Foto("Fotoname1", "images/35835723323_b3ed4bf5d1_o.jpg", FotoUtil.readMetadata(f));

			f1.setMetadaten(FotoUtil.readMetadata(f));
			a1.addFoto(f1);
		} catch (FotoMetadatenException | ImageProcessingException | MetadataException | NullPointerException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fv.druckeAlleAlben(System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fv.findeAlbumMitName("Albumname1");

		try {
			fv.exportiereEintraegeAlsCsv(new File("test"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fv.ladenAusDatei(new File("test"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fv.speichernAusDatei(new File("test"));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Alle Tests bestanden");
		*/
	}

}
