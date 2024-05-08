package main;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        while (true) {
            if (game.isGameover()) {
                game = new Game();
                System.gc();
            }
        }
    }
}
