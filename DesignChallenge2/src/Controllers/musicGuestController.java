package Controllers;

import Controllers.FacadePackages.MusicPlayerBottom;
import Controllers.FacadePackages.MusicPlayerMiddle;
import Controllers.FacadePackages.MusicPlayerTop;
import Database.BlobSongGetter;
import Database.PlaylistBuildTemp;
import Database.PlaylistSongAddHandler;
import Database.UserBuildTemp;
import Model.Playlist;
import Model.Song;
import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML
    private ScrollPane scrollAnchor;


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
    @FXML
    private JFXButton addPlaylistBtn;


//    Packages
    MusicPlayerTop musicPlayerTop;
    MusicPlayerBottom musicPlayerBottom;
    MusicPlayerMiddle musicPlayerMiddle;

//    Song passing
    BlobSongGetter blobSongGetter = new BlobSongGetter();
    private Song songSelected;
    Image cover = new Image("Controllers/defaultCover.png");

//    User passing
    String username;
    User user;

//    Playlist passing
    ArrayList<Playlist> playlistsList;

//    Filter
    String filter = "Song List";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        musicPlayerTop = new MusicPlayerTop(selectedTitleLbl,selectedArtistLbl,selectedGenreLbl,selectedAlbumLbl,selectedFromLbl,acoverImg);
        musicPlayerMiddle = new MusicPlayerMiddle(songlistView, gridPane, listViewPane, tableViewPane, user!=null);
        musicPlayerBottom = new MusicPlayerBottom(songProgress,songVolume,videoMv, musicPlayerMiddle, blobSongGetter);

        musicPlayerBottom.initialize();
        musicPlayerMiddle.initialize(null,null);
        listViewPane.setVisible(true);
        tableViewPane.setVisible(false);

        userMenu.setText("Guest Gulapanatic");
        accountItem.setDisable(true);
        addPlaylistBtn.setDisable(true);
        backAccount(null);
        sideSong(null);

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
        addPlaylistController.setUserID(user.getUserID());
        stage.showAndWait();

        if(addPlaylistController.getPlaylistadded() != null){
            addPlaylistButton(addPlaylistController.getPlaylistadded());
        }
    }

//    User Related
    public void setUsername(String username){
        this.username = username;
        initUser();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/fxml/mainStart.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = (Stage)((Node)userMenu).getScene().getWindow();
        stage.setScene(scene);
        musicPlayerBottom.dispose();
        stage.show();
    }


//    Side Pane
    @FXML
    void sideSong (ActionEvent event){
        filter = null;
        musicPlayerTop.initialize(songSelected, "Song List", null);
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


    private void initUser(){
        if(username == null){
            userMenu.setText("Guest Gulapanatic");
            accountItem.setDisable(true);
            addPlaylistBtn.setDisable(true);
        }
        else{
            userMenu.setText(username);
            UserBuildTemp userBuildTemp = new UserBuildTemp();
            user = userBuildTemp.getUser(username);
            PlaylistBuildTemp playlistBuildTemp = new PlaylistBuildTemp();
            playlistsList = playlistBuildTemp.getPlaylists(user.getUserID());
            for(int i = 0; i < playlistsList.size();i++){
                addPlaylistButton(playlistsList.get(i));
            }

            accountItem.setDisable(false);
            addPlaylistBtn.setDisable(false);
        }

    }

    private void addPlaylistButton(Playlist playlist){
        JFXButton newplaylistButton = new JFXButton(playlist.getPlaylistName());
//            Set button

        newplaylistButton.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                event.consume();
            }
        });

        newplaylistButton.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                PlaylistSongAddHandler playlistSongAddHandler = new PlaylistSongAddHandler();
                playlistSongAddHandler.addSongtoPlaylist(Integer.parseInt(db.getString()), playlist.getPlaylistID());
            }
        });

        newplaylistButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                musicPlayerTop.initialize(songSelected, playlist.getPlaylistName(), null);
                musicPlayerMiddle.initialize(playlist.getPlaylistID());
            }
        });

        newplaylistButton.getStyleClass().clear();
        newplaylistButton.getStyleClass().add("pl-btn");

        sideVbox.getChildren().add(newplaylistButton);
    }

    @FXML
    public void accountMenu(ActionEvent event){
        scrollAnchor.setVisible(true);
        rightAnchor.setVisible(false);
    }

    @FXML
    public void backAccount(ActionEvent event){
        scrollAnchor.setVisible(false);
        rightAnchor.setVisible(true);
    }
}
