package game;

import org.w3c.dom.ranges.Range;

import javax.print.attribute.standard.DialogOwner;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class enemy extends tank{

   

    static URL enemyDURL = start.class.getResource("../images/enemy/enemy1D.gif");
    static ImageIcon enemyD = new ImageIcon(enemyDURL);

    static URL enemyRURL = start.class.getResource("../images/enemy/enemy1R.gif");
    static ImageIcon enemyR = new ImageIcon(enemyRURL);

    static URL enemyLURL = start.class.getResource("../images/enemy/enemy1L.gif");
    static ImageIcon enemyL = new ImageIcon(enemyLURL);

    static URL enemyUURL = start.class.getResource("../images/enemy/enemy1U.gif");
    static ImageIcon enemyU = new ImageIcon(enemyUURL);
    int dir = 1;

    int moveTime = 0;
    public enemy(String img, int x, int y, start panel, String upimg, String leftimg, String rightimg, String downimg) {
        super(img, x, y, panel, upimg, leftimg, rightimg, downimg);
    }

    public Direction getRandomDirection(){
        Random random = new Random();
        int rnum = random.nextInt(4);
        switch (rnum){
            case 0:
                dir = 1;
                return Direction.UP;
            case 1:
                dir = 2;
                return Direction.DOWN;
            case 2:
                dir = 3;
                return Direction.LEFT;
            case 3:
                dir = 4;
                return Direction.RIGHT;
            default:
                return null;
        }
    }

//    地方坦克移动方法
    public void go(){
        attack();
        if(moveTime>=20){
            direction = getRandomDirection();
            moveTime = 0;
        }else {
            moveTime++;
        }
        switch (direction){
            case LEFT :
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
            case UP:
                upward();
                break;
            case DOWN:
                drownward();
                break;
            default:
                break;
        }

    }

    public void attack(){
//        if(attackCoolDown){
//            Point p = this.getHeadPoint();
//            bullet bullett = new bullet("../images/bullerGreen.gif",p.x,p.y,this.panel,this.direction);
//            this.panel.bulletList.add(bullett);
//            new AttackCD().start();
//
//        }
        Point p = getHeadPoint();
        Random random = new Random();
        int num = random.nextInt(400);
//        15%的概率发射子弹
        if(num<16){
            this.panel.bulletList.add(new enemybullet("../images/bullerGreen.gif",p.x,p.y,this.panel,this.direction));
        }

    }
    @Override
    public void paintSelf(Graphics g,start s) {
        g.setColor(Color.white);
        g.fillRect(x,y,40,40);
//        g.drawImage();
        if( dir == 1){
            enemyU.paintIcon(s,g,x,y);
        } if(  dir == 2){
            enemyD.paintIcon(s,g,x,y);
        } if( dir == 4){
            enemyR.paintIcon(s,g,x,y);
        }if( dir == 3) {
            enemyL.paintIcon(s,g,x,y);
        }
        go();

        this.hitplayer();
//        this.hitBot();
    }

//    public void hitBot(){
//        ArrayList<enemy> ems = this.panel.enemylist;
////        enemy temp = null;
//        for(enemy em: ems){
//            if(this.getRec().intersects(em.getRec())){
////                temp = em;
////                this.panel.enemylist.remove(em);
//                this.panel.enemylist.add(this);
//                break;
//            }
//        }
//        this.panel.enemylist.remove(temp);
//    }
    public void hitplayer(){
        ArrayList<tank> players = this.panel.playList;
        for(tank player: players){
            if(this.getRec().intersects(player.getRec())){
                this.panel.playList.remove(player);
                player.alive = false;
                break;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
}
