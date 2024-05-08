package graficos;

import object.util.GameObjeto;
import main.Game;

public class Camara {

    // VARIABLES
    private static int x, y;
    private static int ultimaX;

    public Camara(int x, int y) {
        this.x = x;
        this.y = y;
        this.ultimaX = 0;
    }

    public void tick(GameObjeto player) {
        x = (int) (-player.getX() + Game.getSCREEN_WIDTH() / 2 + 16);
        if (getX() > ultimaX) {
            setX(ultimaX);
        }
        setLastX(x);
    }

    public static int getX() {
        return x;
    }

    public static void setX(int newX) {
        x = newX;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int newY) {
        y = -newY;
    }

    public static int getLastX() {
        return ultimaX;
    }

    public static void setLastX(int newUltimaX) {
        ultimaX = newUltimaX;
    }
}
