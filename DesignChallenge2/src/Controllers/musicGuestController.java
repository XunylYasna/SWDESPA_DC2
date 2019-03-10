package Controllers;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

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
//        mediaPlayer.setMute(true);
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
}
