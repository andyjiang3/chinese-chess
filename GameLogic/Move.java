package GameLogic;
import GameLogic.Pieces.Piece;

/**
 * Class to generate the move object, which handles moves on the board
 *
 * @author Venkat
 * @version 4/1/20
 */

public class Move {
    private int originX;
    private int originY;
    private int finalX;
    private int finalY;


    private int dx;
    private int dy;
    private boolean isHorizontal;
    private boolean isVertical;
    private boolean isDiagonal;
    private boolean isValid;
    //private boolean isClear;
    //private boolean numObstacles; //number of pieces on the path

    public Move(int originX, int originY, int finalX, int finalY) {
        this.dx = finalX - originX;
        this.dy = finalY - originY;
        if (dx == 0 && dy != 0) {
            this.isVertical = true;
        }
        if (dy == 0 && dx != 0) {
            this.isHorizontal = true;
        }
        if (dx == dy && dx != 0) {
            this.isDiagonal = true;
        }

    }

    public Move(Piece piece, int originX, int originY, int finalX, int finalY) {
        //this iteration makes the move object hold the piece? Not sure how to structure
    }

    public void setValid(boolean v) {
        this.isValid = v;
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public boolean isDiagonal() {
        return isDiagonal;
    }

    public boolean isValid() {
        return isValid;
    }




}
