package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SongFavoriteHandler {
    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    ResultSet resultSet;


    public SongFavoriteHandler(){

    }

    public void favSong(int songID, int userID) {
        String sql = "INSERT INTO songuserfavorites (songID, userID) values (?,?);";
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setInt(1, songID);
            prepStatement.setInt(2, userID);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
