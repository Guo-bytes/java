package src.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

public class gamepanel extends JPanel implements KeyListener, ActionListener {
    //定义小蛇的数据
    int length;//蛇的长度
    int[] snakeX = new int[600];//蛇的坐标
    int[] snakeY = new int[500];
    String fx;//方向

//    食物的坐标
    int foodx;
    int foody;
    Random random = new Random();

//    分值
    int sorce;
    int max = 0;
    boolean start = false;
    boolean live = true;

//    定时器
    Timer timer = new Timer(100,this);

//    构造器
    public gamepanel(){
        init();
        this.setFocusable(true);//获得焦点事件
        this.addKeyListener(this);
        timer.start();
    }
//    初始化
    public void init(){
        length = 3;
        snakeX[0] = 100;snakeY[0] = 100;
        snakeX[1] = 75;snakeY[1] = 100;
        snakeX[2] = 50;snakeY[2] = 100;
        fx = "R";
        sorce = 0;

//        食物的初始化
        foodx = 25 + 25* random.nextInt(34);
        foody = 75 + 25* random.nextInt(24);
    }



//    面板绘画
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponents(g);//清空屏幕
        this.setBackground(Color.BLACK);
//        绘制版面
        this.setBackground(Color.lightGray);
        getdate.header.paintIcon(this,g,25,11);
//        g.fillRect(10,10,880,660);
        g.fillRect(25,75,850,605);

//        画食物
        getdate.food.paintIcon(this,g,foodx,foody);

        g.setColor(Color.black);
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("长度"+length,200,40);
        g.drawString("分数"+sorce,30,40);
        g.drawString("最好记录"+max,400,40);

//        装饰
        getdate.body.paintIcon(this,g,0,0);
        getdate.down.paintIcon(this,g,0,25);
        getdate.down.paintIcon(this,g,100,10);
        getdate.left.paintIcon(this,g,820,30);
        getdate.body.paintIcon(this,g,845,30);
        getdate.body.paintIcon(this,g,870,30);
        getdate.body.paintIcon(this,g,350,20);
        getdate.body.paintIcon(this,g,325,20);
        getdate.body.paintIcon(this,g,300,20);
        getdate.left.paintIcon(this,g,275,20);
        getdate.right.paintIcon(this,g,627,20);
        getdate.body.paintIcon(this,g,600,20);
        getdate.body.paintIcon(this,g,575,20);
        getdate.body.paintIcon(this,g,550,20);
        getdate.up.paintIcon(this,g,700,35);
//        画小蛇
        //    第一个身体的坐标

        for(int i = 1;i<length;i++){
            getdate.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        if(fx.equals("R")){
            getdate.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("L")) {
            getdate.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("U")) {
            getdate.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("D")) {
            getdate.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }






//    游戏状态的设定
        if(!start &&live){
            g.setColor(Color.pink);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按键空格进行",300,300);
        }
        if(!live){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("GAME OVER",300,260);
            g.drawString("长度"+length,340,300);
            g.drawString("分数"+sorce,340,340);
            g.drawString("最好记录"+max,320,380);
            if(sorce>max){max = sorce;}
            start = false;
        }
        for(int i = 1;i<length;i++){
            if (snakeY[0] == snakeY[i] && snakeX[0] == snakeX[i]) {
                live = false;
                break;
            }
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

//    键盘监听事件
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if(keycode == KeyEvent.VK_SPACE){
            if(live && !start) {
                start = true;
                repaint();
            }else if(!live && !start){
                live = true;
                init();
            }else if(live&&start){
                start = false;
            }
        }
        if(live){
            if(keycode == KeyEvent.VK_UP && !(fx.equals("D"))){
                fx = "U";
            }else if(keycode == KeyEvent.VK_DOWN && !(fx.equals("U"))){
                fx = "D";
            }else if(keycode == KeyEvent.VK_RIGHT && !(fx.equals("L"))){
                fx = "R";
            }else if(keycode == KeyEvent.VK_LEFT && !(fx.equals("R"))){
                fx = "L";
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        定时器对应的活动
        if(start&&live){
            for(int i = length - 1;i>0;i--){
                snakeY[i] = snakeY[i-1];
                snakeX[i] = snakeX[i-1];
            }

            if(foody == snakeY[0] && foodx == snakeX[0]){
                length++;
                foodx = 25 + 25* random.nextInt(34);
                foody = 75 + 25* random.nextInt(24);
                sorce+=10;

            }

            if(fx.equals("R")){
                snakeX[0] = snakeX[0]+25;
                if(snakeX[0]>850){snakeX[0] = 25;};
            }else if(fx.equals("L")){
                snakeX[0] =snakeX[0] -25;
                if(snakeX[0]<25){snakeX[0] = 850;}
            }else if(fx.equals("U")){
                snakeY[0] = snakeY[0]-25;
                if(snakeY[0]<75){snakeY[0] = 650;}
            }else if(fx.equals("D")){
                snakeY[0] = snakeY[0]+25;
                if(snakeY[0]>650){snakeY[0] = 75;}
            }


            repaint();
        }
        timer.start();

    }
}
