package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/**
 * This is the starting menu. It accepts user input on a gui to create a profile object which is passed to core.start()
 */
public class StartFrame extends JFrame {

    private JPanel tippyTop, top, middle, bottom; //larger containers
    private JPanel topLeftPanel, topRightPanel; //medium containers
    private JPanel p1Colors, p2Colors, p1Names, p2Names, bgColors, timers, fgColors, lineColors; //basic containers
    private JButton p1Chooser, p2Chooser, bgChooser, begin, loadGame, fgChooser, lineChooser;
    private JTextField p1Name, p2Name;
    private BoardFrame boardFrame;
    private JSpinner minutes;


    private Profile profile;

    public StartFrame(Core core) {
        this.setLayout(new GridLayout(4, 0));

        Font bigFont = new Font("Sans_Serif", Font.PLAIN, 40);

        //Player 1 stuff
        p1Names = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p1Names.add(new JLabel("Name: "));
        p1Name = new JTextField("Player 1", 12);
        p1Names.add(p1Name);

        p1Colors = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p1Colors.add(new JLabel("Color: "));
        p1Chooser = new JButton("Select");
        p1Colors.add(p1Chooser);

        //Player 2 stuff
        p2Names = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p2Names.add(new JLabel("Name: "));
        p2Name = new JTextField("Player 2", 12);
        p2Names.add(p2Name);

        p2Colors = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p2Colors.add(new JLabel("Color: "));
        p2Chooser = new JButton("Select");
        p2Colors.add(p2Chooser);


        //tippy top stuff
        tippyTop = new JPanel(new GridLayout(0, 2));
        JLabel leftTitle = new JLabel("Player 1:");
        JLabel rightTitle = new JLabel("Player 2:");
        leftTitle.setFont(bigFont);
        leftTitle.setHorizontalAlignment(SwingConstants.CENTER);

        rightTitle.setFont(bigFont);
        rightTitle.setHorizontalAlignment(SwingConstants.CENTER);

        tippyTop.add(leftTitle);
        tippyTop.add(rightTitle);

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

        //background stuff
        bgColors = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        bgColors.add(new JLabel("Background Color"));
        bgChooser = new JButton("Select");
        bgColors.add(bgChooser);

        //foreground stuff
        fgColors = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        fgColors.add(new JLabel("Foreground Color"));
        fgChooser = new JButton("Select");
        fgColors.add(fgChooser);

        //lineColor stuff
        lineColors = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        lineColors.add(new JLabel("Line Colors"));
        lineChooser = new JButton("Select");
        lineColors.add(lineChooser);


        //timer stuff
        timers = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        timers.add(new JLabel("Time Limit"));
        minutes = new JSpinner(new SpinnerNumberModel(10, 1, 60, 1));
        timers.add(minutes);

        //middle stuff
        middle = new JPanel(new GridLayout(0, 2));
        JPanel middleLeftHolder = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));

        middleLeftHolder.add(bgColors);
        middleLeftHolder.add(fgColors);
        middleLeftHolder.add(lineColors);
        middleLeftHolder.add(timers);

        middle.add(middleLeftHolder);


        //Preview Panel
        class preview extends JPanel {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(profile.getBackground());
                int xOffset = 45;
                int yOffset = 10;
                g2.fill(new Rectangle2D.Double(xOffset, yOffset, 200, 100));

                g2.setColor(profile.getLineColor());
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(0 + xOffset, 50 + yOffset, 200 + xOffset, 50 + yOffset);
                g2.drawLine(50 + xOffset, 0 + yOffset, 50 + xOffset, 100 + yOffset);
                g2.drawLine(150 + xOffset, 0 + yOffset, 150 + xOffset, 100 + yOffset);


                g2.setColor(profile.getForeGround());
                g2.fill(new Ellipse2D.Double(xOffset + 10, 10 + yOffset, 80, 80));
                g2.fill(new Ellipse2D.Double(xOffset + 110, 10 + yOffset, 80, 80));

                g2.setStroke(new BasicStroke(5));
                g2.setColor(profile.getP1Color());
                g2.draw(new Ellipse2D.Double(10 + xOffset, 10 + yOffset, 80, 80));
                g2.drawString("General", 25 + xOffset, 55 + yOffset);

                g2.setColor(profile.getP2Color());
                g2.draw(new Ellipse2D.Double(110 + xOffset, 10 + yOffset, 80, 80));
                g2.drawString("Elephant", 125 + xOffset, 55 + yOffset);
            }

        }

        JPanel preview2 = new preview();
        middle.add(preview2);


        //bottom stuff
        bottom = new JPanel(new GridLayout(0, 2));
        loadGame = new JButton("Load Game");
        begin = new JButton("Start New Game");
        bottom.add(loadGame);
        bottom.add(begin);


        profile = new Profile();

        //Action listeners

        p1Chooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", profile.getP1Color());
                profile.setP1Color(temp);
                preview2.repaint();

            }
        });
        p2Chooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", profile.getP2Color());
                profile.setP2Color(temp);
                preview2.repaint();

            }
        });
        bgChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", profile.getBackground());
                profile.setBackGround(temp);
                preview2.repaint();

            }
        });
        fgChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", Color.LIGHT_GRAY);
                profile.setForeGround(temp);
                preview2.repaint();

            }
        });

        lineChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Choose Line Color", profile.getLineColor());
                profile.setLineColor(temp);
                preview2.repaint();

            }
        });

        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profile.setMinutes((int) minutes.getValue());
                profile.setP1String(p1Name.getText());
                profile.setP2String(p2Name.getText());
                core.start(profile);

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

        this.setPreferredSize(new Dimension(600, 600));
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
