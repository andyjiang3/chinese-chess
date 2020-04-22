package GUI;

import GameLogic.Pieces.Piece;
import GameLogic.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 *  TurnTimerPanel creates the timer gui and update the time elapsed and time left for each player
 *
 * @author Andy Jiang
 */
public class TurnTimerPanel extends JPanel {

    private JPanel timerDisplayPanel;
    private JPanel redTimerPanel;
    private JPanel blackTimerPanel;

    private JPanel redNumberPanel;
    private JPanel blackNumberPanel;

    private JLabel redTimerLabel;
    private JLabel blackTimerLabel;

    private JLabel timerLabel;

    private Player redPlayer;
    private Player blackPlayer;

    private long redTime;
    private long blackTime;

    Thread newThread;

    private int timeLimit;

    /**
     * Constructor that creates the timer gui and initialize variables.
     *
     * @param player1 the player playing the game
     * @param player1 set this player's side on board
     * @param profile profile of the game
     */
    public TurnTimerPanel(Player player1, Player player2, Profile profile) {

        if (player1.getPlayerSide() == Piece.Side.DOWN) {  //Player1 is red
            this.redPlayer = player1;
            this.blackPlayer = player2;
        } else {                                           //Player2 is red
            this.redPlayer = player2;
            this.blackPlayer = player1;
        }

        //Set time limit
        timeLimit = profile.getMinutes();

        //Set time elapsed
        redTime = 0;
        blackTime = 0;

        //GUI DRAWING ===========================================================================

        //Timer Label ==============================
        timerLabel = new JLabel("Timer");
        timerLabel.setFont(new Font("Sans_Serif", Font.PLAIN, 40));

        //Black Timer ==============================
        Border blackLine = BorderFactory.createLineBorder(profile.getP2Color(), 2);

        blackTimerLabel = new JLabel(blackPlayer.elapsedTimeToString(timeLimit));
        blackTimerLabel.setForeground(Color.LIGHT_GRAY);
        blackTimerLabel.setFont(new Font("Sans_Serif", Font.PLAIN, 40));

        blackNumberPanel = new JPanel();
        blackNumberPanel.add(blackTimerLabel);

        blackTimerPanel = new JPanel(new BorderLayout());
        blackTimerPanel.setBorder(blackLine);
        blackTimerPanel.add(blackNumberPanel, BorderLayout.CENTER);

        //Red Timer ==============================
        Border redLine = BorderFactory.createLineBorder(profile.getP1Color(), 2);

        redTimerLabel = new JLabel(redPlayer.elapsedTimeToString(timeLimit));
        redTimerLabel.setFont(new Font("Sans_Serif", Font.PLAIN, 40));

        redNumberPanel = new JPanel();
        redNumberPanel.add(redTimerLabel);

        redTimerPanel = new JPanel(new BorderLayout());
        redTimerPanel.setBorder(redLine);
        redTimerPanel.add(redNumberPanel, BorderLayout.CENTER);

        timerDisplayPanel = new JPanel(new GridLayout(3, 1, 0, 3)); //2 rows, 1 col
        timerDisplayPanel.add(timerLabel);
        timerDisplayPanel.add(blackTimerPanel);
        timerDisplayPanel.add(redTimerPanel);

        this.add(timerDisplayPanel, BorderLayout.CENTER);

        //Set width to be bigger so it doesn't touch edge
        this.setPreferredSize(new Dimension(200, 0));

    }

    /**
     * Update the red timer in the timer gui.
     * Uses Thread to update asynchronously with game
     */
    public void updateRedTime() {

        blackTimerLabel.setForeground(Color.LIGHT_GRAY);
        redTimerLabel.setForeground(Color.BLACK);

        //Restart thread by creating a new one since you can't restart dead thread.
        newThread = new Thread(() -> {
            while (redPlayer.isTimerRunning()) {

                redTimerLabel.setText(redPlayer.elapsedTimeToString(timeLimit));

                //For testing
                //System.out.println(redPlayer.elapsedTimeToString());
                //System.out.println(redPlayer.isTimerRunning());

            }
        });

        //Start thread
        newThread.start();

    }

    /**
     * Update the black timer in the timer gui.
     * Uses Thread to update asynchronously with game
     */
    public void updateBlackTime() {

        blackTimerLabel.setForeground(Color.BLACK);
        redTimerLabel.setForeground(Color.LIGHT_GRAY);

        //Restart thread by creating a new one since you can't restart dead thread.
        newThread = new Thread(() -> {
            while (blackPlayer.isTimerRunning()) {
                blackTimerLabel.setText(blackPlayer.elapsedTimeToString(timeLimit));

                //For testing
                //System.out.println(blackPlayer.elapsedTimeToString());
                //System.out.println(blackPlayer.isTimerRunning());

            }
        });

        //Start thread
        newThread.start();

    }

}
