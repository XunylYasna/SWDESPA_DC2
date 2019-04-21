package Controllers;

import Database.RegistrationHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuButton;

public class RegistrationController {

    public MenuButton typeMenu;
    @FXML
    private JFXButton exitBtn;

    @FXML
    private JFXTextField firstnameTf;
    @FXML
    private JFXTextField lastnameTf;

    @FXML
    private JFXTextField usernameTf;

    @FXML
    private JFXTextField emailTf;

    @FXML
    private JFXPasswordField passwordPf;

    @FXML
    private Labeled messageLbl;

    String user_type = "listener";

    @FXML
    void register(ActionEvent event){
        RegistrationHandler regis = new RegistrationHandler();
        String message = regis.registration(firstnameTf.getText(), lastnameTf.getText(), usernameTf.getText(), emailTf.getText(), passwordPf.getText(), user_type);

        messageLbl.setText(message);
    }

    @FXML
    void exitProgram(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void setUsertype(ActionEvent event) {
        typeMenu.setText("Listener");
        user_type = "listener";
    }

    public void setArtisttype(ActionEvent event) {
        typeMenu.setText("Artist");
        user_type = "artist";
    }
}
