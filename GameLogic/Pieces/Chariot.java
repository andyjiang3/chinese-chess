package GameLogic.Pieces;

import GameLogic.Move;

/**
 * Chariot Piece
 */
public class Chariot extends Piece{

    public Chariot(Side side) {
        super(side);
        this.type = "Chariot";
        this.canWinAlone = true;
    }

    @Override

    public void checkPattern(Move move) {
        super.checkPattern(move);

        if (!move.isHorizontal() && !move.isVertical()) {
            move.setValid(false);
        }
    }
}
