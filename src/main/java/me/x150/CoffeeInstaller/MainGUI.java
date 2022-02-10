package me.x150.CoffeeInstaller;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public JFrame frame;

    public MainGUI(JPanel panel) {

        /* MAIN WINDOW */
        frame = new JFrame("Coffee Installer");
        frame.setResizable(false);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (Component component : panel.getComponents()) {
            frame.add(component);
        }
        /* SHOW */
        frame.setVisible(true);
    }

    public void setContent(JPanel newContent) {
        frame.getContentPane().removeAll();
        for (Component component : newContent.getComponents()) {
            frame.add(component);
        }
        frame.getContentPane().revalidate();
        frame.repaint();
    }
}
