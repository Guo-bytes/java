package src.game;

import java.awt.font.ImageGraphicAttribute;
import java.net.URL;
import javax.swing.*;

public class getdate {
    public static URL headerURL = getdate.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    public static URL upURL = getdate.class.getResource("/statics/up.png");
    public static URL downURL = getdate.class.getResource("/statics/down.png");
    public static URL rightURL = getdate.class.getResource("/statics/right.png");
    public static URL leftURL = getdate.class.getResource("/statics/left.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon right = new ImageIcon(rightURL);
    public static ImageIcon left = new ImageIcon(leftURL);

    public static URL bodyURL = getdate.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    public static URL foodURL = getdate.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);

}
