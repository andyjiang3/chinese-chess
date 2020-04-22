package GameLogic;


import java.util.ArrayList;

import GUI.Profile;
import GUI.TurnTimerPanel;
import GameLogic.Pieces.*;
import Run.Core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
//import javafx.concurrent.Task;

/**
 * Class to generate the Player object, which handles data about player.
 * Keeps track of checkmate status, pieces captured, side on board, and time elapsed.
 *
 * @author Andy Jiang
 * @version 4/15/20
 */

public class Player {

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
     * Constructor without player name and core. Name will be defaulted to Player + id.
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


        // Set the color of the player based on their side. Color will be used for theming.

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

        //loop through ArrayList and print out all pieces captured
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

    /**
     * Start the timer. Call at start of turn. Update the timer gui.
     * @param panel the timer gui to update
     */
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
     * Get player's time elapsed, different from time left.
     * @return the player's time elapsed.
     */
    public long getElapsedTime() {
        return timeElapsed;
    }

    /**
     * Return the time elapsed of player, from last turn ended.
     * String is in format - Time: mm:ss
     */
    public String printElapsedTime() {
        Date date = new Date(timeElapsed);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String formatted = formatter.format(date);
        return formatted;

    }

    /**
     * Get player's time elapsed, most up-to-date time elapsed.
     * @return the player's time elapsed.
     */
    public String elapsedTimeToString() {
        //format to mm:ss.
        Date date = new Date(timer.getTime() + timeElapsed);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String formatted = formatter.format(date);
        return formatted;

    }

    /**
     * Get player's time left. This is different from getting time elapsed.
     * @param timeLimit the time limit for the game.
     * @return the player's time left, countdown version.
     */
    public String elapsedTimeToString(int timeLimit) {

        //convert the timeLimit (min) to milliseconds
        timeLimit = timeLimit * 60000;
        if (timer.getTime()+ timeElapsed >= timeLimit) {    //check if the timeElapsed have passed the timeLimit
            if (this.getPlayerSide() == Piece.Side.DOWN) {    //player 1 is always down river
                Board.setWinner(Board.PLAYER2_TIMEOUT_WIN);         //call end game since timer limit has passed
                core.callEnd();
                return "00:00";

            } else {
                Board.setWinner(Board.PLAYER1_TIMEOUT_WIN);  //player 2 up river
                core.callEnd();
                return "00:00";
            }

        } else {

            //if time elapsed have not passed the time limit, return time left in mm:ss format
            Date date = new Date(timeLimit - (timer.getTime() + timeElapsed));
            SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
            String formatted = formatter.format(date);
            return formatted;
        }

    }

    /**
     * Get whether the player's timer is running
     * @return the status of the timer, whether its running or not.
     */
    public boolean isTimerRunning() {
        return timer.isStillRunning();
    }

}

