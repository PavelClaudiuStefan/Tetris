package pavelclaudiustefan.tetris;

import pavelclaudiustefan.tetris.game.Tetris;

/**
 * Created by Claudiu on 05-Jul-17.
 *
 * Tetris
 */

public class Main {

    public static void main(String[] args) {
        /* TODO:
        * Replace main with a gui starting menu
        * Respective gui allows choosing between tetromino styles (Dota || Default)
        */
        Tetris game = new Tetris(0);
        game.start();

        /*//TODO - Temporar
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("|"+i+","+j+"|");
            }
            System.out.println();
        }*/
    }

}