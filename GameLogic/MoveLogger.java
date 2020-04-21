package GameLogic;

import java.util.ArrayList;
import GameLogic.Pieces.*;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * MoveLogger object holds all the moves completed in the board, including the piece and the piece captured.
 *
 * @author Andy Jiang
 */
public class MoveLogger {

    //Red = Piece.Side.DOWN
    //Black = Piece.Side.UP

    private static ArrayList<Move> redMoveHistory = new ArrayList<Move>();
    private static ArrayList<Move> blackMoveHistory = new ArrayList<Move>();

    public MoveLogger() {
        redMoveHistory = new ArrayList<Move>(0);
        blackMoveHistory = new ArrayList<Move>(0);
    }

    public static void addMove(Move move) {


            int redCount = redMoveHistory.size();
            int blackCount = blackMoveHistory.size();
            //last turn is red.
            if (redCount > blackCount) {
                blackMoveHistory.add(move);
            } else {
                redMoveHistory.add(move);
            }

        //test: red = {1, 1} 2
        //       blue = {1}; 1
    }

    public static int getRedRound() {

        return redMoveHistory.size() + 1;
    }

    public static int getBlackRound() {

        return blackMoveHistory.size() + 1;
    }

    public static boolean isRoundDone() {
        //if size of red and black move history is same, then round is done.
        return redMoveHistory.size() == blackMoveHistory.size();
    }

    /**
     * Get the last executed move logged.
     * @return last executed move
     */

    public static Move getLastMove() {


            int redCount = redMoveHistory.size();
            int blackCount = blackMoveHistory.size();

            //last turn was red
            if (redCount > blackCount) {
                if (redMoveHistory.isEmpty()) {
                    return null;
                } else {
                    return redMoveHistory.get(redMoveHistory.size() - 1); //get last move
                }
                //last turn was black. blackCount == redCount
            } else {
                if (blackMoveHistory.isEmpty()) {
                    return null;
                } else {
                    return blackMoveHistory.get(blackMoveHistory.size() - 1);  //get last move
                }
            }

    }
    /**
     * Get the last executed move logged of a side/color.
     * @param player the player side relative to the river. UP = black, DOWN = red.
     * @return last executed move
     */
    public static Move getLastMove(Player player) {
        if (player.getPlayerSide() == Piece.Side.DOWN) { //red side
            //check to see if empty
            if (redMoveHistory.isEmpty()) {
                return null;
            } else {
                return redMoveHistory.get(redMoveHistory.size() - 1); //get last move
            }
        } else {
            //check to see if empty
            if (blackMoveHistory.isEmpty()) {
                return null;
            } else {
               return blackMoveHistory.get(blackMoveHistory.size() - 1);  //get last move
            }
        }
    }

