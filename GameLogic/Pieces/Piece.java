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

    public enum Color {
        WHITE,
        BLACK
    }

    private Color color;
    private boolean captured;

    public Piece(Color color){
        this.color = color;
        this.captured = false;


    }

    public abstract void doMove(Move move);

    public Color getColor() {
        return color;
    }


    public void Capture() {
        this.captured = true;
    }

    public String toString() {

        return this.type;
    }


}
