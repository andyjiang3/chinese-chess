package GameLogic;

/**
 * @version 4/10/20
 */
public class MoveChecker {
    private int obstacleCount; //number of pieces in the way
    private boolean isClear; //if obstacles = 0
    private boolean isCheck; //checks the board for check (probably gonna make this a separate class at this point
    private boolean checkPiece; //checks if the move is okay for the selected piece
    private Board board;
    private Move move;

    MoveChecker(Board board, Move move) {
        this.board = board;
        this.move = move;
        CheckPiece();
        obstacleCount();
        if (obstacleCount == 0 ? this.isClear == true : this.isClear == false);

        }


    /**set up a a way to check if the move takes the piece to the otherside of the river
     * note: what side of the river does it come from
     * which side is it on currently
     * is it's move logic different (ie pawns)
     * is it allowed to be on the otherside anyway(elephants)
     * same thing for kings quarers(ie general and guards)
     *
     *
     */

    public void CheckPiece(){
        board.getPoint(move.getOriginX(), move.getOriginY()).getPiece().doMove(move);
        if (!move.isValid()) {
            this.checkPiece = false;

        }
    }
    public void obstacleCount(){
        if (move.isDiagonal() || move.isHorizontal() || move.isVertical()) {
            for (int x = move.getOriginX() + 1; x <= move.getFinalX(); x++) {
                for (int y = move.getOriginY() + 1; y <= move.getFinalY(); y++) {
                    if (board.getPoint(x, y).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }
        }
    }
}
