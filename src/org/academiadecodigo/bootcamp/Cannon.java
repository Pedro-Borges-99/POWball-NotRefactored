package org.academiadecodigo.bootcamp;


import org.academiadecodigo.bootcamp.Grid.Ball;
import org.academiadecodigo.bootcamp.Grid.BallColors;
import org.academiadecodigo.bootcamp.Grid.BallsGrid;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static org.academiadecodigo.bootcamp.Grid.BallsGrid.*;

public class Cannon {
    public static boolean isGameOver;
    private static int points = 0;
    private Ball loadedBall;

    private Ball ammoBall;
    private Ball ammoBall_third;
    private LinkedList<Ball> ammunition;

    private ArrayList<ArrayList<Ball>> ballList;
    private MouseKeyboardHandler mouseKeyboardHandler;
    private boolean isBallMoving;
    private TestForCollisions testForCollisions;
    private TestForAloneBalls testForAloneBalls;
    private int shootCounter = 0;

    public Cannon(ArrayList<ArrayList<Ball>> ballList) {

        generateLoadedBall();
        this.ballList = ballList;
        this.mouseKeyboardHandler = new MouseKeyboardHandler(this);

    }

    public static void addPoints(int x) {
        points += x;
    }

    public static int getPoints() {
        return points;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isBallMoving() {
        return isBallMoving;
    }

    public void setBallMoving(boolean ballMoving) {
        isBallMoving = ballMoving;
    }

    public Ball getLoadedBall() {
        return loadedBall;
    }

    public void generateLoadedBall() {
        if (ammoBall == null) {
            loadedBall = Ball.generateBall((BallsGrid.numBalls * (Ball.width + pixelsBetweenBalls) + leftBoarderDistance) / 2, 800);
            loadedBall.getBall().draw();
            ammoBall = Ball.generateBall((BallsGrid.numBalls * (Ball.width + pixelsBetweenBalls) + leftBoarderDistance) / 3, 800);
            ammoBall.getBall().draw();
            ammoBall_third = Ball.generateBall((BallsGrid.numBalls * (Ball.width + pixelsBetweenBalls) + leftBoarderDistance) / 4, 800);
            ammoBall_third.getBall().draw();

        } else {
            loadedBall = Ball.generateBall((BallsGrid.numBalls * (Ball.width + pixelsBetweenBalls) + leftBoarderDistance) / 2, 800);
            loadedBall.setColorPath(ammoBall.getColorPath());
            loadedBall.getBall().load(ammoBall.getColorPath());
            loadedBall.getBall().draw();
            ammoBall.setColorPath(ammoBall_third.getColorPath());
            ammoBall.getBall().load(ammoBall_third.getColorPath());
            ammoBall_third.getBall().delete();
            ammoBall_third = Ball.generateBall((BallsGrid.numBalls * (Ball.width + pixelsBetweenBalls) + leftBoarderDistance) / 4, 800);
            ammoBall_third.getBall().draw();
        }
    }

    public TestForCollisions getTestForCollisions() {
        return testForCollisions;
    }

    public void shoot(int mouseX, int mouseY) {
        // Hard coded mouseX and mouseY for testing purposes.


        mouseY = (mouseY < 800) ? mouseY : 790;
        double gridMaxX = (BallsGrid.numBalls * (Ball.width + pixelsBetweenBalls) + leftBoarderDistance);

        double ballVelocity = 20;
        double xVelocity = ((double) (mouseX - ((gridMaxX / 2) + 25)));
        double yVelocity = (mouseY - (800 + 25));
        double length = Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
        xVelocity *= ballVelocity / length;
        yVelocity *= ballVelocity / length;

        // Implementing a threaded task for the movement of the loaded ball below

        AtomicReference<Double> finalXVelocity = new AtomicReference<>(xVelocity);
        double finalYVelocity = yVelocity;

        Runnable movingLoadedball = () -> {
            while (!hasCollidedWithBall()) {
                if ((loadedBall.getBall().getX() < GameCfgs.leftBoarderDistance) || ((loadedBall.getBall().getX() + 50) > gridMaxX)) {
                    finalXVelocity.updateAndGet(v -> new Double((double) (v * -1)));
                }
                loadedBall.getBall().delete();
                loadedBall.getBall().translate(finalXVelocity.get(), finalYVelocity);
                loadedBall.getBall().draw();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            MusicGen.ballHit();
            activateBallInGrid();


            isBallMoving = false;
        };
        new Thread(movingLoadedball).start();

    }

    public boolean hasCollidedWithBall() {
        Ball checkedAgainstBall;
        String noBall = BallColors.NOBALL.getImagePath();
        // Top left corner of ball
        for (int i = 0; i < ballList.size(); i++) {
            for (int j = 0; j < ballList.get(i).size(); j++) {
                checkedAgainstBall = ballList.get(i).get(j);
                if (!noBall.equals(checkedAgainstBall.getColorPath()) || (loadedBall.getBall().getY() < -1)) {
                    // Compares left side of loaded ball with right side of other balls
                    if (loadedBall.getBall().getX() + 10 < (checkedAgainstBall.getX() + 40) &&
                            ((loadedBall.getBall().getX() + 40 > (checkedAgainstBall.getX() + 10)) &&
                                    (loadedBall.getBall().getY() + 10 < (checkedAgainstBall.getY() + 40) &&
                                            ((loadedBall.getBall().getY() + 40 > (checkedAgainstBall.getY() + 10))))) || (loadedBall.getBall().getY() < -1)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public void activateBallInGrid() {
        Ball checkedAgainstBall = null;
        Ball closestEmptyBall = null;
        String noBall = BallColors.NOBALL.getImagePath();
        double distance;
        double minimumDistance = 10000;

        for (int i = 0; i < ballList.size(); i++) {
            for (int j = 0; j < ballList.get(i).size(); j++) {
                checkedAgainstBall = ballList.get(i).get(j);

                if (noBall.equals(checkedAgainstBall.getColorPath())) {
                    distance = calculateDistance(loadedBall.getBall().getX(), checkedAgainstBall.getBall().getX(), loadedBall.getBall().getY(), checkedAgainstBall.getBall().getY());
                    if (distance <= minimumDistance) {
                        minimumDistance = distance;
                        closestEmptyBall = ballList.get(i).get(j);
                    }
                }
            }
        }

        closestEmptyBall.setColorPath(loadedBall.getColorPath());
        closestEmptyBall.getBall().load(loadedBall.getColorPath());


        // new TestForCollisions(loadedBall.getBall(),ballList);
        loadedBall.getBall().delete();
        closestEmptyBall.setCanExplode(true);
        testForCollisions = new TestForCollisions(closestEmptyBall, ballList);
        testForCollisions.doCheckCollisions();

        for (int i = 0; i < ballList.size(); i++) {
            for (int j = 0; j < ballList.get(i).size(); j++) {
                if (ballList.get(i).get(j).getCanExplode()) {
                    ballList.get(i).get(j).getBall().load(noBall);
                    ballList.get(i).get(j).setColorPath(noBall);
                    ballList.get(i).get(j).setCanExplode(false);
                }
            }
        }

        testForAloneBalls = new TestForAloneBalls(ballList);
        testForAloneBalls.doCheckCollisions();

        shootCounter++;

        if (shootCounter == 5) {
            addNewRound();
            shootCounter = 0;
        }


        if (isGameOver()) {
            loadedBall.getBall().delete();
            ammoBall.getBall().delete();
            ammoBall_third.getBall().delete();
        } else {
            generateLoadedBall();
        }
        GameSaver.displayCurrentScore();
    }


    private double calculateDistance(double loadedBallX, double comparedBallX, double loadedBallY, double comparedBallY) {
        double xDifference = loadedBallX - comparedBallX;
        double yDifference = loadedBallY - comparedBallY;
        double xSquared = xDifference * xDifference;
        double ySquared = yDifference * yDifference;
        return Math.sqrt(xSquared + ySquared);
    }


    public void addNewRound() {

        // Here we're "moving" two rows below by copying two rows above
        for (int i = ballList.size() - 1; i > 1; i--) {
            for (int x = 0; x < ballList.get(i).size(); x++) {
                ballList.get(i).get(x).setColorPath(ballList.get(i - 2).get(x).getColorPath());
                ballList.get(i).get(x).getBall().load(ballList.get(i - 2).get(x).getColorPath());
            }
        }


        // (BallColors colors, int x, int y, int row, int col)

        for (int i = 0; i < 2; i++) {
            for (int x = 0; x < ballList.get(i).size(); x++) {
                ballList.get(i).get(x).setColorPath(Ball.getColorRandom().getImagePath());
                ballList.get(i).get(x).getBall().load(ballList.get(i).get(x).getColorPath());
            }
        }

        for (int i = 0; i < ballList.get(i).size(); i++) {
            if (!Objects.equals(ballList.get(ballList.size() - 1).get(i).getColorPath(), BallColors.NOBALL.getImagePath())) {
                Cannon.isGameOver = true;
                AnimationGen.setGameOver();
            }
        }
    }
}
