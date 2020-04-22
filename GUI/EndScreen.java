package GUI;

import GameLogic.*;
import Run.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndScreen extends JFrame {
    private String message;
    private JButton exitBtn;
    private JButton restartBtn;
    private JButton closeBtn;
    private Core core;
    private JPanel buttonPanel;

    public EndScreen(Core core, int winner, Profile profile) {
        this.core = core;
        setSize(new Dimension(450,200));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setLayout(new GridLayout(2, 0));
        //setPreferredSize(new Dimension(300,200));
        //JFrame popUp = new JFrame();

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

        //JOptionPane.showMessageDialog(popup, message);
        JLabel winMessage = new JLabel(message, SwingConstants.CENTER);
        winMessage.setFont(new Font(winMessage.getFont().getName(), Font.BOLD, 14));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));

        exitBtn = new JButton("Exit Game");
        exitBtn.addActionListener(new ActionListener() {
            // terminate application when user clicks exitItem
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        restartBtn = new JButton("Restart Game");
        restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                core.setInvisible();
                Core newCore = new Core();
                setVisible(false);
            }
        });
        closeBtn = new JButton("Cancel");
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        buttonPanel.add(exitBtn);
        buttonPanel.add(restartBtn);
        buttonPanel.add(closeBtn);

        add(winMessage);
        add(buttonPanel);
        setVisible(true);
    }

}