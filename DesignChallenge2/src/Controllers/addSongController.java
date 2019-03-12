package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class addSongController {

    @FXML
    private JFXButton songCBtn;

    @FXML
    private JFXButton uploadFBtn;

    @FXML
    private JFXButton confirmBtn;

    File coverFile;
    File songFile;

    FileChooser fileChooser = new FileChooser();

    @FXML
    void chooseSongCover(ActionEvent event){
        System.out.println("cho");
        coverFile = fileChooser.showOpenDialog(null);
    }

    @FXML
    void confirm(ActionEvent event){
        String songTitle;
        String songTitle;
        String songTitle;
        String songTitle;
    }

    @FXML
    void uploadSong(ActionEvent event){
        System.out.println("up");
        songFile = fileChooser.showOpenDialog(null);
    }

    @FXML
    void cancel(ActionEvent event) {
        System.out.println("forgot Pass");

    }
}
