package GameLogic;
import GameLogic.Move;
/**
 * Abstract class for Piece
 * @author Venkat Pamulapati
 * @version 4/1/20
 *
 */

public abstract class Piece{
    enum Color{
        WHITE,
        BLACK
    }
    enum Type{
        KING,
        ADVISOR,
        ELEPHANT,
        HORSE,
        ROOK,
        CANNON,
        PAWN
    }

    private Color color;
    private Type type;
    private boolean captured;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.captured = false;


    }

    public abstract void doMove(Move move);

    public Color getColor(){
        return color;
    }
    public Type getType() {
        return type;
    }
}
