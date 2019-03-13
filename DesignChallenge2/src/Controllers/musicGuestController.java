package Controllers;

import Database.SongListBuildTemp;
import Model.Song;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.jfoenix.controls.JFXDialog.DialogTransition.BOTTOM;

public class musicGuestController implements Initializable {

    @FXML
    private MediaView videoMv;

    @FXML
    private Labeled selectedsongLbl;

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private ListView<Song> songlistView;

    private MediaPlayer mediaPlayer;

    private static final String dantevidURL = "dantevid.mp4";

    private SongListBuildTemp songListBuildTemp = new SongListBuildTemp();
    private ArrayList<Song> songList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


//      Side video load
        Media dantevid = new Media(this.getClass().getResource(dantevidURL).toExternalForm());
        videoMv.setPreserveRatio(false);

        mediaPlayer = new MediaPlayer(dantevid);
        mediaPlayer.setMute(true);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });

        videoMv.setMediaPlayer(mediaPlayer);

//      Song load

//        for(int i = 0; i < songList.size(); i++){
//            list.add(songList.get(i).getSongTitle());
//        }
//        songlistView.getItems().addAll(list);

        songList = songListBuildTemp.getSongs();
        list.removeAll();
        list.addAll(songList);
        songlistView.setItems(list);

        songlistView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> param) {
                return new SongListViewCell();
            }
        });


    }



    @FXML
    public void selectSong(MouseEvent event){
        Song songSelected = songlistView.getSelectionModel().getSelectedItem();
        if(songSelected == null){
            selectedsongLbl.setText("");
        }

        else{
            selectedsongLbl.setText(songSelected.getSongTitle());
        }
    }

    @FXML
    void exitProgram(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }



    @FXML
    void addsongDialog(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root;

        root = FXMLLoader.load(getClass().getResource("../Views/fxml/addSong.fxml"));

        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
//        songlistView.refresh();
    }
}
