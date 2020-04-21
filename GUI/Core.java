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
    private TurnTimerPanel timerPanel;
    private BoardMenu boardMenu;
    private Board board;
    private int counter;
    private Player player1;
    private Player player2;
    private static StartFrame2 startFrame2;
    private Profile profile;

    
	public Core() {


        player1 = new Player(1, "DownGamer", Piece.Side.DOWN);
        player2 = new Player(2, "GamersRise", Piece.Side.UP);
        board = new Board();
//        boardPanel = new BoardPanel(this);
//        boardMenu = new BoardMenu(this);
//        timerPanel = new TurnTimerPanel(player1,player2);
//        boardFrame = new BoardFrame(this);


        counter = 0;

		//board.tryMove(new Move(4, 9, 4, 8 ));
		//boardPanel.userRepaint();
        //player1.startTurnTimer(timerPanel);
    }
	public void start() {
        inGame = false;
        //startFrame = new StartFrame(this);
        startFrame2 = new StartFrame2(this);

    }
    public void initializeFrame(){
        boardPanel = new BoardPanel(this);
        boardMenu = new BoardMenu(this);
        timerPanel = new TurnTimerPanel(player1,player2);
        boardFrame = new BoardFrame(this);
        player1.startTurnTimer(timerPanel);
    }
	public void doMove(Move move) {
        System.out.println(counter);
        if (counter % 2 == 0) {
            if (board.tryMove3(move, player1)) {
                    //first round
                player1.stopTurnTimer();
                player2.startTurnTimer(timerPanel);

                counter++;
            }
        } else if (counter % 2 == 1) {
            //player2.startTurnTimer(timerPanel);
            if (board.tryMove3(move, player2)) {
                player2.stopTurnTimer();
                player1.startTurnTimer(timerPanel);
                counter++;
            }
            //player2.stopTurnTimer();
        }

    }
    public void saveGame(){

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
    public TurnTimerPanel getTurnTimerPanel() {
        return timerPanel;
    }
    public Board getBoard() {
    	return board;
    }
    public BoardMenu getBoardMenu() {
        return boardMenu;
    }

    public void setProfile(Profile newProfile) {
        this.profile = newProfile;
    }
}
