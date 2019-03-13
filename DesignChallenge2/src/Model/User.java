package Model;

import java.util.ArrayList;

public class User {
    private int userID;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;

    private ArrayList<Playlist> playlistList;

    public User(int userID, String username, String firstname, String lastname, String password, String email, ArrayList<Playlist> playlistList) {
        this.userID = userID;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.playlistList = playlistList;
    }
}
