package GameLogic.Pieces;

import GameLogic.Move;

import static GameLogic.Pieces.Piece.Side.UP;

public class General extends Piece {
    public General(Side side) {
        super(side);

        this.type = "General";
    }

    @Override

    public void doMove(Move move) {
        if (!move.isHorizontal() && !move.isVertical()) {
            move.setValid(false);
        }
        if (move.getDx() > 1 || move.getDy() > 1) {
            move.setValid(false);
        }

        //stays in generals quarters
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
