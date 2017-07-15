package pavelclaudiustefan.tetris.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * This class contains the game logic
 * The tetris grid is represented by a matrix:
 *      > 0 -> Occupied square
 *      = 0 -> Free square
 */
class GridLogic {

    private int[][] grid;
    private boolean gameOver;
    private boolean roundOver;
    private boolean tetrominoControllable;
    private int completedLines;
    private int score;
    private int topScore;
    private Queue<Tetromino> tetrominos;
    private Tetromino tetromino;

    GridLogic() {
        gameOver = false;
        score = 0;
        completedLines = 0;
        initializeTopScoreFromFile();
        grid = new int[20][10];
        tetrominos = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            tetrominos.add(new Tetromino());
        }
    }

    private void initializeTopScoreFromFile() {
        // TODO - A special class for player rankings
        try {
            Scanner in = new Scanner(new File("src/pavelclaudiustefan/tetris/players_info.txt"));
            topScore = in.nextInt();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    void setTopScore() {
        // TODO - A special class for player rankings
        if (score > topScore) {
            topScore = score;
            try {
                PrintWriter out = new PrintWriter(new File("src/pavelclaudiustefan/tetris/players_info.txt"));
                out.print(topScore);
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    int getTopScore() {
        return topScore;
    }

    boolean isGameOver() {
        return gameOver;
    }

    boolean isRoundOver() {
        return roundOver;
    }

    void removeCompletedLines() {
        int roundCompletedLines = 0;
        int[] scoreForNLines = new int[]{0, 10, 40, 60, 100};
        for (int y = 0; y < 20; y++) {
            boolean lineCompleted = true;
            for (int x = 0; x < 10; x++) {
                if (grid[y][x] == 0)
                    lineCompleted = false;
            }
            if (lineCompleted) {
                roundCompletedLines++;
                for (int x = 0; x < 10; x++) {
                    grid[y][x] =0;
                }
                fillEmptyLine(y);
            }
        }
        completedLines += roundCompletedLines;
        score += scoreForNLines[roundCompletedLines];
    }

    // Fills the empty line created when a line is completed
    private void fillEmptyLine(int level) {
        for (int y = level; y > 0; y--)
            System.arraycopy(grid[y - 1], 0, grid[y], 0, 10);
    }

    void spawnTetromino() {
        roundOver = false;
        tetromino = tetrominos.remove();
        tetrominos.add(new Tetromino());
        if (canTetrominoSpawn()) {
            for (int i = 0; i < 4; i++) {
                int y = tetromino.getY(i);
                int x = tetromino.getX(i);
                grid[y][x] = tetromino.getType();
            }
            tetrominoControllable = true;
        } else {
            gameOver = true;
        }
    }

    void moveTetrominoLeft() {
        if (canTetrominoMove(-1) && tetrominoControllable) {
            moveTetromino(-1);
            tetromino.moveLeft();
        }
    }

    void moveTetrominoRight(){
        if (canTetrominoMove(1) && tetrominoControllable) {
            moveTetromino(1);
            tetromino.moveRight();
        }
    }

    void moveTetrominoDown() {
        if (canTetrominoMove(0)) {
            moveTetromino(0);
            tetromino.moveDown();
        }
    }

    synchronized void moveTetrominoDownCompletely() {
        while (canTetrominoMove(0)) {
            moveTetromino(0);
            tetromino.moveDown();
        }
        tetrominoControllable = false;
        roundOver = true;
    }

    private void moveTetromino(int direction) {
    // direction = -1 -> moveTetrominoLeft
    // direction = 1 -> moveTetrominoRight
    // direction = 0 -> moveTetrominoDown
        for (int i = 0; i < 4; i++) {
            int x = tetromino.getX(i);
            int y = tetromino.getY(i);
            grid[y][x] = 0;
        }
        if (direction == 0) {
            for (int i = 0; i < 4; i++) {
                int x = tetromino.getX(i);
                int y = tetromino.getY(i);
                grid[y + 1][x] = tetromino.getType();
            }
        } else {
            for (int i = 0; i < 4; i++) {
                int x = tetromino.getX(i);
                int y = tetromino.getY(i);
                grid[y][x + direction] = tetromino.getType();
            }
        }

    }

    private boolean canTetrominoMove(int direction) {
    // direction = -1 -> moveTetrominoLeft
    // direction = 1 -> moveTetrominoRight
    // direction = 0 -> down
        if (direction < -1 && direction > 1) {
            System.out.println("Something is wrong in GridLogic.java with the variable direction (out of bounds)");
            return false;
        }

        if (direction == 0) {
            // This checks if the tetromino can moveTetromino down
            // First, check if the tetromino wants to go out of bounds
            for (int i = 0; i < 4; i++) {
                int y = tetromino.getY(i) + 1;
                if (y > 19) {
                    tetrominoControllable = false;
                    roundOver = true;
                    return false;
                }
            }
            // Second, check if the tetromino wants to occupy a square already occupied
            // If the tetromino wants to occupy squares that are not empty or occupied by itself return false
            for (int i = 0; i < 4; i++) {
                int x = tetromino.getX(i);
                int y = tetromino.getY(i) + 1;
                if (!canSquareAdvance(x, y, i)) {
                    tetrominoControllable = false;
                    roundOver = true;
                    return false;
                }
            }

        } else {
            // This checks if the tetromino can moveTetromino left (-1), or right (1)
            // First, check if the tetromino wants to go out of bounds
            for (int i = 0; i < 4; i++) {
                int x = tetromino.getX(i) + direction;
                if (x < 0 || x > 9)
                    return false;
            }
            // Second, check if the tetromino wants to occupy a square already occupied
            for (int i = 0; i < 4; i++) {
                int x = tetromino.getX(i) + direction;
                int y = tetromino.getY(i);
                if (!canSquareAdvance(x, y, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    void rotateTetrominoRight() {
        if (tetromino.getType() != Tetromino.SQUARE) {
            // Take the tetromino out of the grid for updating position
            for (int i = 0; i < 4; i++) {
                int x = tetromino.getX(i);
                int y = tetromino.getY(i);
                grid[y][x] = 0;
            }
            tetromino.rotateRight();
            // Place it back in the grid after updating position
            for (int i = 0; i < 4; i++) {
                int x = tetromino.getX(i);
                int y = tetromino.getY(i);
                grid[y][x] = tetromino.getType();
            }
        }
    }
    
    private boolean canTetrominoSpawn() {
        for (int i = 0; i < 4; i++) {
            int y = tetromino.getY(i);
            int x = tetromino.getX(i);
            if (grid[y][x] != 0)
                return false;
        }
        return true;
    }

    private boolean canSquareAdvance(int x, int y, int i) {
        if (grid[y][x] != 0) {
            boolean squareCanAdvance = false;
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    int x2 = tetromino.getX(j);
                    int y2 = tetromino.getY(j);
                    if (y == y2 && x == x2) {
                        squareCanAdvance = true;
                    }
                }
            }
            if (!squareCanAdvance) {
                return false;
            }
        }
        return true;
    }

    int getSquareType(int x, int y) {
        return grid[x][y];
    }

    int getScore() {
        return score;
    }

    int getNumberOfCompletedLines() {
        return completedLines;
    }

    Queue getTetrominos() {
        return tetrominos;
    }

}
