package Database;

import Model.Playlist;
import Model.Song;

import java.sql.*;
import java.util.ArrayList;

public class PlaylistBuildTemp {

    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    public ArrayList<Playlist> getPlaylists(int UserID){
        ArrayList<Playlist> playlistsofuser = new ArrayList<>();

        int playlistID;
        String playlistName;
        String playlistDescription;

        Playlist playlist;

        try {
            PreparedStatement prepStatement = myConn.prepareStatement("SELECT * FROM gulaplay.playlist WHERE UserID = ?");
            prepStatement.setInt(1,UserID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()){
                playlistID = resultSet.getInt("PlaylistID");
                playlistName = resultSet.getString("PlaylistName");
                playlistDescription = resultSet.getString("PlaylistDescription");

                playlist = new Playlist(playlistID, playlistName,playlistDescription,UserID);
                System.out.println(playlist.getPlaylistName());
                playlistsofuser.add(playlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlistsofuser;
    }
}
