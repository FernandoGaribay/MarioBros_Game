package object.util;

import graficos.LibreriaGrafica;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import object.GameObject;
import object.Player;

public class Handler {

    private List<GameObject> gameObjs;
    private Player player;

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

    public int setPlayer(Player player) {
        if (this.player != null) {
            return -1;
        }

        addObj(player);
        this.player = player;
        return 0;
    }

    public int removePlayer() {
        if (player == null) {
            return -1;
        }

        removeObj(player);
        this.player = null;
        return 0;
    }
    
    public Player getPlayer(){
        return player;
    }
}