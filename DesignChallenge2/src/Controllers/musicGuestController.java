package Controllers;

import Database.BlobSongGetter;
import Database.SongListBuildTemp;
import Model.Song;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class musicGuestController implements Initializable {

    @FXML
    private MediaView videoMv;

    @FXML
    private Labeled selectedsongLbl;

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private ListView<Song> songlistView;

    @FXML
    private ImageView acoverImg;

    @FXML
    private JFXSlider songProgress;

    @FXML
    private JFXSlider songVolume;


    private static final String dantevidURL = "dantevid.mp4";

    private SongListBuildTemp songListBuildTemp = new SongListBuildTemp();
    private ArrayList<Song> songList;

    private File songAudio;
    private Song songSelected;

    private MediaPlayer videoPlayer;

    private MediaPlayer audioPlayer;

    BlobSongGetter blobSongGetter = new BlobSongGetter();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


//      Side video load
        Media dantevid = new Media(this.getClass().getResource(dantevidURL).toExternalForm());
        videoMv.setPreserveRatio(false);

        videoPlayer = new MediaPlayer(dantevid);
        videoPlayer.setMute(true);
        videoPlayer.setAutoPlay(false);
        videoPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                videoPlayer.seek(Duration.ZERO);
                videoPlayer.play();
            }
        });

        videoMv.setMediaPlayer(videoPlayer);

//      Song load
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

//      Volume and song slider init

        songVolume.setValue(100.0);
        songProgress.setValue(0.0);

    }



    @FXML
    public void selectSong(MouseEvent event){
        songSelected = songlistView.getSelectionModel().getSelectedItem();
        refreshSongSelected();
    }

    @FXML
    void exitProgram(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    void refreshSongSelected(){
        if(songSelected == null){
            selectedsongLbl.setText("");
        }
        else{
            selectedsongLbl.setText(songSelected.getSongTitle());
            Image cover = blobSongGetter.getSongCover(songSelected.getSongID());

            if(cover == null){
                acoverImg.setImage(null);
            }
            else{
                acoverImg.setImage(cover);
            }

            playSongInit(songlistView.getSelectionModel().getSelectedIndices().get(0));
        }
    }


//    Song Playing Related
    @FXML
    void playpauseSong(ActionEvent event){
        if(songSelected != null){
            if(audioPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
                videoPlayer.pause();
                audioPlayer.pause();
            }

            else{
                audioPlayer.play();
                videoPlayer.play();
            }
        }

    }

    @FXML
    void nextSong(ActionEvent event){
        ObservableList<Integer> indices = songlistView.getSelectionModel().getSelectedIndices();
        if(indices.get(0) + 1 < list.size()) {
            playSongInit(indices.get(0) + 1);
            songlistView.getSelectionModel().select(indices.get(0) + 1);
            songSelected = songlistView.getSelectionModel().getSelectedItem();
            refreshSongSelected();
        }
    }

    @FXML
    void prevSong(ActionEvent event){
        ObservableList<Integer> indices = songlistView.getSelectionModel().getSelectedIndices();
        if(indices.get(0) - 1 != -1){
            playSongInit(indices.get(0) -1);
            songlistView.getSelectionModel().select(indices.get(0)-1);
            songSelected = songlistView.getSelectionModel().getSelectedItem();
            refreshSongSelected();
        }
    }

    void playSongInit(int songPosition){

        songAudio = blobSongGetter.getSongAudio(songList.get(songPosition).getSongID());
        if(songAudio == null){
            if(audioPlayer != null){
                audioPlayer.dispose();
            }
        }

        else{
            if(audioPlayer != null){
                audioPlayer.dispose();
            }
            Media songMedia = new Media(songAudio.toURI().toString());
            audioPlayer = new MediaPlayer(songMedia);

            audioPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {

//                    System.out.println("Duration: "+songMedia.getDuration().toSeconds());
//
//                    // display media's metadata
//                    for (Map.Entry<String, Object> entry : songMedia.getMetadata().entrySet()){
//                        System.out.println(entry.getKey() + ": " + entry.getValue());
//                    }
//
//                    // play if you want

                    audioPlayer.play();
                    songProgress.setMax(songMedia.getDuration().toSeconds());
                    System.out.println(audioPlayer.getTotalDuration().toSeconds());
                    audioPlayer.play();
                    videoPlayer.play();
                }
            });

        }

        audioPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue,
                                Duration newValue) {
                songProgress.setValue(newValue.toSeconds());
            }
        });

        songProgress.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                audioPlayer.pause();
                audioPlayer.seek(Duration.seconds(songProgress.getValue()));
                audioPlayer.play();

            }
        });

        songProgress.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                audioPlayer.pause();
                audioPlayer.seek(Duration.seconds(songProgress.getValue()));
                audioPlayer.play();

            }
        });

        songVolume.setValue(audioPlayer.getVolume() * 100);

        songVolume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                audioPlayer.setVolume(songVolume.getValue()/100);
            }
        });

        audioPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                nextSong(null);
            }
        });


    }




//    Adding Song
    @FXML
    void addsongDialog(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/fxml/addSong.fxml"));
        root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        addSongController newaddSongController = (addSongController) fxmlLoader.getController();
        stage.showAndWait();


        if(newaddSongController.getSongAdded() != null){
            list.add(newaddSongController.getSongAdded());
            songList.add(newaddSongController.getSongAdded());
            refreshListView();
        }

    }

    void refreshListView(){
        songlistView.refresh();
        songlistView.getItems().removeAll();
        songlistView.setItems(list);


    }
}
