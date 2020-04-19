package GUI;
import GameLogic.Pieces.Piece;
import GameLogic.Point;
import GameLogic.Board;
import GameLogic.Move;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//defines the entire JFrame for the GUI
public class BoardFrame extends JFrame {
	public BoardFrame() {
		super("Chinese Chess");
		BoardPanel board = new BoardPanel();
		add(board, BorderLayout.CENTER);
	}
}
//defines a JPanel that the board is drawn to
class BoardPanel extends JPanel {
	private Board board;
	private JLabel[][] pointIcons = new JLabel[10][9];
	//private Icons[][] pointIcons = new Icons[10][9];
	private int squareWidth, radius;
	private int[] pressLoc = new int[2], releaseLoc = new int[2];
	private boolean pressed = false, pressIsValid = false;
	private int[] chosen = new int[2];
	public BoardPanel() {
		//setSize(500, 500);
		board = new Board();
		
		//the mouse listener really confusing, just ask me how it works
		//I will change it later to make it less jumbled
		MouseAdapter handler = new MouseAdapter() {
			private int xCoord, yCoord;
			public void mouseClicked(MouseEvent e) {
				selectedLabel(e);
				//if a piece has not been selected, perform this first block of code
				if(pressed == false) {
					if (storePressed() == true) {
						pressed = true;
						repaint();
					}
				}
				//if a piece has been selected, perform this first block of code
				else if(pressed == true) {
					if (storeReleased() == true) {
						//stores new pressed piece if the two locations have pieces that are on the same side
						//so that you can choose a new piece without calling the move method
						if(board.getPoint(pressLoc[0],pressLoc[1]).getPiece() != null &&
								board.getPoint(releaseLoc[0],releaseLoc[1]).getPiece() != null &&
								(board.getPoint(pressLoc[0],pressLoc[1]).getPiece().getSide() == 
								board.getPoint(releaseLoc[0],releaseLoc[1]).getPiece().getSide())) {
							storePressed();
							repaint();
						}
						else {
							pressed = false;
							board.tryMove(new Move(pressLoc[0], pressLoc[1],releaseLoc[0], releaseLoc[1]));
							System.out.println("Origin XCoord, Origin YCoord, Destination XCoord, Destination YCoord");
							System.out.println(pressLoc[0] + "," + pressLoc[1] + "," + releaseLoc[0] + "," + releaseLoc[1]);
							repaint();
						}
					}
				}
				//System.out.println("clicked");
			}
			public void mousePressed(MouseEvent e) {
				selectedLabel(e);
				if(pressed == false)
					storePressed();
			}
			/*public void mouseReleased(MouseEvent e) {
				selectedLabel(e);
				storeReleased();
				board.tryMove(new Move(pressLoc[0], pressLoc[0],releaseLoc[0], releaseLoc[0]));
				//System.out.println(pressLoc[0] + ", " + pressLoc[1] + "," + releaseLoc[0] + ", " + releaseLoc[1]);
				//System.out.println("released");
				//System.out.println(xCoord + "," + yCoord);
				repaint();
			}*/
			//this method finds which JLabel in the point is being selected and returns its index
			public void selectedLabel(MouseEvent e) {
				for(int y = 0; y<10; y++) {
					for(int x = 0; x<9; x++) {
						if (e.getSource() == pointIcons[y][x]) {
							yCoord = y;
							xCoord = x;
							break;
						}
					}
	            }
			}
			//this method stores the location of the pressed piece
			public boolean storePressed() {
				if(board.getPoint(xCoord,yCoord).getPiece() == null)
					pressIsValid = false;
				else {
					pressIsValid = true;
					pressLoc[0] = xCoord;
					pressLoc[1] = yCoord;
					return true;
				}
				return false;
			}
			//this method stores the location of the released piece
			public boolean storeReleased() {
				if(pressIsValid == true) {
					releaseLoc[0] = xCoord;
					releaseLoc[1] = yCoord;
					return true;
				}
				return false;
			}
		};
		
		for(int y = 0; y<10; y++) {
			for(int x = 0; x<9; x++) {
				//pointIcons[y][x] = new Icons(x,y,board.getPoint(x, y));
				pointIcons[y][x] = new JLabel();
				pointIcons[y][x].addMouseListener(handler);
				//pointIcons[x][y].setBackground(Color.black);
				//pointIcons[x][y].setOpaque(true);
				add(pointIcons[y][x]);
			}
		}
		setLayout(null);
		//board.tryMove(new Move(4, 9, 4, 8));
	}
	public void paintComponent(Graphics g) {
		int xDisplacement, yDisplacement;	
		//sets a new width for the grid squares
		if(8*getHeight() > 9*getWidth())
			squareWidth = getWidth()/10;
		else
			squareWidth = getHeight()/10;
		radius = squareWidth*4/10;
		
		//this loop sets new positions for each of the points in the grid for if the board is resized.
		for(int y = 0; y<10; y++) {
			for(int x = 0; x<9; x++) {
				xDisplacement = (4-x)*squareWidth;
				yDisplacement = (10-2*y)*squareWidth/2 - squareWidth/3;
				board.getPoint(x,y).setPosition(getWidth()/2 - xDisplacement, 8+getHeight()/2 - yDisplacement);
				pointIcons[y][x].setLocation(board.getPoint(x,y).getX() - radius, board.getPoint(x,y).getY()-radius);
				pointIcons[y][x].setSize(radius*2, radius*2);
			}
		}
		super.paintComponent( g );
		Graphics2D g2 = (Graphics2D) g;
		drawBoard(g2);
		drawPieces(g2);
	}
	public void drawBoard(Graphics2D g2) {
		for(int y = 0; y<10; y++) {
			for(int x = 0; x<9; x++) {
				if(x==8)
					continue;
				g2.drawLine(board.getPoint(x,y).getX(), board.getPoint(x,y).getY(), board.getPoint(x+1,y).getX(), board.getPoint(x+1,y).getY());
			}
			for(int x = 0; x<9; x++) {
				if(y==9)
					continue;
				else if(y == 4 && x<8 && x>0)
					continue;
				g2.drawLine(board.getPoint(x,y).getX(), board.getPoint(x,y).getY(), board.getPoint(x,y+1).getX(), board.getPoint(x,y+1).getY());
			}
		}
		//draws the middle square
		g2.drawLine(board.getPoint(3,0).getX(), board.getPoint(3,0).getY(), board.getPoint(5,2).getX(), board.getPoint(5,2).getY());
		g2.drawLine(board.getPoint(5,0).getX(), board.getPoint(5,0).getY(), board.getPoint(3,2).getX(), board.getPoint(3,2).getY());
		g2.drawLine(board.getPoint(3,7).getX(), board.getPoint(3,7).getY(), board.getPoint(5,9).getX(), board.getPoint(5,9).getY());
		g2.drawLine(board.getPoint(5,7).getX(), board.getPoint(5,7).getY(), board.getPoint(3,9).getX(), board.getPoint(3,9).getY());
		//marks up the top half of the board
		drawPoint(g2, board.getPoint(1,2),0,360);
		drawPoint(g2, board.getPoint(7,2),0,360);
		drawPoint(g2, board.getPoint(0,3),-90,90);
		drawPoint(g2, board.getPoint(2,3),0,360);
		drawPoint(g2, board.getPoint(4,3),0,360);
		drawPoint(g2, board.getPoint(6,3),0,360);
		drawPoint(g2, board.getPoint(8,3),90,270);
		//marks up the bottom half of the board
		drawPoint(g2, board.getPoint(0,6),-90,90);
		drawPoint(g2, board.getPoint(2,6),0,360);
		drawPoint(g2, board.getPoint(4,6),0,360);
		drawPoint(g2, board.getPoint(6,6),0,360);
		drawPoint(g2, board.getPoint(8,6),90,270);
		drawPoint(g2, board.getPoint(1,7),0,360);
		drawPoint(g2, board.getPoint(7,7),0,360);
	}
	//marks the points that the soldiers go on at the start of the game
	//added the start and end angle arguments so I could draw the points on the edge
	public void drawPoint(Graphics2D g2, Point point, int startAngle, int endAngle) {
		for (int x = startAngle/90; x<endAngle/90; x++) {
			g2.drawLine(point.getX() + (int)(Math.cos(Math.toRadians(45+90*x))*squareWidth/10), 
					point.getY() + (int)(Math.sin(Math.toRadians(45+90*x))*squareWidth/10), 
					point.getX() + (int)(Math.cos(Math.toRadians(45+90*x))*squareWidth/10),
					point.getY() + (int)(Math.sin(Math.toRadians(45+90*x))*squareWidth/5));
			g2.drawLine(point.getX() + (int)(Math.cos(Math.toRadians(45+90*x))*squareWidth/10), 
					point.getY() + (int)(Math.sin(Math.toRadians(45+90*x))*squareWidth/10), 
					point.getX() + (int)(Math.cos(Math.toRadians(45+90*x))*squareWidth/5),
					point.getY() + (int)(Math.sin(Math.toRadians(45+90*x))*squareWidth/10));
		}	
	}
	public void drawPieces(Graphics2D g2) {
		g2.setFont(new Font("Sans_Serif", Font.PLAIN, 12));
		FontMetrics metrics = g2.getFontMetrics();
		int xCoord,yCoord;
		for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                if (board.getPoint(x,y).getPiece() == null)
                    continue;
                else {
                	g2.setColor(new Color(245,245,220));
                	g2.fillOval(board.getPoint(x,y).getX()-radius, board.getPoint(x,y).getY()-radius, radius*2, radius*2);
                	if(board.getPoint(x,y).getPiece().getSide() == Piece.Side.UP)
                		g2.setColor(Color.red);
                	else
                		g2.setColor(Color.black);
                	g2.drawOval(board.getPoint(x,y).getX()-radius, board.getPoint(x,y).getY()-radius, radius*2, radius*2);
                	xCoord = board.getPoint(x,y).getX() - metrics.stringWidth(board.getPoint(x,y).getPiece().toString()) / 2;
                	yCoord = board.getPoint(x,y).getY() - metrics.getHeight()/2 + metrics.getAscent();
                    g2.drawString(board.getPoint(x,y).getPiece().toString(), xCoord,yCoord);
                }
            }
        }
		if(pressed == true) {
			g2.setColor( Color.yellow);
			g2.drawOval(board.getPoint(pressLoc[0],pressLoc[1]).getX()-radius, board.getPoint(pressLoc[0],pressLoc[1]).getY()-radius, radius*2, radius*2);
		}
	}

	/*class Icons extends JLabel{
		private int x, y;
		private Point point;
		public Icons(int x, int y, Point point) {
			this.x = x;
			this.y = y;
			this.point = point;
			setHorizontalAlignment(JLabel.CENTER);
		    setVerticalAlignment(JLabel.CENTER);
		    if (point.getPiece() != null)
		    	setText(point.getPiece().toString());
		    
		}
		public void paintComponent(Graphics g) {
			super.paintComponent( g );
			Graphics2D g2 = (Graphics2D) g;
			if (point.getPiece() != null) {
				if(point.getPiece().getSide() == Piece.Side.UP)
					g2.setColor(Color.red);
				else
					g2.setColor(Color.black);
			}
			
			if (point.getPiece() != null) {
				g2.drawOval(getX(), getY(), getWidth(), getHeight());
			}
			g2.setFont(new Font("Sans_Serif", Font.PLAIN, 12));
			FontMetrics metrics = g2.getFontMetrics();
			int xCoord,yCoord;
			for (int y = 0; y < 10; y++) {
	            for (int x = 0; x < 9; x++) {
	                if (board.getPoint(x,y).getPiece() == null)
	                    continue;
	                else {
	                	g2.setColor(new Color(245,245,220));
	                	g2.fillOval(board.getPoint(x,y).getX()-radius, board.getPoint(x,y).getY()-radius, radius*2, radius*2);
	                	if(board.getPoint(x,y).getPiece().getSide() == Piece.Side.UP)
	                		g2.setColor(Color.red);
	                	else
	                		g2.setColor(Color.black);
	                	g2.drawOval(board.getPoint(x,y).getX()-radius, board.getPoint(x,y).getY()-radius, radius*2, radius*2);
	                	xCoord = getX() - metrics.stringWidth(board.getPoint(x,y).getPiece().toString()) / 2;
	                	yCoord = getY() - metrics.getHeight()/2 + metrics.getAscent();
	                    g2.drawString(board.getPoint(x,y).getPiece().toString(), xCoord,yCoord);
	                }
	            }
	        }*/
}

