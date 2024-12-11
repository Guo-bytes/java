package src.game;

import javax.swing.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setBounds(10,10,910,740);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(new gamepanel());


        frame.setVisible(true);
    }
}
