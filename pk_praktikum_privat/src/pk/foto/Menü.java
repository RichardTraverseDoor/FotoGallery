package pk.foto;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.MetadataException;

import pk.foto.util.FotoUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.InputMismatchException;

public class Menü {
	// Attribute

	FotoVerwaltung fotoverwaltung = new FotoVerwaltung();

	// Kommentar ausklammern zum Ausführen der Tests
	// Tester.testeProgramm();

	public void starteMenue() {

		Scanner sc = new Scanner(System.in);

		int eingabe = 0;

		while (eingabe != 7) {
			try {
				System.out.println("  Foto-App \n\n" + "\t1. Album hinzufügen\n" + "\t2. Drucke alle Alben\n"
						+ "\t3. Drucke Album mit Name\n" + "\t4. CSV-Export\n" + "\t5. Lade aus Datei\n"
						+ "\t6. Speichere in Datei\n" + "\t7. Beenden\n\n" + "\tBitte Aktion wählen: \n");
				eingabe = sc.nextInt();
				if (eingabe < 1 || eingabe > 7) {
					throw new IllegalArgumentException("Falscher Eingabewert, bitte wiederholen!");
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Du musst eine ganze Zahl eingeben!");
				sc.nextLine();
			}
			switch (eingabe) {
			case 1:
				try {
					fotoverwaltung.addAlbum(eingabeAlbum());
				} catch (AlbumVorhandenException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 2:
				System.out.println("Gewählt: 2");

				try {
					fotoverwaltung.druckeAlleAlben(System.out);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(eingabe);
				break;
			case 3:
				System.out.println("Gewählt: 3");
				String name = albumMitName();
				if (fotoverwaltung.findeAlbumMitName(name) != null) {
					try {
						fotoverwaltung.findeAlbumMitName(name).drucke(System.out);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 4:
				System.out.println("Gewählt: 4");
				boolean abgeschlossen = false;
				while (!abgeschlossen) {
					String dateiname = JOptionPane.showInputDialog(null, "Gib den Dateinamen ein");

					if (dateiname.isBlank()) {
						JOptionPane.showInternalMessageDialog(null, "Der Dateiname darf nicht leer sein");
						continue;
					}
					int auswahl = JOptionPane.showConfirmDialog(null,
							"Möchten Sie die existierende datei wirklich überschreiben?");
					if (auswahl == 0) {
						try {
							fotoverwaltung.exportiereEintraegeAlsCsv(new File(dateiname));
						} catch (IOException e) {
							JOptionPane.showInternalMessageDialog(null, "Ein Fehler ist aufgetreten");

						}
					} else {
						continue;
					}
					abgeschlossen = true;
				}
				break;
			case 5:
				System.out.println("Gewählt: 5");
				try {
					fotoverwaltung.ladenAusDatei(new File("serialisiertTest"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 6:
				System.out.println("Gewählt: 6");
				try {
					File f = new File("serialisiertTest");
					fotoverwaltung.speichernInDatei(f);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 7:
				System.out.println("Gewählt: 7");
				System.out.println("Anwendung wird beendet");
				return;

			}

		}

	}

	public static Album eingabeAlbum() {

		String albumname = null;
		String besitzer = "";
		Album alb;

		try {
			albumname = JOptionPane.showInputDialog(null, "Gib den Namen des Albums ein:");
			if (albumname.isBlank()) {
				throw new IllegalArgumentException("Der Albumname darf nicht leer sein!");
			}
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			eingabeAlbum();
		}
		besitzer = JOptionPane.showInputDialog(null, "Gib den Besitzer des Albums ein:");

		alb = new Album(albumname, besitzer);
		int showConfirmDialog;
		do {
			try {
				alb.addFoto(eingabeFoto());
			} catch (FotoMetadatenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			showConfirmDialog = JOptionPane.showConfirmDialog(null,
					"Möchtest du ein weiteres Foto zum Album hinzufügen?", "choose one", JOptionPane.YES_NO_OPTION);

		} while (showConfirmDialog != 1);

		return alb;

	}

	public static Foto eingabeFoto() {

		String fotoname = "";
		try {
			fotoname = JOptionPane.showInputDialog(null, "Gib den Namen des Fotos ein:");
			if (fotoname.isBlank()) {
				throw new IllegalArgumentException("Der Fotoname darf nicht leer sein!");
			}
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
			eingabeFoto();
		}

		String dateiname = JOptionPane.showInputDialog(null, "Gib den Dateinamen des Fotos ein:");

		while (!Files.exists(Path.of("images/" + dateiname))) {
			JOptionPane.showMessageDialog(null, "Du musst den Namen einer bereits existierenden Datei eingeben!",
					"Fehler", JOptionPane.ERROR_MESSAGE);
			dateiname = JOptionPane.showInputDialog(null, "Gib den Dateinamen des Fotos ein:");
		}

		Foto f1 = new Foto(fotoname, dateiname,
				new FotoMetadaten(1, 1, "Beispielmarke", "Beispielmodell", LocalDateTime.now()));

		try {

			f1 = new Foto(fotoname, dateiname, FotoUtil.readMetadata(new File("images/" + dateiname)));
			f1.setMetadaten(new File("images/" + dateiname));
		} catch (ImageProcessingException | MetadataException | NullPointerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f1;
	}

	public static String albumMitName() {
		String albenname = JOptionPane.showInputDialog(null,
				"Gib den Namen des Albums ein, dessen Fotos du ausgeben möchtest:");
		return albenname;
	}

}
