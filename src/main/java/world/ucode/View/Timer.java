package world.ucode.View;

import javafx.animation.AnimationTimer;
import world.ucode.Controller.GameController;

public class Timer extends AnimationTimer {
    @Override
    public void handle(long l) {
        try {
            doHandle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void doHandle() throws Exception {
        GameController game = Main.loader4.getController();
        if (game.thirst <= 0 || game.health <= 0 || game.cleanless <= 0 || game.happiness <= 0 || game.eat <= 0) {
            stop();
            Main main = new Main();
            Main.sc = Main.sc.GameOver;
            main.start(Main.currentStage);
        }
        else {
            game.eat -= 0.0005;
            game.health -= 0.0005;
            game.cleanless -= 0.0002;
            game.happiness -= 0.0002;
            game.thirst -= 0.0002;
            game.setProgressBars(game.eat, game.health, game.thirst, game.happiness, game.cleanless, game.maxHP);
        }
    }
}


