package Controllers;

import Database.LoginHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class LogInController extends LoginHandler{

//    private LoginHandler loginHandler = new LoginHandler();

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
    void login(ActionEvent event) throws IOException {
        message.setText("login");
        System.out.println("login");
        System.out.println(usernameTf.getText());
        System.out.println(passwordPf.getText());
        this.test();

        String status = this.verifyCredentials(usernameTf.getText(), passwordPf.getText());

        if(status.equals("Log In")){
            Parent root = FXMLLoader.load(getClass().getResource("../Views/fxml/musicGuest.fxml"));
            Scene scene = new Scene(root);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

        else{
            usernameTf.setText("");
            passwordPf.setText("");
            message.setText(status);
        }
    }

    @FXML
    void forgotPassword(ActionEvent event) {
        System.out.println("forgot Pass");
        message.setText("Tangina ka bahala ka sa buhay mo");
    }
}
