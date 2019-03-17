package Model;

import java.util.ArrayList;

public class Playlist {
    private int playlistID;
    private String playlistName;
    private String description;
    private ArrayList<Song> songList;
    private int author;

    public Playlist(int playlistID, String playlistName, String description, int author) {
        this.playlistID = playlistID;
        this.playlistName = playlistName;
        this.description = description;
        this.author = author;
        songList = new ArrayList<>();
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

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public int getAuthor() {
        return author;
    }
}
