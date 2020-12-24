package world.ucode.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import world.ucode.View.DBConection;
import world.ucode.View.Main;

public class LoadController {
    @FXML
    public TextField pName;

    @FXML
    public TextField password;

    @FXML
    public Text error;

    @FXML
    public void pokGo() throws Exception {
        DBConection db = new DBConection();
        int st = db.checkPok(pName.getText(), password.getText());
        printStatus(st);
        if (st == 1) {
            Main main = new Main();
            Main.sc = Main.Status.GameScene;
            main.start(Main.currentStage);
            db.loadPok(pName.getText());
        }
    }

    @FXML
    public void back() throws Exception {
        Main main = new Main();
        Main.sc = Main.Status.Menu;
        main.start(Main.currentStage);
    }

    public void printStatus(int status) {
        if (status == 1) {
            error.setText("Pet loaded.");
        }
        if (status == 2) {
            error.setText("There is no pet with that name.");
        }
        if (status == 3) {
            error.setText("Invalid password.");
        }
    }

}
