package game;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    JLabel title;
    final LoginFrame frame;

    public ConfigPanel(LoginFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        title = new JLabel("Welcome to Hangman!");
        add(title);
    }
}
