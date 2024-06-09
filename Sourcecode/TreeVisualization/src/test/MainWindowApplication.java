package test;

import screen.controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader mainLoader = new FXMLLoader(
            MainWindowApplication.class.getResource("/screen/fxml/mainWindow.fxml"));
            MainWindowController mainController = new MainWindowController();
            mainLoader.setController(mainController);
            Scene scene = new Scene(mainLoader.load());
            stage.setTitle("Tree View Visualizer");
            stage.setScene(scene);
            // stage.setFullScreen(true);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}