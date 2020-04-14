package GameLogic.Pieces;

import GameLogic.Move;

public class Elephant extends Piece {
    public Elephant(Side side) {
        super(side);
        this.type = "Elephant";
    }

    @Override

    public void doMove(Move move) {
        super.doMove(move);

        if (!move.isDiagonal()) {
            move.setValid(false);
        }
        if (Math.abs(move.getDx()) != 2) {
            move.setValid(false);
        }

        if (side == Side.UP) {
            if (move.getFinalY() > 4) {
                move.setValid(false);
            }
        }
        if (side == Side.DOWN) {
            if (move.getFinalY() < 5) {
                move.setValid(false);
            }
        }
        //need to also stop from crossing river
    }
}
