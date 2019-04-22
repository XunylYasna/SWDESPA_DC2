package Database;

import Model.Playlist;

import java.sql.*;

public class PlaylistSongAddHandler {

    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    public void addSongtoPlaylist(int songID, int playlistID) {
        EventRecorder eventRecorder = null;
        String sql = "INSERT INTO songtoplaylist (SongID, PlaylistID)\n" +
                "values (?, ?)";// insert insert user query here
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setInt(1, songID);
            prepStatement.setInt(2, playlistID);
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        eventRecorder = new EventRecorder("User added a song to a playlist" , "user");

        return;
    }
}
