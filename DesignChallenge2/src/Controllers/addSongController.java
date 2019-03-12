package Controllers;

import Database.SongAddHandler;
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
    private JFXButton songCBtn;

    @FXML
    private JFXButton uploadFBtn;

    @FXML
    private JFXButton confirmBtn;

    @FXML
    private JFXTextField titleTf;
    @FXML
    private JFXTextField artistTf;
    @FXML
    private JFXTextField albumTf;
    @FXML
    private JFXTextField genreTf;

    @FXML
    private Labeled songFileLbl;


    File coverFile;
    File songFile;

    FileChooser fileChooser = new FileChooser();

    SongAddHandler songAddHandler = new SongAddHandler();

    @FXML
    void chooseSongCover(ActionEvent event){
        System.out.println("cho");
        coverFile = fileChooser.showOpenDialog(null);
        Image cover = new Image(coverFile.toURI().toString());
        BackgroundImage myBI= new BackgroundImage(cover,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(150,150,false,false,false,false));
        songCBtn.setBackground(new Background(myBI));
        songCBtn.setText("");
    }

    @FXML
    void confirm(ActionEvent event){
        String songTitle = titleTf.getText();
        String artist = artistTf.getText();
        String genre = genreTf.getText();
        String album = albumTf.getText();

        songAddHandler.addSong(songTitle, artist, album, genre, coverFile, songFile);

    }

    @FXML
    void uploadSong(ActionEvent event){
        System.out.println("up");
        songFile = fileChooser.showOpenDialog(null);
        songFileLbl.setText(songFile.getName());
    }

    @FXML
    void cancel(ActionEvent event) {
        System.out.println("forgot Pass");

    }
}
