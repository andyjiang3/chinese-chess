package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class BoardMenu extends JMenuBar {
	private String languageKey;
	public BoardMenu(ActionListener saveHandler) {
		JMenuItem aboutItem = new JMenuItem( "About..." );
	    aboutItem.setMnemonic( 'A' );
	    aboutItem.addActionListener(
	       new ActionListener() {  
	          // display message dialog when user selects About...
	          public void actionPerformed( ActionEvent event ) {
	        	  JOptionPane.showMessageDialog( BoardMenu.this,
	                "This Chinese Chess game was created by the following members:\nVenkatratnam Pamulaptai\nMichael Yu\nAndy Jiang",
	                "About", JOptionPane.PLAIN_MESSAGE );
	          } 
	       } 
	    ); 
	    JMenuItem saveItem = new JMenuItem( "Save" );
	    saveItem.setMnemonic( 'S' ); 
	    saveItem.addActionListener(saveHandler);
	    JMenuItem exitItem = new JMenuItem( "Exit" );
	    exitItem.setMnemonic( 'x' );
	    exitItem.addActionListener(
	       new ActionListener() {  
	          // terminate application when user clicks exitItem
	          public void actionPerformed( ActionEvent event ){
	             System.exit( 0 );
	          } 
	       } 
	    );
	    JMenu fileMenu = new JMenu( "File" );
	    fileMenu.setMnemonic( 'F' );
	    fileMenu.add( aboutItem );
	    fileMenu.add( saveItem );
	    fileMenu.add( exitItem );
	    
	    JMenu viewMenu = new JMenu( "View" );
	    viewMenu.setMnemonic( 'V' );
	    String language[] = { "English", "Chinese" };
	    JMenu languageMenu = new JMenu( "Language" );
	    languageMenu.setMnemonic( 'a' ); 
	    ButtonGroup languageButtonGroup = new ButtonGroup(); // manages languages
	    JRadioButtonMenuItem englishButton = new JRadioButtonMenuItem( "English" ); // create item
	    JRadioButtonMenuItem chineseButton = new JRadioButtonMenuItem( "Chinese" );
	 	languageMenu.add(englishButton ); // add item to language menu
	 	languageButtonGroup.add(englishButton );
	 	languageMenu.add(chineseButton ); // add to group
	 	languageButtonGroup.add(chineseButton );
	    ActionListener itemHandler = new ActionListener() {
	    	public void actionPerformed( ActionEvent event ){
	           // process language selection
	           if(englishButton.isSelected()) {
	        	   languageKey = "English";
	           }
	           if(chineseButton.isSelected()) {
	        	   languageKey = "Chinese";
	           }
	        }
	    };
	    englishButton.addActionListener( itemHandler );
	    chineseButton.addActionListener( itemHandler );
	    englishButton.setSelected( true ); // select first language item
	    viewMenu.add( languageMenu );
	    //viewMenu.addSeparator();
	    JMenu helpMenu = new JMenu("Help");
	    helpMenu.setMnemonic( 'H' ); 
	    JMenuItem rules = new JMenuItem("How to Play...");
	    rules.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://en.wikipedia.org/wiki/Xiangqi"));
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
	    	}
	    });
	    helpMenu.add(rules);
	    add( fileMenu ); // add file menu to menu bar
	    add( viewMenu );
	    add( helpMenu );
	}
	public String getLanguage() {
		return languageKey;
	}
}
