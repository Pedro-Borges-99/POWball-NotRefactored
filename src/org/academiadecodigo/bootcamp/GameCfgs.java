package org.academiadecodigo.bootcamp;

public class GameCfgs {
     static int pixelsBetweenBalls=2;
     static int pixelsBetweenRows=1;
     static int numBalls =15;
     static int numRows =10;
     static int leftBoarderDistance =30;//PADDING


    public int getPixelsBetweenBalls() {
        return pixelsBetweenBalls;
    }

    public int getPixelsBetweenRows() {
        return getPixelsBetweenBalls();
    }
    public int getNumBalls() {
        return numBalls;
    }
    public int getNumRows() {
        return numRows;
    }
    public int getLeftBoarderDistance() {
        return leftBoarderDistance;
    }
}
