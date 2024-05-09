package main;

// Fernando Garibay Ceja - 21310414
// Gráficas por computadora 2D y 3D

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
