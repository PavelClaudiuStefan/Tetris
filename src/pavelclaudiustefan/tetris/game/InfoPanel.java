package pavelclaudiustefan.tetris.game;

import javax.swing.*;
import java.awt.*;

class InfoPanel extends JPanel{

    InfoPanel(GridLogic logic) {
        setLayout(new GridLayout(0, 1));

        /*JLabel nextPieceLabel = new JLabel();
        nextPieceLabel.setText("Next piece");
        nextPieceLabel.setHorizontalAlignment(JLabel.CENTER);
        add(nextPieceLabel);

        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Score: 999");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        add(scoreLabel);*/



        JLabel temporar = new JLabel();
        temporar.setText(logic.getVisualGrid());
        temporar.setFont(new Font("Courier", Font.PLAIN, 14));
        temporar.setHorizontalAlignment(JLabel.CENTER);
        add(temporar);
    }

}
