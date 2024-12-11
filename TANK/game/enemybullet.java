package game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class enemybullet extends bullet{

    static URL bulletURL = start.class.getResource("../images/bullet/bulletYellow.gif");
    static ImageIcon bullet = new ImageIcon(bulletURL);
    public enemybullet(String img, int x, int y, start panel, Direction direction) {
        super(img, x, y, panel, direction);
    }

    public void hitplayer(){
        ArrayList<tank> players = this.panel.playList;
        for(tank player: players){
            if(this.getRec().intersects(player.getRec())){
                this.panel.playList.remove(player);
                this.panel.removelist.add(this);
                player.alive = false;
                break;
            }
        }
    }

    @Override
    public void paintSelf(Graphics g,start s) {
//        g.setColor(Color.BLACK);
//        g.fillRect(x,y,10,10);
//        super.paintSelf(g,s);
        bullet.paintIcon(s,g,x,y);
        super.go();
        hitplayer();

    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
