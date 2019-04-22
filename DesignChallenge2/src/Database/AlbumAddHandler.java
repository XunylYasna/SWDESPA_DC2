package Database;

import Model.Playlist;
import Model.Song;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class AlbumAddHandler {
    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;


    public Boolean addAlbum(String albumName, File cover, String user) {

        Boolean success = false;
        EventRecorder eventRecorder = null;
//      temp
        String sql = "INSERT INTO album (albumname, songcover, artist)\n" +
                "values (?, ?, ?)";// insert insert user query here
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setString(1, albumName);
            if(cover != null){
                FileInputStream input = new FileInputStream(cover);
                prepStatement.setBinaryStream(2,input);
            }
            else{
                prepStatement.setBinaryStream(2,null);
            }

            prepStatement.setString(3, user);

            prepStatement.execute();

            eventRecorder = new EventRecorder("User added album" + albumName, "artist");

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return success;
    }
}
