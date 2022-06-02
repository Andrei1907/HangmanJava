package game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MessagePanel extends JPanel {
    JLabel title;
    JLabel picture;
    final LoginFrame frame;

    public MessagePanel(LoginFrame frame, String message) {
        this.frame = frame;
        init(message);
    }

    private void init(String message) {
        title = new JLabel(message);
        Font font = new Font("SansSerif", Font.PLAIN, 20);
        title.setFont(font);

        ImageIcon myPicture = new ImageIcon("C:\\Users\\User\\IdeaProjects\\HangmanClient\\pictures\\hangman_icon.png");
        picture = new JLabel(myPicture);
        Border padding = BorderFactory.createEmptyBorder(50, 10, 50, 10);
        setBorder(padding);

        add(picture);
        add(title);
    }
}
