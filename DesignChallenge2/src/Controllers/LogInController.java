package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;

import java.awt.*;

public class LogInController {
    @FXML
    private JFXButton exitBtn;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXButton forgotBtn;

    @FXML
    private JFXTextField usernameTf;

    @FXML
    private JFXPasswordField passwordPf;

    @FXML
    private Labeled message;


    @FXML
    void exitProgram(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void login(ActionEvent event) {
        message.setText("login");
        System.out.println("login");
        System.out.println(usernameTf.getText());
        System.out.println(passwordPf.getText());
    }

    @FXML
    void forgotPassword(ActionEvent event) {
        System.out.println("forgot Pass");
        message.setText("Tangina ka bahala ka sa buhay mo");
    }
}
