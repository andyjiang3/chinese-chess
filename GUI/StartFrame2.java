package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartFrame2 extends JFrame {

    private JPanel topLeftPanel, topRightPanel, top, middle, bottom, tippyTop;
    private JPanel p1Colors, p2Colors, p1Names, p2Names, bgColors, timers;
    private JButton p1Chooser, p2Chooser, bgChooser, begin, loadGame;
    private JTextField p1Name, p2Name;
    private BoardFrame boardFrame;
    private JSpinner minutes;


    private Profile profile;

    public StartFrame2(Core core) {
        this.setLayout(new GridLayout(4, 0));

        //Player 1 stuff
        p1Names = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p1Names.add(new JLabel("Name: "));
        p1Name = new JTextField("Northern Gamer");
        p1Names.add(p1Name);

        p1Colors = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p1Colors.add(new JLabel("Color: "));
        p1Chooser = new JButton("Select");
        p1Colors.add(p1Chooser);

        //Player 2 stuff
        p2Names = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p2Names.add(new JLabel("Name: "));
        p2Name = new JTextField("Northern Gamer");
        p2Names.add(p2Name);

        p2Colors = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p2Colors.add(new JLabel("Color: "));
        p2Chooser = new JButton("Select");
        p2Colors.add(p2Chooser);


        //tippy top stuff
        tippyTop = new JPanel(new GridLayout(0, 2));
        tippyTop.add(new JLabel("Player 1 Options:", SwingConstants.CENTER));
        tippyTop.add(new JLabel("Player 2 Options:", SwingConstants.CENTER));

        //top left panel stuff
        topLeftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        topLeftPanel.add(p1Names);
        topLeftPanel.add(p1Colors);


        //top Right Panel stuff
        topRightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        topRightPanel.add(p2Names);
        topRightPanel.add(p2Colors);

        //top panel stuff
        top = new JPanel(new GridLayout(0, 2));
        top.add(topLeftPanel);
        top.add(topRightPanel);

        //bgstuff
        bgColors = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        bgColors.add(new JLabel("Background Color"));
        bgChooser = new JButton("Select");
        bgColors.add(bgChooser);


        //timer stuff
        timers = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        timers.add(new JLabel("Time Limit"));
        minutes = new JSpinner(new SpinnerNumberModel(10, 1, 60, 1));
        timers.add(minutes);

        //middle stuff
        middle = new JPanel(new GridLayout(0, 2));


        middle.add(bgColors, BOTTOM_ALIGNMENT);

        middle.add(timers);

        //bottom stuff
        bottom = new JPanel(new GridLayout(0, 2));
        loadGame = new JButton("Load Game");
        begin = new JButton("Start New Game");
        bottom.add(loadGame);
        bottom.add(begin);


        profile = new Profile();

        //Action listeners
//        private JButton p1Chooser, p2Chooser, bgChooser, begin, loadGame;
        p1Chooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", Color.LIGHT_GRAY);
                profile.setP1Color(temp);

            }
        });
        p2Chooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", Color.LIGHT_GRAY);
                profile.setP2Color(temp);

            }
        });
        bgChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", Color.LIGHT_GRAY);
                profile.setBgColor1(temp);

            }
        });
        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                core.initializeFrame();

                //core.getBoardPanel().setProfile(profile);
                boardFrame = core.getBoardFrame();
                //core.getBoardPanel().setProfile(profile);
                boardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                boardFrame.setSize(900, 700);
                boardFrame.setVisible(true);
                setVisible(false);
            }
        });

        add(tippyTop);
        add(top);
        add(middle);
        add(bottom);

        this.setPreferredSize(new Dimension(600, 500));
        this.pack();
        this.setResizable(false);
        setVisible(true);

//        newGameButton = new JButton("New Game");
//        newGameButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                boardFrame = core.getBoardFrame();
//                boardFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//                boardFrame.setSize( 900, 700 ); // set frame size
//                boardFrame.setVisible( true );
//                setVisible(false);
//            }
//        });
        //newGameButtonPanel = new JPanel(new GridLayout(1, 1));
        //newGameButtonPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 25));
        //newGameButtonPanel.add(newGameButton);
        //loadGameFileChooser = new JFileChooser("Load Saved Game");
        //loadGameFileChooser.setFileFilter(new FileNameExtensionFilter("Saved Game", "chessgame"));
//        loadGameButton = new JButton("Load Game");
//        loadGameButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //loadGameFileChooser.showOpenDialog(Core.getLaunchFrame());
//                //SaverLoader.loadGame(loadGameFileChooser.getSelectedFile());
//            }
//        });
//        //loadGameButtonPanel = new JPanel(new GridLayout(1, 1));
//        //loadGameButtonPanel.setBorder(BorderFactory.createEmptyBorder(40, 25, 40, 50));
//        //loadGameButtonPanel.add(loadGameButton);
//
//
//        buttonsPanel = new JPanel(new GridLayout(2, 1));
//        buttonsPanel.setPreferredSize(new Dimension(300, 200));
//        buttonsPanel.add(newGameButton);
//        buttonsPanel.add(loadGameButton);
//
//        this.setLayout(new BorderLayout());
//        this.add(buttonsPanel, BorderLayout.CENTER);
//        this.pack();
//        this.setVisible(true);
//        this.setResizable(false);
//        this.setLocationRelativeTo(null);
    }
}
