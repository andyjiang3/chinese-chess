package GameLogic.Pieces;

import GameLogic.Move;

public class Soldier extends Piece {
    private Side curSide;

    public Soldier(Side side) {
        super(side);
        this.type = "Soldier";
    }

    @Override

    public void doMove(Move move) {
        //finds which side of river it's on, and sets it as member data, maybe scope could just be method?
        if (move.getOriginY() > 4) {
            this.curSide = Side.UP;
        } else {
            this.curSide = Side.DOWN;
        }

        //checks for vertical forward moves only based on side
        if (this.side == curSide) {
            if (this.side == Side.UP) {
                if (move.getFinalY() - move.getOriginY() != 1 || !move.isVertical()) {
                    move.setValid(false);
                }
            }
            if (this.side == Side.DOWN) {
                if (move.getFinalY() - move.getOriginY() != -1 || !move.isVertical()) {
                    move.setValid(false);
                }
            }

        }
        //allows for horizontal movement once river is crossed
        if (this.side != curSide) {
            if (!move.isHorizontal() && !move.isVertical()) {
                move.setValid(false);
            }

            if (this.side == Side.UP) {
                if (move.getFinalY() - move.getOriginY() != 1 || move.getDx() != 1) {
                    move.setValid(false);
                }
            }
            if (this.side == Side.DOWN) {
                if (move.getFinalY() - move.getOriginY() != -1 || move.getDx() != 1) {
                    move.setValid(false);
                }
            }

        }
    }
}
