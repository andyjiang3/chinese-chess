package GUI;
import GameLogic.Pieces.Piece;
import GameLogic.Point;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
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
	private Point grid[][] = new Point[10][9];
	private int squareWidth;
	public BoardPanel() {
		setSize(500, 500);
		//created my own 2d array of points instead of using the Board class to make sure it works first
		//also didn't want to use the Board class because it creates a board that is sideways, not vertical
		for(int x = 0; x<10; x++) {
			for(int y = 0; y<9; y++) {
				grid[x][y] = new Point(x,y);
			}
		}
	}
	public void paintComponent(Graphics g) {
		//sets a new width for the grid squares
		if(8*getHeight() > 9*getWidth())
			squareWidth = getWidth()/10;
		else
			squareWidth = getHeight()/10;
		int xDisplacement, yDisplacement;
		//this loop sets new positions for each of the points in the grid for if the board is resized.
		for(int x = 0; x<10; x++) {
			for(int y = 0; y<9; y++) {
				xDisplacement = (4-y)*squareWidth;
				yDisplacement = (10-2*x)*squareWidth/2 ;
				grid[x][y].setPosition(getWidth()/2 - xDisplacement, 8+getHeight()/2 - yDisplacement);
			}
		}
		super.paintComponent( g );
		Graphics2D g2 = (Graphics2D) g;
		drawBoard(g2);
	}
	public void drawBoard(Graphics2D g2) {
		//old code
		//int initXPoint = getWidth()/10;
		//int initYPoint = getWidth()/20;
		for(int x = 0; x<10; x++) {
			g2.drawLine(grid[x][0].getX(), grid[x][0].getY(), grid[x][8].getX(), grid[x][0].getY());
			//g2.drawLine(initXPoint, initYPoint + x*getHeight()/10, 
			//			getWidth() - initXPoint, initYPoint+ x*getHeight()/10);
		}
		for(int y = 0; y<9; y++) {
			g2.drawLine(grid[0][y].getX(), grid[0][y].getY(), grid[0][y].getX(), grid[9][y].getY());
			//g2.drawLine(initXPoint+ y*getHeight()/10, initYPoint, 
			//			initXPoint +y*getHeight()/10, getHeight() - initYPoint);
		}
		//draws the middle square
		g2.drawLine(grid[0][3].getX(), grid[0][3].getY(), grid[2][5].getX(), grid[2][5].getY());
		g2.drawLine(grid[0][5].getX(), grid[0][5].getY(), grid[2][3].getX(), grid[2][3].getY());
		g2.drawLine(grid[7][3].getX(), grid[7][3].getY(), grid[9][5].getX(), grid[9][5].getY());
		g2.drawLine(grid[7][5].getX(), grid[7][5].getY(), grid[9][3].getX(), grid[9][3].getY());
		//marks up the top half of the board
		drawPoint(g2, grid[2][1],0,360);
		drawPoint(g2, grid[2][7],0,360);
		drawPoint(g2, grid[3][0],-90,90);
		drawPoint(g2, grid[3][2],0,360);
		drawPoint(g2, grid[3][4],0,360);
		drawPoint(g2, grid[3][6],0,360);
		drawPoint(g2, grid[3][8],90,270);
		//marks up the bottom half of the board
		drawPoint(g2, grid[6][0],-90,90);
		drawPoint(g2, grid[6][2],0,360);
		drawPoint(g2, grid[6][4],0,360);
		drawPoint(g2, grid[6][6],0,360);
		drawPoint(g2, grid[6][8],90,270);
		drawPoint(g2, grid[7][1],0,360);
		drawPoint(g2, grid[7][7],0,360);
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
}
