package Controllers.sideAnchorControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class songManagerAnchorController {

    public AnchorPane songManagerAnchor;
    public Label artistnameLbl;
    public Label numFollowersLbl;
    public JFXButton uploadSongbtn;
    public JFXListView artistsongLv;
    public JFXListView albumSongLv;
    public JFXButton albumBtn;

    public songManagerAnchorController(AnchorPane songManagerAnchor, Label artistnameLbl, Label numFollowersLbl, JFXButton uploadSongbtn, JFXListView artistsongLv, JFXListView albumSongLv, JFXButton albumBtn) {
        this.songManagerAnchor = songManagerAnchor;
        this.artistnameLbl = artistnameLbl;
        this.numFollowersLbl = numFollowersLbl;
        this.uploadSongbtn = uploadSongbtn;
        this.artistsongLv = artistsongLv;
        this.albumSongLv = albumSongLv;
        this.albumBtn = albumBtn;
    }
}
