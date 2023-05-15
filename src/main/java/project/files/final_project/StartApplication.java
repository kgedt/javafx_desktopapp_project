package project.files.final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import static project.files.pages.Pages.*;

public class StartApplication extends Application {
    public static Stage app_stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource(authorizePage));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.getIcons().add(new Image(String.valueOf(StartApplication.class.getResource("img/ico.png"))));
        stage.setTitle("MILITARY MARKET");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        app_stage = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}

//  url('file:C:/Users/magzu/IdeaProjects/miniProjectJava/src/main/resources/miniProjectJava/img/)