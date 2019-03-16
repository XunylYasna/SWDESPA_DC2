package Controllers.FacadePackages;

import Controllers.SongListViewCell;
import Database.BlobSongGetter;
import Model.Song;
import com.jfoenix.controls.JFXSlider;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.File;

public class MusicPlayerBottom {


    //    Bottom UI
    private JFXSlider songProgress;
    private JFXSlider songVolume;
    private MediaPlayer videoPlayer;
    private MediaPlayer audioPlayer;
    private MediaView videoMv;
    private static final String dantevidURL = "dantevid.mp4";

    MusicPlayerMiddle musicPlayerMiddle;
    BlobSongGetter blobSongGetter;

    public MusicPlayerBottom(JFXSlider songProgress, JFXSlider songVolume, MediaView videoMv, MusicPlayerMiddle musicPlayerMiddle, BlobSongGetter blobSongGetter) {
        this.songProgress = songProgress;
        this.songVolume = songVolume;
        this.videoMv = videoMv;
        this.musicPlayerMiddle = musicPlayerMiddle;
        this.blobSongGetter = blobSongGetter;
    }

    public void initialize(){
//        Side Video Load
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

//        Volume and song slider init
        songVolume.setValue(100.0);
        songProgress.setValue(0.0);
    }

    public Song songInit(int songID){
        File songAudio = blobSongGetter.getSongAudio(songID);
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
                    audioPlayer.play();
                    songProgress.setMax(songMedia.getDuration().toSeconds());
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
                playNextSong();
            }
        });

        return musicPlayerMiddle.getSongselected();
    }

    public Song playSongInit(){
        return songInit(musicPlayerMiddle.getSongselected().getSongID());
    }

    public void playPauseSong(){
        if(audioPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
            videoPlayer.pause();
            audioPlayer.pause();
        }

        else{
            audioPlayer.play();
            videoPlayer.play();
        }
    }

    public Song playNextSong() {
        Song song = musicPlayerMiddle.getNextSong();

        if(song == null){
            audioPlayer.dispose();
            return null;
        }
        return songInit(song.getSongID());
    }

    public Song playPrevSong() {
        Song song = musicPlayerMiddle.getPrevSong();

        if(song == null){
            audioPlayer.dispose();
            return null;
        }

        return songInit(song.getSongID());
    }
}
