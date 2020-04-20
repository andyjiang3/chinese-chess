package GUI;
import GameLogic.Pieces.Piece;
import GameLogic.Player;
import GameLogic.Point;
import GameLogic.Board;
import GameLogic.Move;
import GUI.BoardPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

//defines the entire JFrame for the GUI
public class BoardFrame extends JFrame {
	public BoardFrame() {
		super("Chinese Chess");
		BoardPanel board = new BoardPanel();
		add(board, BorderLayout.CENTER);

		//for testing, would be called in core class
		Player player1 = new Player(1, "Hi", Piece.Side.DOWN);
		Player player2 = new Player(2, "Hi2", Piece.Side.UP );
		JFrame testFrame = new JFrame("Timer test");
		TurnTimerPanel timerPanel = new TurnTimerPanel(player1, player2);
		add(timerPanel, BorderLayout.EAST);


		ActionListener saveHandler = new ActionListener() {  
		       //saves the board when the player presses saveItem
		       public void actionPerformed( ActionEvent event ){
		          //add code to save the board
		       }
		    }; 
		BoardMenu boardMenu = new BoardMenu(saveHandler);
		setJMenuBar(boardMenu);
		
	    JPopupMenu popupMenu = new JPopupMenu(); // create pop-up menu
	    String popupChoices[] = {"Undo", "Save"};
		JMenuItem[] popupItems = new JMenuItem[popupChoices.length];
	    JMenuItem popupSave = new JMenuItem("Save");
	    popupSave.addActionListener(saveHandler);
	    JMenuItem popupUndo = new JMenuItem("Undo");
	    popupUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add code or methods to undo last move
			}
	    });
	    popupMenu.add(popupUndo);
	    popupMenu.add(popupSave);
	    addMouseListener( new MouseAdapter() {  
	           // handle mouse press event
	        public void mousePressed( MouseEvent event ){ 
	                checkForTriggerEvent( event ); // check for trigger
	        }
	        // handle mouse release event
	        public void mouseReleased( MouseEvent event )
	        { 
	            checkForTriggerEvent( event ); // check for trigger
	        }
	               // determine whether event should trigger popup menu
	        private void checkForTriggerEvent( MouseEvent event )
	        {
	            if ( event.isPopupTrigger() ) 
	                popupMenu.show( 
	                event.getComponent(), event.getX(), event.getY() );  
	        }
	    }); // end call to addMouseListener
	}
}

