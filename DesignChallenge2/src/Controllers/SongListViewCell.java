package Controllers;

import Model.Song;
import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class SongListViewCell extends ListCell<Song> {

    @FXML
    private Labeled cellSongLbl;
    @FXML
    private Labeled cellArtistLbl;
    @FXML
    private Labeled cellAlbumLbl;
    @FXML
    private Labeled cellGenreLbl;
    @FXML
    private HBox cellHbox;
//    @FXML
//    private MenuButton cellBurg;

    FXMLLoader mLLoader;

    @Override
    protected void updateItem(Song song, boolean empty) {
        super.updateItem(song, empty);

        if (empty || song == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../Views/fxml/songCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            cellSongLbl.setText(String.valueOf(song.getSongTitle()));
            cellArtistLbl.setText(song.getArtist());
            cellAlbumLbl.setText(song.getAlbum());
            cellGenreLbl.setText(song.getGenre());


            setText(null);
            setGraphic(cellHbox);
        }
    }

}