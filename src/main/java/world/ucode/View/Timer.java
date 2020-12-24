package world.ucode.View;

import javafx.animation.AnimationTimer;
import javafx.geometry.NodeOrientation;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import world.ucode.Controller.GameController;

import java.util.Random;

public class Timer extends AnimationTimer {
    private int millisecond = 0;
    private int XYStep = 0;
    private int kal = 0;

    private int moveToX = 0;
    private int moveToY = 0;
    private double stepToX = 0d;
    private double stepToY = 0d;
    private boolean moving = false;
    private boolean movingXSide = false; // false - left, true - right
    private boolean movingYSide = false; // false - down, true - up
    private boolean movingXEnd = false;
    private boolean movingYEnd = false;

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
        //if (game.thirst <= 0 || game.health <= 0 || game.cleanless <= 0 || game.happiness <= 0 || game.eat <= 0) {
        if (game.health <= 0 || game.happiness <= 0 || game.thirst <= 0 || game.eat <= 0) {
            stop();
            Main main = new Main();
            Main.sc = Main.sc.GameOver;
            main.start(Main.currentStage);
            DBConection db = new DBConection();
            db.deadPet(game.pokName);
        }
        else {
            MovePet();
            game.health -= game.health / 10000;
            game.eat -= 0.0002;
            game.thirst -= 0.0002;
            if (game.eat < 0.7 || game.thirst < 0.7 || game.happiness < 0.7) {
                game.health -= (game.health / 10000) * 2;
            }
            game.cleanless -= 0.0002;
            if(game.cleanless < 0.7) {
                game.kal.setEffect(null);
                game.happiness -= 0.0005;
            }
            else
                game.kal.setEffect(new Shadow());
            game.happiness -= 0.0002;

            game.setProgressBars(game.eat, game.health, game.thirst, game.happiness, game.cleanless, game.maxHP);
        }
    }

    private void MovePet() {
        GameController contr = Main.loader4.getController();
        if(moving) {
            XYStep++;
            if(movingXSide && XYStep % 2 == 0 && !movingXEnd) {
                contr.petia.setLayoutX(contr.petia.getLayoutX() + stepToX);
                if(contr.petia.getLayoutX() > moveToX) {
                    contr.petia.setLayoutX(moveToX);
                    movingXEnd = true;
                }
            } else if(!movingXSide && XYStep % 2 == 0 && !movingXEnd) {
                contr.petia.setLayoutX(contr.petia.getLayoutX() - stepToX);
                if(contr.petia.getLayoutX() < moveToX) {
                    contr.petia.setLayoutX(moveToX);
                    movingXEnd = true;
                }
            } else if(!movingYSide && XYStep % 2 != 0 && !movingYEnd) {
                contr.petia.setLayoutY(contr.petia.getLayoutY() + stepToY);
                if(contr.petia.getLayoutY() > moveToY) {
                    contr.petia.setLayoutY(moveToY);
                    movingYEnd = true;
                }
            } else if(movingYSide && XYStep % 2 != 0 && !movingYEnd) {
                contr.petia.setLayoutY(contr.petia.getLayoutY() - stepToY);
                if(contr.petia.getLayoutY() < moveToY) {
                    contr.petia.setLayoutY(moveToY);
                    movingYEnd = true;
                }
            } else if(movingXEnd && movingYEnd) {
                moving = false;
                XYStep = 0;
            }
        } else if(millisecond > getRandomNumberIntsMilliS()) {
            moveToX = getRandomNumberIntsX();
            moveToY = getRandomNumberIntsY();
            if (contr.petia.getLayoutX() < moveToX) {
                stepToX = (moveToX - contr.petia.getLayoutX()) / 100d;
                contr.petia.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
                movingXSide = true;
            } else if (contr.petia.getLayoutX() > moveToX) {
                stepToX = (contr.petia.getLayoutX() - moveToX) / 100d;
                contr.petia.nodeOrientationProperty().setValue(NodeOrientation.LEFT_TO_RIGHT);
                movingXSide = false;
            } else {
                moveToX += 1d;
                stepToX += 1d;
                movingXSide = true;
            }
            if (contr.petia.getLayoutY() < moveToY) {
                stepToY = (moveToY - contr.petia.getLayoutY()) / 100d;
                movingYSide = false;
            } else if (contr.petia.getLayoutY() > moveToY) {
                stepToY = (contr.petia.getLayoutY() - moveToY) / 100d;
                movingYSide = true;
            } else {
                moveToY += 1d;
                stepToY += 1d;
                movingYSide = false;
            }
            millisecond = 0;
            movingXEnd = false;
            movingYEnd = false;
            moving = true;
        } else {
            millisecond++;
            kal++;
        }
    }

    public static int getRandomNumberIntsX() {
        Random rand = new Random();
        return rand.ints(20, (630 + 1)).findFirst().getAsInt();
    }

    public static int getRandomNumberIntsY() {
        Random rand = new Random();
        return rand.ints(150, (350 + 1)).findFirst().getAsInt();
    }

    public static int getRandomNumberIntsMilliS() {
        Random rand = new Random();
        return rand.ints(500, (1000 + 1)).findFirst().getAsInt();
    }
}


