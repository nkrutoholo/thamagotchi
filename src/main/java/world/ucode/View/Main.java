package world.ucode.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import java.io.File;

public class Main extends Application {
    public static FXMLLoader loader;
    public static Parent root;
    public static Stage currentStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("/somebody.fxml"));
        //loader = new FXMLLoader(new File("./MainScene.fxml").toURI().toURL());
        root = loader.load();
        currentStage = primaryStage;
        currentStage.setScene(new Scene(root));
        currentStage.setMinWidth(800);
        currentStage.setMinHeight(600);
        currentStage.setResizable(true);
        currentStage.setTitle("Tamagotchi");
        currentStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
