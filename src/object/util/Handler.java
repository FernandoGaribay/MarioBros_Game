package object.util;

import graficos.LibreriaGrafica;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import object.Player;

public class Handler {

    private List<GameObject> gameObjs;

    public Handler() {
        this.gameObjs = new LinkedList<GameObject>();
    }

    public void tick() {
        for (GameObject obj : gameObjs) {
            obj.tick();
        }
    }

    public void render(LibreriaGrafica g) {
        for (GameObject obj : gameObjs) {
            obj.render(g);
        }
    }

    public void addObj(GameObject obj) {
        gameObjs.add(obj);
    }

    public void removeObj(GameObject obj) {
        gameObjs.remove(obj);
    }

    public List<GameObject> getGameObj() {
        return gameObjs;
    }
}
