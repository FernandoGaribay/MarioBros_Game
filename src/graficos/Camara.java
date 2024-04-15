package graficos;

import object.util.GameObject;
import main.Game;

public class Camara {

    private int x, y;
    private int ultimaX;

    public Camara(int x, int y) {
        this.x = x;
        this.y = y;
        this.ultimaX = 0;
    }

    public void tick(GameObject player) {
        this.x = (int) (-player.getX() + Game.getSCREEN_WIDTH() / 2);
        if (getX() > ultimaX) {
            setX(ultimaX);
        }
        setLastX(this.x);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLastX() {
        return ultimaX;
    }

    public void setLastX(int lastX) {
        this.ultimaX = lastX;
    }

}
