package Controllers;

import Database.RegistrationHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;

public class RegistrationController {

    @FXML
    private JFXButton exitBtn;

    @FXML
    private JFXTextField usernameTf;

    @FXML
    private JFXTextField emailTf;

    @FXML
    private JFXPasswordField passwordPf;

    @FXML
    void register(ActionEvent event){
        RegistrationHandler regis = new RegistrationHandler();
        regis.registration(usernameTf.getText(), emailTf.getText(), passwordPf.getText());
        System.out.println(usernameTf.getText() + " " + emailTf.getText() + " " + passwordPf.getText());

    }

    @FXML
    void exitProgram(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}
