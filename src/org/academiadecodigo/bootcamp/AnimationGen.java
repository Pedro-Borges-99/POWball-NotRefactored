package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.Grid.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.bootcamp.Grid.BallsGrid.leftBoarderDistance;
import static org.academiadecodigo.bootcamp.Grid.BallsGrid.pixelsBetweenBalls;


public class AnimationGen {
    static private String background = "backgroundNEW.png";

    static private String signatureBackGroundPath = "My project.png";

    static Picture myBackGround;

    static Picture signatureBackGround;

    static Picture soundButton;

    static Picture gameOver;

    static Picture highScore;

    static Picture ammoBox;

    static Picture cannon;

    static Picture handShoot;

    static Picture fireline;

    private static GameSaver gameSaver;



/*
            Use default background
 */
    public void initBackGround() {

        double gridMaxX = (BallsGrid.numBalls * (Ball.width + pixelsBetweenBalls) + leftBoarderDistance);

        myBackGround =new Picture(0,0,this.getBackground());
        myBackGround.draw();

        signatureBackGround = new Picture (1250,400,signatureBackGroundPath);
        signatureBackGround.draw();

        soundButton = new Picture(1000,800, "audiobutton.png");
        soundButton.draw();

        highScore = new Picture(1000,80, "highscoretable.png");
        highScore.draw();

        ammoBox = new Picture(100,788, "ammobox.png");
        ammoBox.draw();


        //handShoot = new Picture(345,840,"C:\\Users\\JonnyWork\\Desktop\\workspace\\NEWBUBBLESHOOTER\\powballshooter\\src\\Images\\shoothand.png");
/*        handShoot = new Picture(345,815,"Images/rsz_1rsz_1maybethisone.png");
        handShoot.draw();*/

        fireline = new Picture(35,700, "rsz_1fireline.png");
        fireline.draw();

        cannon = new Picture(((gridMaxX/2) - 11),745, "cannonPowBalls.png");
        cannon.draw();

    }

    public void setGameSaver(GameSaver gameSaver) {
        AnimationGen.gameSaver = gameSaver;
    }

    public static void setGameOver() {
        /*
                Here we need to struct the game
         */
        gameOver = new Picture(0,0, "gameOver.png");
        gameOver.draw();
        AnimationGen.gameSaver.updateHighscore(Cannon.getPoints());
    }

    /*
            Method overloading, give a new path for new background || use setBackGround and set it
     */

    public void initBackGround(String path) {
        myBackGround =new Picture(0,0,path);
        myBackGround.draw();
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String path) {
        this.background = path;
    }
}
