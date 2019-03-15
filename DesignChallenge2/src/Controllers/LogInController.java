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

public class LogInController{

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

    private String username;

    @FXML
    void exitProgram(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        LoginHandler loginHandler = new LoginHandler();

        message.setText("login");
        System.out.println("login");
        System.out.println(usernameTf.getText());
        System.out.println(passwordPf.getText());


        String status = loginHandler.verifyCredentials(usernameTf.getText(), passwordPf.getText());

        if(status.equals("Log In")){
            username = usernameTf.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/fxml/musicUser.fxml"));
            Parent root = fxmlLoader.load();

//            musicUserController musicUserController = (musicUserController) fxmlLoader.getController();
//            musicUserController.setUsername(username);

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

    String getUsername(){
        return username;
    }
}
