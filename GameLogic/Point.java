package GameLogic;
import GameLogic.Pieces.Piece;

public class Point {
    enum riverSide{
        upRiver,
        downRiver
    }
    private int x;
    private int y;
    
    private int x2;
    private int y2;
    
    private Piece piece;
    private riverSide side;

    /**
     * Analogous to the spaces on a chess board, except they are points in Chinese Chess.
     * Usually we aren't concerned with the x/y of a point, but rather the x/y provided by the move object.
     * @param x
     * @param y
     */
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



    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece newPiece) {
        this.piece = newPiece;
    }

    //These getter and setter methods utilize x2 and y2 which represent the coordinates of the point on the GUI window
    public int getX() {
        return this.x2;
    }
    public int getY() {
        return this.y2;
    }

    public void setPosition(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }
}
