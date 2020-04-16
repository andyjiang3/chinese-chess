package GameLogic;

import java.util.ArrayList;
import GameLogic.Pieces.*;
import java.text.SimpleDateFormat;
import java.util.Date;


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

    private String color;

    public Player(int id, Piece.Side side) {
        this.id = id;
        this.name = "Player" + id;
        this.side = side;
        this.piecesCaptured = new ArrayList<Piece>();
        this.checkmateStatus = false;
        this.timeElapsed = 0;

        if (side == Piece.Side.DOWN) {
            color = "Red";
        } else {
            color = "Black";
        }

        timer = new Timer();

    }

    public Player(int id, String name, Piece.Side side) {
        this.id = id;
        this.name = name;
        this.side = side;
        this.piecesCaptured = new ArrayList<Piece>();
        this.checkmateStatus = false;
        this.timeElapsed = 0;

        if (side == Piece.Side.DOWN) {
            color = "Red";
        } else {
            color = "Black";
        }

        timer = new Timer();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return color;
    }

    public Piece.Side getPlayerSide() {
        return this.side;
    }

    public void setPlayerSide(Piece.Side side) {
        this.side = side;
    }

    public void addPieceCaptured(Piece pieceCaptured) {
        piecesCaptured.add(pieceCaptured);
    }

    public ArrayList<Piece> getPiecesCaptured() {
        return piecesCaptured;
    }

    public void printPiecesCaptured() {
        System.out.print("Pieces Captured: ");
        for (Piece piece : piecesCaptured) {
            System.out.println(piece + ", ");
        }
    }

    public int getNumPiecesCaptured() {
        return this.piecesCaptured.size();
    }

    public void clearPiecesCaptured() {
        piecesCaptured.clear();
    }

    public boolean getCheckmateStatus() {
        return checkmateStatus;
    }

    public void setCheckmateStatus(Boolean checkmateStatus) {
        this.checkmateStatus = checkmateStatus;
    }


    //Call at start of turn.
    public void startTurnTimer() {
        timer.start();
    }

    //Call at end of turn
    public void stopTurnTimer() {
        timer.stop();
        timeElapsed += timer.getTime();
    }

    public long getElapsedTime() {
        return timeElapsed;
    }

    public void printElapsedTime() {
        Date date = new Date(timeElapsed);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String formatted = formatter.format(date);
        System.out.println("Time: " + formatted);

    }
















}