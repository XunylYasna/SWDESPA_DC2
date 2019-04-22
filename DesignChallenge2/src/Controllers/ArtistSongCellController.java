package Controllers;

import Database.SongDeleteHandler;
import Database.SongFavoriteHandler;
import Model.Song;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ArtistSongCellController extends ListCell<Song> {
    @FXML
    private Labeled cellSongLbl;

    @FXML
    private HBox cellHbox;

    @FXML
    private JFXButton deleteItem;

    FXMLLoader mLLoader;

    @Override
    protected void updateItem(Song song, boolean empty) {
        super.updateItem(song, empty);

        if (empty || song == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("Views/fxml/artistSongCell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            cellSongLbl.setText(String.valueOf(song.getSongTitle()));


            deleteItem.setOnAction(event -> {
                SongDeleteHandler songDeleteHandler = new SongDeleteHandler();
                System.out.println("Delete " + getItem().getSongTitle() );
                songDeleteHandler.deleteSong(getItem().getSongID());
                getListView().getItems().remove(getItem());
            });



            setOnDragDetected(event -> {
                Dragboard db = this.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                db.setDragView(this.snapshot(null, null));
                ClipboardContent cc = new ClipboardContent();
                cc.putString(this.getItem().getSongID()+"");
                db.setContent(cc);
            });


            setText(null);
            setGraphic(cellHbox);
        }
    }
}
