package Database;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

//    private File getSongAudio(int songID){
//
//    }
}
