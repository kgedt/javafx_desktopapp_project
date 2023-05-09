package project.files.final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class StartApplication extends Application {
    public static Stage app_stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("authorizePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Authorize");
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