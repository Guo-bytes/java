package game;

import java.awt.*;

//游戏父类的添加
public abstract class gamefather {
    public Image img;
   public int x;
    public int y;
    public start panel;


    public gamefather(String img, int x, int y, start panel){
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.panel = panel;
    }



    //    预留的碰撞检测的矩形


    public abstract void paintSelf(Graphics g, start s);

    public abstract Rectangle getRec();
}
