package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.Grid.Ball;
import org.academiadecodigo.bootcamp.Grid.BallColors;
import org.academiadecodigo.bootcamp.Grid.BallsGrid;

import java.util.ArrayList;

public class TestForAloneBalls {
    private boolean firstBall=true;
    private ArrayList<ArrayList<Ball>> gameArray;
    private ArrayList<Ball> ballsToCheck =new ArrayList<>();
    public TestForAloneBalls(ArrayList<ArrayList<Ball>> gameArray){
        for (Ball x : gameArray.get(0)){
            x.setCanStay(true);
            ballsToCheck.add(x);
        }
        this.gameArray = gameArray;

    }
    public ArrayList<ArrayList<Ball>> doCheckCollisions(){
        while(ballsToCheck.size()!=0){
            checkCollisions(ballsToCheck.get(0), gameArray);
            ballsToCheck.remove(0);
        }
        cleanAloneBallsAndResetGrid();
        return gameArray; //after all shots our gameArray will be the return of this method (updates array with all balls that should explode);
    }




    public void checkCollisions(Ball ball, ArrayList<ArrayList<Ball>> grid) {

        int row = ball.getRow();
        int col = ball.getCol();
        boolean notTransparente=!ball.getColorPath().equals(BallColors.NOBALL.getImagePath());

        boolean atMinRow = row == 0;
        boolean atMaxRow = row == grid.size() - 1;
        boolean atMinCol = col == 0;
        boolean atMaxCol = col == grid.get(0).size() - 1;

        if (row % 2 == 0) {
            if (!atMaxCol) {
                if (notTransparente && !grid.get(row).get(col + 1).getCanExplode() && !grid.get(row).get(col + 1).getCanStay()) {
                    grid.get(row).get(col + 1).setCanStay(true);
                    ballsToCheck.add(grid.get(row).get(col + 1));
                }
            } //checks right
            if (!atMinCol) {
                if (notTransparente && !grid.get(row).get(col - 1).getCanExplode() && !grid.get(row).get(col - 1).getCanStay()) {
                    grid.get(row).get(col - 1).setCanStay(true);
                    ballsToCheck.add(grid.get(row).get(col - 1));
                }
            } //checks left
            if (!atMinRow && !atMinCol) {
                if (notTransparente && !grid.get(row - 1).get(col - 1).getCanExplode() && !grid.get(row - 1).get(col - 1).getCanStay()) {
                    grid.get(row - 1).get(col - 1).setCanStay(true);
                    ballsToCheck.add(grid.get(row - 1).get(col - 1));
                }
            } //checks UP left
            if (!atMinRow) {
                if (notTransparente && !grid.get(row - 1).get(col).getCanExplode() && !grid.get(row - 1).get(col).getCanStay()) {
                    grid.get(row - 1).get(col).setCanStay(true);
                    ballsToCheck.add(grid.get(row - 1).get(col));
                }
            }//checks UP right
            if (!atMaxRow && !atMinCol) {
                if (notTransparente && !grid.get(row + 1).get(col - 1).getCanExplode() && !grid.get(row + 1).get(col - 1).getCanStay()) {
                    grid.get(row + 1).get(col - 1).setCanStay(true);
                    ballsToCheck.add(grid.get(row + 1).get(col - 1));
                }
            } //checks down left
            if (!atMaxRow) {
                if (notTransparente && !grid.get(row + 1).get(col).getCanExplode() && !grid.get(row + 1).get(col).getCanStay()) {
                    grid.get(row + 1).get(col).setCanStay(true);
                    ballsToCheck.add(grid.get(row + 1).get(col));
                }
            } // checks down right


        }else{
            if (!atMaxCol) {
                if (notTransparente && !grid.get(row).get(col + 1).getCanExplode() && !grid.get(row).get(col + 1).getCanStay()) {
                    grid.get(row).get(col + 1).setCanStay(true);
                    ballsToCheck.add(grid.get(row).get(col + 1));
                }
            } //checks right
            if (!atMinCol) {
                if (notTransparente && !grid.get(row).get(col - 1).getCanExplode() && !grid.get(row).get(col - 1).getCanStay()) {
                    grid.get(row).get(col - 1).setCanStay(true);
                    ballsToCheck.add(grid.get(row).get(col - 1));
                }
            } //checks left
            if (!atMinRow){
                if (notTransparente && !grid.get(row - 1).get(col).getCanExplode() && !grid.get(row - 1).get(col).getCanStay()) {
                    grid.get(row - 1).get(col).setCanStay(true);
                    ballsToCheck.add(grid.get(row - 1).get(col));
                }
            } //checks UP left
            if (!atMinRow && !atMaxCol) {
                if (notTransparente && !grid.get(row - 1).get(col + 1).getCanExplode() && !grid.get(row - 1).get(col + 1).getCanStay()) {
                    grid.get(row - 1).get(col + 1).setCanStay(true);
                    ballsToCheck.add(grid.get(row - 1).get(col + 1));
                }
            }//checks UP right
            if (!atMaxRow) {
                if (notTransparente && !grid.get(row + 1).get(col).getCanExplode() && !grid.get(row + 1).get(col).getCanStay()) {
                    grid.get(row + 1).get(col).setCanStay(true);
                    ballsToCheck.add(grid.get(row + 1).get(col));
                }
            } //checks down left
            if (!atMaxRow && !atMaxCol) {
                if (notTransparente && !grid.get(row + 1).get(col + 1).getCanExplode() && !grid.get(row + 1).get(col + 1).getCanStay()) {
                    grid.get(row + 1).get(col + 1).setCanStay(true);
                    ballsToCheck.add(grid.get(row + 1).get(col + 1));
                }
            } // checks down right

        }
    }

    public void cleanAloneBallsAndResetGrid() {
        for (int i = 0; i < gameArray.size(); i++) {
            for (int j = 0; j < gameArray.get(i).size(); j++) {
                if (!gameArray.get(i).get(j).getCanStay()) {
                    // gameArray.get(i).get(j).getBall().delete();
                    gameArray.get(i).get(j).setColorPath(BallColors.NOBALL.getImagePath());
                    gameArray.get(i).get(j).getBall().load(BallColors.NOBALL.getImagePath());
                    gameArray.get(i).get(j).setCanStay(false);
                } else {
                    gameArray.get(i).get(j).setCanStay(false);
                }

            }
        }
    }

}