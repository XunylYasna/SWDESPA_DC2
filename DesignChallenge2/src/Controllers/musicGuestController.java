package Controllers;

import Controllers.FacadePackages.MusicPlayerBottom;
import Controllers.FacadePackages.MusicPlayerMiddle;
import Controllers.FacadePackages.MusicPlayerTop;
import Database.BlobSongGetter;
import Model.Song;
import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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

    //    Right Anchor Pane UI
    @FXML
    private AnchorPane rightAnchor;


    //    Music Player Middle UI
    @FXML
    private StackPane stackPane;
    @FXML
    private Pane tableViewPane;
    @FXML
    private Pane listViewPane;
    @FXML
    private ListView<Song> songlistView;
    @FXML
    private GridPane gridPane;

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
    @FXML
    private VBox sideVbox;


    //    Packages
    MusicPlayerTop musicPlayerTop;
    MusicPlayerBottom musicPlayerBottom;
    MusicPlayerMiddle musicPlayerMiddle;

//    Song passing
    BlobSongGetter blobSongGetter = new BlobSongGetter();
    private Song songSelected;
    Image cover = new Image("Controllers/defaultCover.png");

//    User passing
    String username = null;
    User user;

//    Filter
    String filter = "Artist";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        musicPlayerTop = new MusicPlayerTop(selectedTitleLbl,selectedArtistLbl,selectedGenreLbl,selectedAlbumLbl,selectedFromLbl,acoverImg);
        musicPlayerMiddle = new MusicPlayerMiddle(songlistView, gridPane, listViewPane, tableViewPane);
        musicPlayerBottom = new MusicPlayerBottom(songProgress,songVolume,videoMv, musicPlayerMiddle, blobSongGetter);

        musicPlayerBottom.initialize();
        musicPlayerMiddle.initialize(null,null);

        if(username != null){
            userMenu.setText(username);
        }

        else{
            userMenu.setText("Guest Gulapa");
            accountItem.setDisable(true);
        }

        listViewPane.setVisible(true);
        tableViewPane.setVisible(false);

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
            cover = blobSongGetter.getSongCover(songSelected.getSongID());
            musicPlayerTop.initialize(songSelected,filter, cover);
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

//    Adding Playlist
    @FXML
    void addplaylistDialog(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/fxml/addPlaylist.fxml"));
        root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        addPlaylistController addPlaylistController = (addPlaylistController) fxmlLoader.getController();
        addPlaylistController.setUserID(4); //temporary Hirap 123
        stage.showAndWait();

        if(addPlaylistController.getPlaylistadded() != null){
            JFXButton newplaylistButton = new JFXButton(addPlaylistController.getPlaylistadded().getPlaylistName());
//            Set button
            newplaylistButton.getStyleClass().clear();
            newplaylistButton.getStyleClass().add("pl-btn");
            sideVbox.getChildren().add(newplaylistButton);
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
        Parent root = FXMLLoader.load(getClass().getResource("../Views/fxml/mainStart.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = (Stage)((Node)userMenu).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


//    Side Pane
    @FXML
    void sideSong (ActionEvent event){
        filter = null;
        musicPlayerMiddle.initialize(null,null);
        listViewPane.setVisible(true);
        tableViewPane.setVisible(false);
    }

    @FXML
    void sideArtist (ActionEvent event){
        filter = "Artist";
        musicPlayerTop.initialize(songSelected,"Artists", cover);
        musicPlayerMiddle.setGridPane(filter);
        listViewPane.setVisible(false);
        tableViewPane.setVisible(true);
    }

    @FXML
    void sideAlbum (ActionEvent event){
        filter = "Album";
        musicPlayerTop.initialize(songSelected,"Albums", cover);
        musicPlayerMiddle.setGridPane(filter);
        listViewPane.setVisible(false);
        tableViewPane.setVisible(true);
    }

    @FXML
    void sideGenre (ActionEvent event){
        filter = "Genre";
        musicPlayerTop.initialize(songSelected,"Genres", cover);
        musicPlayerMiddle.setGridPane(filter);
        listViewPane.setVisible(false);
        tableViewPane.setVisible(true);
    }

}
