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
        Tetris game = new Tetris(2);
        game.start();
    }

}
