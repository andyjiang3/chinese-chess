package GameLogic.Pieces;
import GameLogic.Move;
import GameLogic.Pieces.Piece;

public class Cannon extends Piece{
    public Cannon(Color color) {
        super(color);
    }
    @Override

    public void doMove(Move move) {
        if (!move.isHorizontal() && !move.isVertical()) {
            move.setValid(false);
        }
    }
}
