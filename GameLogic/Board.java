package GameLogic;

import GameLogic.Pieces.*;
import jdk.nashorn.internal.ir.Block;
import jdk.nashorn.internal.ir.WhileNode;

import javax.xml.crypto.dsig.keyinfo.KeyName;

public class Board {


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
        board[0][0].setPiece(new Chariot(Piece.Color.WHITE));
        board[8][0].setPiece(new Chariot(Piece.Color.WHITE));

        board[0][9].setPiece(new Chariot(Piece.Color.BLACK));
        board[8][9].setPiece(new Chariot(Piece.Color.BLACK));

        //Cannons
        board[1][2].setPiece(new Cannon(Piece.Color.WHITE));
        board[7][2].setPiece(new Cannon(Piece.Color.WHITE));

        board[1][7].setPiece(new Cannon(Piece.Color.BLACK));
        board[7][7].setPiece(new Cannon(Piece.Color.BLACK));

        //Horses/Knights
        board[1][0].setPiece(new Horse(Piece.Color.WHITE));
        board[7][0].setPiece(new Horse(Piece.Color.WHITE));

        board[1][9].setPiece(new Horse(Piece.Color.BLACK));
        board[7][9].setPiece(new Horse(Piece.Color.BLACK));

        //Elephants/bishops
        board[2][0].setPiece(new Horse(Piece.Color.WHITE));
        board[6][0].setPiece(new Horse(Piece.Color.WHITE));

        board[2][9].setPiece(new Horse(Piece.Color.BLACK));
        board[6][9].setPiece(new Horse(Piece.Color.BLACK));

        //Guard/Advisors
        board[3][0].setPiece(new Horse(Piece.Color.WHITE));
        board[5][0].setPiece(new Horse(Piece.Color.WHITE));

        board[3][9].setPiece(new Horse(Piece.Color.BLACK));
        board[5][9].setPiece(new Horse(Piece.Color.BLACK));

        //General/King
        board[4][0].setPiece(new General(Piece.Color.WHITE));
        board[4][9].setPiece(new General(Piece.Color.BLACK));

        //Solider/pawns
        board[0][3].setPiece(new Soldier(Piece.Color.WHITE));
        board[2][3].setPiece(new Soldier(Piece.Color.WHITE));
        board[4][3].setPiece(new Soldier(Piece.Color.WHITE));
        board[6][3].setPiece(new Soldier(Piece.Color.WHITE));
        board[8][3].setPiece(new Soldier(Piece.Color.WHITE));

        board[0][6].setPiece(new Soldier(Piece.Color.BLACK));
        board[2][6].setPiece(new Soldier(Piece.Color.BLACK));
        board[4][6].setPiece(new Soldier(Piece.Color.BLACK));
        board[6][6].setPiece(new Soldier(Piece.Color.BLACK));
        board[8][6].setPiece(new Soldier(Piece.Color.BLACK));

    }


}
