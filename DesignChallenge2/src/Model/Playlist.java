package Model;

import java.util.ArrayList;

public class Playlist {
    private int playlistID;
    private String playlistName;
    private String description;
    private int author;

    public Playlist(int playlistID, String playlistName, String description, int author) {
        this.playlistID = playlistID;
        this.playlistName = playlistName;
        this.description = description;
        this.author = author;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getDescription() {
        return description;
    }

    public int getAuthor() {
        return author;
    }
}
