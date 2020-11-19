package world.ucode.Controller;

import javafx.fxml.FXML;
import world.ucode.View.Main;

public class MainController {
    @FXML
    public void newPet() throws Exception {
        Main main = new Main();
        main.start(Main.currentStage);
    }

    @FXML
    public void loadPet() throws Exception {
        Main main = new Main();
        main.start(Main.currentStage);
    }

    @FXML
    public void quit() {
        System.exit(0);
    }
}
