package game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class wall extends gamefather{
//    尺寸
    int length = 60;

    static URL wallURL = start.class.getResource("../images/wall/wall.gif");
    static ImageIcon wall = new ImageIcon(wallURL);
    public wall(String img, int x, int y, start panel) {
        super(img, x, y, panel);
    }

    @Override
    public void paintSelf(Graphics g,start s) {
        g.setColor(Color.BLUE);
//        g.fillRect(x,y,60,60);
        this.wall.paintIcon(s,g,x+7,y);

    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,length,length);
    }
}
