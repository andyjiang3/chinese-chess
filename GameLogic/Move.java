package GameLogic;

import GameLogic.Pieces.Piece;

/**
 * Class to generate the move object, which handles moves on the board
 * It does not actually carry out moves or check for their validity.
 * It simply just represents the details of a move, namely the original and final
 * x and y cooridnates (note, these are not indecies on the board array. They are coordinates which are translated to indecies by the board object.)
 *
 * @author Venkat Pamulapati
 * @version 4/1/20
 */

public class Move {
    private Piece piece;
    private Piece capturedPiece;

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
    private int numObstacles; //number of pieces on the path

    /**
     * Is a simple construct of a move. Only carries the starting location and the final location. No validation.
     *
     * @param originX the starting x coordinate
     * @param originY starting y coordinate
     * @param finalX  the destination x coordinate
     * @param finalY  the destination y coordinate
     */
    public Move(int originX, int originY, int finalX, int finalY) {
        this.originX = originX;
        this.originY = originY;
        this.finalX = finalX;
        this.finalY = finalY;

        this.dx = finalX - originX;
        this.dy = finalY - originY;
        if (dx == 0 && dy != 0) {
            this.isVertical = true;
        }
        if (dy == 0 && dx != 0) {
            this.isHorizontal = true;
        }
        if (Math.abs(dx) == Math.abs(dy) && dx != 0) {
            this.isDiagonal = true;
        }

    }

    /**
     * Used in move logger, not in board to save a move that isn't an attack
     *
     * @param piece   the piece that is being moved
     * @param originX the starting x coordinate
     * @param originY starting y coordinate
     * @param finalX  the destination x coordinate
     * @param finalY  the destination y coordinate
     */
    public Move(Piece piece, int originX, int originY, int finalX, int finalY) {
        //this iteration makes the move object hold the piece? Not sure how to structure
        this.piece = piece;
        this.originX = originX;
        this.originY = originY;
        this.finalX = finalX;
        this.finalY = finalY;

        this.dx = finalX - originX;
        this.dy = finalY - originY;
        if (dx == 0 && dy != 0) {
            this.isVertical = true;
        }
        if (dy == 0 && dx != 0) {
            this.isHorizontal = true;
        }
        if (Math.abs(dx) == Math.abs(dy) && dx != 0) {
            this.isDiagonal = true;
        }
    }

    /**
     * Used in move logger for logging an attack move.
     *
     * @param piece         the piece that is being moved
     * @param capturedPiece the piece that is being captured
     * @param originX       the starting x coordinate
     * @param originY       starting y coordinate
     * @param finalX        the destination x coordinate
     * @param finalY        the destination y coordinate
     */
    public Move(Piece piece, Piece capturedPiece, int originX, int originY, int finalX, int finalY) {

        this.piece = piece;
        this.capturedPiece = capturedPiece;
        this.originX = originX;
        this.originY = originY;
        this.finalX = finalX;
        this.finalY = finalY;

        this.dx = finalX - originX;
        this.dy = finalY - originY;
        if (dx == 0 && dy != 0) {
            this.isVertical = true;
        }
        if (dy == 0 && dx != 0) {
            this.isHorizontal = true;
        }
        if (Math.abs(dx) == Math.abs(dy) && dx != 0) {
            this.isDiagonal = true;
        }
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

    /**
     * Accesed by the Piece object to set wheter or not a move follows the appropriate move pattern.
     *
     * @param v true = good to go, false = that isn't even normally allowed, go read the rules.
     */
    public void setValid(boolean v) {
        this.isValid = v;
    }

    public Piece getPiece() {
        return piece;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public String toString() {
        return originX + ", " + originY + ", " + finalX + ", " + finalY;
    }


}
