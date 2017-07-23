package pavelclaudiustefan.tetris.game;

import java.util.concurrent.ThreadLocalRandom;

class Tetromino {

    final static int MAX_NUMBER_OF_SQUARES = 4;
    private final static int EMPTY = 0;
    private final static int LINE = 1;
    final static int SQUARE = 2;
    private final static int L = 3;
    private final static int J = 4;
    private final static int S = 5;
    private final static int Z = 6;
    private final static int T = 7;

    //[0, 4] -> Starting point
    //Every tetromino square is relative to the starting point
    private int[][] shape;
     int[][] position;
     int[][] backupPosition;
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
                shape = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {-1, 2}};
                type = 1;
                break;
            case SQUARE:
                // Square shape
                shape = new int[][]{{-1, 0}, {-1, 1}, {0, 0}, {0, 1}};
                type = 2;
                break;
            case L:
                // L shape
                shape = new int[][]{{0, 0}, {-1, 1}, {0, -1}, {0, 1}};
                type = 3;
                break;
            case J:
                // J shape
                shape = new int[][]{{0, 0}, {-1, -1}, {0, -1}, {0, 1}};
                type = 4;
                break;
            case S:
                // S shape
                shape = new int[][]{{0, 0}, {-1, 0}, {-1, 1}, {0, -1}};
                type = 5;
                break;
            case Z:
                // Z shape
                shape = new int[][]{{0, 0}, {-1, -1}, {-1, 0}, {0, 1}};
                type = 6;
                break;
            case T:
                // T shape
                shape = new int[][]{{0, 0}, {-1, 0}, {0, -1}, {0, 1}};
                type = 7;
                break;
            default:
                System.out.println("Error in Tetromino.java, randomNum out of bounds!");
                break;
        }
        //Set initial position
        position = new int[4][2];
        int yOffset = 1;
        int xOffset = 4;
        for (int i = 0; i < 4; i++) {
            position[i][0] = shape[i][0] + yOffset;
            position[i][1] = shape[i][1] + xOffset;
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
                 shape = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {-1, 2}};
                 type = 1;
                 break;
             case SQUARE:
                 // Square shape
                 shape = new int[][]{{-1, 0}, {-1, 1}, {0, 0}, {0, 1}};
                 type = 2;
                 break;
             case L:
                 // L shape
                 shape = new int[][]{{0, 0}, {-1, 1}, {0, -1}, {0, 1}};
                 type = 3;
                 break;
             case J:
                 // J shape
                 shape = new int[][]{{0, 0}, {-1, -1}, {0, -1}, {0, 1}};
                 type = 4;
                 break;
             case S:
                 // S shape
                 shape = new int[][]{{0, 0}, {-1, 0}, {-1, 1}, {0, -1}};
                 type = 5;
                 break;
             case Z:
                 // Z shape
                 shape = new int[][]{{0, 0}, {-1, -1}, {-1, 0}, {0, 1}};
                 type = 6;
                 break;
             case T:
                 // T shape
                 shape = new int[][]{{0, 0}, {-1, 0}, {0, -1}, {0, 1}};
                 type = 7;
                 break;
             default:
                 System.out.println("Error in Tetromino.java, randomNum out of bounds!");
                 break;
         }
         //Set initial position
         position = new int[4][2];
         int yOffset = 1;
         int xOffset = 4;
         for (int i = 0; i < 4; i++) {
             position[i][0] = shape[i][0] + yOffset;
             position[i][1] = shape[i][1] + xOffset;
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

    void savePosition() {
        backupPosition = new int[4][2];
        for (int i = 0; i < MAX_NUMBER_OF_SQUARES; i++) {
            backupPosition[i][0] = position[i][0];
            backupPosition[i][1] = position[i][1];
        }
    }

    void undoPositionChange() {
        for (int i = 0; i < MAX_NUMBER_OF_SQUARES; i++) {
            position[i][0] = backupPosition[i][0];
            position[i][1] = backupPosition[i][1];
        }
    }

    void rotateRight() {
        if (type != SQUARE) {
            rotate();
        }
    }

    private void rotate() {
        savePosition();

        if (type == LINE) {
            // TODO - Fix line rotation (First rotation is one unit to the left than what it should be)
            int yOffset = position[0][0] - shape[0][0];
            int xOffset = position[0][1] - shape[0][1];
            // lineUnit is -1, or 1 and it's used to know how to rotate the line
            int lineUnit = -shape[0][1];
            for (int squareOrder = 0; squareOrder < 4; squareOrder++) {
                if (lineUnit > 0) {
                    int temp = shape[squareOrder][0];
                    shape[squareOrder][0] = shape[squareOrder][1];
                    shape[squareOrder][1] = temp + lineUnit;
                } else {
                    int temp = shape[squareOrder][0];
                    shape[squareOrder][0] = shape[squareOrder][1] + lineUnit;
                    shape[squareOrder][1] = temp;
                }

                position[squareOrder][0] = shape[squareOrder][0] + yOffset;
                position[squareOrder][1] = shape[squareOrder][1] + xOffset;
            }
        } else {
            for (int squareOrder = 1; squareOrder < 4; squareOrder++) {
                int temp = shape[squareOrder][0];
                shape[squareOrder][0] = shape[squareOrder][1];
                shape[squareOrder][1] = -temp;

                position[squareOrder][0] = position[0][0] + shape[squareOrder][0];
                position[squareOrder][1] = position[0][1] + shape[squareOrder][1];
            }
        }
    }

    // TODO - Delete this
    private void displayPosition() {
        for (int i = 0; i < MAX_NUMBER_OF_SQUARES; i++) {
            System.out.print("(" + position[i][0] + ", " + position[i][1] + ")  ");
        }
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
