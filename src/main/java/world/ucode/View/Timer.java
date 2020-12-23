package world.ucode.View;

import javafx.animation.AnimationTimer;
import world.ucode.Controller.GameController;

import java.util.Random;

public class Timer extends AnimationTimer {
    private int millisecond = 0;

    private int moveToX = 0;
    private int moveToY = 0;
    private double stepToX = 0d;
    private double stepToY = 0d;
    private boolean moving = false;
    private boolean movingWay = false;

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
//            if(game.maxHP > 0 && game.maxHP <= 1)
//                game.eat -= 0.0005;
//            else if(game.maxHP > 1 && game.maxHP <= 10)
//                game.health -= 0.01;
//            else if(game.maxHP > 10 && game.maxHP <= 100)
//                game.health -= 0.5;
//            else
//                game.health -= 0.1;
            MovePet();
            game.eat -= 0.0005;
            game.cleanless -= 0.0002;
            game.happiness -= 0.0002;
            game.thirst -= 0.0002;
            game.setProgressBars(game.eat, game.health, game.thirst, game.happiness, game.cleanless, game.maxHP);
        }
    }
    private void MovePet() {
        System.out.println(millisecond);
        if(moving) {
            GameController contr = Main.loader4.getController();

            System.out.println("Get X: " + contr.petia.getX());
            System.out.println("moveToX: " + moveToX);
            System.out.println("stepToX: " + stepToX);
            System.out.println();
            System.out.println("Get Y: " + contr.petia.getY());
            System.out.println("moveToY: " + moveToY);
            System.out.println("stepToY: " + stepToY);
            System.out.println();

            if(contr.petia.getX() < moveToX) {
                contr.petia.setX(contr.petia.getX() + stepToX);
                if(contr.petia.getX() > moveToX) {
                    contr.petia.setX(moveToX);
                    moving = false;
                }
            } else if(contr.petia.getX() > moveToX) {
                contr.petia.setX(contr.petia.getX() - stepToX);
                if(contr.petia.getX() < moveToX) {
                    contr.petia.setX(moveToX);
                    moving = false;
                }
            } else if(contr.petia.getY() < moveToY) {
                contr.petia.setY(contr.petia.getY() + stepToY);
                if(contr.petia.getY() > moveToY) {
                    contr.petia.setY(moveToY);
                    moving = false;
                }
            } else if(contr.petia.getY() > moveToY) {
                contr.petia.setY(contr.petia.getY() - stepToY);
                if(contr.petia.getY() < moveToY) {
                    contr.petia.setY(moveToY);
                    moving = false;
                }
            } else {
                moving = false;
            }
        } else if(millisecond == 1500) {
            GameController contr = Main.loader4.getController();

            moveToX = getRandomNumberIntsX();
            moveToY = getRandomNumberIntsY();

            if(contr.petia.getX() < moveToX) {
                double x = contr.petia.getX();
            }

            stepToX = moveToX / 1000;
            stepToY = moveToY / 1000;

            millisecond = 0;
            moving = true;
        } else {
            millisecond++;
        }
    }

    public static int getRandomNumberIntsX() {
        Random rand = new Random();
        return rand.ints(20, (430 + 1)).findFirst().getAsInt();
    }

    public static int getRandomNumberIntsY() {
        Random rand = new Random();
        return rand.ints(105, (200 + 1)).findFirst().getAsInt();
    }
}


