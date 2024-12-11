package game;

import java.awt.*;
import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;

public abstract class tank extends gamefather{
    public int width = 50;
    public int height = 45;
    public int speed = 5+bullet.getDeathEnemyNums();

    public boolean alive = false;

//    攻击冷却时间
    private boolean attackCoolDown = true;
//    攻击冷却时间为1000ms
    private int attackcoolDowntime = 1000;

//    无图片绘制
    public Direction direction = Direction.UP;

//    图片
    private String upimg;
    private String leftimg;
    private String rightimg;
    private String downimg;


    public tank(String img,int x,int y,start panel,
                String upimg,String leftimg,String rightimg,String downimg){
        super(img,x,y,panel);
        this.upimg = upimg;
        this.leftimg = leftimg;
        this.rightimg = rightimg;
        this.downimg = downimg;
    }

//    tank的移动
    public void leftward(){
        direction = Direction.LEFT;
//        setimg(leftimg);
        if(!hitwall(x-speed,y) && !removeborder(x-speed,y)){
            x-=speed;
        }

    }
    public void upward(){
         direction = Direction.UP;
//        setimg(upimg);
       if(!hitwall(x,y-speed) && !removeborder(x,y-speed)){
           y -= speed;
       }
    }
    public void rightward(){
        direction = Direction.RIGHT;
//        setimg(rightimg);
        if(!hitwall(x+speed,y) && !removeborder(x+speed,y)){
            x+=speed;
        }
    }
    public void drownward(){
       direction = Direction.DOWN;
//        setimg(downimg);
        if(!hitwall(x,y+speed) && !removeborder(x,y+speed)){
            y+=speed;
        }
    }



//    发射子弹
    public void attack(){
        if(attackCoolDown && alive){
            Point p = this.getHeadPoint();
            bullet bullett = new bullet("../images/bullerGreen.gif",p.x,p.y,this.panel,this.direction);
            this.panel.bulletList.add(bullett);
            new AttackCD().start();

        }

    }

//    新建线程
    class AttackCD extends Thread{
        public void run(){
//            冷却
            attackCoolDown = false;
//            休眠1s
            try{
                Thread.sleep(attackcoolDowntime);
            }catch (Exception e){
                e.printStackTrace();
            }
//            解除冷却状态
            attackCoolDown = true;
            this.interrupt();
        }
}

//    获取子弹的坐标
    public Point getHeadPoint(){
        switch (direction){
            case LEFT :
                return new Point(x-12,y+ height/2-5);
            case RIGHT:
                return new Point(x+width+2,y+height/2-5);
            case UP:
                return new Point(x+width/2-5,y-12);
            case DOWN:
                return new Point(x+width/2-5,y+height+2);
            default:
                return null;
        }
    }

//    围墙与坦克相撞
    public boolean hitwall(int x,int y){
        ArrayList<wall> walls = this.panel.wallList;

        Rectangle next = new Rectangle(x,y,width,height);
        for(wall wall:walls){
            if(next.intersects(wall.getRec())){
                return true;
            }
        }
        return false;
    }

    public boolean removeborder(int x,int y){
        if(x<0){
            return true;
        }else if(x+width>this.panel.getWidth()){
            return true;
        }else  if(y<0){
            return true;
        }else if(y+height>this.panel.getHeight()){
            return true;
        }
        else return false;
    }
    private void setimg(String img){
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }
    @Override
    public abstract void paintSelf(Graphics g,start s);
    //    预留的碰撞检测的矩形
    @Override
    public abstract Rectangle getRec();


}
