package pavelclaudiustefan.tetris.game;

import java.util.concurrent.ThreadLocalRandom;

class Tetromino {

    //[0, 4] -> Starting point
    //Every tetromino square is relative to the starting point
    private int[][] shape;
    private int[][] position;
    private int type = 0;

    Tetromino() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 8);
        switch (randomNum) {
            case 1:
                // Line shape
                shape = new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}};
                type = 1;
                break;
            case 2:
                // Square shape
                shape = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}};
                type = 2;
                break;
            case 3:
                // L shape
                shape = new int[][]{{0, 1}, {1, -1}, {1, 0}, {1, 1}};
                type = 3;
                break;
            case 4:
                // J shape
                shape = new int[][]{{0, -1}, {1, -1}, {1, 0}, {1, 1}};
                type = 4;
                break;
            case 5:
                // S shape
                shape = new int[][]{{0, 0}, {0, 1}, {1, -1}, {1, 0}};
                type = 5;
                break;
            case 6:
                // Z shape
                shape = new int[][]{{0, -1}, {0, 0}, {1, 0}, {1, 1}};
                type = 6;
                break;
            case 7:
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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(shape[i][j] + " ");
            }
            System.out.println();
        }

    }

    int getType() {
        return type;
    }

    int getY(int i) {
        return position[i][0];
    }

    int getX(int i) {
        return position[i][1];
    }

}
