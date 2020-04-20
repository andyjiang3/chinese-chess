package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class BoardTester {
	public static void main(String[] args) {
		BoardFrame border = new BoardFrame(); 
	    border.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    border.setSize( 900, 700 ); // set frame size
	    border.setVisible( true );
	}
}
