package Database;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BlobSongGetter {
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;
    String sql;

    public BlobSongGetter(){

    }

    public Image getSongCover(int songID){

        BufferedImage albumCover = null;

        sql = "SELECT AlbumCover From gulaplay.song WHERE SongID LIKE " + songID;

        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(sql);
            byte[] imagebytes = null;
            while (resultSet.next()){
                imagebytes = resultSet.getBytes("AlbumCover");
            }
            if(imagebytes != null)
                albumCover = ImageIO.read(new ByteArrayInputStream(imagebytes));

        } catch (SQLException e) {
            System.out.println("SQL problem" + songID);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Conver problem" + songID);
            e.printStackTrace();
        }

        WritableImage coverImage = null;
        if (albumCover != null) {
            coverImage = new WritableImage(albumCover.getWidth(), albumCover.getHeight());
            PixelWriter pw = coverImage.getPixelWriter();
            for (int x = 0; x < albumCover.getWidth(); x++) {
                for (int y = 0; y < albumCover.getHeight(); y++) {
                    pw.setArgb(x, y, albumCover.getRGB(x, y));
                }
            }
        }

        return coverImage;
    }

    public File getSongAudio(int songID){
        String path = "songToPlay.mp3";
        File songFile = null;

        sql = "SELECT SongFile From gulaplay.song WHERE SongID LIKE " + songID;

        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(sql);
            byte[] filebytes = null;
            while (resultSet.next()){
                filebytes = resultSet.getBytes("SongFile");
            }
            if(filebytes != null){
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(filebytes);
                fos.close();
                songFile = new File(path);
            }


        } catch (SQLException e) {
            System.out.println("SQL problem" + songID);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Conver problem" + songID);
            e.printStackTrace();
        }


        return songFile;
    }
}
