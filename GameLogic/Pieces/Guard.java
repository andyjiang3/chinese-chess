package GameLogic.Pieces;
import GameLogic.Move;
import GameLogic.Pieces.Piece;

public class Guard extends Piece{
    public Guard(Color color) {
        super(color);
    }
    @Override

    public void doMove(Move move) {
        if (!move.isDiagonal()) {
            move.setValid(false);
        }
        if (move.getDx() != 1) {
            move.setValid(false);
        }

        //need to find a way to keep inside the general's chambers
    }
}
