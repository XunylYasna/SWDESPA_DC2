package Controllers.sideAnchorControllers;

import Database.DatabaseConnection;
import Database.SearchBuildTemp;
import Model.Playlist;
import Model.Song;
import Model.User;
import com.jfoenix.controls.JFXListView;
import javafx.scene.control.Label;

import java.sql.*;
import java.util.ArrayList;

public class searchAnchorController {
    public Label searchLbl;
    public JFXListView searchArtistLv;
    public JFXListView searchSongLV;
    public JFXListView searchPlaylistLv;


    public searchAnchorController(Label searchLbl, JFXListView searchArtistLv, JFXListView searchSongLV, JFXListView searchPlaylistLv) {
        this.searchLbl = searchLbl;
        this.searchArtistLv = searchArtistLv;
        this.searchSongLV = searchSongLV;
        this.searchPlaylistLv = searchPlaylistLv;
    }

//    public void updateQuery(String query){
//
//    }

    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;
    User user;
    Song song;
    Playlist playlist;
    ArrayList<Song> artistList;
    ArrayList<Song> songList;
    ArrayList<Playlist> playlistList;

    public ArrayList<Song> SearchKeyinArtist(String key){
        int SongID;
        String MusicTitle;
        String Artist;
        String Genre;
        String Album;
        ArrayList<Song> artistListFound = new ArrayList<>();

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
                artistList.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0; i<artistList.size();i++){
            if(artistList.get(i).getArtist().contains(key))
                artistListFound.add(artistList.get(i));
        }
        for(int k=0; k<artistListFound.size();k++){
            searchArtistLv.getItems().add(artistListFound.get(k));
        }
        return artistListFound;
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
        for(int k=0; k<songListFound.size();k++){
            searchSongLV.getItems().add(songListFound.get(k));
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
        for(int k=0; k<playlistListFound.size();k++){
            searchPlaylistLv.getItems().add(playlistListFound.get(k));
        }
        return playlistListFound;
    }

}
