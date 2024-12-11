package src;

import javax.swing.*;
import java.awt.*;

//    空格：发射子弹
//    大写Q：回收弹药
//    鼠标单击：开始/暂停/继续
//    UP,DOWN,RIGHT,LEFT:移动飞机

public class StartGame {
    public  static  void stat(){



    }


    public static void main(String[] args){
        //构建一个窗体
        JFrame jf = new JFrame();
        //给予一个标题
        jf.setTitle("飞机大战");
//        设置一个窗口颜色，但是被面板覆盖了
//        jf.setBackground(new Color(183, 58, 58));
        //设置窗体的弹出位置，窗体的大小
//        setBounds方法对应4个参数
//        左上角的X坐标，左上角的y轴坐标
//        窗体的宽高

        //得到屏幕的宽高
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        jf.setBounds((width-640)/2,60,640,750);
        //固定窗口大小
        jf.setResizable(false);
        //默认关闭
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //创建一个面板，添加一个面板到窗体中
        GamePanel gp = new GamePanel();
        jf.add(gp);

        //默认隐藏，
        jf.setVisible(true);
    }
}