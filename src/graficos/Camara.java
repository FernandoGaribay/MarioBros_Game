package graficos;

import object.GameObject;
import main.Game;

public class Camara {

    private int x, y;

    public Camara(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player) {
        this.x = (int) (-player.getX() + Game.getSCREEN_WIDTH() / 2);
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

}
