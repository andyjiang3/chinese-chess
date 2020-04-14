package GameLogic;

import GameLogic.Pieces.*;

public class Board {

    //test
    private Point[][] gBoard;

    public Board() {
        gBoard = new Point[9][10];
        initialize(gBoard);

    }

    public void doMove(Move move) {
        //if(isValid(move)){...}
        Piece temp = gBoard[move.getOriginX()][move.getOriginY()].getPiece();
        this.gBoard[move.getFinalX()][move.getFinalY()].setPiece(temp);
        gBoard[move.getOriginX()][move.getOriginY()].setPiece(null);

    }

    public Point getPoint(int x, int y) {
        return gBoard[x][y];
    }

    private static void initialize(Point[][] board) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 10; y++) {
                board[x][y] = new Point(x, y);
            }
        }

        //Chariots/Guards
        board[0][0].setPiece(new Chariot(Piece.Side.UP));
        board[8][0].setPiece(new Chariot(Piece.Side.UP));

        board[0][9].setPiece(new Chariot(Piece.Side.DOWN));
        board[8][9].setPiece(new Chariot(Piece.Side.DOWN));

        //Cannons
        board[1][2].setPiece(new Cannon(Piece.Side.UP));
        board[7][2].setPiece(new Cannon(Piece.Side.UP));

        board[1][7].setPiece(new Cannon(Piece.Side.DOWN));
        board[7][7].setPiece(new Cannon(Piece.Side.DOWN));

        //Horses/Knights
        board[1][0].setPiece(new Horse(Piece.Side.UP));
        board[7][0].setPiece(new Horse(Piece.Side.UP));

        board[1][9].setPiece(new Horse(Piece.Side.DOWN));
        board[7][9].setPiece(new Horse(Piece.Side.DOWN));

        //Elephants/bishops
        board[2][0].setPiece(new Horse(Piece.Side.UP));
        board[6][0].setPiece(new Horse(Piece.Side.UP));

        board[2][9].setPiece(new Horse(Piece.Side.DOWN));
        board[6][9].setPiece(new Horse(Piece.Side.DOWN));

        //Guard/Advisors
        board[3][0].setPiece(new Horse(Piece.Side.UP));
        board[5][0].setPiece(new Horse(Piece.Side.UP));

        board[3][9].setPiece(new Horse(Piece.Side.DOWN));
        board[5][9].setPiece(new Horse(Piece.Side.DOWN));

        //General/King
        board[4][0].setPiece(new General(Piece.Side.UP));
        board[4][9].setPiece(new General(Piece.Side.DOWN));

        //Solider/pawns
        board[0][3].setPiece(new Soldier(Piece.Side.UP));
        board[2][3].setPiece(new Soldier(Piece.Side.UP));
        board[4][3].setPiece(new Soldier(Piece.Side.UP));
        board[6][3].setPiece(new Soldier(Piece.Side.UP));
        board[8][3].setPiece(new Soldier(Piece.Side.UP));

        board[0][6].setPiece(new Soldier(Piece.Side.DOWN));
        board[2][6].setPiece(new Soldier(Piece.Side.DOWN));
        board[4][6].setPiece(new Soldier(Piece.Side.DOWN));
        board[6][6].setPiece(new Soldier(Piece.Side.DOWN));
        board[8][6].setPiece(new Soldier(Piece.Side.DOWN));

    }


}
