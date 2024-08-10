package jfx.loginregisterusingmysql;

import db.MyJDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {
    public TextField fullNameTextField;
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public PasswordField rePasswordTextField;
    public Button registerBtn;
    public Label messageLabel;
    public Button loginBtn;

    public static void switchToRegisterScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(RegisterController.class.getResource("RegisterScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Register!");
        String css = Objects.requireNonNull(RegisterController.class.getResource("RegisterCSS.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
    }

    public void registerBtnController(ActionEvent event) {
        String fullname = fullNameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String rePassword = rePasswordTextField.getText();
        if (fullname.isEmpty())
            messageLabel.setText("Full name is required");
        else if (username.isEmpty())
            messageLabel.setText("Username is required");
        else if (password.isEmpty())
            messageLabel.setText("Password is required");
        else if (!password.equals(rePassword))
            messageLabel.setText("Passwords do not match");
        else if (!MyJDBC.register(username, password, fullname)) {
            messageLabel.setText("Username already exists");
        } else {
            messageLabel.setTextFill(javafx.scene.paint.Color.web("#99cc00"));
            messageLabel.setText("Registration successful!");
        }
    }

    public void loginBtnController(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("LoginScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(RegisterController.class.getResource("LoginCSS.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("Login form");
        stage.show();
    }
}
