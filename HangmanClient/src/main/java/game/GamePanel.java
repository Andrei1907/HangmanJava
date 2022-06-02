package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel {
    JLabel word;
    JLabel picture;
    List<JButton> letters;
    final LoginFrame frame;

    public GamePanel(LoginFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //display word
        String receivedWord = MyHandler.run2();
        word = new JLabel(receivedWord);

        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.5);
        Font font = new Font("Monospaced", Font.BOLD, 30);
        word.setFont(font.deriveFont(attributes));

        c.gridx = 0;
        c.gridy = 0;
        add(word, c);

        //display picture
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("C:\\Users\\User\\IdeaProjects\\HangmanClient\\pictures\\hangman.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        picture = new JLabel(new ImageIcon(myPicture));
        c.gridx = 14;
        c.gridy = 0;
        add(picture, c);

        //display buttons
        c.insets = new Insets(20, 3, 3, 6);
        letters = new ArrayList<>(26);
        Dimension dimension = new Dimension(100, 100);
        int k = 0;
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            JButton aux = new JButton(Character.toString(letter));
            aux.setMinimumSize(dimension);
            aux.setFont(new Font("SansSerif", Font.PLAIN, 20));
            letters.add(k, aux);
            k++;
        }

        c.gridy = 1;
        for(int i = 0; i < 13; i++) {
            c.gridx = i+1;
            add(letters.get(i), c);
        }
        c.insets = new Insets(3, 3, 3, 6);
        c.gridy = 2;
        for(int i = 13; i < 26; i++) {
            c.gridx = i-12;
            add(letters.get(i), c);
        }

        for(var letter : letters)
        {
            letter.addActionListener(e -> action(letter));
        }
    }

    private void action(JButton button) {
        System.out.println(button.getText());
        button.setBackground(Color.GRAY);

        MyHandler.run1(button.getText());
        String response = MyHandler.run2();

        List<String> result = Arrays.stream(response.split("-")).toList();
        System.out.println("Am primit tripletul " + result);

        switch (result.get(0)) {
            case "Won" -> {
                frame.messagePanel = new MessagePanel(frame, "<html>Congratulations! You guessed the word: "
                        + result.get(3) + "!<br>Your current score is " + result.get(1) + " and your highscore is " + result.get(2)
                        + ".<br>Do you want to continue or exit the game?</html>");
                frame.configRematchPanel();
            }
            case "Lost" -> {
                frame.messagePanel = new MessagePanel(frame, "<html>Uh oh! You didn't guess the word: "
                        + result.get(3) + "!<br>Your current score is " + result.get(1) + " and your highscore is " + result.get(2)
                        + ".<br>Do you want to try again or exit the game?</html>");
                frame.configRematchPanel();
            }
            case "Right" -> word.setText(result.get(2));
            case "Guessed" -> JOptionPane.showMessageDialog(this,
                        "You have already guessed this letter",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
            default -> {
                ImageIcon myPicture = new ImageIcon("C:\\Users\\User\\IdeaProjects\\HangmanClient\\pictures\\hangman" + result.get(1) + ".png");
                picture.setIcon(myPicture);
                frame.repaint();
            }
        }
    }
}
