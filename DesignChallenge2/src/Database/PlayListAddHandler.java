package Database;

import Model.Playlist;
import Model.Song;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class PlayListAddHandler {
    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;


    public Playlist addPlaylist(String playlistName, String playlistDescription, int userID) {

//      temp
        Playlist playlist = null;
        String sql = "INSERT INTO playlist (PlaylistName, PlaylistDescription, UserID)\n" +
                "values (?, ?, ?)";// insert insert user query here
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setString(1, playlistName);
            prepStatement.setString(2, playlistDescription);
            prepStatement.setInt(3, userID);
            prepStatement.execute();

            statement = myConn.createStatement();
            sql = "Select * from playlist where PlaylistID=LAST_INSERT_ID();";
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(sql);

            int newPlaylistID = 0;

            while (resultSet.next()){
                newPlaylistID = resultSet.getInt("PlaylistID");
            }

            playlist = new Playlist(newPlaylistID, playlistName, playlistDescription, userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlist;
    }
}
