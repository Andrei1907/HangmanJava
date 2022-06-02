package game;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    TitlePanel titlePanel;
    TextPanel textPanel;
    ConfigPanel configPanel;
    MessagePanel messagePanel;
    StartPanel startPanel;
    GamePanel gamePanel;
    RematchPanel rematchPanel;

    public LoginFrame() {
        super("Hangman");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(750, 500));

        titlePanel = new TitlePanel(this, "Welcome to Hangman!");
        textPanel = new TextPanel(this);
        configPanel = new ConfigPanel(this);

        add(titlePanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(configPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        pack();
    }

    public void configStartPanel() {
        getContentPane().removeAll();
        repaint();
        messagePanel = new MessagePanel(this, "<html>You have successfully logged in.<br>Start playing!</html>");
        startPanel = new StartPanel(this);
        add(messagePanel, BorderLayout.NORTH);
        add(startPanel, BorderLayout.CENTER);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }

    public void configRematchPanel() {
        getContentPane().removeAll();
        setMinimumSize(new Dimension(750, 500));
        repaint();
        rematchPanel = new RematchPanel(this);
        add(messagePanel, BorderLayout.CENTER);
        add(rematchPanel, BorderLayout.SOUTH);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }
}
