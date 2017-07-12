package pavelclaudiustefan.tetris.game;

import javax.swing.*;
import java.awt.*;

class InfoPanel extends JPanel{

    InfoPanel(GridLogic logic) {
        setLayout(new GridLayout(0, 1));

        JLabel nextPieceLabel = new JLabel();
        StringBuilder pieces = new StringBuilder("<html>Next pieces:<br>");
        for (Object tetromino : logic.getTetrominos()) {
            pieces.append("<br>").append(tetromino);
        }
        nextPieceLabel.setText(pieces.toString());
        nextPieceLabel.setHorizontalAlignment(JLabel.CENTER);
        add(nextPieceLabel);

        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Score: " + logic.getScore());
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        add(scoreLabel);

    }

}
