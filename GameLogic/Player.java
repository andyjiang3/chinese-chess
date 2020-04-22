package GameLogic;


import GUI.Profile;
import GUI.TurnTimerPanel;
import GameLogic.Pieces.Piece;
import Run.Core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import javafx.concurrent.Task;

/**
 * Class to generate the Player object, which handles data about player.
 * Keeps track of checkmate status, pieces captured, side on board, and time elapsed.
 *
 * @author Andy Jiang
 * @version 4/15/20
 */

public class Player {

    /*
        This to include:
        Color                    - check
        Pieces Captured          - check
        Timers                   - check
        Checkmate status         - check, needs to implement with testChecker() in Board !!
        Side of board they're on - check
        If Move successful - put under Core class


     */

    protected Timer timer;

    private int id;
    private String name;
    private Piece.Side side;
    private ArrayList<Piece> piecesCaptured;
    private boolean checkmateStatus;
    private long timeElapsed;
    private Core core;

    private String color;

    /**
     * Constructor without player name. Name will be defaulted to Player + id.
     *
     * @param id   set this player's id
     * @param side set this player's side on board
     */
    public Player(int id, Piece.Side side, Profile profile) {
        this.id = id;
        this.name = "Player" + id;
        this.side = side;
        this.piecesCaptured = new ArrayList<Piece>();
        this.checkmateStatus = false;
        this.timeElapsed = 0;

        timer = new Timer();

        /*
            Set the color of the player based on their side. Color will be used for theming.
         */
        if (side == Piece.Side.DOWN) {
            color = profile.getP1Color().toString();
        } else {
            color = profile.getP2Color().toString();
        }

    }

    /**
     * Complete constructor.
     *
     * @param id   set this player's id
     * @param name set name of player
     * @param side set this player's side on board
     */
    public Player(int id, String name, Piece.Side side, Core core) {
        this.id = id;
        this.name = name;
        this.side = side;
        this.piecesCaptured = new ArrayList<Piece>();
        this.checkmateStatus = false;
        this.timeElapsed = 0;
        this.core = core;

        timer = new Timer();

        /*
            Set the color of the player based on their side. Color will be used for theming.
         */
        if (side == Piece.Side.DOWN) {
            color = "Red";
        } else {
            color = "Black";
        }

    }
    //============================================================================
    //PLAYER DATA

    /**
     * Get this player's id.
     *
     * @return this player's id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get this player's name.
     *
     * @return this oplayer's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get this player's color.
     *
     * @return this player's color. (Red or Black based on side)
     */
    public String getColor() {
        return color;
    }

    /**
     * Get this player's side relative to river.
     *
     * @return this player's side. (UP river or DOWN river)
     */
    public Piece.Side getPlayerSide() {
        return this.side;
    }

    /**
     * Set player's side relative to river
     *
     * @param side the side the player is on.
     */
    public void setPlayerSide(Piece.Side side) {
        this.side = side;
    }

    /**
     * Add piece captured by player to ArrayList of all pieces captured.
     *
     * @param pieceCaptured the piece captured by the player
     */

    //============================================================================
    //PIECES CAPTURED
    public void addPieceCaptured(Piece pieceCaptured) {
        piecesCaptured.add(pieceCaptured);
    }

    /**
     * Set player's side based on river
     *
     * @return an ArrayList<Piece> of all pieces captured.
     */
    public ArrayList<Piece> getPiecesCaptured() {
        return piecesCaptured;
    }

    /**
     * Print all pieces captured by player.
     * FORMAT: Pieces Captured: (all Pieces captured)
     */
    public void printPiecesCaptured() {
        System.out.print("Pieces Captured: ");

        for (int i = 0; i < piecesCaptured.size(); i++) {
            if (piecesCaptured.size() - 1 == i) {
                System.out.println(piecesCaptured.get(i));
            } else {
                System.out.print(piecesCaptured.get(i) + ", ");
            }
        }
    }

