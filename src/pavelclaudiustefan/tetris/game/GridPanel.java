package pavelclaudiustefan.tetris.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class GridPanel extends JPanel{

    private int dota;
    private GridLogic logic;
    private String path;
    private BufferedImage borderSprite;
    private ArrayList<BufferedImage> tetrominoSprites;

    GridPanel(GridLogic logic, int dota) {
        setLayout(null);

        this.dota = dota;
        this.logic = logic;

        initiateSprites();
        if (borderSprite != null) {
            drawTopBorder();
            drawBottomBorder();
            drawLeftBorder();
            drawRightBorder();
        }
        drawGridArea();
    }
    private void initiateSprites() {
        initiateTextureType();

        borderSprite = null;
        tetrominoSprites = new ArrayList<>();
        try {
            borderSprite = ImageIO.read(new File("src/pavelclaudiustefan/tetris/sprites/border.png"));
            tetrominoSprites.add(ImageIO.read(new File(path + "empty.png")));
            tetrominoSprites.add(ImageIO.read(new File(path + "line.png")));
            tetrominoSprites.add(ImageIO.read(new File(path + "square.png")));
            tetrominoSprites.add(ImageIO.read(new File(path + "L.png")));
            tetrominoSprites.add(ImageIO.read(new File(path + "J.png")));
            tetrominoSprites.add(ImageIO.read(new File(path + "S.png")));
            tetrominoSprites.add(ImageIO.read(new File(path + "Z.png")));
            tetrominoSprites.add(ImageIO.read(new File(path + "T.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initiateTextureType() {
        switch (dota) {
            case 1:
                path = "src/pavelclaudiustefan/tetris/sprites/dota/";
                break;
            case 2:
                path = "src/pavelclaudiustefan/tetris/sprites/dota_v2/";
                break;
            case 3:
                path = "src/pavelclaudiustefan/tetris/sprites/dota_v3/";
                break;
            case 9:
                path = "src/pavelclaudiustefan/tetris/sprites/hitler/";
                break;
            default:
                path = "src/pavelclaudiustefan/tetris/sprites/default/";
                break;
        }
    }

    private void drawTopBorder() {
        for (int i = 0;  i < 12; i++) {
            int x = 20 * i;
            int y = 0;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }
    }

    private void drawBottomBorder() {
        for (int i = 0;  i < 12; i++) {
            int x = 20 * i;
            int y = 420;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }
    }

    private void drawLeftBorder() {
        for (int i = 1;  i <= 20; i++) {
            int x = 0;
            int y = i * 20;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }
    }

    private void drawRightBorder() {
        for (int i = 1;  i <= 20; i++) {
            int x = 220;
            int y = 20 * i;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }
    }

    private void drawGridArea() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                int x = 20 + j * 20;
                int y = 20 + i * 20;
                JLabel borderLabel = new JLabel(new ImageIcon(tetrominoSprites.get(logic.getSquareType(i, j))));
                borderLabel.setBounds(x, y, 20, 20);
                add(borderLabel);
            }
        }
    }

}
