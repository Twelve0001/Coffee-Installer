package me.x150.CoffeeInstaller;

import com.github.weisj.darklaf.LafManager;
import me.x150.CoffeeInstaller.gui.HomeScreen;

public class Main {
    public static MainGUI mainGUI;

    public static void main(String[] args) {
        /* THEME */
        LafManager.setTheme(new com.github.weisj.darklaf.theme.OneDarkTheme());
        LafManager.install();
        /* GUI */
        mainGUI = new MainGUI(new HomeScreen().getContents());

    }
}
