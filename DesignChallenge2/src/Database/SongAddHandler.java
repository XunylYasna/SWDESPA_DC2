package Database;

import Model.Song;

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

    public Song addSong(String title, String artist, String genre, String album, File photo, File song) {

//      temp
        FileInputStream input;
        String sql = "INSERT INTO song (MusicTitle, Artist, Genre, Album, AlbumCover, SongFile)\n" +
                "values (?, ?, ?, ?, ?, ?)";// insert insert user query here
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setString(1, title);
            prepStatement.setString(2, artist);
            prepStatement.setString(3, genre);
            prepStatement.setString(4, album );
            if(photo != null){
                 input = new FileInputStream(photo);
                prepStatement.setBinaryStream(5,input);
            }
            else{
                prepStatement.setBinaryStream(5,null);
            }
            if(song != null){
                input = new FileInputStream(song);
                prepStatement.setBinaryStream(6,input);
            }
            else{
                prepStatement.setBinaryStream(6,null);
            }
            prepStatement.execute();

            sql = "Select * from song where SongID=LAST_INSERT_ID();";
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(sql);

            int songID = 0;
            String newSongTitle = "error";
            String newArtist = "error";
            String newAlbum = "error";
            String newGenre = "error";

            while (resultSet.next()){
                songID = resultSet.getInt("SongID");
                newSongTitle = resultSet.getString("MusicTitle");
                newArtist = resultSet.getString("Artist");
                newAlbum = resultSet.getString("Genre");
                newGenre = resultSet.getString("Album");
            }


            return new Song(songID, newSongTitle,newArtist, newAlbum, newGenre);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
