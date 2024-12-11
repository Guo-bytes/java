package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.KeyRep;
import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends JPanel {

//    一些常量

    private static final int bombNumber = 10;
    private static final int speed = 10;
    private static final int speedbob = 10;
    private static final int bulletspeed = 15;
    private static final int bulletnumber = 20+1;


    //定义飞机的xy轴坐标
    int planX;
    int planY;
    boolean up,down,left,right,attack,LIVE = true;

    int max = 0;
    int sorce;


    Timer timer;

    int[] bombX = new int[bombNumber];
    int[] bombY = new int[bombNumber];
    Boolean[] bomblive = new Boolean[bombNumber];
    int[] bobX = new int[bombNumber];
    int[] bobY = new int[bombNumber];

    int[] bulletX = new int[bulletnumber];
    int[] bulletY = new int[bulletnumber];
    int count = bulletnumber-1;
//    bulletnumber是实际子弹数目
//    ArrayList<String> bulletX = new ArrayList<>();
//    ArrayList<String> list = new ArrayList<>();
    //游戏开始的标志
    Boolean IsStar = false;
    Random r = new Random();
    public void init(){
        //初始化飞机的坐标
        planX = 200;
        planY = 550;

        count = bulletnumber-1;

        for(int i = 0;i<bombNumber;i++){
            bombX[i] = r.nextInt(640);
            bombY[i] = -r.nextInt(200);
            bobX[i] = -500;
            bobY[i] = 0;
        }

        for(int i = 0;i<bulletnumber;i++){
            bulletX[i] = -10;
            bulletY[i] = -10;
        }
        for (int i = 0;i<bombNumber;i++){
            bomblive[i] = true;
        }
        sorce = 0;


    }
//    弹药回收
    public void full(){
        for(int i = 0;i<bulletnumber;i++){
            bulletX[i] = -10;
            bulletY[i] = -10;
        }
    }
    public GamePanel(){
        init();
        //设置焦点
//        this.requestFocus(true);
        this.setFocusable(true);
        //加入监听效果
//      鼠标的监听
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!LIVE&&!IsStar){
                    init();
                    LIVE = true;
//                    IsStar = true;
                }else if (!LIVE){
                    IsStar = false;
                    LIVE = true;
                }else if(!IsStar){
                    IsStar = true;

                }



                else if (LIVE&&IsStar){
                    IsStar = false;
                }

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.addKeyListener(new KeyAdapter() {

            //监听键盘的按压
            @Override
            public void keyPressed(KeyEvent e){
                super.keyPressed(e);
                //获取按键信息
                int KeyCode = e.getKeyCode();
//                System.out.println(KeyCode);
                if(KeyCode == KeyEvent.VK_SPACE){
                    attack = true;
                } else if(KeyCode == KeyEvent.VK_UP) {
                    up = true;
                }else if(KeyCode == KeyEvent.VK_DOWN){
                    down = true;
                }else if(KeyCode == KeyEvent.VK_RIGHT){
                    right = true;
                }else if(KeyCode == KeyEvent.VK_LEFT){
                    left = true;
                }else if(KeyCode == KeyEvent.VK_Q){
                    count = bulletnumber-1;
                    full();
                }
            }

//            按键抬起
            @Override
            public void keyReleased(KeyEvent e){
//                super.keyReleased(e);
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_DOWN){
                    down = false;
                }else if(keyCode == KeyEvent.VK_LEFT){
                    left = false;
                }else if(keyCode == KeyEvent.VK_RIGHT){
                    right = false;
                }else if(keyCode == KeyEvent.VK_UP){
                    up = false;
                }
                if(keyCode == KeyEvent.VK_SPACE){
                    System.out.println(false);
                    attack = false;
                }
            }

        });

//        计时器
        timer = new Timer(100, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(IsStar) {
                    if (left) {
                        planX -= speed;
                    } else if (right) {
                        planX += speed;
                    } else if (up) {
                        planY -= speed;
                    } else if (down) {
                        planY += speed;
                    }
                    if(attack && count>0 ){
//                        System.out.println(attack);
//                        System.out.println(count);
                        bulletY[count-1] = planY;
                        bulletX[count-1] = planX+45;
                        count--;
                        System.out.println("--");
                    }
                    for(int i = 0;i<bulletnumber;i++){
                        if(bulletX[i]>0){
                            bulletY[i] -=bulletspeed;
                        }

//                        if(bulletY[i]<0 && bulletX[i]>0 && count>=0){
//
//                            System.out.println(bulletX[i]+" "+bulletY[i]+" "+count+"++");
//                            bulletY[i] = -1;
//                            bulletX[i] = -1;
//                            count++;
//
//                        }
                    }

                    for (int i = 0; i < bombNumber; i++) {
                        bombY[i] += speedbob;
                        if(bombY[i]>750){
                            bombX[i] =  r.nextInt(640);
                            bombY[i] = 0;
                        }
                    }




                    for(int i = 0;i<bombNumber;i++){//飞机死亡判断
                        boolean flage = new Rectangle(planX+30,planY+90,40,40).intersects(new Rectangle(bombX[i],bombY[i],55,90));
                        if(flage){
                            LIVE = false;
                            IsStar = false;
                            if(sorce>max){
                                max = sorce;
                            }

                        }
                    }
                    for(int i = 0;i<bulletnumber;i++){//炸弹爆炸判断
                        for(int j =0;j<bombNumber;j++){
                            boolean flage =new Rectangle(bulletX[i],bulletY[i],8,14).intersects(new Rectangle(bombX[j],bombY[j],45,30));
                            if(flage){
                                bobX[j] = bombX[j];
                                bobY[j] = bombY[j];
                                bomblive[j] = false;
                                bombX[j] =  r.nextInt(640);
                                bombY[j] = 0;

                                bulletY[i] = -10;
                                bulletX[i] = -10;

                                sorce+=10;
                                bomblive[j] = true;
                            }

                        }

                    }


                }
                repaint();
            }
        });
        timer.start();


    }



//面板中绘画的方法
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //设置一个背景颜色
        this.setBackground(new Color(187, 239, 232));
//          设置一个背景图片
        imgs.bulletImg.paintIcon(this,g,0,10);
        g.setColor(new Color(255, 255, 255, 255));
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("子弹数："+(count),10,22);
        g.drawString("分数："+sorce,10,42);
        g.drawString("最好成绩："+max,10,62);
        imgs.planeImg.paintIcon(this,g,planX,planY);

//        画敌方飞机
        for(int i = 0;i<bombNumber;i++){
            imgs.shellImg.paintIcon(this,g,bombX[i],bombY[i]);
            imgs.bobImg.paintIcon(this,g,bobX[i],bobY[i]);
            bobX[i] = -500;
            bobY[i] = 0;
        }
//        System.out.println(bulletX.length);
        for(int i=0;i<bulletX.length-1;i++){
            imgs.bulletImg.paintIcon(this,g,bulletX[i],bulletY[i]);
        }

        if(!IsStar&&LIVE){
            g.setColor(new Color(115, 44, 143));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("start!",200,300);
        }
        if(!IsStar&&!LIVE){
            g.setColor(new Color(248, 0, 78));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("GAME OVER!",200,260);
            g.drawString("最好纪录"+max,220,300);
            g.drawString("得分"+sorce,260,340);
        }
    }



}
