package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

public class playone extends tank {


    static URL play1DURL = start.class.getResource("../images/player1/p1tankD.gif");
    static ImageIcon play1D = new ImageIcon(play1DURL);

    static URL play1LURL = start.class.getResource("../images/player1/p1tankL.gif");
    static ImageIcon play1L = new ImageIcon(play1LURL);

    static URL play1RURL = start.class.getResource("../images/player1/p1tankR.gif");
    static ImageIcon play1R = new ImageIcon(play1RURL);

    static URL play1UURL = start.class.getResource("../images/player1/p1tankU.gif");
    static ImageIcon play1U = new ImageIcon(play1UURL);
    int dir = 1;


    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    public playone(String img, int x, int y, start panel, String upimg, String leftimg, String rightimg, String downimg) {
        super(img, x, y, panel, upimg, leftimg, rightimg, downimg);
    }

    //    键盘按压实现
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_A:
                left = true;
                dir = 3;
                break;
            case KeyEvent.VK_W:
                up = true;
                dir = 1;
                break;
            case KeyEvent.VK_S:
                down = true;
                dir = 2;
                break;
            case KeyEvent.VK_D:
                right = true;
                dir = 4;
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_SPACE:
                attack();
                break;
            default:
                break;
        }
    }

    public void move() {
        if (left) {
            leftward();
        } else if (right) {
            rightward();
        } else if (up) {
            upward();
        } else if (down) {
            drownward();
        }
    }


    @Override
    public void paintSelf(Graphics g, start s) {
        g.setColor(Color.red);
//        g.drawImage(img,x,y,null);
//        g.fillRect(x,y,40,40);
        if (up || dir == 1) {
            play1U.paintIcon(s, g, x, y);
        }
        if (down || dir == 2) {
            play1D.paintIcon(s, g, x, y);
        }
        if (right || dir == 4) {
            play1R.paintIcon(s, g, x, y);
        }
        if (left || dir == 3) {
            play1L.paintIcon(s, g, x, y);
        }
        move();
    }


    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
