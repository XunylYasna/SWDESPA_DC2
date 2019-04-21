package Database;

import Model.Playlist;
import Model.Song;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class SearchBuildTemp {
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;
    User user;
    Song song;
    Playlist playlist;
    ArrayList<User> userList;
    ArrayList<Song> songList;
    ArrayList<Playlist> playlistList;

    public ArrayList<User> SearchKeyinUser(String key){
        int userID;
        String firstName;
        String username;
        String lastName;
        String password;
        String email;
        String usertype;
        ArrayList<User> userListFound = new ArrayList<>();

        try {
            PreparedStatement prepStatement = myConn.prepareStatement("SELECT * FROM gulaplay.user");
           // prepStatement.setString(1,key);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                userID = resultSet.getInt("iduser");
                username = resultSet.getString("username");
                firstName = resultSet.getString("firstname");
                lastName = resultSet.getString("lastname");
                password = resultSet.getString("password");
                email = resultSet.getString("email");
                usertype = resultSet.getString("user_type");
                user = new User(userID, username,firstName, lastName, password, email,usertype);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
             for(int i=0; i<userList.size();i++){
                if(userList.get(i).getUsername().contains(key))
                    userListFound.add(userList.get(i));
             }
        return userListFound;
    }


    public ArrayList<Song> SearchKeyinSong(String key){
        int SongID;
        String MusicTitle;
        String Artist;
        String Genre;
        String Album;
        ArrayList<Song> songListFound = new ArrayList<>();

        try {
            PreparedStatement prepStatement = myConn.prepareStatement("SELECT * FROM gulaplay.song");
            // prepStatement.setString(1,key);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                SongID = resultSet.getInt("SongID");
                MusicTitle = resultSet.getString("MusicTitle");
                Artist = resultSet.getString("Artist");
                Genre = resultSet.getString("Genre");
                Album = resultSet.getString("Album");
                song = new Song(SongID,MusicTitle,Artist,Album,Genre);
                songList.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0; i<songList.size();i++){
            if(songList.get(i).getSongTitle().contains(key))
                songListFound.add(songList.get(i));
        }

        return songListFound;
    }

    public ArrayList<Playlist> SearchKeyinPlaylist(String key){
        int PlaylistID;
        int UserID;
        String PlaylistName;
        String PlaylistDescription;
        ArrayList<Playlist> playlistListFound = new ArrayList<>();

        try {
            PreparedStatement prepStatement = myConn.prepareStatement("SELECT * FROM gulaplay.playlist");
            // prepStatement.setString(1,key);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                PlaylistID = resultSet.getInt("PlaylistID");
                PlaylistName = resultSet.getString("PlaylistName");
                PlaylistDescription = resultSet.getString("PlaylistDescription");
                UserID = resultSet.getInt("UserID");
                playlist = new Playlist(PlaylistID,PlaylistName,PlaylistDescription,UserID);
                playlistList.add(playlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0; i<playlistList.size();i++){
            if(playlistList.get(i).getPlaylistName().contains(key))
                playlistListFound.add(playlistList.get(i));
        }
        return playlistListFound;
    }
    }
