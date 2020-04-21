package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class EndScreen extends JFrame {


    private String message;

    public EndScreen(int winner, Profile profile) {

        JFrame popUp = new JFrame();

        switch (winner) {

            case -1:
                message = "Test Message";
                break;
            case 0:
                message = "It's a draw!";
                break;
            case 1:
                message = "Checkmate!  " + profile.getP1String() + "  is the winner";
                break;
            case 2:
                message = "Checkmate!  " + profile.getP2String() + "  is the winner";
                break;
            default:
                message = "Test Message";
        }

        JOptionPane.showMessageDialog(popUp, message);
    }

}