package world.ucode.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import world.ucode.View.DBConection;
import world.ucode.View.Main;

public class LoadController {
    @FXML
    public TextField pName;

    @FXML
    public TextField password;

    @FXML
    public void pokGo() throws Exception {
        DBConection db = new DBConection();
        db.checkPok(pName.getText(), password.getText());
        Main main = new Main();
        Main.sc = Main.Status.GameScene;
        main.start(Main.currentStage);
        db.loadPok(pName.getText());
    }

    @FXML
    public void back() throws Exception {
        Main main = new Main();
        Main.sc = Main.Status.Menu;
        main.start(Main.currentStage);
    }
}
