package GameLogic;

import GameLogic.Pieces.*;

import javax.sound.midi.Soundbank;
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
    private boolean checkMate;


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
    //HERE IS THE LAST FUNCTIONAL INSTANCE WHERE CHECKMATE WAS WORKING GREAT, AFTER THIS I STARTED ADDING ANDY'S STUFF.
    public void tryMove(Move move, Player player) {
        if (new MoveChecker(this, move).isLegal()) {

            Piece curr = gBoard[move.getOriginY()][move.getOriginX()].getPiece();
            Piece captured = this.gBoard[move.getFinalY()][move.getFinalX()].getPiece();

            if (curr.getSide() == player.getPlayerSide()) {
                doMove(move);
                testCheck();
                if (curr.getSide() == Piece.Side.UP && upCheck) {
                    System.out.println("Illegal Move! You're in check");
                    undoMove(move, captured);
                }
                if (curr.getSide() == Piece.Side.DOWN && downCheck) {
                    System.out.println("Illegal Move! You're in check");
                    undoMove(move, captured);
                } else { //Everything is LEGALLLLL lets postprocess
                    //the move is legal, now lets see if it's a winning move.
                    if (upCheck && curr.getSide() == Piece.Side.DOWN) {
                        if (checkMate(Piece.Side.UP)) {
                            checkMate = true;
                            System.out.println("##############CHECK MATE#############################");
                        }
                    }
                    if (downCheck && curr.getSide() == Piece.Side.UP) {
                        if (checkMate(Piece.Side.DOWN)) {
                            checkMate = true;
                            System.out.println("##############CHECK MATE#############################");
                        }
                    }

                }
            } else {
                System.out.println("Not your turn");
            }
        } else {
            System.out.println("Illegal Move!");
        }
    }

    public boolean tryMove3(Move move, Player player) {

        if (new MoveChecker(this, move).isLegal()) {

            Piece curr = gBoard[move.getOriginY()][move.getOriginX()].getPiece();
            Piece captured = this.gBoard[move.getFinalY()][move.getFinalX()].getPiece();

            int x = move.getOriginX();
            int y = move.getOriginY();
            int finalX = move.getFinalX();
            int finalY = move.getFinalY();


            if (curr.getSide() == player.getPlayerSide()) {
                doMove(move);
                testCheck();
                if (curr.getSide() == Piece.Side.UP && upCheck) {
                    System.out.println("Illegal Move! You're in check");
                    undoMove(move, captured);
                    return false;
                }
                if (curr.getSide() == Piece.Side.DOWN && downCheck) {
                    System.out.println("Illegal Move! You're in check");
                    undoMove(move, captured);
                    return false;
                } else { //Everything is LEGALLLLL lets postprocess
                    //the move is legal, now lets see if it's a winning move.
                    if (upCheck && curr.getSide() == Piece.Side.DOWN) {
                        if (checkMate(Piece.Side.UP)) {
                            checkMate = true;
                            System.out.println("##############CHECK MATE#############################");
                            return false;
                        }
                    }
                    if (downCheck && curr.getSide() == Piece.Side.UP) {
                        if (checkMate(Piece.Side.DOWN)) {
                            checkMate = true;
                            System.out.println("##############CHECK MATE#############################");
                            return false;
                        }
                    }

                    if (!checkMate) {   //LEGAL MOVE AND NOT IN CHECKMATE?
                        System.out.println("Moved " + curr + " from (" + x + ", " + y + ") to (" + finalX + ", " + finalY + ")");
                        if (captured != null) {
                            player.addPieceCaptured(captured);

                            System.out.println(captured + " Captured!");
                            MoveLogger.addMove(new Move(curr, captured, x, y, finalX, finalY));
                        } else {
                            MoveLogger.addMove(new Move(curr, x, y, finalX, finalY));
                        }

                        //DO OTHER THINGS =============

                        return true;

                    }




                }
            } else {
                System.out.println("Not your turn");
                return false;
            }
        } else {
            System.out.println("Illegal Move!");
            return false;
        }
        return false;  //gives error when not put? ..
    }


    //in progress tryMove v2

    public boolean tryMove2(Move move, Player player, MoveLogger logger) {

        if (new MoveChecker(this, move).isLegal()) {
            if (gBoard[move.getOriginY()][move.getOriginX()].getPiece().getSide() == player.getPlayerSide()) {  //should probably be put in movechecker
                Piece curr = gBoard[move.getOriginY()][move.getOriginX()].getPiece();
                Piece captured = this.gBoard[move.getFinalY()][move.getFinalX()].getPiece();
                int x = move.getOriginX();
                int y = move.getOriginY();
                int finalX = move.getFinalX();
                int finalY = move.getFinalY();

                //checkMate(move, player);
                updateGeneral(move);
                doMove(move);
                if (generalOpen()) {
                    undoMove(move, captured);
                    System.out.println("Illegal Move! General Exposed. ( potential check).");
                    return false;

                } else {//this probably should be implemented better and integrated with player class. i kinda just hacked it out bc no player gui yet
                    testCheck();
                    if (curr.getSide() == Piece.Side.UP && upCheck) {
                        System.out.println("Illegal Move! You're in check.");
                        undoMove(move, captured);
                        return false;

                    }
                    if (curr.getSide() == Piece.Side.DOWN && downCheck) {
                        System.out.println("Illegal Move! You're in check.");
                        undoMove(move, captured);
                        return false;
                    } else {
                        //temp, for ascii board
                        System.out.println("Moved " + curr + " from (" + x + ", " + y + ") to (" + finalX + ", " + finalY + ")");
                        if (captured != null) {
                            player.addPieceCaptured(captured);

                            System.out.println(captured + " Captured!");
                            MoveLogger.addMove(new Move(curr, captured, x, y, finalX, finalY));
                        } else {
                            MoveLogger.addMove(new Move(curr, x, y, finalX, finalY));
                        }


                /*
                Switch Player
                Repaint Board
                Switch Timer, part of player
                 */
                        return true;
                    }
                }
            } else {
                System.out.println("Illegal Move! Not your piece");
                return false;
            }

        } else {
            System.out.println("Illegal Move!");
            return false;
        }


    }

    /**
     * Executes a given move on the board by altering the game board array.
     *
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
     *
     * @param move
     */
    public void undoMove(Move move, Piece captured) {
        Piece curr = getPoint(move.getFinalX(), move.getFinalY()).getPiece();
        getPoint(move.getOriginX(), move.getOriginY()).setPiece(curr);
        getPoint(move.getFinalX(), move.getFinalY()).setPiece(captured);
        //System.out.print(" Illegal Move");
    }


    /**
     * Returns a point object at a specified board location (not array index, but coordinate instead.
     *
     * @param x
     * @param y
     * @return
     */
    public Point getPoint(int x, int y) {
        return gBoard[y][x];
    }

    /**
     * Sets up the board to play by placing all the pieces down as instantiated points. If there is no piece, it is set to null.
     *
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
        board[0][7].setPiece(new Horse(Piece.Side.UP));

        board[9][1].setPiece(new Horse(Piece.Side.DOWN));
        board[9][7].setPiece(new Horse(Piece.Side.DOWN));

        //Elephants/bishops
        board[0][2].setPiece(new Elephant(Piece.Side.UP));
        board[0][6].setPiece(new Elephant(Piece.Side.UP));

        board[9][2].setPiece(new Elephant(Piece.Side.DOWN));
        board[9][6].setPiece(new Elephant(Piece.Side.DOWN));

        //Guard/Advisors
        board[0][3].setPiece(new Guard(Piece.Side.UP));
        board[0][5].setPiece(new Guard(Piece.Side.UP));

        board[9][3].setPiece(new Guard(Piece.Side.DOWN));
        board[9][5].setPiece(new Guard(Piece.Side.DOWN));

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


    /**
     * need to replace this with a method that just scans the nine possible points for each general on each move
     *
     * @param move
     */
    private void updateGeneral(Move move) {
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

    public void updateGenerals() {
        //finds location of generals

        for (int x = 3; x < 6; x++) {
            for (int y = 0; y < 3; y++) {
                Piece curr = getPoint(x, y).getPiece();
                if (curr != null && curr.toString().equals("General")) {
                    setUpGeneralX(x);
                    setUpGeneralY(y);
                }

            }

            for (int y = 7; y < 10; y++) {
                Piece curr = getPoint(x, y).getPiece();
                if (curr != null && curr.toString().equals("General")) {
                    setDownGeneralX(x);
                    setDownGeneralY(y);
                }
            }
        }
    }

    /**
     * Scans the board to check if either general is in check.
     */
    private void testCheck() {
        updateGenerals();
        downCheck = false;
        upCheck = false;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 10; y++) {
                if (getPoint(x, y).getPiece() != null) {

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
    }

    /**
     * Checks for checkmate. currently it runs by checking if you have any possible moves, and if not your in checkmate
     * tomorow when i wake up I'll try and do it such that it checks if your opponent has a Player has any possible moves
     * and changes his her checkmate status.
     * <p>
     * Don't get fooled by the number of forloopps and brackets, this is a master piece that runs in constant time and constant space.
     * Experts call it Eulers second algorithm. An algorithm so efficient and well thought out, one that would never be written at 2:07am in less than
     * 10 minutes.
     * <p>
     * Anyway, it's actually not that complicated or slow.
     */
    private boolean checkMate(Piece.Side loserSide) {//, Player player) {
//        Piece.Side currSide = player.getPlayerSide();
        updateGenerals();
        //Piece.Side currSide = getPoint(move.getOriginX(), move.getOriginY()).getPiece().getSide();

        checkMate = true;

        //running through every loser piece
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 10; y++) {

                if (getPoint(x, y).getPiece() != null && getPoint(x, y).getPiece().getSide() == loserSide) {

                    //running through every possible point to generate every possible move
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 10; j++) {
                            Move tempMove = new Move(x, y, i, j); //generating the temporary move
                            Piece tempCaptured = getPoint(i, j).getPiece();
                            //if that move is legal then attempt it.
                            if (new MoveChecker(this, tempMove).isLegal()) { //trying every possible move for the piece
                                doMove(tempMove); //doing the temporary move
                                testCheck(); //updates check status
                                //if any of these moves were both legal, and result with us not being in check, we aren't in checkmate.
                                if (loserSide == Piece.Side.DOWN) {
                                    if (!downCheck) {
                                        undoMove(tempMove, tempCaptured);
                                        return false;
                                    }
                                }
                                if (loserSide == Piece.Side.UP) {
                                    if (!upCheck) {
                                        undoMove(tempMove, tempCaptured);
                                        return false;
                                    }
                                }
                                undoMove(tempMove, tempCaptured);

                            }
                        }
                    }
                }
            }
        }
        return true;
    }


    /**
     * Checks if the generals are facing each other
     *
     * @return
     */
    private boolean generalOpen() {

        int obstacleCount = 0;
        if (upGeneralX != downGeneralX) {
            return false;
        } else {
            for (int i = upGeneralY + 1; i < downGeneralY; i++) {
                if (getPoint(downGeneralX, i).getPiece() != null) {
                    obstacleCount++;
                }
            }
        }
        if (obstacleCount == 0) {
            System.out.print(" Generals Exposed!");
            return true;
        }

        return false;


    }


    protected void printBoard() {
        System.out.println("       0         1        2         3         4         5         6         7         8     ");
        String hLine = "  -------------------------------------------------------------------------------------------";
        System.out.println(hLine);

        for (int y = 0; y < 10; y++) {
            System.out.print(y + " |");
            for (int x = 0; x < 9; x++) {

                if (gBoard[y][x].getPiece() == null) {
                    System.out.printf("%8s%2s", "", "|");
                } else {
                    System.out.printf("%8s%2s", gBoard[y][x].getPiece(), "|");
                }

            }
            System.out.println();
            System.out.println(hLine);

        }
    }

    public int getUpGeneralX() {
        return upGeneralX;
    }

    public void setUpGeneralX(int upGeneralX) {
        this.upGeneralX = upGeneralX;
    }

    public int getUpGeneralY() {
        return upGeneralY;
    }

    public void setUpGeneralY(int upGeneralY) {
        this.upGeneralY = upGeneralY;
    }

    public int getDownGeneralX() {
        return downGeneralX;
    }

    public void setDownGeneralX(int downGeneralX) {
        this.downGeneralX = downGeneralX;
    }

    public int getDownGeneralY() {
        return downGeneralY;
    }

    public void setDownGeneralY(int downGeneralY) {
        this.downGeneralY = downGeneralY;
    }
}
