package GameLogic;

import GameLogic.Pieces.*;

public class Board {

    //test
    private Point[][] gBoard;

    public Board() {
        gBoard = new Point[10][9];
        initialize(gBoard);

    }

    public void doMove(Move move) {
        //if(isValid(move)){...}
        Piece temp = gBoard[move.getOriginX()][move.getOriginY()].getPiece();
        this.gBoard[move.getFinalX()][move.getFinalY()].setPiece(temp);
        gBoard[move.getOriginX()][move.getOriginY()].setPiece(null);

    }

    public Point getPoint(int x, int y) {
        return gBoard[y][x];
    }

    private static void initialize(Point[][] board) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 10; y++) {
                board[x][y] = new Point(x, y);
            }
        }

        //Chariots/Rook
        board[0][0].setPiece(new Chariot(Piece.Side.UP));
        board[0][8].setPiece(new Chariot(Piece.Side.UP));

        board[9][0].setPiece(new Chariot(Piece.Side.DOWN));
        board[9][8].setPiece(new Chariot(Piece.Side.DOWN));

        //Cannons
        board[2][1].setPiece(new Cannon(Piece.Side.UP));
        board[2][7].setPiece(new Cannon(Piece.Side.UP));

        board[7][1].setPiece(new Cannon(Piece.Side.DOWN));
        board[7][7].setPiece(new Cannon(Piece.Side.DOWN));

        //Horses/Knights
        board[0][1].setPiece(new Horse(Piece.Side.UP));
        board[7][0].setPiece(new Horse(Piece.Side.UP));

        board[9][1].setPiece(new Horse(Piece.Side.DOWN));
        board[9][7].setPiece(new Horse(Piece.Side.DOWN));

        //Elephants/bishops
        board[0][2].setPiece(new Horse(Piece.Side.UP));
        board[0][6].setPiece(new Horse(Piece.Side.UP));

        board[9][2].setPiece(new Horse(Piece.Side.DOWN));
        board[9][6].setPiece(new Horse(Piece.Side.DOWN));

        //Guard/Advisors
        board[0][3].setPiece(new Horse(Piece.Side.UP));
        board[0][5].setPiece(new Horse(Piece.Side.UP));

        board[9][3].setPiece(new Horse(Piece.Side.DOWN));
        board[9][5].setPiece(new Horse(Piece.Side.DOWN));

        //General/King
        board[0][4].setPiece(new General(Piece.Side.UP));
        board[9][4].setPiece(new General(Piece.Side.DOWN));

        //Solider/pawns
        board[3][0].setPiece(new Soldier(Piece.Side.UP));
        board[3][2].setPiece(new Soldier(Piece.Side.UP));
        board[3][4].setPiece(new Soldier(Piece.Side.UP));
        board[3][6].setPiece(new Soldier(Piece.Side.UP));
        board[3][8].setPiece(new Soldier(Piece.Side.UP));

        board[6][0].setPiece(new Soldier(Piece.Side.DOWN));
        board[6][2].setPiece(new Soldier(Piece.Side.DOWN));
        board[6][4].setPiece(new Soldier(Piece.Side.DOWN));
        board[6][6].setPiece(new Soldier(Piece.Side.DOWN));
        board[6][8].setPiece(new Soldier(Piece.Side.DOWN));

    }


}
