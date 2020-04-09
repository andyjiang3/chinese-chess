package GameLogic.Pieces;
import GameLogic.Move;
import GameLogic.Pieces.Piece;

public class Elephant extends Piece{
    public Elephant(Color color) {
        super(color);
        this.type = "Elephant";
    }
    @Override

    public void doMove(Move move) {
        if (!move.isDiagonal()) {
            move.setValid(false);
        }
        if (move.getDx() != 2) {
            move.setValid(false);
        }
        //need to also stop from crossing river
    }
}
