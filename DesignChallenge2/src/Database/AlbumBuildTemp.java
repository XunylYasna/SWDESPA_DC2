package Database;

import Model.Album;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class AlbumBuildTemp {
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    public ArrayList<Album> getAlbumsofArtist(String artist){
        ArrayList<Album> albumofuser = new ArrayList<>();

        int albumid;
        String albumname;
        BufferedImage songcover;

        Album album;

        try {
            PreparedStatement prepStatement = myConn.prepareStatement("SELECT * FROM gulaplay.album WHERE artist = ?");
            prepStatement.setString(1,artist);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                albumid = resultSet.getInt("albumid");
                albumname = resultSet.getString("albumname");
                byte[] imagebytes = null;
                songcover = null;
                imagebytes = resultSet.getBytes("songcover");

                if(imagebytes != null)
                    songcover = ImageIO.read(new ByteArrayInputStream(imagebytes));

                album = new Album(albumid, albumname,songcover);
                albumofuser.add(album);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return albumofuser;
    }


    public Album getAlbum(int albumid){
        Album album = null;

        String albumname;
        BufferedImage songcover;


        try {
            PreparedStatement prepStatement = myConn.prepareStatement("SELECT * FROM gulaplay.album WHERE albumid = ?");
            prepStatement.setInt(1,albumid);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                albumid = resultSet.getInt("albumid");
                albumname = resultSet.getString("albumname");
                byte[] imagebytes = null;
                songcover = null;
                imagebytes = resultSet.getBytes("songcover");

                if(imagebytes != null)
                    songcover = ImageIO.read(new ByteArrayInputStream(imagebytes));

                album = new Album(albumid, albumname,songcover);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return album;
    }
}
