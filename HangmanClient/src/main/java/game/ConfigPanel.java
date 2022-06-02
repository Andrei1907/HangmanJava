package game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ConfigPanel extends JPanel {
    JButton loginBtn;
    JButton registerBtn;
    final LoginFrame frame;

    public ConfigPanel(LoginFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");

        Border padding = BorderFactory.createEmptyBorder(10, 10, 70, 10);
        setBorder(padding);

        Dimension dimension = new Dimension(140, 60);
        Font font = new Font("SansSerif", Font.BOLD, 20);
        loginBtn.setPreferredSize(dimension);
        loginBtn.setFont(font);
        registerBtn.setPreferredSize(dimension);
        registerBtn.setFont(font);

        add(loginBtn);
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(registerBtn);

        loginBtn.addActionListener(e -> action(loginBtn, "This username doesn't exist. Please register first!"));
        registerBtn.addActionListener(e -> action(registerBtn, "This username already exists. Try a new one!"));
    }

    private void action(JButton button, String dialogueMessage) {
        System.out.println(frame.textPanel.getUsername());

        String username = button.getText() + " " + frame.textPanel.getUsername();
        MyHandler.run1(username);

        String verification = MyHandler.run2();
        while (verification.equals("ErrorUser")) {
            JOptionPane.showMessageDialog(this,
                    dialogueMessage,
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        frame.configStartPanel();
    }
}
