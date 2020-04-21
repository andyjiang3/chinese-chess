package GUI;

import javax.swing.*;
import java.awt.*;

public class Profile {
    private Color p1Color = Color.lightGray;
    private Color p2Color = Color.lightGray;
    private Color bgColor1 = Color.lightGray;
    private String p1String;
    private String p2String;
    private int minutes;

    public void setP1Color(Color p1Color) {
        this.p1Color = p1Color;
    }

    public void setP2Color(Color p2Color) {
        this.p2Color = p2Color;
    }

    public void setBgColor1(Color bgColor1) {
        this.bgColor1 = bgColor1;
    }

    public void setP1String(String p1String) {
        this.p1String = p1String;
    }

    public void setP2String(String p2String) {
        this.p2String = p2String;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Color getP1Color() {
        return p1Color;
    }

    public Color getP2Color() {
        return p2Color;
    }

    public Color getBgColor1() {
        return bgColor1;
    }

    public String getP1String() {
        return p1String;
    }

    public String getP2String() {
        return p2String;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "p1Color=" + p1Color +
                ", p2Color=" + p2Color +
                ", bgColor1=" + bgColor1 +
                ", p1String='" + p1String + '\'' +
                ", p2String='" + p2String + '\'' +
                ", minutes=" + minutes +
                '}';
    }

    public int getMinutes() {
        return minutes;
    }

    Profile(Color p1, Color p2, Color bg, String p1Name, String p2Name, int min) {
        p1Color = p1;
        p2Color = p2;
        bgColor1 = bg;
        p1String = p1Name;
        p2String = p2Name;
        minutes = min;
    }

    Profile() {
    }

    ;

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("StartMenu2");
//        frame.setContentPane(new StartMenu2().$$$getRootComponent$$$());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }

}
