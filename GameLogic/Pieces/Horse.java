package GameLogic.Pieces;
import GameLogic.Move;
import GameLogic.Pieces.Piece;

public class Horse extends Piece{
    public Horse(Color color) {
        super(color);
        this.type = "Horse";
    }
    @Override

    public void doMove(Move move) {
        if( ! ((Math.abs(move.getDx()) ==1 && Math.abs(move.getDy()) == 2) || (Math.abs(move.getDx()) == 2 && Math.abs(move.getDy()) == 1))){
            move.setValid(false);
        }
    }
}
