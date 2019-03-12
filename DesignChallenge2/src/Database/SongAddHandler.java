package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class SongAddHandler {

    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    {
        try {
            statement = myConn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String addSong(String name, String artist, String album, String genre, File photo, File song) {

//      temp
        String sql = "INSERT INTO user (firstname, lastname, username, password, email)\n" +
                "values (?, ?, ?, ?, ?, ?)";// insert insert user query here
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setString(1, name);
            prepStatement.setString(2, artist);
            prepStatement.setString(3, album);
            prepStatement.setString(4, genre);
            FileInputStream input = new FileInputStream(photo);
            prepStatement.setBinaryStream(5,input);
//            prepStatement.setString(5, input);
            input = new FileInputStream(song);
            prepStatement.setBinaryStream(6,input);
//            prepStatement.setString(6, songlocation);
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "Registration Successful.";


    }
}
