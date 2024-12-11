package game;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class bullet extends gamefather{
    static URL bulletURL = start.class.getResource("../images/bullet/bulletGreen.gif");
    static ImageIcon bullet = new ImageIcon(bulletURL);

    static  int count = 0;

    //    尺寸
        int width = 10;
        int height = 10;
//    速度
    int bulletspeed = 10 + count;
//    方向
    Direction direction;

    public bullet(String img, int x, int y, start panel, Direction direction) {
        super(img, x, y, panel);
        this.direction = direction;
    }

//    编写子弹的移动方法
    public void leftward(){
        x-=bulletspeed;
    }
    public void rightward(){
        x+=bulletspeed;
    }
    public void upward(){
        y-=bulletspeed;
    }
    public void downward(){
        y+=bulletspeed;
    }

//    子弹的移动判定
    public void go(){
        switch (direction){
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
            case LEFT:
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
            default:
                break;
        }
        this.hitwall();
        this.moveToBorder();
        this.hitBase();
    }

    public int hitBot(){
        ArrayList<enemy> ems = this.panel.enemylist;
        for(enemy em: ems){
            if(this.getRec().intersects(em.getRec())){
                count++;
                this.panel.enemylist.remove(em);
                this.panel.removelist.add(this);
                return count;
            }
        }
        return count;
    }

    public void hitBase(){
        ArrayList<Base> Bas = this.panel.basesList;
        for(Base B:Bas){
            if(this.getRec().intersects(B.getRec())){
                this.panel.basesList.remove(B);
                this.panel.removelist.add(this);
                break;
            }
        }
    }

//    围墙的碰撞
    public void hitwall(){
//        初始化围墙列表
        ArrayList<wall> walls = this.panel.wallList;
//        遍历所有的元素
        for(wall wall:walls){
            if(this.getRec().intersects(wall.getRec())){
                this.panel.wallList.remove(wall);
                this.panel.removelist.add(this);
//                停止循环
                break;
            }
        }
    }

    public void moveToBorder(){
        if(x<0 || x+width>this.panel.getWidth() ){
            this.panel.removelist.add(this);
        }
        if(y<0 || y+height>this.panel.getHeight()){
            this.panel.removelist.add(this);
        }
    }
    @Override
    public void paintSelf(Graphics g,start s) {
        g.setColor(Color.black);
//        g.fillRect(x,y,10,10);
        bullet.paintIcon(s,g,x,y);
        go();

        hitBot();
    }
    public static int getDeathEnemyNums(){
        return count;
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
}
