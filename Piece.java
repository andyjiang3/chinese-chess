/**
 * @authors: Venkat
 * @date 4/1/20
 * @purpose abstract class for peice
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

    public abstract Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.captured = false;


    }

    public Color getColor(){
        return color;
    }
    public Type getType() {
        return type;
    }
}