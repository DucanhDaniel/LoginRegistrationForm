package jfx.loginregisterusingmysql;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Please login!");
        stage.setScene(scene);
        String css = Objects.requireNonNull(this.getClass().getResource("LoginCSS.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();

    }

    public static void main(String[] args) throws IOException {


        launch();
    }
}