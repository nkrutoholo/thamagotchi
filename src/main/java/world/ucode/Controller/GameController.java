package world.ucode.Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import world.ucode.View.Main;
import world.ucode.View.Timer;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public static double health = 1;
    public static double happiness = 1;
    public static double eat = 1;
    public static double thirst = 1;
    public static double cleanless = 1;
    public static double maxHP;

    public static final Image[] img = new Image[3];

    private AnimationTimer timer = new Timer();

    @FXML
    public ImageView petia;

    @FXML
    public ProgressBar hp;

    @FXML
    public ProgressBar happy;

    @FXML
    public ProgressBar hunger;

    @FXML
    public ProgressBar water;

    @FXML
    public ProgressBar cream;

    private String pokName;

    @FXML
    public void play() throws Exception {
        happy.setProgress(happy.getProgress() + 0.05);
    }

    @FXML
    public void feed() throws Exception {
        hunger.setProgress(hunger.getProgress() + 0.05);
    }

    @FXML
    public void clean() throws Exception {
        cream.setProgress(cream.getProgress() + 0.05);
    }

    @FXML
    public void giveMedicine() throws Exception {
        hp.setProgress(hp.getProgress() + 0.05);
    }

    @FXML
    public void giveWater() throws Exception {
        water.setProgress(water.getProgress() + 0.05);
    }

    @FXML
    public void quit() {
        System.exit(0);
    }

    public void setProgressBars(double eat, double health, double thirst, double happiness, double cleanless, double maxHp) {
        hp.setProgress((health / (maxHp / 100d)) / 100d);
        hunger.setProgress(eat);
        hp.setProgress(health);
        water.setProgress(thirst);
        cream.setProgress(cleanless);
        happy.setProgress(happiness);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        img[0] = new Image("/Pikachu.gif");
        img[1] = new Image("/Squirtle.gif");
        img[2] = new Image("/Charmander.gif");
        timer.start();
    }
}
