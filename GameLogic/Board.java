package GameLogic;

import GameLogic.Pieces.*;

/**
 * The Board class holds an 2D array with a bunch of point objects, which in turn carry the pieces. The board object is aware of it's pieces and can run checks
 * on things like if a move is legal by seeing if it leaves a player in check. It is basically the lowest level in the applicatoin.
 *
 * @author Venkat Pamulapati
 */
public class Board {


    //test

    public static final int PLAYER1_WINS = 1;
    public static final int PLAYER2_WINS = 2;
    public static final int DRAW = 0;
    public static final int NA = -1;

    private Point[][] gBoard;
    private int upGeneralX = 4;
    private int upGeneralY = 0;
    private int downGeneralX = 4;
    private int downGeneralY = 9;
    private boolean upCheck = false; //up is in check
    private boolean downCheck = false; //down is in check
    private boolean checkMate = false;
    private boolean staleMate = false;


    private int winner;


    private boolean gameOver = false;


    /**
     * The default and only constructor creates a 2D array of Point objects and runs the initialize method (puts Pieces on the board)
     */
    public Board() {
        winner = NA;
        gBoard = new Point[10][9];
        initialize(gBoard);

    }


    /**
     * Tries a move by running it through move checker.
     * Updates General Positions as member data
     * Sees if the current player is in check after moving, if so, does not accept move
     * If move is legal, then the board switches the current player and repaints the gui.
     *
     * @param move the attempting move
     */
    //HERE IS THE LAST FUNCTIONAL INSTANCE WHERE CHECKMATE WAS WORKING GREAT, AFTER THIS I STARTED ADDING ANDY'S STUFF.
    public boolean tryMove3(Move move, Player player) {

        if (new MoveTester(this, move).isLegal()) {

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


                } else {
                    //the move is legal, now lets see if it's a winning move.
                    if (upCheck && curr.getSide() == Piece.Side.DOWN) {
                        if (checkMate(Piece.Side.UP)) {
                            winner = PLAYER1_WINS;
                            System.out.println("##########################CHECK MATE#############################");
                            System.out.println(player.getName() + "WINS!");
                            System.out.println("##########################CHECK MATE#############################");
                        }
//                        return true;

                        //the move is legal, now lets see if it's a winning move.
                    } else if (downCheck && curr.getSide() == Piece.Side.UP) {
                        if (checkMate(Piece.Side.DOWN)) {
                            winner = PLAYER2_WINS;
                            System.out.println("##########################CHECK MATE#############################");
                            System.out.println(player.getName() + "WINS!");
                            System.out.println("##########################CHECK MATE#############################");
                        }
//                        return true;

                        //the move is legal, now lets see if it's a stalemate
                    } else if (curr.getSide() == Piece.Side.DOWN) {
                        if (checkMate(Piece.Side.UP) || separated()) {
                            winner = DRAW;
                            System.out.println("##########################STALE MATE#############################");
                            System.out.println("ITS A DRAW");
                            System.out.println("##########################STALE MATE#############################");
                        }
//                        return true;
                    } else if (curr.getSide() == Piece.Side.UP) {
                        if (checkMate(Piece.Side.DOWN) || separated()) {
                            winner = DRAW;
                            System.out.println("##########################STALE MATE#############################");
                            System.out.println("ITS A DRAW");
                            System.out.println("##########################STALE MATE#############################");
                        }
//                        return true;
                    }

                    // if (!checkMate) {   //LEGAL MOVE AND NOT IN CHECKMATE?
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
                    //   }

                }
            } else {
                System.out.println("Not your turn");
                return false;
            }
        }
        System.out.println("Illegal Move!!");
        return false;


    }


    //in progress tryMove v2


    /**
     * Physically executes a move by changing the pieces that are presnt on certian points.
     * @param move the specified move
     */
    public void doMove(Move move) {
        Piece curr = gBoard[move.getOriginY()][move.getOriginX()].getPiece();
        //Piece captured = this.gBoard[move.getFinalY()][move.getFinalX()].getPiece();
        this.gBoard[move.getFinalY()][move.getFinalX()].setPiece(curr);
        this.gBoard[move.getOriginY()][move.getOriginX()].setPiece(null);
    }


    /**
     * Physically undoes a move by altering the state of the pieces
     * Not for use in the undo button. It used for move testing reasons.
     *
     * @param move
     * @param captured The piece that was previously captured, so that it can be restored
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
     * @param x the x coordinate on the board where the left most point is 0. (note not indecies)
     * @param y the y coordinate on the board where the top most point is 0. (note not indecies)
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
     * Scans the generals quarters to find their locations and update that data.
     * Is generally called before testing procedured like check and checkMate
     */
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
     * It works by trying to move every enemy piece to the friendly general and checking
     * if the move is valid.
     */
    private void testCheck() {
        updateGenerals();
        downCheck = false;
        upCheck = false;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 10; y++) {
                if (getPoint(x, y).getPiece() != null) {

                    if (!downCheck && getPoint(x, y).getPiece().getSide() == Piece.Side.UP) {
                        if (new MoveTester(this, new Move(x, y, downGeneralX, downGeneralY), 0).isLegal()) {
                            downCheck = true;
                            System.out.println("Down is in check");
                        }
                    } else if (!upCheck && getPoint(x, y).getPiece().getSide() == Piece.Side.DOWN) {
                        if (new MoveTester(this, new Move(x, y, upGeneralX, upGeneralY), 0).isLegal()) {
                            upCheck = true;
                            System.out.println("up is in check");
                        }
                    }
                }
            }
        }
    }

    /**
     * Runs through the board trying every possible move for the passed in side, and if no moves result in leaving a check it returns true.
     * <p>
     * It also works for stalemate, since it is not dependent on the current status.
     * We set the actually check for checkmate by seeing if this returns true and the player is already in check.
     *
     * @param loserSide the side that we suspect has lost.
     */
    private boolean checkMate(Piece.Side loserSide) {
        updateGenerals();
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
                            if (new MoveTester(this, tempMove).isLegal()) { //trying every possible move for the piece
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
     * Covers some edge cases where it is going to be guarantee stalmate like no pieces crossing rier.
     * @return
     */
    private boolean separated() {

        int cannonCounter = 0;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 10; y++) {
                if (getPoint(x, y).getPiece() != null) {
                    if (getPoint(x, y).getPiece().canWinAlone()) {
                        return false;
                    }
                    if (getPoint(x, y).getPiece().toString().equals("Cannon")) {
                        cannonCounter++;
                        if (cannonCounter > 1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }


    public void setWinner(int winner) {
        this.winner = winner;
    }

    /**
     * An Ascii board used for testing purposes
     */
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

    protected int getUpGeneralX() {
        return upGeneralX;
    }


    protected void setUpGeneralX(int upGeneralX) {
        this.upGeneralX = upGeneralX;
    }

    protected int getUpGeneralY() {
        return upGeneralY;
    }

    protected void setUpGeneralY(int upGeneralY) {
        this.upGeneralY = upGeneralY;
    }

    protected int getDownGeneralX() {
        return downGeneralX;
    }

    protected void setDownGeneralX(int downGeneralX) {
        this.downGeneralX = downGeneralX;
    }

    protected int getDownGeneralY() {
        return downGeneralY;
    }

    protected void setDownGeneralY(int downGeneralY) {
        this.downGeneralY = downGeneralY;
    }

    public boolean isUpCheck() {
        return upCheck;
    }

    public boolean isDownCheck() {
        return downCheck;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public boolean isStaleMate() {
        return staleMate;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWinner() {
        return winner;
    }
}
