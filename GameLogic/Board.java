package GameLogic;

import GameLogic.Pieces.Piece;
import GameLogic.Pieces.Rook;

public class Board {


    private Point[][] gBoard;

    public Board() {
        gBoard = new Point[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                gBoard[x][y] = new Point(x, y);
            }
        }
        gBoard[0][0].setPiece(new Rook(Piece.Color.WHITE));


    }


}
