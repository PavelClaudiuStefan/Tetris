package pavelclaudiustefan.tetris.game_logic;

public class Tetris {

    public Tetris(int dota) {
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
    }

    public void start() {
        System.out.println("Game on!");
    }

}
