package GUI;

import javax.swing.JFrame;

import GameLogic.Board;
import GameLogic.Move;
import GameLogic.Pieces.Piece;
import GameLogic.Player;

public class Core {
	
    private static boolean inGame;
    private static StartFrame startFrame;
    private BoardFrame boardFrame;
    private BoardPanel boardPanel;
    private Board board;
    private int counter;
    private Player player1;
    private Player player2;
    
	public Core() {
		board = new Board();
		boardPanel = new BoardPanel(this);
		boardFrame = new BoardFrame(this);
        player1 = new Player(1, "DownGamer", Piece.Side.DOWN);
        player2 = new Player(2, "GamersRise", Piece.Side.UP);
        counter = 0;

		//board.tryMove(new Move(4, 9, 4, 8 ));
		//boardPanel.userRepaint();
    }
	public void start() {
        inGame = false;
        startFrame = new StartFrame(this);
    }

	public void doMove(Move move) {
        System.out.println(counter);
        if (counter % 2 == 0) {
            if (board.tryMove3(move, player1)) {
                counter++;
            }
        } else if (counter % 2 == 1) {
            if (board.tryMove3(move, player2)) {
                counter++;
            }
        }

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
