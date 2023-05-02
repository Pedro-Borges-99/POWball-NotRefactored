package org.academiadecodigo.bootcamp.Grid;

public enum BallColors {
    RED("redPowBubbleBall.png"),
    YELLOW("yellowPowBubbleBall.png"),
    BLACK("blackPowBubbleBall.png"),
    BLUE("bluePowBubbleBall.png"),
    //DARKBLUE("Images/darkBluePowBubbleBall.png"),
    GRAY("grayPowBubbleBall.png"),
    GREEN("greenPowBubbleBall.png"),
    //ORANGE("Images/orangePowBubbleBall.png"),
    PURPLE("purplePowBubbleBall.png"),
    WHITE("whitePowBubbleBall.png"),
    NOBALL("bolaTransparente.png");



    private String imagePath;
    BallColors(String imagePath){
        this.imagePath=imagePath;

    }
    public String getImagePath(){
        return this.imagePath;
    }




}

