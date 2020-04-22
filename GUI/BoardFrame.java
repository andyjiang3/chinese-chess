package GUI;

import Run.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;

/**
 * Creates the main JFrame for the Chinese Chess Game.
 * It displays the chat box, the timer, the menu bar, and the game board.
 * It also handles mouse right clicks to display a popup menu.
 *
 * @author Michael Yu
 */

public class BoardFrame extends JFrame {
	private BoardPanel board;
	private TurnTimerPanel timerPanel;
	private JPanel sidePanel;

	public BoardFrame(Core core) {
		super("Chinese Chess");
		board = core.getBoardPanel();
		timerPanel = core.getTurnTimerPanel();
		add(board, BorderLayout.CENTER);

		sidePanel = new JPanel();
		sidePanel.setLayout(new GridLayout(2, 0, 0, 3));
		sidePanel.add(timerPanel);
        ChatBox chatBox = new ChatBox(core);
		System.setOut(new PrintStream(new StreamIntake(chatBox, System.out)));
		sidePanel.add(chatBox);
		add(sidePanel, BorderLayout.EAST);

		ActionListener saveHandler = new ActionListener() {
			//saves the board when the player presses saveItem
			public void actionPerformed(ActionEvent event) {
				//add code to save the board
				try {
					core.saveGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		BoardMenu boardMenu = core.getBoardMenu();
		setJMenuBar(boardMenu);

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem popupSave = new JMenuItem("Save");
		popupSave.addActionListener(saveHandler);

		popupMenu.add(popupSave);
		addMouseListener(new MouseAdapter() {
			// handle mouse press event
			public void mousePressed(MouseEvent event) {
				checkForTriggerEvent(event); // check for trigger
			}

			// handle mouse release event
			public void mouseReleased(MouseEvent event) {
				checkForTriggerEvent(event); // check for trigger
			}

			// determine whether event should trigger popup menu
			private void checkForTriggerEvent(MouseEvent event) {
				if (event.isPopupTrigger())
					popupMenu.show(
							event.getComponent(), event.getX(), event.getY());
			}
		}); // end call to addMouseListener
	}
}

