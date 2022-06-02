package game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class StartPanel extends JPanel {
    JButton startBtn;
    final LoginFrame frame;

    public StartPanel(LoginFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        startBtn = new JButton("Start");
        Dimension dimension = new Dimension(140, 60);
        Font font = new Font("SansSerif", Font.BOLD, 20);
        startBtn.setPreferredSize(dimension);
        startBtn.setFont(font);

        Border padding = BorderFactory.createEmptyBorder(10, 10, 50, 10);
        setBorder(padding);

        add(startBtn);

        startBtn.addActionListener(e -> startGame());
    }

    private void startGame() {
        MyHandler.run1("Start");
        frame.setMinimumSize(new Dimension(1500, 700));
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.gamePanel = new GamePanel(frame);
        frame.add(frame.gamePanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
    }
}
