package Controllers;

import Database.SongDeleteHandler;
import Database.editSongtoAlbumHandler;
import Model.Album;
import Model.Song;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.IOException;

public class ArtistAlbumController extends ListCell<Album> {

    FXMLLoader mLLoader;
    Labeled albumLabel;
    ImageView albumCover;

    @Override
    protected void updateItem(Album album, boolean empty) {
        super.updateItem(album, empty);

        if (empty || album == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("Views/fxml/albumCell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            

            setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    editSongtoAlbumHandler editSongtoAlbumHandler = new editSongtoAlbumHandler();
                    editSongtoAlbumHandler.putSongtoAlbum(Integer.parseInt(db.getString()), album.getIdAlbum());
                }
            });


            setText(null);
//            setGraphic(cellHbox);
        }
    }
}
