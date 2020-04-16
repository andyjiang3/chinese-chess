package GameLogic;

import java.util.ArrayList;
import GameLogic.Pieces.*;

public class tester {

    public static void main(String[] args) throws Exception {



        //test player side methods
        Player player1 = new Player(1, "Hi", Piece.Side.UP);
        Player player2 = new Player(2, "Hi2", Piece.Side.DOWN);
        if (player1.getPlayerSide() == Piece.Side.DOWN) {
            System.out.println("Player is down river");
        } else {
            System.out.println("Player is up river");


        }

        //test color
        System.out.println("Color: " + player1.getColor());

        //test player captured pieces
        System.out.println(player1.getNumPiecesCaptured());
        player1.addPieceCaptured(new Horse(player2.getPlayerSide()));
        System.out.println(player1.getNumPiecesCaptured());
        player1.printPiecesCaptured();

        player1.clearPiecesCaptured();
        System.out.println(player1.getNumPiecesCaptured());

        //test timer
        player1.startTurnTimer();
        Thread.sleep(1000);  //1s
        player1.stopTurnTimer();
        player1.printElapsedTime();





        Board gBoard1 = new Board();

        ArrayList<Move> testMoves = new ArrayList<Move>();

        testMoves.add(new Move(4, 9, 4, 8));
        testMoves.add(new Move(1, 7, 1, 0));
        testMoves.add(new Move(4, 3, 4, 4));
        testMoves.add(new Move(5, 0, 4, 1));
        testMoves.add(new Move(7, 9, 4, 0));

        testMoves.add(new Move(4, 6, 4, 5));
        testMoves.add(new Move(4, 5, 4, 4));
        testMoves.add(new Move(4, 5, 4, 4));
        testMoves.add(new Move(4, 4, 4, 3));
        testMoves.add(new Move(4, 3, 5, 3));




        for (Move move : testMoves) {
            printMove2(move, gBoard1);
            gBoard1.printBoard();
            System.out.println("############################################################################################################################");
            System.out.println();
        }

        gBoard1.printBoard();
    }

//    public static void printMove(Move move, Board board) {
//        System.out.printf("%n%12s%12s%12s%n", "", "Original", "Final");
//        System.out.printf("%12s%12s%12s%n", "Before move:", board.getPoint(move.getOriginX(), move.getOriginY()).getPiece(), board.getPoint(move.getFinalX(), move.getFinalY()).getPiece() );
//        board.tryMove(move);
//        System.out.printf("%12s%12s%12s%n", "After move:", board.getPoint(move.getOriginX(), move.getOriginY()).getPiece(), board.getPoint(move.getFinalX(), move.getFinalY()).getPiece() );
//    }

    private static void printMove2(Move move, Board board) {
        System.out.print(" Moved " + board.getPoint(move.getOriginX(), move.getOriginY()).getPiece() + " from (" + move.getOriginX() + ", " + move.getOriginY() + ") to (" + move.getFinalX() + ", " + move.getFinalY() + ")");
        if (board.getPoint(move.getFinalX(), move.getFinalY()).getPiece() != null) {
            System.out.print("  Captured " + board.getPoint(move.getFinalX(), move.getFinalY()).getPiece() + "  ");

        }
        board.tryMove(move);
        System.out.println();
    }


}
