package GameLogic.Pieces;
import GameLogic.Move;

public class Cannon extends Piece{
    public Cannon(Side side) {
        super(side);
        this.type = "Cannon";
    }
    @Override

    public void doMove(Move move) {
        super.doMove(move);

        if (!move.isHorizontal() && !move.isVertical()) {
            move.setValid(false);
        }
    }
}
