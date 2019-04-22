package Database;
import Model.Album;

import javax.imageio.ImageIO;
import java.io.*;
import java.sql.*;
public class editSongtoAlbumHandler {

    public void putSongtoAlbum(int songid, int albumid){

        Connection myConn = DatabaseConnection.getDatabaseConn();
        PreparedStatement prepStatement;
        Statement statement = null;
        ResultSet resultSet;

        AlbumBuildTemp albumBuildTemp = new AlbumBuildTemp();
        Album album = albumBuildTemp.getAlbum(albumid);

        String sql = "INSERT INTO song.gulaplay (album, albumcover)\n" + "values(?,?) WHERE songid = ?";

        try{
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setString(1, album.getAlbumName());

            if(album.getSongCover() != null){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(album.getSongCover(), "png", baos);
                InputStream is = new ByteArrayInputStream(baos.toByteArray());
                prepStatement.setBinaryStream(2,is);
            }

            prepStatement.setInt(3, songid);

            prepStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
