package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import java.io.IOException;
import java.security.Key;
import java.util.Set;

public class MouseKeyboardHandler implements KeyboardHandler, MouseHandler {

    private Cannon cannon;
    private Keyboard keyboard;
    private Mouse mouse;

    public MouseKeyboardHandler(Cannon cannon) {
        this.cannon = cannon;
        init();
    }

    public void init() {

        keyboard = new Keyboard(this);
        mouse = new Mouse(this);


        // Handles the spaceBar clicking and listening
        KeyboardEvent fireTheBallWithSpacebar = new KeyboardEvent();
        KeyboardEvent soundMute = new KeyboardEvent();

        KeyboardEvent quitGame = new KeyboardEvent();
        KeyboardEvent restartGame = new KeyboardEvent();


        quitGame.setKey(KeyboardEvent.KEY_Q);
        quitGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        restartGame.setKey(KeyboardEvent.KEY_R);
        restartGame.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        fireTheBallWithSpacebar.setKey(KeyboardEvent.KEY_SPACE);
        fireTheBallWithSpacebar.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        soundMute.setKey(KeyboardEvent.KEY_M);
        soundMute.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        keyboard.addEventListener(fireTheBallWithSpacebar);
        keyboard.addEventListener(soundMute);
        keyboard.addEventListener(restartGame);
        keyboard.addEventListener(quitGame);

        // Handles the left mouse licking
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case (KeyboardEvent.KEY_SPACE):
                // Do something by clicking space
                break;
            case  (KeyboardEvent.KEY_M):
                try {
                    if (!MusicGen.isPlayBackGround()) {
                        MusicGen.playBackGround();
                    } else {
                        MusicGen.muteBackGround();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case (KeyboardEvent.KEY_Q) :
                // Create method to Quit Game
                if(cannon.isGameOver()) {
                    System.exit(0);
                }

                break;
            case (KeyboardEvent.KEY_R):
                // Create method to Restart Game
                try {
                    if(cannon.isGameOver()) {
                        Runtime.getRuntime().exec("java -jar powballshooter.jar");
                        System.exit(0);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                break;


                // Purposefully left as a switch in case we want to add more buttons for other stuff.
        }

    }

    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEventType.MOUSE_CLICKED) {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            if(!cannon.isBallMoving()&&!cannon.isGameOver()) {
                cannon.shoot((int) x,(int) y);
                cannon.setBallMoving(true);
            }

            // To be used to fire the ball.
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEventType.MOUSE_MOVED) {
            double newX = mouseEvent.getX();
            double newY = mouseEvent.getY();
            // System.out.println("Your X coordinates are:" + newX + " and your Y are: " + newY);

            // To be used to update the straight line from the loaded ball.
        }
    }
}
