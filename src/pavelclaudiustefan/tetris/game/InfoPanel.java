package pavelclaudiustefan.tetris.game;

import javax.swing.*;
import java.awt.*;

class InfoPanel extends JPanel{

    private GridLogic logic;

    InfoPanel(GridLogic logic) {
        setLayout(new GridLayout(0, 1));
        this.logic = logic;

        drawNextTetrominoesPanel();

        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("<html>Score: " + logic.getScore() +
                           "<br>Top score: " + logic.getTopScore() +
                           "<br>Completed lines: " + logic.getNumberOfCompletedLines() + "</html>");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(scoreLabel);
    }

    private void drawNextTetrominoesPanel() {
        JPanel nextTetrominoesPanel = new GridPanel(logic, 8, 4);
        nextTetrominoesPanel.setBackground(Color.cyan);
        add(nextTetrominoesPanel);
    }

}
