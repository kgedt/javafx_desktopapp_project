package project.files;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import project.files.final_project.StartApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helper {
    public static String cypher(String pass) throws NoSuchAlgorithmException {
        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        byte[] arr = msg.digest(pass.getBytes(StandardCharsets.UTF_8));
        StringBuilder res = new StringBuilder();

        for (var i : arr) {
            res.append(String.format("%02x", i));
        }

        return res.toString();
    }

    public static void changeScene(Button clickedButton, String fxml_filename) throws IOException {
        Stage stage = StartApplication.app_stage;

        clickedButton.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(StartApplication.class.getResource(fxml_filename));

        fxmlLoader.load();

        Parent root = fxmlLoader.getRoot();

        stage.setScene(new Scene(root));
        stage.setResizable(false);

        stage.show();
    }

}
