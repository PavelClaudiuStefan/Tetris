package pavelclaudiustefan.tetris.game;

import javax.swing.*;
import java.awt.*;

class GameOverFrame extends JFrame {

    GameOverFrame(GridLogic logic) {
        super("Tetris");
        setLayout(new GridLayout(1, 1));
        setSize(320, 180);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        add(panel);
        String text = "<html><div style='text-align: center;'>Game over!<br><br>" + "Final score : " + logic.getScore() + "<br>Number of completed lines : " + logic.getNumberOfCompletedLines() + "</html>";
        JLabel gameOverText = new JLabel(text, SwingConstants.CENTER);
        gameOverText.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        panel.add(gameOverText);
    }

    void showFrame() {
        setVisible(true);
    }

}
