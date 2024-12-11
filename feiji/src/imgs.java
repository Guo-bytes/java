package src; /**
 * 当做图片的工具类
 */
import javax.swing.*;
import java.net.URL;
 class imgs {
     //封装一个图片对象
    static URL shellURl = imgs.class.getResource("img/bomb.png");
    static ImageIcon shellImg = new ImageIcon(shellURl);
     static URL bgURl = imgs.class.getResource("img/background.png");
     static ImageIcon bgImg = new ImageIcon(bgURl);
     static URL planeURl = imgs.class.getResource("img/plane.png");
     static ImageIcon planeImg = new ImageIcon(planeURl);
     static URL bulletURl = imgs.class.getResource("img/bullet.png");
     static ImageIcon bulletImg = new ImageIcon(bulletURl);
     static URL bobURl = imgs.class.getResource("img/bob.png");
     static ImageIcon bobImg = new ImageIcon(bobURl);
}
