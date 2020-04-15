package GameLogic.Pieces;

import GameLogic.Move;

public class Soldier extends Piece {
    //private Side curSide;


    public Soldier(Side side) {
        super(side);
        this.type = "Soldier";
    }

    @Override


    /**
     * Checks if the move pattern is actually valid or not for a piece. This does not handle the legality of a move.
     * For example: A pawn moves forward, but only moves left or right when it crosses a river. It can never move two spaces
     *
     * @param move
     */
    public void checkPattern(Move move) {
        super.checkPattern(move);

        //finds which side of river it's on, and sets it as member data, maybe scope could just be method?
        Side curSide;
        if (move.getOriginY() <= 4) {
            curSide = Side.UP;
        } else {
            curSide = Side.DOWN;
        }

        //checks for vertical forward moves only based on side
        if (side == curSide) {
            if (this.side == Side.UP) {
                if (move.getDy() != 1 || !move.isVertical()) {
                    move.setValid(false);
                }
            }
            if (side == Side.DOWN) {
                if (move.getDy() != -1 || !move.isVertical()) {
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
                if (!(move.getDy() == 1 || Math.abs(move.getDx()) == 1)) {
                    move.setValid(false);
                }
            }
            if (this.side == Side.DOWN) {
                if (!(move.getDy() == -1 || Math.abs(move.getDx()) == 1)) {
                    move.setValid(false);
                }
            }

        }
    }
}
