package GameLogic;

import GameLogic.Pieces.*;

import java.util.Arrays;

/**
 * When working with the array natively via [][] ALWAYS FLIP X AND Y because that's how all the functions handle it.
 */
public class Board {

    //test
    private Point[][] gBoard;
    private int upGeneralX = 4;
    private int upGeneralY = 0;
    private int downGeneralX = 4;
    private int downGeneralY = 9;
    private boolean upCheck = false; //up is in check
    private boolean downCheck = false; //down is in check


    /**
     * Creating a Board object to handle the game
     */
    public Board() {
        gBoard = new Point[10][9];
        initialize(gBoard);

    }


    /**
     * Tries a move by running it through move checker.
     * Updates General Positions as member data
     * Sees if the current player is in check after moving, if so, does not accept move
     * If move is legal, then the board switches the current player and repaints the gui.
     *
     * @param move
     */
    public void tryMove(Move move) {
        if (new MoveChecker(this, move).isLegal()) {
            Piece curr = gBoard[move.getOriginY()][move.getOriginX()].getPiece();
            Piece captured = this.gBoard[move.getFinalY()][move.getFinalX()].getPiece();


            updateGeneral(move);
            doMove(move);
            if(generalOpen()){
                undoMove(move);
            }
            else {
                testCheck();
                if (curr.getSide() == Piece.Side.UP && upCheck) {
                    System.out.println("Illegal Move! You're in check");
                    undoMove(move);

                }
                if (curr.getSide() == Piece.Side.DOWN && downCheck) {
                    System.out.println("Illegal Move! You're in check");
                    undoMove(move);
                }
                else{
                /*
                Switch Player
                Repaint Board
                Switch Timer, part of player
                 */
                }
            }


        } else {
            System.out.println("Illegal Move!");
        }


    }

    /**
     * Executes a given move on the board by altering the game board array.
     * @param move
     */
    public void doMove(Move move) {
        Piece curr = gBoard[move.getOriginY()][move.getOriginX()].getPiece();
        //Piece captured = this.gBoard[move.getFinalY()][move.getFinalX()].getPiece();
        this.gBoard[move.getFinalY()][move.getFinalX()].setPiece(curr);
        this.gBoard[move.getOriginY()][move.getOriginX()].setPiece(null);
    }

    /**
     * Undoes a given move on the board by altering the game board array.
     * Used when testing for checks, not for general purpose undoing yet.
     * @param move
     */
    public void undoMove(Move move) {
        Piece curr = gBoard[move.getOriginY()][move.getOriginX()].getPiece();
        Piece captured = this.gBoard[move.getFinalY()][move.getFinalX()].getPiece();
        this.gBoard[move.getOriginY()][move.getOriginX()].setPiece(curr);
        this.gBoard[move.getFinalY()][move.getFinalX()].setPiece(captured);
    }

    /**
     * Returns a point object at a specified board location (not array index, but coordinate instead.
     * @param x
     * @param y
     * @return
     */
    public Point getPoint(int x, int y) {
        return gBoard[y][x];
    }

    /**
     * Sets up the board to play by placing all the pieces down as instantiated points. If there is no piece, it is set to null.
     * @param board
     */
    private static void initialize(Point[][] board) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                board[y][x] = new Point(x, y); //weird but basically transposes for easier coding and visualization. x and y are coordinates if the origin was the top right of the gameboard.
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

    public void updateGeneral(Move move) {
        Piece temp = gBoard[move.getOriginY()][move.getOriginX()].getPiece();
        if (temp.toString() == "General") {
            if (temp.getSide() == Piece.Side.DOWN) {
                downGeneralX = move.getFinalX();
                downGeneralY = move.getFinalY();

            } else if (temp.getSide() == Piece.Side.UP) {
                upGeneralX = move.getFinalX();
                upGeneralY = move.getFinalY();
            }
        }
    }

    /**
     * Scans the board to check if either general is in check.
     */
    public void testCheck() {
        downCheck = false;
        upCheck = false;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 10; y++) {
                if (getPoint(x, y).getPiece().getSide() == Piece.Side.UP) {
                    if (new MoveChecker(this, new Move(x, y, downGeneralX, downGeneralY)).isLegal()) {
                        downCheck = true;
                    }
                }
                if (getPoint(x, y).getPiece().getSide() == Piece.Side.DOWN) {
                    if (new MoveChecker(this, new Move(x, y, upGeneralX, upGeneralY)).isLegal()) {
                        upCheck = true;
                    }
                }
            }
        }
    }

    /**
     * Checks if the generals are facing each other
     * @return
     */
    public boolean generalOpen() {


        int obstacleCount = 0;

        if (upGeneralX != downGeneralX) {
            return false;
        } else {
            for (int i = upGeneralY + 1; i < downGeneralX; i++) {
                if (getPoint(downGeneralX, i) != null) {
                    return true;
                }
            }
        }
        return false;



    }


}
