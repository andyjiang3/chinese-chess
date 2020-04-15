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

    /**
     * Constructor for creating a Piece. All that is needed is which side of the board the piece is on. The side is provided as an enum.
     * @param side
     */
    public Piece(Side side){
        this.side = side;
        this.captured = false;
    }

    public void checkPattern(Move move){
        move.setValid(true);
    }

    /**
     * Are we up river or down river? Determines the player
     * @return
     */
    public Side getSide() {
        return side;
    }


    /**
     * Sets the current piece to a captured state, not capturing a different piece.
     * If this piece was captured by another, than you would use capture on this peice.
     */
    public void Capture() {
        this.captured = true;
    }

    public String toString() {

        return this.type;
    }


}
