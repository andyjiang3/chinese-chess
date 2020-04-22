package GameLogic.Pieces;

import GameLogic.Move;

/**
 * Guard Piece
 */
public class Guard extends Piece {

    public Guard(Side side) {
        super(side);
        this.type = "Guard";
        this.canWinAlone = false;
    }

    @Override

    public void checkPattern(Move move) {
        super.checkPattern(move);

        if (!move.isDiagonal()) {
            move.setValid(false);
        }
        if (Math.abs(move.getDx()) != 1) {
            move.setValid(false);
        }

        if (move.getFinalX() < 3 || move.getFinalX() > 5) {
            move.setValid(false);
        }

        if (side == Side.UP) {
            if (move.getFinalY() > 2) {
                move.setValid(false);
            }
        }
        if (side == Side.DOWN) {
            if (move.getFinalY() < 7) {
                move.setValid(false);
            }
        }


    }
}
