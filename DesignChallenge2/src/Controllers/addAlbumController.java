package Controllers;

import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;

import java.io.File;

public class addAlbumController {
    public Label message;
    public JFXTextField albumTf;
    public JFXButton songCBtn;
    public JFXButton confirmBtn;
    public Label statusLbl;

    User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void chooseSongCover(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File coverFile = fileChooser.showOpenDialog(null);
        Image cover = new Image(coverFile.toURI().toString());
        BackgroundImage myBI= new BackgroundImage(cover,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(115,115,false,false,false,false));
        songCBtn.setBackground(new Background(myBI));
        songCBtn.setText("");
    }

    public void confirm(ActionEvent event) {
//        add album
    }
}
