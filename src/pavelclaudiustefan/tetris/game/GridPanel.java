/**
 * 0 -> Empty
 * 1 -> line
 * 2 -> square
 * 3 -> L
 * 4 -> J
 * 5 -> S
 * 6 -> Z
 * 7 -> T
 */

package pavelclaudiustefan.tetris.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class GridPanel extends JPanel{

    GridPanel(GridLogic logic, int dota) {
        setLayout(null);

        String path;
        if (dota == 1) {
            path = "src/pavelclaudiustefan/tetris/sprites/dota/";
        } else if (dota == 2) {
            path = "src/pavelclaudiustefan/tetris/sprites/dota_v2/";
        } else {
            path = "src/pavelclaudiustefan/tetris/sprites/default/";
        }

        BufferedImage borderSprite = null;
        ArrayList<BufferedImage> tetrominoSprite = new ArrayList<>();
        try {
            borderSprite = ImageIO.read(new File("src/pavelclaudiustefan/tetris/sprites/border.png"));
            tetrominoSprite.add(ImageIO.read(new File(path + "empty.png")));
            tetrominoSprite.add(ImageIO.read(new File(path + "line.png")));
            tetrominoSprite.add(ImageIO.read(new File(path + "square.png")));
            tetrominoSprite.add(ImageIO.read(new File(path + "L.png")));
            tetrominoSprite.add(ImageIO.read(new File(path + "J.png")));
            tetrominoSprite.add(ImageIO.read(new File(path + "S.png")));
            tetrominoSprite.add(ImageIO.read(new File(path + "Z.png")));
            tetrominoSprite.add(ImageIO.read(new File(path + "T.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Top border
        for (int i = 0;  i < 12; i++) {
            int x = 20 * i;
            int y = 0;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }

        //Bottom border
        for (int i = 0;  i < 12; i++) {
            int x = 20 * i;
            int y = 420;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }

        //Left border
        for (int i = 1;  i <= 20; i++) {
            int x = 0;
            int y = i * 20;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }

        //Right border
        for (int i = 1;  i <= 20; i++) {
            int x = 220;
            int y = 20 * i;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                int x = 20 + j * 20;
                int y = 20 + i * 20;
                JLabel borderLabel = new JLabel(new ImageIcon(tetrominoSprite.get(logic.getSquareType(i, j))));
                borderLabel.setBounds(x, y, 20, 20);
                add(borderLabel);
            }
        }
    }


}
