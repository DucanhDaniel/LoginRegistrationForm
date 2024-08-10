package jfx.loginregisterusingmysql;

import db.MyJDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    public PasswordField passwordTextField;
    public Button loginBtn;
    public Button registerBtn;
    public TextField usernameTextField;
    public Label messageLabel;

    public static void switchToLoginScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("LoginScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(RegisterController.class.getResource("LoginCSS.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Login form");
        stage.show();

    }
    public void registerBtnController(ActionEvent event) throws IOException {
        RegisterController.switchToRegisterScene(event);
    }

    public void loginBtnController(ActionEvent event) throws IOException  {
        //TODO: validate login credential and go to the welcome scene
        String password = passwordTextField.getText();
        String username = usernameTextField.getText();

        //if the login is successful, switch to the welcome scene
        if (MyJDBC.validateLogin(username, password)) {
            //Show the alert message that the login is successful
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System message");
            alert.setHeaderText(null);
            alert.setContentText("Login Successful!");
            alert.showAndWait();

            WelcomeController.switchToWelcomeScene(event);
        }
        else {
            messageLabel.setText("Invalid username or password");
        }
    }
}
