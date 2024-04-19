package object.util;

import graficos.LibreriaGrafica;
import java.util.LinkedList;
import java.util.List;
import main.Game;
import object.Player;

public class Handler {

    private List<GameObject> gameObjs;
    private Player player;

    public Handler() {
        this.gameObjs = new LinkedList<GameObject>();
    }

    public void tick() {
        int renderIzquierda = (int) (player.getX() - Game.getMAX_RENDERIZADO());
        int renderDerecha = (int) (player.getX() + Game.getMAX_RENDERIZADO());

        for (GameObject obj : gameObjs) {
            if (obj.getX() < renderDerecha && obj.getX() > renderIzquierda) {
                obj.tick();
            }
        }
    }

    public void render(LibreriaGrafica g) {
        int renderIzquierda = (int) (player.getX() - Game.getMAX_RENDERIZADO());
        int renderDerecha = (int) (player.getX() + Game.getMAX_RENDERIZADO());

        for (GameObject obj : gameObjs) {
            if (obj.getX() < renderDerecha && obj.getX() > renderIzquierda) {
                obj.render(g);
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
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
