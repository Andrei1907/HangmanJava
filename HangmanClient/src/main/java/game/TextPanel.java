package game;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {
    JLabel label;
    JTextField text;
    final LoginFrame frame;

    public TextPanel(LoginFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new JLabel("Enter username:");
        text = new JTextField(15);
        Font font = new Font("SansSerif", Font.PLAIN, 20);
        label.setFont(font);
        text.setFont(font);

        add(label);
        add(text);
    }

    public String getUsername() {
        return text.getText();
    }

    public void resetUsername() {
        text.setText("");
    }
}
