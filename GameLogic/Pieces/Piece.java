package GameLogic.Pieces;

import GameLogic.Move;

/**
 * Abstract class for Piece. Each piece is unaware of it's location. They contain little member data and are stored on points, which are managed by boards.
 *
 * @author Venkat Pamulapati
 * @version 4/1/20
 */


public abstract class Piece {
    protected String type;
    protected boolean canWinAlone;
    protected Side side;
    private boolean captured;

    /**
     * Constructor for creating a Piece. All that is needed is which side of the board the piece is on. The side is provided as an enum.
     *
     * @param side
     */
    public Piece(Side side) {
        this.side = side;
        this.captured = false;
    }

    /**
     * This method will check if a given move is a move that this piece can generally make. For example, elephants can move diagonally two spaces. In a way, this is what defines a piece.
     *
     * @param move
     */
    public void checkPattern(Move move) {
        move.setValid(true);
    }

    /**
     * Are we up river or down river? Determines the player
     *
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

    /**
     * returns the matching file name of a piece
     *
     * @return
     * @author Michael Yu
     */
    public String getImageName() {
        String fileName = "";
        if (side == Piece.Side.UP)
            fileName += "black_";
        else
            fileName += "red_";

        if (type.equals("Soldier"))
            fileName += "soldier";
        else if (type.equals("General"))
            fileName += "general";
        else if (type.equals("Cannon"))
            fileName += "cannon";
        else if (type.equals("Horse"))
            fileName += "horse";
        else if (type.equals("Elephant"))
            fileName += "elephant";
        else if (type.equals("Guard"))
            fileName += "guard";
        else if (type.equals("Chariot"))
            fileName += "chariot";

        fileName += ".png";
        return fileName;
    }

    /**
     * If a type of piece can't win by having only it and the gneeral it cannot win alone. This is used to check for stalemate edge cases.
     *
     * @return
     */
    public boolean canWinAlone() {
        return canWinAlone;
    }

    /**
     * The side the player is from. Player 1 corresponds to Down, Player 2 corresponds to up.
     */
    public enum Side {
        UP,
        DOWN
    }

}
