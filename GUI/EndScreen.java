package GUI;

import GameLogic.*;
import javax.swing.*;

public class EndScreen extends JFrame {
    private String message;

    public EndScreen(int winner, Profile profile) {

        JFrame popUp = new JFrame();

        switch (winner) {

            case Board.NA:
                message = "Test Message";
                break;
            case Board.DRAW:
                message = "Stalemate, it's a draw!";
                break;
            case Board.PLAYER1_WINS:
                message = "Checkmate!  " + profile.getP1String() + "  is the winner";
                break;
            case Board.PLAYER2_WINS:
                message = "Checkmate!  " + profile.getP2String() + "  is the winner";
                break;
            default:
                message = "Test Message";
        }

        JOptionPane.showMessageDialog(popUp, message);
    }

}