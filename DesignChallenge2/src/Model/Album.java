package Model;

import java.io.File;

public class Album {
    int idAlbum;
    String albumName;
    File songCover;

    public Album(int idAlbum, String albumName, File songCover) {
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

    public File getSongCover() {
        return songCover;
    }
}
