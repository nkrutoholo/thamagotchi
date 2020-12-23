package world.ucode.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        db.createPok(petName.getText(), psswrd.getText(), Double.parseDouble(maxHp.getText()), index);
        Main main = new Main();
        Main.sc = Main.Status.GameScene;
        main.start(Main.currentStage);
        GameController g = Main.loader4.getController();
        db.loadPok(petName.getText());
//        save();
    }

//    public void save() throws SQLException, ClassNotFoundException {
//        String petName1 = petName.getText();
//        DBConection data = new DBConection();
//        data.execTable(petName1, Double.parseDouble(maxHp.getText()), psswrd.getText(), index);
//    }
}
