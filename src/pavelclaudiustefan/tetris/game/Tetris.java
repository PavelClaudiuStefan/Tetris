package pavelclaudiustefan.tetris.game;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {

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
        setVisible(true);
    }

    public void start() {
        System.out.println("Game on!");
        logic = new GridLogic();
        redrawGridAndInfo();

        //Main game flow
        while (!logic.isGameOver()) {
            logic.spawnTetromino();
            redrawGridAndInfo();
        }
        //TODO - Temporar
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logic.reset();
            logic.spawnTetromino();
            redrawGridAndInfo();
        }
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

}
