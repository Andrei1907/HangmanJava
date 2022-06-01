package game;

import javax.swing.*;

public class TitlePanel extends JPanel {
    JLabel title;
    final LoginFrame frame;

    public TitlePanel(LoginFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        title = new JLabel("Welcome to Hangman!");
        add(title);
    }
}
