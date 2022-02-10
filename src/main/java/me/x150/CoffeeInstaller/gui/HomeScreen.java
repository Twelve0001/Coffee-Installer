package me.x150.CoffeeInstaller.gui;

import me.x150.CoffeeInstaller.Main;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class HomeScreen extends GuiBase {
    @Override
    public JPanel getContents() {
        JPanel panel = new JPanel(null);
        JLabel title = new JLabel("<html><body style=\"font-size:25px;\">Installing Coffee</body></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(100, 2, 300, 45);
        JLabel desc = new JLabel("<html><body style=\"text-align:center;\">This will install Coffee Client onto your computer.<br>Do you wish to continue?</body></html>");
        desc.setHorizontalAlignment(JLabel.CENTER);
        desc.setBounds(-100, 60, 700, 30);
        JButton yes = new JButton("Yes");
        yes.setBounds(150, 130, 200, 30);
        yes.setHorizontalAlignment(JButton.CENTER);
        yes.addActionListener(e -> Main.mainGUI.setContent(new InstallerScreen().getContents()));
        JButton no = new JButton("No");
        no.setBounds(150, 165, 200, 30);
        no.setHorizontalAlignment(JButton.CENTER);
        no.addActionListener(e -> Main.mainGUI.frame.dispatchEvent(new WindowEvent(Main.mainGUI.frame, WindowEvent.WINDOW_CLOSING)));
        panel.add(title);
        panel.add(desc);
        panel.add(yes);
        panel.add(no);
        return panel;
    }
}
