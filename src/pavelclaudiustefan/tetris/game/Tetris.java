package pavelclaudiustefan.tetris.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tetris extends JFrame implements KeyListener{

    private JPanel panel = null;
    private GridLogic logic;

    public Tetris(int dota) {
        super("Tetris");

        logic = new GridLogic(dota);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 479);
        setMinimumSize(new Dimension(400, 479));
        setLocationRelativeTo(null);
        addKeyListener(this);
        setVisible(true);
    }

    public void start() {
        advanceGameThread();
        drawGuiThread();
    }

    private void advanceGameThread() {
        //Main game flow
        new Thread(() -> {
            while (!logic.isGameOver()) {

                logic.spawnTetromino();

                while (!logic.isRoundOver()) {
                    try {
                        Thread.sleep(1000);
                        logic.moveTetrominoDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                logic.removeCompletedLines();
            }
            // TODO - Proper game over screen
            System.out.println("Game over!");
            removeKeyListener(this);
            logic.setTopScore();
        }).start();
    }

    private void drawGuiThread() {
        new Thread(() -> {
            while (!logic.isGameOver()) {
                redrawGUI();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            redrawGUI();
        }).start();
    }

    private void redrawGUI() {
        if (panel != null)
            remove(panel);
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        add(panel);

        GridPanel grid = new GridPanel(logic, 20, 10);
        //grid.setBackground(Color.CYAN);
        panel.add(grid);

        InfoPanel info = new InfoPanel(logic);
        info.setBackground(Color.CYAN);
        panel.add(info);

        revalidate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            logic.moveTetrominoLeft();
        }

        if (key == KeyEvent.VK_RIGHT) {
            logic.moveTetrominoRight();
        }

        if (key == KeyEvent.VK_DOWN) {
            logic.moveTetrominoDown();
        }

        if (key == KeyEvent.VK_SPACE) {
            logic.moveTetrominoDownCompletely();
        }

        if (key == KeyEvent.VK_UP) {
            logic.rotateTetrominoRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
