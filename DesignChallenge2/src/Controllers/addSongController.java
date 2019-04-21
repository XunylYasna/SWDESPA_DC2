package Controllers;

import Database.SongAddHandler;
import Model.Song;
import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;

public class addSongController {

    @FXML
    private JFXButton uploadFBtn;

    @FXML
    private JFXButton confirmBtn;

    @FXML
    private JFXTextField titleTf;
    @FXML
    private JFXTextField genreTf;

    @FXML
    private Labeled songFileLbl;
    @FXML
    private Labeled statusLbl;


    File songFile = null;

    FileChooser fileChooser = new FileChooser();

    SongAddHandler songAddHandler = new SongAddHandler();
    Song songAdded = null;
    User user;


    @FXML
    void confirm(ActionEvent event){
        String songTitle = titleTf.getText();
        String artist = user.getUsername();
        String genre = genreTf.getText();

        songAdded = songAddHandler.addSong(songTitle, artist, null, genre, null, songFile);
        if(songAdded != null){
            statusLbl.setText("Song added. You may now close the window");
        }
    }

    @FXML
    void uploadSongFile(ActionEvent event){
        songFile = fileChooser.showOpenDialog(null);
        songFileLbl.setText(songFile.getName());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSongAdded(){
        return songAdded;
    }
}
