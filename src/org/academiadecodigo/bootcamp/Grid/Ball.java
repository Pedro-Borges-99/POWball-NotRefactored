package org.academiadecodigo.bootcamp.Grid;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Ball {
    private Picture ball;


    private static BallColors myColor;

    public static int width=50; public static int getWidth(){return width;}
    public static int height=50; public static int getHeight(){return height;}
    private int row; public int getRow() {return row;}

    private int col; public int getCol() {return col;}
    private boolean canExplode=false; public void setCanExplode(boolean explode){this.canExplode=explode;}public boolean getCanExplode(){return canExplode;}
    private boolean canStay=false; public void setCanStay(boolean stay){this.canStay=stay;}public boolean getCanStay(){return canStay;}

    private static BallColors[] colors=BallColors.values();//array of possible ball colors
    private String colorPath; public String getColorPath() {return colorPath;}

    public void setColorPath(String colorPath) {
        this.colorPath = colorPath;
    }

    private int x;public int getX() {return x;}
    private int y;public int getY() {return y;}

    public Ball(BallColors colors, int x, int y, int row, int col){
        this.row=row;
        this.col=col;
        this.x=x;
        this.y=y;
        this.ball=new Picture(x,y, colors.getImagePath());
        this.colorPath= colors.getImagePath();
    }

    public BallColors getMyColor() {
        return myColor;
    }

    public void setMyColor(BallColors myColor) {
        Ball.myColor = myColor;
    }



    public static BallColors getColorRandom(){ // has to be -1 because the final ENUM is the NOBALL
        int value = (int)(Math.random()* (colors.length-1));
        Ball.myColor = colors[value];
        return colors[value];
    }

    public Picture getBall() {
        return this.ball;
    }
    public static Ball generateBall(int x,int y){
        return new Ball(getColorRandom(),x,y,0,0);
    } //will create another constructer without cols and rows for cannon bal
    public static Ball generateCannonBall(BallColors color,int x , int y) {
        return new Ball(color,(BallsGrid.numBalls*(Ball.width+BallsGrid.pixelsBetweenBalls)+BallsGrid.leftBoarderDistance)/2, 800,x,y);
    }
}
