package Controllers;

import Database.SongDeleteHandler;
import Model.Song;
import com.jfoenix.controls.JFXHamburger;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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
    @FXML
    private MenuItem deleteItem;
    @FXML
    private MenuItem addplaylistItem;

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


//    @FXML
//    public void dragDetect(){
//        System.out.println("detect");
//    }
//
//    @FXML
//    public void dragDone(){
//        System.out.println("done");
//    }
//
//    @FXML
//    public void dragDrop(){
//        System.out.println("drop");
//    }
//
//    @FXML
//    public void dragEntered(){
//        System.out.println("entered");
//    }
//
//    @FXML
//    public void dragExited(){
//        System.out.println("exited");
//    }


}
