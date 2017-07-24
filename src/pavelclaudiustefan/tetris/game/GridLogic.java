package pavelclaudiustefan.tetris.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains the game logic
 * The tetris grid is represented by a 2 dimensinal array:
 *      > 0 -> Occupied square
 *      = 0 -> Free square
 */
class GridLogic {

    private int dota;
    private int[][] grid;
    private int[][] previewGrid;
    private boolean gameOver;
    private boolean roundOver;
    private boolean tetrominoControllable;
    private int completedLines;
    private int score;
    private int topScore;
    private ArrayList<Tetromino> tetrominos;
    private Tetromino tetromino;

    GridLogic(int dota) {
        this.dota = dota;
        gameOver = false;
        score = 0;
        completedLines = 0;
        initializeTopScoreFromFile();
        grid = new int[20][10];
        tetrominos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tetrominos.add(new Tetromino());
        }
    }

    int getDota() {
        return dota;
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

    private void fillEmptyLine(int level) {
        // Fills the empty line created when a line is completed
        for (int y = level; y > 0; y--)
            System.arraycopy(grid[y - 1], 0, grid[y], 0, 10);
    }

    void spawnTetromino() {
        roundOver = false;
        tetromino = tetrominos.remove(0);
        tetrominos.add(new Tetromino());
        setPreviewGrid();
        if (isTetrominoValid()) {
            placeTetrominoInGrid();
            tetrominoControllable = true;
        } else {
            roundOver = true;
            gameOver = true;
        }
    }

    private void setPreviewGrid() {
        previewGrid = new int[10][6];
        for (int t = 2; t >= 0; t--) {
            for (int i = 0; i < 4; i++) {
                Tetromino tetr = tetrominos.get(t);
                int x = tetr.getX(i) - 3;
                int y = tetr.getY(i) + (t * 3);
                previewGrid[y][x] = tetr.getType();
            }
        }
    }

    void moveTetrominoLeft() {
        if (tetrominoControllable) {
            removeTetrominoFromGrid();
            tetromino.savePosition();
            tetromino.moveLeft();
            if (isTetrominoValid()) {
                placeTetrominoInGrid();
            } else {
                tetromino.undoPositionChange();
                placeTetrominoInGrid();
            }
        }
    }

    void moveTetrominoRight(){
        if (tetrominoControllable) {
            removeTetrominoFromGrid();
            tetromino.savePosition();
            tetromino.moveRight();
            if (isTetrominoValid()) {
                placeTetrominoInGrid();
            } else {
                tetromino.undoPositionChange();
                placeTetrominoInGrid();
            }
        }
    }

    synchronized void moveTetrominoDown() {
        if (tetrominoControllable) {
            removeTetrominoFromGrid();
            tetromino.savePosition();
            tetromino.moveDown();
            if (isTetrominoValid()) {
                placeTetrominoInGrid();
            } else {
                tetrominoControllable = false;
                roundOver = true;
                tetromino.undoPositionChange();
                placeTetrominoInGrid();
            }
        }
    }

    synchronized void moveTetrominoDownCompletely() {
        while (tetrominoControllable) {
            moveTetrominoDown();
        }
    }

    void rotateTetrominoRight() {
        if (tetromino.getType() != Tetromino.SQUARE && tetrominoControllable) {
            removeTetrominoFromGrid();
            tetromino.savePosition();
            tetromino.rotateRight();
            // Dirty solution :
            // If rotated tetromino is valid place it in the grid
            // If rotated tetromino is not valid, move it right
            // If not valid, move it right
            // If not valid, move it left 3 times
            // If not valid, undo position change
            if (isTetrominoValid()) {
                placeTetrominoInGrid();
            } else {
                if (shouldTetrominoBePushed()) {
                    tetromino.moveRight();
                    if (isTetrominoValid()) {
                        placeTetrominoInGrid();
                    } else {
                        tetromino.moveRight();
                        if (isTetrominoValid()) {
                            placeTetrominoInGrid();
                        } else {
                            tetromino.moveLeft();
                            tetromino.moveLeft();
                            tetromino.moveLeft();
                            if (isTetrominoValid()) {
                                placeTetrominoInGrid();
                            } else {
                                tetrominoControllable = false;
                                tetromino.undoPositionChange();
                                placeTetrominoInGrid();
                            }
                        }
                    }
                } else {
                    tetrominoControllable = false;
                    tetromino.undoPositionChange();
                    placeTetrominoInGrid();
                }
            }
        }
    }

    private boolean shouldTetrominoBePushed() {
        // TODO -  If the tetromino colides with squares under it and not with squares on either side -> don't push it
        return true;
    }

    private void removeTetrominoFromGrid() {
        // Take the tetromino out of the grid for updating position
        for (int i = 0; i < 4; i++) {
            int x = tetromino.getX(i);
            int y = tetromino.getY(i);
            grid[y][x] = 0;
        }
    }

    private void placeTetrominoInGrid() {
        for (int i = 0; i < 4; i++) {
            int x = tetromino.getX(i);
            int y = tetromino.getY(i);
            grid[y][x] = tetromino.getType();
        }
    }

    private boolean isTetrominoValid() {
        // Returns false if it's outside the grid or in collision with other tetromino squares, else returns true
        for (int i = 0; i < Tetromino.MAX_NUMBER_OF_SQUARES; i++) {
            int y = tetromino.getY(i);
            int x = tetromino.getX(i);
            if (y < 0 || y > 19 || x < 0 || x > 9)
                return false;
            if (grid[y][x] != 0) {
                return false;
            }
        }
        return true;
    }

    int getSquareType(int y, int x) {
        return grid[y][x];
    }

    int getPreviewSquareType(int y, int x) {
        return previewGrid[y][x];
    }

    int getScore() {
        return score;
    }

    int getNumberOfCompletedLines() {
        return completedLines;
    }
}
