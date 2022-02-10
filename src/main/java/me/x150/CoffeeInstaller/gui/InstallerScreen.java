package me.x150.CoffeeInstaller.gui;

import me.x150.CoffeeInstaller.Config;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class InstallerScreen extends GuiBase {
    @Override
    public JPanel getContents() {
        JPanel panel = new JPanel(null);
        JLabel title = new JLabel("<html><body style=\"font-size:25px;\">Installing Coffee</body></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(100, 2, 300, 40);
        JLabel location = new JLabel("<html><body style=\"text-align:center;\">Fabric installation folder</body></html>");
        location.setBounds(150, 85, 200, 20);
        location.setHorizontalAlignment(JLabel.CENTER);
        JTextField installLocation = new JTextField("File path");
        installLocation.setHorizontalAlignment(0);
        installLocation.setBounds(23, 110, 300, 30);
        installLocation.setEditable(false);
        JProgressBar progress = new JProgressBar();
        progress.setVisible(false);
        progress.setBounds(23, 200, 454, 20);
        progress.setMinimum(0);
        progress.setMaximum(100);
        JButton install = new JButton("Install");
        install.setEnabled(false);
        install.setHorizontalAlignment(SwingConstants.CENTER);
        install.setBounds(23, 150, 454, 30);
        install.addActionListener(e -> {
            install.setEnabled(false);
            if (Config.installLoc == null) {
                JOptionPane.showMessageDialog(panel, "Please avoid tampering with system memory.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            progress.setVisible(true);
            File modsFolder = new File(Config.installLoc.getAbsolutePath() + "/mods");
            if (!modsFolder.exists()) {
                int result = JOptionPane.showConfirmDialog(panel, "Mods folder not found. Create one and proceed with installation?", "Warning", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    boolean success = modsFolder.mkdir();
                    System.out.println("Created mods folder: "+success);
                } else return;
            }
            progress.setValue(10);
            File coffeeJar = new File(modsFolder.getAbsolutePath() + "/sipoverprivate-1.0.0.jar");
            String coffee = "https://gitlab.com/0x151/coffeeclientpublic/-/raw/master/bin/sipoverprivate-1.0.0.jar?inline=false";
            progress.setValue(20);
            try {
                new Thread(() -> {
                    try {
                        FileUtils.copyURLToFile(new URL(coffee), coffeeJar);
                        SwingUtilities.invokeLater(() -> progress.setValue(60));
                        SwingUtilities.invokeLater(() -> progress.setValue(100));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }).start();
            } finally {
                progress.setValue(100);
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    SwingUtilities.invokeLater(() -> {
                        progress.setValue(0);
                        progress.setVisible(false);
                        JOptionPane.showMessageDialog(panel, "Successfully installed Coffee Client! Run your Fabric launcher profile to use the client.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    });
                }).start();
            }
        });
        JButton pick = new JButton("Pick location");
        pick.setBounds(327, 110, 150, 30);
        pick.setHorizontalAlignment(JButton.CENTER);
        pick.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int opt = fileChooser.showOpenDialog(panel);
            if (opt == JFileChooser.APPROVE_OPTION) {
                File selected = fileChooser.getSelectedFile();
                installLocation.setText(selected.getAbsolutePath());
                Config.installLoc = selected;
                install.setEnabled(true);
            }
        });
        panel.add(title);
        panel.add(location);
        panel.add(installLocation);
        panel.add(pick);
        panel.add(install);
        panel.add(progress);
        return panel;
    }
}
