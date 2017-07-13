package pavelclaudiustefan.tetris.game;

import java.util.concurrent.ThreadLocalRandom;

class Tetromino {

    private final static int EMPTY = 0;
    private final static int LINE = 1;
    private final static int SQUARE = 2;
    private final static int L = 3;
    private final static int J = 4;
    private final static int S = 5;
    private final static int Z = 6;
    private final static int T = 7;

    //[0, 4] -> Starting point
    //Every tetromino square is relative to the starting point
    private int[][] shape;
    private int[][] position;
    private int type = 0;

    Tetromino() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 8);
        switch (randomNum) {
            case EMPTY:
                // Empty shape
                shape = new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}};
                type = 0;
                break;
            case LINE:
                // Line shape
                shape = new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}};
                type = 1;
                break;
            case SQUARE:
                // Square shape
                shape = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}};
                type = 2;
                break;
            case L:
                // L shape
                shape = new int[][]{{0, 1}, {1, -1}, {1, 0}, {1, 1}};
                type = 3;
                break;
            case J:
                // J shape
                shape = new int[][]{{0, -1}, {1, -1}, {1, 0}, {1, 1}};
                type = 4;
                break;
            case S:
                // S shape
                shape = new int[][]{{0, 0}, {0, 1}, {1, -1}, {1, 0}};
                type = 5;
                break;
            case Z:
                // Z shape
                shape = new int[][]{{0, -1}, {0, 0}, {1, 0}, {1, 1}};
                type = 6;
                break;
            case T:
                // T shape
                shape = new int[][]{{0, 0}, {1, -1}, {1, 0}, {1, 1}};
                type = 7;
                break;
            default:
                System.out.println("Error in Tetromino.java, randomNum out of bounds!");
                break;
        }
        //Set initial position
        position = new int[4][2];
        for (int i = 0; i < 4; i++) {
            position[i][0] = shape[i][0];
            position[i][1] = shape[i][1] + 4;
        }
    }

    /*Tetromino(int typeNumber) {
        switch (typeNumber) {
            case EMPTY:
                // Empty shape
                shape = new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}};
                type = 0;
                break;
            case LINE:
                // Line shape
                shape = new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}};
                type = 1;
                break;
            case SQUARE:
                // Square shape
                shape = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}};
                type = 2;
                break;
            case L:
                // L shape
                shape = new int[][]{{0, 1}, {1, -1}, {1, 0}, {1, 1}};
                type = 3;
                break;
            case J:
                // J shape
                shape = new int[][]{{0, -1}, {1, -1}, {1, 0}, {1, 1}};
                type = 4;
                break;
            case S:
                // S shape
                shape = new int[][]{{0, 0}, {0, 1}, {1, -1}, {1, 0}};
                type = 5;
                break;
            case Z:
                // Z shape
                shape = new int[][]{{0, -1}, {0, 0}, {1, 0}, {1, 1}};
                type = 6;
                break;
            case T:
                // T shape
                shape = new int[][]{{0, 0}, {1, -1}, {1, 0}, {1, 1}};
                type = 7;
                break;
            default:
                System.out.println("Error in Tetromino.java, randomNum out of bounds!");
                break;
        }
        //Set initial position
        position = new int[4][2];
        for (int i = 0; i < 4; i++) {
            position[i][0] = shape[i][0];
            position[i][1] = shape[i][1] + 4;
        }
    }*/

    int getType() {
        return type;
    }

    int getY(int i) {
        return position[i][0];
    }

    int getX(int i) {
        return position[i][1];
    }

    void moveLeft(){
        for (int i = 0; i < 4; i++) {
            position[i][1]--;
        }
    }

    void moveRight(){
        for (int i = 0; i < 4; i++) {
            position[i][1]++;
        }
    }

    void moveDown() {
        for (int i = 0; i < 4; i++) {
            position[i][0]++;
        }
        // TODO - Everytime it moves down, reset a timer (at the end of the timer the tetromino movesDown automatically)
    }

    void rotateRight() {
        switch (type) {
            case EMPTY:
                System.out.println("Nothing to move");
            case LINE:
                // Special case with 4x4
            case SQUARE:
                System.out.println("Can't be moved");
            case L:

            case J:

            case S:

            case Z:

            case T:
                break;
            default:
                System.out.println("ERROR IN Tetromino.java at toString()");
                break;
        }
    }

    void rotate() {
        System.out.println();
    }

    @Override
    public String toString() {
        switch (type) {
            case EMPTY:
                return "Empty piece";
            case LINE:
                return "Line piece";
            case SQUARE:
                return "Square piece";
            case L:
                return "L piece";
            case J:
                return "J piece";
            case S:
                return "S piece";
            case Z:
                return "Z piece";
            case T:
                return "T piece";
            default:
                return "ERROR IN Tetromino.java at toString()";
        }
    }

}
