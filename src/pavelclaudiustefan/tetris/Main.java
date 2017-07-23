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
        * Make a config file.cfg
        */

        // dota: 0 -> Default style tetrominoes
        // dota: 1 -> Dota basic style tetrominoes
        // dota: 2 -> Dota improved style tetrominoes
        Tetris game = new Tetris(0);
        game.start();
    }

}
