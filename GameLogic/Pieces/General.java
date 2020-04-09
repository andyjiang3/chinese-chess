package GameLogic.Pieces;
import GameLogic.Move;
import GameLogic.Pieces.Piece;

public class General extends Piece{
    public General(Color color) {
        super(color);
        this.type = "General";
    }
    @Override

    public void doMove(Move move) {
        if (!move.isHorizontal() && !move.isVertical()) {
            move.setValid(false);
        }
        if (move.getDx() != 1 || move.getDy() != 1) {
            move.setValid(false);
        }
    }
}
