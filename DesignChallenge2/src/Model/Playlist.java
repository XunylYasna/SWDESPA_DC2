package Model;

import java.util.ArrayList;

public class Playlist {
    private int playlistID;
    private String playlistName;
    private ArrayList<Song> songList = new ArrayList<>();
    private User author;
    private String description;
}
