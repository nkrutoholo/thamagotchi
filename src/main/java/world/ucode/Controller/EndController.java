package world.ucode.Controller;


import javafx.fxml.FXML;
import world.ucode.View.Main;

public class EndController {
    @FXML
    public void menu() throws Exception {
        Main main = new Main();
        Main.sc = Main.Status.Menu;
        main.start(Main.currentStage);
    }

    @FXML
    public void exit() {
        System.exit(0);
    }
}
