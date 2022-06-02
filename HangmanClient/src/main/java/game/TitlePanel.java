package game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TitlePanel extends JPanel {
    JLabel title;
    final LoginFrame frame;

    public TitlePanel(LoginFrame frame, String message) {
        this.frame = frame;
        init(message);
    }

    private void init(String message) {
        title = new JLabel(message);
        title.setFont(new Font("Monospaced", Font.BOLD, 50));

        Border padding = BorderFactory.createEmptyBorder(50, 10, 50, 10);
        setBorder(padding);

        add(title);
    }
}
