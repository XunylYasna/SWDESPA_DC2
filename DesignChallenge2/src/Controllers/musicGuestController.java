package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.jfoenix.controls.JFXDialog.DialogTransition.BOTTOM;

public class musicGuestController implements Initializable {

    @FXML
    private MediaView videoMv;

    private MediaPlayer mediaPlayer;

    private static final String dantevidURL = "dantevid.mp4";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }
}
