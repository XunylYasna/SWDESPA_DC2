package Database;

import Model.Playlist;
import Model.Song;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class SongListBuildTemp {

    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    public ArrayList<Song> getSongs(String filtercolumn, String value){
        int songID;
        String songTitle;
        String artist;
        String album;
        String genre;
        ArrayList<Song> songList = new ArrayList<>();
        Song song;



        String sql = "SELECT * FROM gulaplay.song;";


        try {
            if(filtercolumn != null && value != null){
                PreparedStatement prepStatement = myConn.prepareStatement("SELECT * FROM gulaplay.song WHERE " + filtercolumn + " LIKE ?");
                prepStatement.setString(1,value);
                resultSet = prepStatement.executeQuery();
            }

            else{
                statement = myConn.createStatement();
                resultSet = statement.executeQuery(sql);
            }

            while(resultSet.next()){
                songID = resultSet.getInt("SongID");
                songTitle = resultSet.getString("MusicTitle");
                artist = resultSet.getString("Artist");
                album = resultSet.getString("Genre");
                genre = resultSet.getString("Album");

                song = new Song(songID, songTitle,artist, album, genre);
                songList.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }

    public ArrayList<String> getColumn(String columnname){

        String artist;
        ArrayList<String> artistList = new ArrayList<>();

        String sql = "SELECT DISTINCT " + columnname + " FROM gulaplay.song;";

        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                artist = resultSet.getString(columnname);
                artistList.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistList;
    }
}
