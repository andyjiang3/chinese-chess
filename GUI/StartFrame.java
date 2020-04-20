package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartFrame extends JFrame {
	
	private JPanel buttonsPanel;
	private JButton newGameButton;
	private JButton loadGameButton;
	private BoardFrame boardFrame;
	public StartFrame(Core core) {
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardFrame = core.getBoardFrame();
                boardFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        	    boardFrame.setSize( 900, 700 ); // set frame size
        	    boardFrame.setVisible( true );
                setVisible(false);
            }
        });
        //newGameButtonPanel = new JPanel(new GridLayout(1, 1));
        //newGameButtonPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 25));
        //newGameButtonPanel.add(newGameButton);
        //loadGameFileChooser = new JFileChooser("Load Saved Game");
        //loadGameFileChooser.setFileFilter(new FileNameExtensionFilter("Saved Game", "chessgame"));
        loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //loadGameFileChooser.showOpenDialog(Core.getLaunchFrame());
                //SaverLoader.loadGame(loadGameFileChooser.getSelectedFile());
            }
        });
        //loadGameButtonPanel = new JPanel(new GridLayout(1, 1));
        //loadGameButtonPanel.setBorder(BorderFactory.createEmptyBorder(40, 25, 40, 50));
        //loadGameButtonPanel.add(loadGameButton);


        buttonsPanel = new JPanel(new GridLayout(2, 1));
        buttonsPanel.setPreferredSize(new Dimension(300, 200));
        buttonsPanel.add(newGameButton);
        buttonsPanel.add(loadGameButton);
        
        this.setLayout(new BorderLayout());
        this.add(buttonsPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
	}
}
