package world.ucode.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import java.io.File;

public class Main extends Application {
    public static FXMLLoader loader;
    public static FXMLLoader loader1;
    public static FXMLLoader loader2;
    public static FXMLLoader loader3;
    public static FXMLLoader loader4;
    public Parent root;
    public static Stage currentStage;

    public enum Status{
        Menu,
        CreatePet,
        GameOver,
        LoadGame,
        GameScene,
        PreLoadScene
    };

    public static Status sc = Status.PreLoadScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (sc == Status.PreLoadScene) {
            DBConection db = new DBConection();
            db.firstStart();

            sc = Status.Menu;
        }

        switch (sc) {
            case Menu:
                loader = new FXMLLoader(getClass().getResource("/somebody.fxml"));
                root = loader.load();
                break;
            case CreatePet:
                loader1 = new FXMLLoader(getClass().getResource("/CreateScene.fxml"));
                root = loader1.load();
                break;
            case GameOver:
                loader2 = new FXMLLoader(getClass().getResource("/GameOverScene.fxml"));
                root = loader2.load();
                break;
            case LoadGame:
                loader3 = new FXMLLoader(getClass().getResource("/LoadScene.fxml"));
                root = loader3.load();
                break;
            case GameScene:
                loader4 = new FXMLLoader(getClass().getResource("/GameScene.fxml"));
                root = loader4.load();
                break;
            default:
                return;
        }
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