    //for saving files
    public static void saveAllMoves(Player player1, Player player2, File file) throws Exception {

            FileWriter fw = new FileWriter(new File(file.toString() + ".txt"));
            BufferedWriter bw = new BufferedWriter(fw);

             bw.write("Logged Moves: ");
             bw.newLine();
             bw.newLine();

             if (player1.getPlayerSide() == Piece.Side.DOWN) {          //player 1 is red, player2 is black
             bw.write(player1.getName() + " (Red Side), Time Elapsed: " + player1.printElapsedTime() + " ================");
             bw.newLine();
             } else {                                                   //player 1 is black, player2 is red
                 bw.write(player1.getName() + " (Black Side), Time Elapsed: " + player1.printElapsedTime() + " ================");
                 bw.newLine();
             }
            for (int i = 0; i < redMoveHistory.size(); i++) {
                if (i < 9) {
                    bw.write("Red   - Round 0" + (i + 1) + ": " + "Moved " + redMoveHistory.get(i).getPiece() + " from (" + redMoveHistory.get(i).getOriginX() + ", " + redMoveHistory.get(i).getOriginY() + ") to (" + redMoveHistory.get(i).getFinalX() + ", " + redMoveHistory.get(i).getFinalY() + ").");
                    if (redMoveHistory.get(i).getCapturedPiece() != null) {
                        bw.write(" Captured " + redMoveHistory.get(i).getCapturedPiece());
                        bw.newLine();
                    } else {
                        bw.newLine();
                    }
                } else {
                    bw.write("Red   - Round " + (i + 1) + ": " + "Moved " + redMoveHistory.get(i).getPiece() + " from (" + redMoveHistory.get(i).getOriginX() + ", " + redMoveHistory.get(i).getOriginY() + ") to (" + redMoveHistory.get(i).getFinalX() + ", " + redMoveHistory.get(i).getFinalY() + ").");
                    if (redMoveHistory.get(i).getCapturedPiece() != null) {
                        bw.write(" Captured " + redMoveHistory.get(i).getCapturedPiece());
                        bw.newLine();
                    } else {
                        bw.newLine();
                    }
                }
            }

            bw.newLine();
        if (player2.getPlayerSide() == Piece.Side.DOWN) {          //player 2 is red, player1 is black
            bw.write(player2.getName() + " (Red Side), Time Elapsed: " + player2.printElapsedTime() + " ================");
            bw.newLine();
        } else {                                                   //player 2 is black, player1 is red
            bw.write(player2.getName() + " (Black Side), Time Elapsed: " + player2.printElapsedTime() + " ================");
            bw.newLine();
        }

            for (int i = 0; i < blackMoveHistory.size(); i++) {
                if (i < 9) {
                    bw.write("Black - Round 0" + (i + 1) + ": " + "Moved " + blackMoveHistory.get(i).getPiece() + " from (" + blackMoveHistory.get(i).getOriginX() + ", " + blackMoveHistory.get(i).getOriginY() + ") to (" + blackMoveHistory.get(i).getFinalX() + ", " + blackMoveHistory.get(i).getFinalY() + ").");
                    if (blackMoveHistory.get(i).getCapturedPiece() != null) {
                        bw.write(" Captured " + blackMoveHistory.get(i).getCapturedPiece());
                        bw.newLine();
                    } else {
                        bw.newLine();
                    }
                } else {
                    bw.write("Black - Round " + (i + 1) + ": " + "Moved " + blackMoveHistory.get(i).getPiece() + " from (" + blackMoveHistory.get(i).getOriginX() + ", " + blackMoveHistory.get(i).getOriginY() + ") to (" + blackMoveHistory.get(i).getFinalX() + ", " + blackMoveHistory.get(i).getFinalY() + ").");
                    if (blackMoveHistory.get(i).getCapturedPiece() != null) {
                        bw.write(" Captured " + blackMoveHistory.get(i).getCapturedPiece());
                        bw.newLine();
                    } else {
                        bw.newLine();
                    }
                }
            }

            bw.flush();
            bw.close();



    }

    /**
     * Undo last move in game.
     * @return the move that was removed
     */

    public static Move undoLastMove(Board board) {
        int redCount = redMoveHistory.size();
        int blackCount = blackMoveHistory.size();

        //last turn was red
        if (redCount > blackCount) {
            if (redMoveHistory.isEmpty()) {
                return null;
            } else {
                board.undoMove(redMoveHistory.get(redMoveHistory.size() - 1), redMoveHistory.get(redMoveHistory.size() - 1).getCapturedPiece());
                Move current = redMoveHistory.remove(redMoveHistory.size() - 1);
//                System.out.println(current);
                return current;

            }
            //last turn was black. blackCount == redCount
        } else {
            if (blackMoveHistory.isEmpty()) {
                return null;
            } else {
                board.undoMove(blackMoveHistory.get(blackMoveHistory.size() - 1), blackMoveHistory.get(blackMoveHistory.size() - 1).getCapturedPiece());
                Move current = blackMoveHistory.remove(blackMoveHistory.size() - 1);
                return current;

            }
        }
    }




}
