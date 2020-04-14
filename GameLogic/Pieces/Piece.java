package GameLogic.Pieces;

import GameLogic.Move;

/**
 * Abstract class for Piece
 *
 * @author Venkat Pamulapati
 * @version 4/1/20
 */

public abstract class Piece {
    protected String type;


    public enum Side {
        UP,
        DOWN
    }

    protected Side side;
    private boolean captured;

    public Piece(Side side){
        this.side = side;
        this.captured = false;
    }

    public abstract void doMove(Move move);

    public Side getSide() {
        return side;
    }


    public void Capture() {
        this.captured = true;
    }

    public String toString() {

        return this.type;
    }


}
