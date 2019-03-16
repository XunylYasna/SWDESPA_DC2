package Database;

import Model.Song;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class SongDeleteHandler {
    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    ResultSet resultSet;


    public SongDeleteHandler(){

    }

    public void deleteSong(int songID) {
        String sql = "DELETE FROM song WHERE SongID = ?;";// insert insert user query here
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setInt(1, songID);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
