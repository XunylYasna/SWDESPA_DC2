package Controllers.sideAnchorControllers;

import Model.User;
import com.jfoenix.controls.JFXListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class userProfileAnchorController {
    public ScrollPane userProfilescrollAnchor;
    public AnchorPane userprofileAnchor;
    public Label usernameView1;
    public JFXListView artistProfileLv;
    public Label usernameProfile;
    public JFXListView playlistsProfileLv;
    public Label numProfile;
    public Button followBtn;


    public userProfileAnchorController(ScrollPane userProfilescrollAnchor, AnchorPane userprofileAnchor, Label usernameView1, JFXListView artistProfileLv, Label usernameProfile, JFXListView playlistsProfileLv, Label numProfile, Button followBtn) {
        this.userProfilescrollAnchor = userProfilescrollAnchor;
        this.userprofileAnchor = userprofileAnchor;
        this.usernameView1 = usernameView1;
        this.artistProfileLv = artistProfileLv;
        this.usernameProfile = usernameProfile;
        this.playlistsProfileLv = playlistsProfileLv;
        this.numProfile = numProfile;
        this.followBtn = followBtn;
    }

    public void setUser(User user, int ownid){
        usernameProfile.setText(user.getUsername());

        if(user.getUserID() == ownid){
            followBtn.setDisable(true);
        }

        else{
            followBtn.setDisable(false);
            followBtn.setOnKeyPressed(e -> {
                System.out.println(ownid + " are now following " + user.getUsername());
//                add follower handler here
            });
        }

    }
}
