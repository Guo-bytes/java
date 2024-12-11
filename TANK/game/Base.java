package game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.nio.file.attribute.AclEntry;

public class Base extends gamefather{

    static URL BasURL = start.class.getResource("../images/star.gif");
    static ImageIcon Bas = new ImageIcon(BasURL);
    int length = 40;

    public Base(String img, int x, int y, start panel) {
        super(img, x, y, panel);
    }

    @Override
    public void paintSelf(Graphics g,start s) {
        g.setColor(Color.GRAY);
//        g.fillRect(x,y,length,length);
        Bas.paintIcon(s,g,x,y);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,length, length);
    }
}
