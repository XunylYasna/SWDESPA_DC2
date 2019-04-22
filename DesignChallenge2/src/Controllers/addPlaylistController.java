package Controllers;


import Database.EventRecorder;
import Database.PlayListAddHandler;
import Model.Playlist;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;

public class addPlaylistController {

    @FXML
    private JFXTextField playlistNameTf;
    @FXML
    private JFXTextField descriptionTf;
    @FXML
    private Labeled statusLbl;

    private int userID;
    PlayListAddHandler playListAddHandler = new PlayListAddHandler();
    Playlist playlistadded;
    EventRecorder eventRecorder = null;

    @FXML
    void confirm(ActionEvent event){
        String playlistName = playlistNameTf.getText();
        String description = descriptionTf.getText();

        playlistadded = playListAddHandler.addPlaylist(playlistName,description,userID);
        eventRecorder = new EventRecorder("User created" + playlistName, "user");

        if(playlistadded != null){
            statusLbl.setText("Playlist added. You may now close the window");
        }
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public Playlist getPlaylistadded() {
        return playlistadded;
    }
}
