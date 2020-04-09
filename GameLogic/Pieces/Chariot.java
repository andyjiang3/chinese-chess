package GameLogic.Pieces;
        import GameLogic.Move;
        import GameLogic.Pieces.Piece;

public class Chariot extends Piece{

    public Chariot(Color color) {
        super(color);
        this.type = "Chariot";
    }
    @Override

    public void doMove(Move move) {
        if (!move.isHorizontal() && !move.isVertical()) {
            move.setValid(false);
        }
    }
}
