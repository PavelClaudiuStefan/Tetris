package pavelclaudiustefan.tetris.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tetris extends JFrame implements KeyListener{

    private JPanel panel = null;
    private GridPanel grid;
    private InfoPanel info;
    private GridLogic logic;
    private int dota;

    public Tetris(int dota) {
        super("Tetris");

        this.dota = dota;
        if (dota == 0) {
            // set tetromino sprites folder -> default
            System.out.println("Default");
        } else if (dota == 1) {
            // set tetromino sprites folder -> dota
            System.out.println("Dota");
        } else if (dota == 2) {
            // set tetromino sprites folder -> dota_v2
            System.out.println("Dota v2");
        } else {
            System.out.println("dota value out of bounds");
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 479);
        setMinimumSize(new Dimension(400, 479));
        setLocationRelativeTo(null);
        addKeyListener(this);
        setVisible(true);
    }

    public void start() {
        logic = new GridLogic();
        redrawGridAndInfo();

        //Main game flow
        new Thread(() -> {
            while (!logic.isGameOver()) {
                logic.spawnTetromino();
                redrawGridAndInfo();
                while (!logic.isRoundOver()) {
                    try {
                        Thread.sleep(1000);
                        logic.moveTetrominoDown();
                        redrawGridAndInfo();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // TODO - If there are any completed lines remove them and add points
                logic.removeCompletedLines();
                redrawGridAndInfo();
            }
            // TODO - Proper game over screen
            System.out.println("Game over!");
            removeKeyListener(this);
        }).start();
    }

    private void redrawGridAndInfo() {
        if (panel != null)
            remove(panel);
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        add(panel);

        grid = new GridPanel(logic, dota);
        grid.setBackground(Color.CYAN);
        panel.add(grid);

        info = new InfoPanel(logic);
        info.setBackground(Color.green);
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
            redrawGridAndInfo();
        }

        if (key == KeyEvent.VK_RIGHT) {
            logic.moveTetrominoRight();
            redrawGridAndInfo();
        }

        if (key == KeyEvent.VK_DOWN) {
            logic.moveTetrominoDown();
            redrawGridAndInfo();
        }

        if (key == KeyEvent.VK_SPACE) {
            while (!logic.isRoundOver()) {
                logic.moveTetrominoDown();
            }
            redrawGridAndInfo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
