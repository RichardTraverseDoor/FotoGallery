package pk.foto;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class FotoVerwaltungArray {

    // Attribute
    private Album[] alben;
    private int albumCount;

    // Konstruktor
    public FotoVerwaltungArray() {
        this.alben = new Album[2];  // Initiale Kapazität von 2
        this.albumCount = 0;
    }

    // Methoden und Funktionen
    public void addAlbum(Album album) {
        if (albumCount == alben.length) {
            alben = Arrays.copyOf(alben, alben.length * 2);
        }
        alben[albumCount++] = album;
    }

    public void druckeAlleAlben(OutputStream out) {
        PrintWriter writer = new PrintWriter(out);

        for (int i = 0; i < albumCount; i++) {
            writer.println("=== Album " + (i + 1) + " ===");
            alben[i].drucke(out);
        }

        writer.flush();
    }

    public int gibAnzahlAlben() {
        return albumCount;
    }

    public Album[] gibAlleAlben() {
        return Arrays.copyOf(alben, albumCount);
    }

    public Album findeAlbumMitName(String name) {
        for (int i = 0; i < albumCount; i++) {
            if (alben[i].toString().contains(name)) {
                return alben[i];
            }
        }
        return null;
    }
}
