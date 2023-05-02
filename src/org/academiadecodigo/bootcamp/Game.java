package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.Grid.Ball;
import org.academiadecodigo.bootcamp.Grid.BallsGrid;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.IOException;
import java.util.ArrayList;

public class Game {

    Cannon myCannon;
    boolean gameOver;
    BallsGrid myGrid;

    AnimationGen animationGen;
    GameSaver gameSaver;


    public void init() throws IOException {
        animationGen = new AnimationGen();
        animationGen.initBackGround();

        myGrid = new BallsGrid();
        myGrid.init(7);

        myCannon = new Cannon(myGrid.getBallArrayList());
        // myCannon.shoot(0, 750);

        MusicGen.playBackGround();

        try {
            gameSaver = new GameSaver();
            animationGen.setGameSaver(gameSaver);
            GameSaver.displayCurrentScore();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
