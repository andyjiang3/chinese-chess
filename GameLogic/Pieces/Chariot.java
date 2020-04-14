package GameLogic.Pieces;
        import GameLogic.Move;

public class Chariot extends Piece{

    public Chariot(Side side) {
        super(side);
        this.type = "Chariot";
    }
    @Override

    public void doMove(Move move) {
        super.doMove(move);

        if (!move.isHorizontal() && !move.isVertical()) {
            move.setValid(false);
        }
    }
}
