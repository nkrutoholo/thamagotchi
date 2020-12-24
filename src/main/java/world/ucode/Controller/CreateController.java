package world.ucode.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import world.ucode.View.DBConection;
import world.ucode.View.Main;


import java.io.File;
import java.sql.SQLException;

public class CreateController {
    @FXML
    public ImageView Pika;

    @FXML
    public ImageView Charm;

    @FXML
    public ImageView Squirt;

    @FXML
    public TextField petName;

    @FXML
    public TextField maxHp;

    @FXML
    public TextField psswrd;

    @FXML
    public Text err;

    public static int index = 1;

    @FXML
    public void clickOnPika() {
        Pika.setEffect(null);
        Squirt.setEffect(new Shadow());
        Charm.setEffect(new Shadow());
        index = 0;
    }

    @FXML
    public void clickOnSquirt() {
        Pika.setEffect(new Shadow());
        Squirt.setEffect(null);
        Charm.setEffect(new Shadow());
        index = 1;
    }

    @FXML
    public void clickOnCharm() {
        Pika.setEffect(new Shadow());
        Squirt.setEffect(new Shadow());
        Charm.setEffect(null);
        index = 2;
    }

    @FXML
    public void start() throws Exception {
        DBConection db = new DBConection();
        int st = db.createPok(petName.getText(), psswrd.getText(), Double.parseDouble(maxHp.getText()), index);
        printStatus(st);
        if (st == 4) {
            Main main = new Main();
            Main.sc = Main.Status.GameScene;
            main.start(Main.currentStage);
//            GameController g = Main.loader4.getController();
            db.loadPok(petName.getText());
        }
    }

    @FXML
    public void back() throws Exception {
        Main main = new Main();
        Main.sc = Main.Status.Menu;
        main.start(Main.currentStage);
    }

    public void printStatus(int status) {
        if(status == 4) {
            err.setText("Pet created.");
        }
        if(status == 5) {
            err.setText("This name already exists.");
        }
    }
}
