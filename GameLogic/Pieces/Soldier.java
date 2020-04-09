package GameLogic.Pieces;
import GameLogic.Move;
import GameLogic.Pieces.Piece;

public class Soldier extends Piece{
    public Soldier(Color color) {
        super(color);
        this.type = "Soldier";
    }
    @Override

    public void doMove(Move move) {
        if (!move.isHorizontal() && !move.isVertical()) {
            move.setValid(false);
        }
        if (move.getDx() != 1 || move.getDy() != 1) {
            move.setValid(false);
        }
        //WE NEED TO ENSURE THIS WORKS ONCE IT CROSSES THE RIVER
    }
}