    /**
     * Get the number of pieces captured.
     *
     * @return the number of pieces captured by the player. (Int)
     */
    public int getNumPiecesCaptured() {
        return this.piecesCaptured.size();
    }

    /**
     * Clear player's captured list.
     */
    public void clearPiecesCaptured() {
        piecesCaptured.clear();
    }

    /**
     * Get status on whether the player is in checkmate
     *
     * @return status of checkmate.
     */

    //============================================================================
    //CHECKMATE STATUS
    public boolean getCheckmateStatus() {
        return checkmateStatus;
    }

    /**
     * Set player's checkmate status
     *
     * @param checkmateStatus whether the player is in checkmate
     */
    public void setCheckmateStatus(Boolean checkmateStatus) {
        this.checkmateStatus = checkmateStatus;
    }

    /**
     * Start the timer. Call at start of turn.
     */

    //============================================================================
    //TIMER

    /**
     * Start the timer. Call at start of turn.
     */
    public void startTurnTimer() {
        timer.start();

    }

    public void startTurnTimer(TurnTimerPanel panel) {
        timer.start();

        if (this.side == Piece.Side.DOWN) { //red
            panel.updateRedTime();

        } else {
            panel.updateBlackTime();

        }
    }

    /**
     * Stop the timer. Call at end of turn.
     * Add time elapsed during that turn to previous turn's elapsed time.
     */
    public void stopTurnTimer() {
        timer.stop();
        timeElapsed += timer.getTime();
    }

    /**
     * Get player's time elapsed
     *
     * @return the player's time elapsed.
     */
    public long getElapsedTime() {
        return timeElapsed;
    }

    /**
     * Return the time elapsed of player.
     * String is in format - Time: mm:ss
     */

    public String printElapsedTime() {
        Date date = new Date(timeElapsed);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String formatted = formatter.format(date);
        return formatted;

    }

    public String elapsedTimeToString() {

        Date date = new Date(timer.getTime() + timeElapsed);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String formatted = formatter.format(date);
        return formatted;

    }

    //time
    public String elapsedTimeToString(int timeLimit) {

        timeLimit = timeLimit * 60000;
        if (timer.getTime() + timeElapsed >= timeLimit) {
            if (this.getPlayerSide() == Piece.Side.DOWN) {    //player 1 is always down river
                Board.setWinner(Board.PLAYER1_WINS);
                core.callEnd();

            } else {
                Board.setWinner(Board.PLAYER2_WINS);           //player 2 up river
                core.callEnd();
            }
        }

        Date date = new Date(timeLimit - (timer.getTime() + timeElapsed));
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String formatted = formatter.format(date);
        return formatted;

    }

    public boolean isTimerRunning() {
        return timer.isStillRunning();
    }

    //rough draft //#########ANDY I COMMENTED THIS OUT BECAUSE I DELETED THE TRYMOVE AND TRYMOVE 2. IF YOU WANT TO TEST THIS YOU'LL HAVE TO ADDAPT IT TO TRYMOVE3#############
    /*
    public void tryMove(Move move, Board board, MoveLogger logger) {
        //int size = getNumPiecesCaptured();
        int x, y, finalX, finalY;

        //Piece selected = board.getPoint(x,y).getPiece();

        Scanner in = new Scanner(System.in);
        boolean passed = board.tryMove2(move, this, logger);

        while (!passed) {    //while tryMove is false

            System.out.println("Move (x y x y): ");
            x = in.nextInt();
            y = in.nextInt();
            finalX = in.nextInt();
            finalY = in.nextInt();

            passed = board.tryMove2(new Move(x, y, finalX, finalY), this, logger);
        }

        /*under trymove in board class
        System.out.println("Moved " + selected + " from (" + x + ", " + y + ") to (" + finalX + ", " + finalY + ")");
        if (size < getNumPiecesCaptured()) {
            System.out.println(piecesCaptured.get(getNumPiecesCaptured() - 1) + " Captured!");
        }
        */

}

