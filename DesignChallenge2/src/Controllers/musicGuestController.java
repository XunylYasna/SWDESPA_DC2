package Controllers;

import Controllers.FacadePackages.MusicPlayerBottom;
import Controllers.FacadePackages.MusicPlayerMiddle;
import Controllers.FacadePackages.MusicPlayerTop;
import Database.BlobSongGetter;
import Model.Song;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class musicGuestController implements Initializable {



    //    Music Player Top UI
    @FXML
    private Labeled selectedTitleLbl;
    @FXML
    private Labeled selectedArtistLbl;
    @FXML
    private Labeled selectedGenreLbl;
    @FXML
    private Labeled selectedAlbumLbl;
    @FXML
    private Labeled selectedFromLbl;
    @FXML
    private ImageView acoverImg;

    //    Music Player Middle UI
    @FXML
    private ListView<Song> songlistView;

    //    Music Player Bottom UI
    @FXML
    private JFXSlider songProgress;
    @FXML
    private JFXSlider songVolume;
    @FXML
    private MediaView videoMv;

//    Music Player Side UI
    @FXML
    private MenuButton userMenu;
    @FXML
    private MenuItem accountItem;


    //    Packages
    MusicPlayerTop musicPlayerTop;
    MusicPlayerBottom musicPlayerBottom;
    MusicPlayerMiddle musicPlayerMiddle;

//    Song passing
    BlobSongGetter blobSongGetter = new BlobSongGetter();
    private Song songSelected;

//    User passing
    String username = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        musicPlayerTop = new MusicPlayerTop(selectedTitleLbl,selectedArtistLbl,selectedGenreLbl,selectedAlbumLbl,selectedFromLbl,acoverImg);
        musicPlayerMiddle = new MusicPlayerMiddle(songlistView);
        musicPlayerBottom = new MusicPlayerBottom(songProgress,songVolume,videoMv, musicPlayerMiddle, blobSongGetter);

        musicPlayerBottom.initialize();
        musicPlayerMiddle.initialize(username);

        if(username != null){
            userMenu.setText(username);
        }

        else{
            userMenu.setText("Guest Gulapa");
            accountItem.setDisable(true);
        }

    }


    @FXML
    public void selectSong(MouseEvent event){
        songSelected = musicPlayerMiddle.getSongselected();
        refreshSongSelected();
    }

    @FXML
    void exitProgram(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    void refreshSongSelected(){
        if(songSelected == null){
            selectedTitleLbl.setText("");
        }
        else{
            Image cover = blobSongGetter.getSongCover(songSelected.getSongID());
            musicPlayerTop.initialize(songSelected,"Song List", cover);
            musicPlayerBottom.playSongInit();
        }
    }


//    Song Playing Related
    @FXML
    void playpauseSong(ActionEvent event){
        musicPlayerBottom.playPauseSong();
    }

    @FXML
    void nextSong(ActionEvent event){
        songSelected = musicPlayerBottom.playNextSong();
        refreshSongSelected();
    }

    @FXML
    void prevSong(ActionEvent event){
        songSelected = musicPlayerBottom.playPrevSong();
        refreshSongSelected();
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
            musicPlayerMiddle.addnewSong(newaddSongController.getSongAdded());
        }
    }

//    User Related
    public void setUsername(String username){
        System.out.println(username);
        this.username = username;
        userMenu.setText(username);
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/fxml/mainStart.fxml"));
//        Parent root = (Parent) fxmlLoader.load();
//        Scene scene = new Scene(root);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(scene);
//        window.show();
        Parent root = FXMLLoader.load(getClass().getResource("../Views/fxml/mainStart.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = (Stage)((Node)userMenu).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
