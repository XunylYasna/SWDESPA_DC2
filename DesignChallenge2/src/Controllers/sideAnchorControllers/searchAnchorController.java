package Controllers.sideAnchorControllers;

import com.jfoenix.controls.JFXListView;
import javafx.scene.control.Label;

public class searchAnchorController {
    public Label searchLbl;
    public JFXListView searchArtistLv;
    public JFXListView searchSongLV;
    public JFXListView searchPlaylistLv;


    public searchAnchorController(Label searchLbl, JFXListView searchArtistLv, JFXListView searchSongLV, JFXListView searchPlaylistLv) {
        this.searchLbl = searchLbl;
        this.searchArtistLv = searchArtistLv;
        this.searchSongLV = searchSongLV;
        this.searchPlaylistLv = searchPlaylistLv;
    }

    public void updateQuery(String query){

    }
}
