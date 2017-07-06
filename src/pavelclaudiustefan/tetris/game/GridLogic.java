package pavelclaudiustefan.tetris.game;

/**
 * This class contains the game logic
 * The tetris grid is represented by a matrix:
 *      1 -> Occupied square
 *      0 -> Free square
 */
public class GridLogic {

    private int[][] grid;
    private boolean gameOver;
    private Tetromino tetromino;

    GridLogic() {
        gameOver = false;
        grid = new int[20][10];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void spawnTetromino() {
        //TODO - Get the tetromino from a queue (visible in the infoPanel) instead of spawning a random one now
        tetromino = new Tetromino();
        //Check if the tetromino can be placed in the grid, else gameOver = true
        //TODO - Temporar
        for (int i = 0; i < 4; i++) {
            int y = tetromino.getY(i);
            int x = tetromino.getX(i);
            System.out.println(y+" "+x);
            grid[y][x] = tetromino.getType();
        }
        gameOver = true;
    }

    int getSquareType(int x, int y) {
        return grid[x][y];
    }

    void reset() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = 0;
            }
        }
    }









    String getVisualGrid() {
        String str = "<html>";
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == 0)
                    str += "0 ";
                else
                    str += "X ";
            }
            str += "<br>";
        }
        str += "</html>";
        return str;
    }

}
