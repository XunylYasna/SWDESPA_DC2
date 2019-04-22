package Model;

import java.awt.image.BufferedImage;
import java.io.File;

public class Album {
    int idAlbum;
    String albumName;
    BufferedImage songCover;

    public Album(int idAlbum, String albumName, BufferedImage songCover) {
        this.idAlbum = idAlbum;
        this.albumName = albumName;
        this.songCover = songCover;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public String getAlbumName() {
        return albumName;
    }

    public BufferedImage getSongCover() {
        return songCover;
    }
}
