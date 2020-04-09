package GameLogic;

import GameLogic.Pieces.*;
import jdk.nashorn.internal.ir.Block;
import jdk.nashorn.internal.ir.WhileNode;

import javax.xml.crypto.dsig.keyinfo.KeyName;

public class Board {


    private Point[][] gBoard;

    public Board() {
        gBoard = new Point[9][10];
        intialize(gBoard);

    }

    public void validMove(Move move) {

    }

    public static void intialize(Point[][] gBoard) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                gBoard[x][y] = new Point(x, y);
            }
        }

        //Chariots/Guards
        gBoard[0][0].setPiece(new Chariot(Piece.Color.WHITE));
        gBoard[8][0].setPiece(new Chariot(Piece.Color.WHITE));

        gBoard[0][9].setPiece(new Chariot(Piece.Color.BLACK));
        gBoard[8][9].setPiece(new Chariot(Piece.Color.BLACK));

        //Cannons
        gBoard[1][2].setPiece(new Cannon(Piece.Color.WHITE));
        gBoard[7][2].setPiece(new Cannon(Piece.Color.WHITE));

        gBoard[1][7].setPiece(new Cannon(Piece.Color.BLACK));
        gBoard[7][7].setPiece(new Cannon(Piece.Color.BLACK));

        //Horses/Knights
        gBoard[1][0].setPiece(new Horse(Piece.Color.WHITE));
        gBoard[7][0].setPiece(new Horse(Piece.Color.WHITE));

        gBoard[1][9].setPiece(new Horse(Piece.Color.BLACK));
        gBoard[7][9].setPiece(new Horse(Piece.Color.BLACK));

        //Elephants/bishops
        gBoard[2][0].setPiece(new Horse(Piece.Color.WHITE));
        gBoard[6][0].setPiece(new Horse(Piece.Color.WHITE));

        gBoard[2][9].setPiece(new Horse(Piece.Color.BLACK));
        gBoard[6][9].setPiece(new Horse(Piece.Color.BLACK));

        //Guard/Advisors
        gBoard[3][0].setPiece(new Horse(Piece.Color.WHITE));
        gBoard[5][0].setPiece(new Horse(Piece.Color.WHITE));

        gBoard[3][9].setPiece(new Horse(Piece.Color.BLACK));
        gBoard[5][9].setPiece(new Horse(Piece.Color.BLACK));

        //General/King
        gBoard[4][0].setPiece(new General(Piece.Color.WHITE));
        gBoard[4][9].setPiece(new General(Piece.Color.BLACK));

        //Solider/pawns
        gBoard[0][3].setPiece(new Soldier(Piece.Color.WHITE));
        gBoard[2][3].setPiece(new Soldier(Piece.Color.WHITE));
        gBoard[4][3].setPiece(new Soldier(Piece.Color.WHITE));
        gBoard[6][3].setPiece(new Soldier(Piece.Color.WHITE));
        gBoard[8][3].setPiece(new Soldier(Piece.Color.WHITE));

        gBoard[0][6].setPiece(new Soldier(Piece.Color.BLACK));
        gBoard[2][6].setPiece(new Soldier(Piece.Color.BLACK));
        gBoard[4][6].setPiece(new Soldier(Piece.Color.BLACK));
        gBoard[6][6].setPiece(new Soldier(Piece.Color.BLACK));
        gBoard[8][6].setPiece(new Soldier(Piece.Color.BLACK));

    }


}
