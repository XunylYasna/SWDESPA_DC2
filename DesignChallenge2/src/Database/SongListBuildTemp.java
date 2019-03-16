package Database;

import Model.Playlist;
import Model.Song;
import Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SongListBuildTemp {

    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    public ArrayList<Song> getSongs(){
        int songID;
        String songTitle;
        String artist;
        String album;
        String genre;
        ArrayList<Song> songList = new ArrayList<>();
        Song song;



        String sql = "SELECT * FROM gulaplay.song;";

        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(sql);
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
}
