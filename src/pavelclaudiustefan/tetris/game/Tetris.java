package pavelclaudiustefan.tetris.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tetris extends JFrame implements KeyListener{

    private JPanel panel = null;
    private GridLogic logic;
    private int dota;

    public Tetris(int dota) {
        super("Tetris");

        this.dota = dota;

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

        // TODO - Make game flow and gui refreshing using a timer (checking and refreshing 60 times a second or so)
        //Main game flow
        new Thread(() -> {
            while (!logic.isGameOver()) {
                logic.spawnTetromino();
                redrawGridAndInfo();
                while (!logic.isRoundOver()) {
                    try {
                        Thread.sleep(1000);
                        //logic.moveTetrominoDown();
                        redrawGridAndInfo();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                logic.removeCompletedLines();
                redrawGridAndInfo();
            }
            // TODO - Proper game over screen
            System.out.println("Game over!");
            removeKeyListener(this);
            logic.setTopScore();
            redrawGridAndInfo();
        }).start();

        new Thread(() -> {
            while (!logic.isGameOver()) {
                tempRefreshGUI();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            tempRefreshGUI();
        }).start();

    }

    synchronized private void redrawGridAndInfo() {
        // TODO - Better way to refresh GUI
        /*if (panel != null)
            remove(panel);
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        add(panel);

        GridPanel grid = new GridPanel(logic, dota);
        grid.setBackground(Color.CYAN);
        panel.add(grid);

        InfoPanel info = new InfoPanel(logic);
        info.setBackground(Color.green);
        panel.add(info);

        revalidate();
        repaint();*/
    }

    private void tempRefreshGUI() {
        if (panel != null)
            remove(panel);
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        add(panel);

        GridPanel grid = new GridPanel(logic, dota);
        grid.setBackground(Color.CYAN);
        panel.add(grid);

        InfoPanel info = new InfoPanel(logic);
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
            logic.moveTetrominoDownCompletely();
            redrawGridAndInfo();
        }

        if (key == KeyEvent.VK_UP) {
            logic.rotateTetrominoRight();
            redrawGridAndInfo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
