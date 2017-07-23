package pavelclaudiustefan.tetris.game;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

class GridPanel extends JPanel{

    private GridLogic logic;
    private int height;
    private int width;
    private BufferedImage borderSprite;
    private ArrayList<BufferedImage> tetrominoSprites;

    GridPanel(GridLogic logic, int height, int width) {
        setLayout(null);
        this.logic = logic;
        this.height = height;
        this.width = width;

        initiateSprites();
        if (height == 20 && width == 10) {
            // Default tetris grid size
            if (borderSprite != null) {
                drawTopBorder(0);
                drawBottomBorder(0);
                drawLeftBorder(0);
                drawRightBorder(0);
                drawGridArea();
            }
        } else {
            drawTopBorder(60);
            drawBottomBorder(60);
            drawLeftBorder(60);
            drawRightBorder(60);
            drawPreviewGridArea(80);
        }
    }

    private void initiateSprites() {
        tetrominoSprites = Tetromino.getSprites(logic.getDota());
        borderSprite = tetrominoSprites.get(8);
    }

    private void drawTopBorder(int xOffset) {
        for (int i = 0;  i < width + 2; i++) {
            int x = xOffset + 20 * i;
            int y = 0;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }
    }

    private void drawBottomBorder(int xOffset) {
        for (int i = 0;  i < width + 2; i++) {
            int x = xOffset + 20 * i;
            int y = height * 20 + 20;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }
    }

    private void drawLeftBorder(int xOffset) {
        for (int i = 1;  i <= height; i++) {
            int x = xOffset;
            int y = i * 20;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }
    }

    private void drawRightBorder(int xOffset) {
        for (int i = 1;  i <= height; i++) {
            int x = xOffset + width * 20 + 20;
            int y = 20 * i;
            JLabel borderLabel = new JLabel(new ImageIcon(borderSprite));
            borderLabel.setBounds(x, y, 20, 20);
            add(borderLabel);
        }
    }

    private void drawGridArea() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int x = 20 + j * 20;
                int y = 20 + i * 20;
                JLabel cellLabel = new JLabel(new ImageIcon(tetrominoSprites.get(logic.getSquareType(i, j))));
                cellLabel.setBounds(x, y, 20, 20);
                add(cellLabel);
            }
        }
    }

    private void drawPreviewGridArea(int xOffset) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int x = xOffset + j * 20;
                int y = 20 + i * 20;
                JLabel borderLabel = new JLabel(new ImageIcon(tetrominoSprites.get(logic.getPreviewSquareType(i, j))));
                borderLabel.setBounds(x, y, 20, 20);
                add(borderLabel);
            }
        }
    }

}
