package game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RematchPanel extends JPanel {
    JButton yesBtn;
    JButton noBtn;
    JButton exportBtn;
    final LoginFrame frame;

    public RematchPanel(LoginFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        yesBtn = new JButton("Yes");
        noBtn = new JButton("No");
        exportBtn = new JButton("Export");

        Border padding = BorderFactory.createEmptyBorder(10, 10, 70, 10);
        setBorder(padding);

        Dimension dimension = new Dimension(140, 60);
        Font font = new Font("SansSerif", Font.BOLD, 20);
        yesBtn.setPreferredSize(dimension);
        yesBtn.setFont(font);
        noBtn.setPreferredSize(dimension);
        noBtn.setFont(font);
        exportBtn.setPreferredSize(dimension);
        exportBtn.setFont(font);

        add(yesBtn);
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(noBtn);
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(exportBtn);

        yesBtn.addActionListener(e -> action(yesBtn));
        noBtn.addActionListener(e -> action(noBtn));
        exportBtn.addActionListener(e -> action(exportBtn));
    }

    private void action(JButton button) {
        MyHandler.run1(button.getText());
        if(button.getText().equals("No")) {
            frame.setVisible(false);
            frame.dispose();
            System.exit(0);
        } else if(button.getText().equals("Yes")) {
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
}
