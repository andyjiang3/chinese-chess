package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * The profile object holds user preferences form the menu.
 * It it's attributes are passed around to instantiate the rest of the game.
 *
 * @author Venkat Pamulapati
 */
public class Profile {
    private Color p1Color;
    private Color p2Color;
    private Color backGround;
    private Color foreGround;
    private Color lineColor;
    private String p1String;
    private String p2String;
    private int minutes;


    Profile() {
        p1Color = Color.RED;
        p2Color = Color.BLACK;
        backGround = Color.lightGray;
        foreGround = new Color(245, 245, 220);
        p1String = "Player 1";
        p2String = "Player 2";
        minutes = 10;
        lineColor = Color.BLACK;
    }

    public void setP1Color(Color p1Color) {
        this.p1Color = p1Color;
    }

    public void setP2Color(Color p2Color) {
        this.p2Color = p2Color;
    }


    public Color getForeGround() {
        return foreGround;
    }

    public Color getBackground() {
        return backGround;
    }

    public void setForeGround(Color foreGround) {
        this.foreGround = foreGround;
    }

    public void setBackGround(Color newBackGround) {
        this.backGround = newBackGround;
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

    public Color background() {
        return backGround;
    }

    public String getP1String() {
        return p1String;
    }

    public String getP2String() {
        return p2String;
    }


    public int getMinutes() {
        return minutes;
    }


    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }


}
