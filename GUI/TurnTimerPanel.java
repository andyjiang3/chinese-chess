package GUI;

import GameLogic.Pieces.Piece;
import GameLogic.Player;
import javax.swing.*;
import javax.swing.border.Border;
import GameLogic.Timer;

import java.awt.*;


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


    public TurnTimerPanel(Player player1, Player player2) {
        if (player1.getPlayerSide() == Piece.Side.DOWN) {  //Player1 is red
            this.redPlayer = player1;
            this.blackPlayer = player2;
        } else {                                            //Player2 is red
            this.redPlayer = player2;
            this.blackPlayer = player1;
        }

        redTime = 0;
        blackTime = 0;

        timerLabel = new JLabel("Timer");
        timerLabel.setFont(new Font("Sans_Serif", Font.PLAIN, 40));

        Border blackLine = BorderFactory.createLineBorder(Color.black, 2);

        blackTimerLabel = new JLabel(blackPlayer.elapsedTimeToString());
        blackTimerLabel.setForeground(Color.LIGHT_GRAY);
        blackTimerLabel.setFont(new Font("Sans_Serif", Font.PLAIN, 40));

        blackNumberPanel = new JPanel();
        blackNumberPanel.add(blackTimerLabel);

        blackTimerPanel = new JPanel(new BorderLayout());
        blackTimerPanel.setBorder(blackLine);
        blackTimerPanel.add(blackNumberPanel, BorderLayout.CENTER);

        Border redLine = BorderFactory.createLineBorder(Color.red, 2);

        redTimerLabel = new JLabel(redPlayer.elapsedTimeToString());
        redTimerLabel.setFont(new Font("Sans_Serif", Font.PLAIN, 40));

        redNumberPanel = new JPanel();
        redNumberPanel.add(redTimerLabel);

        redTimerPanel = new JPanel(new BorderLayout());
        redTimerPanel.setBorder(redLine);
        redTimerPanel.add(redNumberPanel, BorderLayout.CENTER);

        timerDisplayPanel = new JPanel(new GridLayout(3,1, 0, 3)); //2 rows, 1 col
        timerDisplayPanel.add(timerLabel);
        timerDisplayPanel.add(blackTimerPanel);
        timerDisplayPanel.add(redTimerPanel);
        this.add(timerDisplayPanel, BorderLayout.CENTER);
        //set width to be bigger
        this.setPreferredSize(new Dimension(200, 0));

    }

    //turn timer test
    public static void main(String args[]) throws Exception {
        Player player1 = new Player(1, "Hi", Piece.Side.DOWN);
        Player player2 = new Player(2, "Hi2", Piece.Side.UP );
        JFrame testFrame = new JFrame("Timer test");
        TurnTimerPanel timerPanel = new TurnTimerPanel(player1, player2);
        testFrame.add(timerPanel);
        testFrame.pack();
        testFrame.setVisible(true);

/*
   timer class
*/

//red
        player1.startTurnTimer(timerPanel);
        Thread.sleep(5000);
        player1.stopTurnTimer();

//black
        player2.startTurnTimer(timerPanel);
        Thread.sleep(7000);
        player2.stopTurnTimer();

        player1.startTurnTimer(timerPanel);
        Thread.sleep(3000);
        player1.stopTurnTimer();

    }



    public void updateRedTime() {

        blackTimerLabel.setForeground(Color.LIGHT_GRAY);
        redTimerLabel.setForeground(Color.BLACK);

        newThread = new Thread(() -> {
            while (redPlayer.isTimerRunning()) {

                redTimerLabel.setText(redPlayer.elapsedTimeToString());


                //System.out.println(redPlayer.elapsedTimeToString());
                //System.out.println(redPlayer.isTimerRunning());

            }
        });



        newThread.start();

//        Task<Void> task1 = new Task<Void>() {
//            @Override protected Void call() throws Exception {
//                while (redPlayer.isTimerRunning()) {
//                    redTimerLabel.setText(redPlayer.elapsedTimeToString());
//
//
//                    System.out.println(redPlayer.elapsedTimeToString());
//                    //System.out.println(redPlayer.isTimerRunning());
//
//                }
//                return null;
//            }
//        };


//        while (redPlayer.isTimerRunning()) {
//            redTimerLabel.setText(redPlayer.elapsedTimeToString());
//
//
//            //System.out.println(redPlayer.elapsedTimeToString());
//            //System.out.println(redPlayer.isTimerRunning());
//
//        }


    }

    public void updateBlackTime() {

        blackTimerLabel.setForeground(Color.BLACK);
        redTimerLabel.setForeground(Color.LIGHT_GRAY);

        newThread = new Thread(() -> {
            while (blackPlayer.isTimerRunning()) {
                blackTimerLabel.setText(blackPlayer.elapsedTimeToString());


                //System.out.println(blackPlayer.elapsedTimeToString());
                //System.out.println(blackPlayer.isTimerRunning());

            }
        });

        newThread.start();


    }

}
