package game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class start extends JFrame {





    boolean fa = true;

    //    定义双缓存图片(用于防止图像的闪动)
    Image offScreem = null;

    //    绘制主要窗口
    int width = 800;
    int height = 610;
    //绘制子弹列表
    ArrayList<bullet> bulletList = new ArrayList<>();
    //    敌人列表
    ArrayList<enemy> enemylist = new ArrayList<>();
    //    子弹列表
    ArrayList<bullet> removelist = new ArrayList<>();
    //    玩家列表
    ArrayList<tank> playList = new ArrayList<>();
    //  围墙列表
    ArrayList<wall> wallList = new ArrayList<>();
    //  基地列表
    ArrayList<Base> basesList = new ArrayList<>();

    //    图片
    Image select = Toolkit.getDefaultToolkit().getImage("../images/selecttank.gif");
    //    Image selects = new ImageIcon(new URL("../images/player1/p1tankD.gif")).getImage();
//    System.out.println("fasdfa")
    int y = 150;
    //    游戏模式
    int sate = 0;
    int tem = 1;
//      绘图次数统计
    int count = 0;
    //    敌方坦克数量
    int enemycount = 0;
    int nums = 0;
    //      玩家1
    playone player1 = new playone("../imgages/player1/p1tankD.gif", 125, 510, this,
            "../imgages/player1/p1tankD.gif", "../imgages/player1/p1tankD.gif", "../imgages/player1/p1tankD.gif", "../imgages/player1/p1tankD.gif");

    //添加基地
    Base base = new Base("../imgages/player1/p1tankD.gif", 375, 570, this);

    //      敌方坦克
    enemy em = new enemy("../imgages/player1/p1tankD.gif", 500, 110, this,
            "../imgages/player1/p1tankD.gif", "../imgages/player1/p1tankD.gif",
            "../imgages/player1/p1tankD.gif", "../imgages/player1/p1tankD.gif");

    public start() {
    }


    public void launch() {
//        标题
        setTitle("TANK WAR");
//        窗口初始大小
        setSize(width, height+20);
//        窗口居中
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
//        窗口不可调节
        setResizable(false);
        setVisible(true);


//        监听事件
        this.addKeyListener(new start.KeyMonitor());

//        添加围墙
        for (int i = 0; i < 15; i++) {
            wallList.add(new wall("../images/walls", i * 60, 170, this));
        }
        wallList.add(new wall("../images/walls", 305, 560, this));
        wallList.add(new wall("../images/walls", 305, 500, this));
        wallList.add(new wall("../images/walls", 365, 500, this));
        wallList.add(new wall("../images/walls", 425, 500, this));
        wallList.add(new wall("../images/walls", 425, 560, this));
        basesList.add(base);
        //        重绘图
        while (fa) {
//            if (enemylist.size() == 0 && enemycount == 10) {
//                sate = 5;
//            }
            if ((playList.size() == 0 && (sate == 1 || sate == 2) || basesList.size() == 0)) {
                sate = 4;
            }
            Random random = new Random();
//&&
            if (count % 100 == 1 && enemycount < 100 && (sate == 1 || sate == 2)) {
                int ThegetEnemy = random.nextInt(80);
                if((ThegetEnemy >0 && ThegetEnemy < 50+nums)|| enemylist.size() == 0 ){
                    int rnum = random.nextInt(760);
                    enemylist.add(new enemy("../imgages/player1/p1tankD.gif", rnum, 110, this,
                            "../imgages/player1/p1tankD.gif", "../imgages/player1/p1tankD.gif",
                            "../imgages/player1/p1tankD.gif", "../imgages/player1/p1tankD.gif"));
//                    count = 0;
                    enemycount += 1;
                }
            }

            repaint();

//            异常检测
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //    重绘方法重写
    @Override
    public void paint(Graphics g) {

//        定义和窗口等大的图片
        if (offScreem == null) {
            offScreem = this.createImage(width, height);
        }
//        得到画笔
//        Graphics2D gimage = (Graphics2D) g.create();
        Graphics gimage = offScreem.getGraphics();

        gimage.setColor(Color.GRAY);
        gimage.fillRect(0, 0, width, height);


        gimage.setColor(Color.WHITE);
        gimage.setFont(new Font("仿宋", Font.BOLD, 50));
        if (sate == 0 ) {
            gimage.drawString("IOS坦克大战", 220, 100);
            gimage.drawString("开始游戏", 220, 200);
            gimage.drawString("WASD移动 空格攻击", 220, 300);
            gimage.drawString("enter开始游戏", 220, 370);





            //        绘制坦克(一个白色的矩形)
            gimage.fillRect(160, y, 40, 40);

//            if(gimage.drawImage(select,160,y,null)){
//                System.out.println("ok");
//            }else {
//                System.out.println(select);
//                System.out.println(gimage.drawImage(select,160,y,null));
//            }

        } else if (sate == 1 || sate == 2) {
//            gimage.drawString("游戏开始", 220,100);
//            if(sate == 1){
//                gimage.drawString("单人模式",220,200);
//            }
//            else if(sate ==2){
//                gimage.drawString("双人模式",220,200);
//            }

            gimage.setFont(new Font("微软雅黑", Font.BOLD, 30));
            gimage.drawString("剩余敌人:" + enemylist.size(), 20, 70);
            gimage.drawString("已击杀敌人:" + bullet.getDeathEnemyNums(), 20, 100);
//          绘制元素
            player1.paintSelf(gimage,this);
//            绘制玩家
            for (tank player : playList) {
                player.paintSelf(gimage,this);
            }
//            循环子弹列表(增强循环)
            for (bullet bullet : bulletList) {
                bullet.paintSelf(gimage,this);
            }

//          删除子弹
            bulletList.removeAll(removelist);
//          绘制敌军
            for (enemy em : enemylist) {
                em.paintSelf(gimage,this);
            }
//            绘制围墙
            for (wall wall : wallList) {
                wall.paintSelf(gimage,this);
            }

//            绘制基地
            for (Base B : basesList) {
                B.paintSelf(gimage,this);
            }


            count++;
        } else if (sate == 3) {
            gimage.drawString("已暂停", 220, 100);
        } else if (sate == 4) {
            fa = false;
            gimage.drawString("YOU LOSER!!", 220, 100);
        }
//        else if (sate == 5) {
//            gimage.drawString("YOU WIN!!", 220, 100);
//        }


//    一次性绘画图形到窗口
        g.drawImage(offScreem, 0, 0, null);


    }

    //    键盘监视器
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

//            System.out.println(e.getKeyChar());
//          返回键值
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_1:
                    tem = 1;
                    y = 150;
                    break;
                case KeyEvent.VK_2:
                    tem = 2;
                    y = 250;
                    break;
                case KeyEvent.VK_ENTER:
                    sate = tem;
                    playList.add(player1);
                    player1.alive = true;


                    break;
                case KeyEvent.VK_P:
                    if (sate != 3) {
                        tem = sate;
                        sate = 3;
                    } else {
                        sate = tem;
                        if (tem == 0) {
                            tem = 1;
                        }
                    }
                default:
                    player1.keyPressed(e);

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);

        }
    }

    public static void main(String[] args) {
        start game = new start();
        game.launch();
    }

//    public void hitTank()

}
