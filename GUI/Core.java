package GUI;

import javax.swing.JFrame;

import GameLogic.Board;
import GameLogic.Move;

public class Core {
	
    private static boolean inGame;
    private static StartFrame startFrame;
    private BoardFrame boardFrame;
    private BoardPanel boardPanel;
    private Board board;
    
	public Core() {
		board = new Board();
		boardPanel = new BoardPanel(this);
		boardFrame = new BoardFrame(this);
		//board.tryMove(new Move(4, 9, 4, 8 ));
		//boardPanel.userRepaint();
    }
	public void start() {
        inGame = false;
        startFrame = new StartFrame(this);
    }
	public void doMove(Move move) {
		board.tryMove(move);
	}
    /*public static void startGame() {
        inGame = true;
        BoardFrame boardFrame = new BoardFrame();
        boardFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    boardFrame.setSize( 700, 700 ); // set frame size
	    boardFrame.setVisible( true );
        //boardGame = new BoardGame();
    }*/
    public BoardPanel getBoardPanel() {
    	return boardPanel;
    }
    public BoardFrame getBoardFrame() {
    	return boardFrame;
    }
    public Board getBoard() {
    	return board;
    }
}
