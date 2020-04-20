package GameLogic;

import java.security.UnresolvedPermission;
import java.util.ArrayList;
import GameLogic.Pieces.*;
import java.util.Scanner;

public class tester {

    public static void main(String[] args) throws Exception {




        //test player side methods
        Player player1 = new Player(1, "Hi", Piece.Side.DOWN);
        Player player2 = new Player(2, "Hi2", Piece.Side.UP );
        if (player1.getPlayerSide() == Piece.Side.DOWN) {
            System.out.println("Player is down river");
        } else {
            System.out.println("Player is up river");


        }

        //test new Move methods/constructor
        Move move1 = new Move(new Horse(player1.getPlayerSide()),4, 8, 4, 9);
        Move move2 = new Move(new Cannon(player1.getPlayerSide()), new Soldier(player1.getPlayerSide()), 3, 2,1, 2);
        Move move3 = new Move(new Horse(player1.getPlayerSide()),4, 8, 4, 9);
        Move move4 = new Move(new Cannon(player1.getPlayerSide()), new Soldier(player1.getPlayerSide()), 3, 2,1, 2);
        Move move5 = new Move(new Horse(player1.getPlayerSide()),4, 8, 4, 9);
        Move move6 = new Move(new Cannon(player1.getPlayerSide()),4, 8, 4, 9);
        Move move7 = new Move(new Cannon(player1.getPlayerSide()), new Soldier(player1.getPlayerSide()), 3, 2,1, 2);
        if (move1.getCapturedPiece() == null) {
            System.out.println("Hey");
        }
        System.out.println(move2.getCapturedPiece());

        //test MoveLogger\
//        MoveLogger logger = new MoveLogger();
//        logger.addMove(move1);
//        logger.addMove(move2);
//        logger.addMove(move3);
//        logger.addMove(move4);
//        logger.addMove(move5);
//        logger.addMove(move6);
//        logger.addMove(move7);
//        logger.printAllMoves(player1);






        //test color
        System.out.println("Color: " + player1.getColor());

        //test player captured pieces
        System.out.println(player1.getNumPiecesCaptured());
        player1.addPieceCaptured(new Horse(player2.getPlayerSide()));
        player1.addPieceCaptured(new Soldier(player2.getPlayerSide()));
        player1.addPieceCaptured(new Cannon(player2.getPlayerSide()));
        player1.addPieceCaptured(new Chariot(player2.getPlayerSide()));
        player1.addPieceCaptured(new Horse(player2.getPlayerSide()));
        System.out.println(player1.getNumPiecesCaptured());
        player1.printPiecesCaptured();

        player1.clearPiecesCaptured();
        System.out.println(player1.getNumPiecesCaptured());

        //test timer


        Board gBoard1 = new Board();
        Scanner in = new Scanner(System.in);
        MoveLogger logger = new MoveLogger();

        int x, y, finalX, finalY;

        gBoard1.printBoard();
        int counter = 0;
        while(true) {

            if (counter % 2 == 0) {

                System.out.println(player1.getName() + "'s turn. " + "(" + player1.getColor() + " Side)" + " - Round " + logger.getRound() + " ===============");
                System.out.print("Total Pieces Captured: " + player1.getNumPiecesCaptured() + " | ");
                player1.printElapsedTime();
                System.out.println();
                System.out.println("Move Logger: ");
                logger.printAllMoves(player1);
                System.out.println();

                System.out.println("Move (x y x y): ");
                player1.startTurnTimer();
                x = in.nextInt();
                y = in.nextInt();
                finalX = in.nextInt();
                finalY = in.nextInt();
                player1.tryMove(new Move(x, y, finalX, finalY), gBoard1, logger);
                player1.stopTurnTimer();

            }

            if (counter % 2 == 1) {
                System.out.println(player2.getName() + "'s turn. " + "(" + player2.getColor() + " Side)" + " - Round " + logger.getRound() + " ===============");
                System.out.print("Total Pieces Captured: " + player2.getNumPiecesCaptured() + " | ");
                player2.printElapsedTime();
                System.out.println();
                System.out.println("Move Logger: ");
                logger.printAllMoves(player2);
                System.out.println();

                System.out.println("Move (x y x y): ");
                player2.startTurnTimer();
                x = in.nextInt();
                y = in.nextInt();
                finalX = in.nextInt();
                finalY = in.nextInt();
                player2.tryMove(new Move(x, y, finalX, finalY), gBoard1, logger);
                player2.stopTurnTimer();

            }



            gBoard1.printBoard();
            System.out.println("############################################################################################################################");
            System.out.println();
            counter++;
        }
        /*
               Red: 4 9 4 8 General
               Black: 4 3 4 4 Soldier
               Red: 4 6 4 5 Soldier
               Black: 4 4 4 5 Soldier! Captured Soldier
               Red: 0 6 0 5 Soldier
               Black 4 5 4 6 Soldier
               Red: 7 9 8 7 Horse
               Black: 8 3 8 4 Soldier
               Red:
         */


    }

//    public static void printMove(Move move, Board board) {
//        System.out.printf("%n%12s%12s%12s%n", "", "Original", "Final");
//        System.out.printf("%12s%12s%12s%n", "Before move:", board.getPoint(move.getOriginX(), move.getOriginY()).getPiece(), board.getPoint(move.getFinalX(), move.getFinalY()).getPiece() );
//        board.tryMove(move);
//        System.out.printf("%12s%12s%12s%n", "After move:", board.getPoint(move.getOriginX(), move.getOriginY()).getPiece(), board.getPoint(move.getFinalX(), move.getFinalY()).getPiece() );
//    }

//    private static void printMove2(Move move, Board board) {
//        System.out.print(" Moved " + board.getPoint(move.getOriginX(), move.getOriginY()).getPiece() + " from (" + move.getOriginX() + ", " + move.getOriginY() + ") to (" + move.getFinalX() + ", " + move.getFinalY() + ")");
//        if (board.getPoint(move.getFinalX(), move.getFinalY()).getPiece() != null) {
//            System.out.print("  Captured " + board.getPoint(move.getFinalX(), move.getFinalY()).getPiece() + "  ");
//
//        }
//        board.tryMove(move);
//        System.out.println();
//    }


}
