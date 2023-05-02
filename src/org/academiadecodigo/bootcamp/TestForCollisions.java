package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.Grid.Ball;
import org.academiadecodigo.bootcamp.Grid.BallsGrid;

import java.util.ArrayList;

public class TestForCollisions {
    private boolean firstBall=true;
    private int explosions;
    private ArrayList<ArrayList<Ball>> gameArray;
    private ArrayList<Ball> ballsExploded = new ArrayList<>();

    public boolean pleaseTellCannonThatGameIsOver;
    private GameSaver gameSaver;
    public TestForCollisions(Ball ball, ArrayList<ArrayList<Ball>> ballList){
        ballsExploded.add(ball);
        gameArray = ballList;

    }
    public int doCheckCollisions(){
        while(ballsExploded.size()!=0){
            checkCollisions(ballsExploded.get(0), gameArray);
            ballsExploded.remove(0);
        }
        Cannon.addPoints((explosions * 10));
        if (explosions > 0) {
            MusicGen.multipleBallHit();
        }
        return explosions;
        //return gameArray; //after all shots our gameArray will be the return of this method (updates array with all balls that should explode);
    }

    public void setGameSaver(GameSaver gameSaver) {
        this.gameSaver = gameSaver;
    }

    public void checkCollisions(Ball ball, ArrayList<ArrayList<Ball>> grid) {


        int row = ball.getRow();
        int col = ball.getCol();

       /* if ( ((row-1) < 0)  ||  ((row+1) > grid.get(0).size()) || ((col-1) < 0) || ((col+1) > grid.size())) {
            return;
        }*/

        boolean atMinRow = row == 0;
        boolean atMaxRow = row == grid.size() - 1;
        boolean atMinCol = col == 0;
        boolean atMaxCol = col == grid.get(0).size() - 1;

        if (row % 2 == 0) {
            if (!atMaxCol) {
                if (ball.getColorPath().equals(grid.get(row).get(col + 1).getColorPath()) && !grid.get(row).get(col + 1).getCanExplode()) {
                    grid.get(row).get(col + 1).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row).get(col + 1));
                }
            }//checks right
            if (!atMinCol) {
                if (ball.getColorPath().equals(grid.get(row).get(col - 1).getColorPath()) && !grid.get(row).get(col - 1).getCanExplode()) {
                    grid.get(row).get(col - 1).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row).get(col - 1));
                }
            } //checks left
            if (!atMinRow && !atMinCol) {
                if (ball.getColorPath().equals(grid.get(row - 1).get(col - 1).getColorPath()) && !grid.get(row - 1).get(col - 1).getCanExplode()) {
                    grid.get(row - 1).get(col - 1).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row - 1).get(col - 1));
                }
            } //checks UP left
            if (!atMinRow) {
                if (ball.getColorPath().equals(grid.get(row - 1).get(col).getColorPath()) && !grid.get(row - 1).get(col).getCanExplode()) {
                    grid.get(row - 1).get(col).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row - 1).get(col));
                }
            }//checks UP right
            if (!atMaxRow && !atMinCol) {
                if (ball.getColorPath().equals(grid.get(row + 1).get(col - 1).getColorPath()) && !grid.get(row + 1).get(col - 1).getCanExplode()) {
                    grid.get(row + 1).get(col - 1).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row + 1).get(col - 1));
                }
            } //checks down left
            if (!atMaxRow) {
                if (ball.getColorPath().equals(grid.get(row + 1).get(col).getColorPath()) && !grid.get(row + 1).get(col).getCanExplode()) {
                    grid.get(row + 1).get(col).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row + 1).get(col));
                }
            } // checks down right


        }else{
            if (!atMaxCol) {
                if (ball.getColorPath().equals(grid.get(row).get(col + 1).getColorPath()) && !grid.get(row).get(col + 1).getCanExplode()) {
                    grid.get(row).get(col + 1).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row).get(col + 1));
                }
            } //checks right
            if (!atMinCol) {
                if (ball.getColorPath().equals(grid.get(row).get(col - 1).getColorPath()) && !grid.get(row).get(col - 1).getCanExplode()) {
                    grid.get(row).get(col - 1).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row).get(col - 1));
                }
            } //checks left
            if (!atMinRow) {
                if (ball.getColorPath().equals(grid.get(row - 1).get(col).getColorPath()) && !grid.get(row - 1).get(col).getCanExplode()) {
                    grid.get(row - 1).get(col).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row - 1).get(col));
                }
            } //checks UP left
            if (!atMinRow && !atMaxCol){
                if (ball.getColorPath().equals(grid.get(row - 1).get(col + 1).getColorPath()) && !grid.get(row - 1).get(col + 1).getCanExplode()) {
                    grid.get(row - 1).get(col + 1).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row - 1).get(col + 1));
                }
            }//checks UP right
            if (!atMaxRow) {
                if (ball.getColorPath().equals(grid.get(row + 1).get(col).getColorPath()) && !grid.get(row + 1).get(col).getCanExplode()) {
                    grid.get(row + 1).get(col).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row + 1).get(col));
                }
            } //checks down left
            if (!atMaxRow && !atMaxCol) {
                if (ball.getColorPath().equals(grid.get(row + 1).get(col + 1).getColorPath()) && !grid.get(row + 1).get(col + 1).getCanExplode()) {
                    grid.get(row + 1).get(col + 1).setCanExplode(true);
                    explosions += 1;
                    ballsExploded.add(grid.get(row + 1).get(col + 1));
                }
            } // checks down right

        }
        if(explosions ==0) {
            ball.setCanExplode(false);
            if(ball.getRow()==grid.size()-1) {
                Cannon.isGameOver = true;
                AnimationGen.setGameOver();
            }
        }

    }
}