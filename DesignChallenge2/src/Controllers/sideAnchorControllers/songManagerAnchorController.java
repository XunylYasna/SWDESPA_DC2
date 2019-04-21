package Controllers.sideAnchorControllers;

import Controllers.addAlbumController;
import Controllers.addSongController;
import Model.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class songManagerAnchorController {

    public AnchorPane songManagerAnchor;
    public Label artistnameLbl;
    public Label numFollowersLbl;
    public JFXButton uploadSongbtn;
    public JFXListView artistsongLv;
    public JFXListView albumSongLv;
    public JFXButton albumBtn;

    private User user;
    public songManagerAnchorController(AnchorPane songManagerAnchor, Label artistnameLbl, Label numFollowersLbl, JFXButton uploadSongbtn, JFXListView artistsongLv, JFXListView albumSongLv, JFXButton albumBtn, User user) {
        this.songManagerAnchor = songManagerAnchor;
        this.artistnameLbl = artistnameLbl;
        this.numFollowersLbl = numFollowersLbl;
        this.uploadSongbtn = uploadSongbtn;
        this.artistsongLv = artistsongLv;
        this.albumSongLv = albumSongLv;
        this.albumBtn = albumBtn;
        this.user = user;

        uploadSongbtn.setOnAction(e -> {
//    Adding Song
            Stage stage = new Stage();
            Parent root;

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/fxml/addSong.fxml"));
            try {
                root = fxmlLoader.load();
                Scene scene = new Scene(root);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                addSongController newaddSongController = (addSongController) fxmlLoader.getController();
                newaddSongController.setUser(user);
                stage.showAndWait();

            } catch (IOException e1) {
                e1.printStackTrace();
            }


        });

        albumBtn.setOnAction(e -> {
            Stage stage = new Stage();
            Parent root;

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Views/fxml/addAlbum.fxml"));
            try {
                root = fxmlLoader.load();
                Scene scene = new Scene(root);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                addAlbumController addAlbumController = (addAlbumController) fxmlLoader.getController();
                addAlbumController.setUser(user);
                stage.showAndWait();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }


}
