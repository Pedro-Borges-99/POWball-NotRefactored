package org.academiadecodigo.bootcamp.Grid;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.ArrayList;

public class BallsGrid {
    public static int pixelsBetweenBalls=2;
    public static int pixelsBetweenRows=1;
    public static int numBalls =15;
    public static int numRows =16;//added 1 to check if game is over or not

    public static int leftBoarderDistance =30;//PADDING

    private Rectangle gameGrid;//eventually add a constructor that dictates the numb of ball and consequently the gameGrid dimensions


    private ArrayList<ArrayList<Ball>> ballArrayList= new ArrayList<>(numBalls);

    private ArrayList<ArrayList<Ball>> ballGrid; //useless, we can just use ballArrayList

    public void init(int rowsFilled) {
        for (int j = 0; j < numRows; j++) {
            int pixelsLeft = (j % 2 == 0) ? leftBoarderDistance : leftBoarderDistance + (Ball.getWidth() / 2);
            int incrementRight = Ball.getWidth() + pixelsBetweenBalls;
            int incrementDown = Ball.getHeight() + pixelsBetweenRows;
            ArrayList<Ball> li = new ArrayList<>();
            for (int i = 0; i < numBalls; i++) {
                if (j < rowsFilled) {
                    Ball ball = new Ball(Ball.getColorRandom(), pixelsLeft + (i * incrementRight), j * incrementDown, j, i);
                    li.add(ball);
                } else {
                    Ball ball = new Ball(BallColors.NOBALL, pixelsLeft + (i * incrementRight), j * incrementDown, j, i);
                    li.add(ball);
                }
            }
            ballArrayList.add(li);
        }
        initGrid();
    }

    public void initGrid() {
        ballGrid=this.getBallArrayList();

        for (int i = 0; i < ballGrid.size(); i++) {
            for (int j = 0; j < ballGrid.get(i).size(); j++) {
                //Canvas.snapshot();
                ballGrid.get(i).get(j).getBall().draw();
            }
        }
    }

    public ArrayList<ArrayList<Ball>> getBallGrid() {
        return this.ballGrid;
    }

    public ArrayList<ArrayList<Ball>> getBallArrayList() {
        return ballArrayList;
    }



}
