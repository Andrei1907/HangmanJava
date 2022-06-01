package game;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    TitlePanel titlePanel;
    ConfigPanel configPanel;

    public LoginFrame() {
        super("Hangman");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        titlePanel = new TitlePanel(this);
        configPanel = new ConfigPanel(this);

        add(titlePanel, BorderLayout.NORTH);
        add(configPanel, BorderLayout.SOUTH);

        pack();
    }
}
