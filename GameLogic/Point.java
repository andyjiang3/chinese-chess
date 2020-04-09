package GameLogic;
import GameLogic.Pieces.Piece;

public class Point {
    enum riverSide{
        upRiver,
        downRiver
    }
    private int x;
    private int y;
    private Piece piece;
    private riverSide side;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
        if (y > 4) {
            this.side = riverSide.downRiver;
        } else {
            this.side = riverSide.upRiver;
        }
    }

    public Point(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        if (y > 4) {
            this.side = riverSide.downRiver;
        } else {
            this.side = riverSide.upRiver;
        }

    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece newPiece) {
        this.piece = newPiece;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
