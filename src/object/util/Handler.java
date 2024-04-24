package object.util;

import graficos.LibreriaGrafica;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import main.Game;
import object.Ladrillo;
import object.Player;

public class Handler {

    private List<GameObject> gameObjs;
    private Player player;

    public Handler() {
        this.gameObjs = new ArrayList<GameObject>();
    }

    public void tick() {
        int renderIzquierda = (int) (player.getX() - Game.getMAX_RENDERIZADO());
        int renderDerecha = (int) (player.getX() + Game.getMAX_RENDERIZADO());

        for (GameObject obj : gameObjs) {
            if (obj.getX() < renderDerecha && obj.getX() > renderIzquierda) {
                obj.tick();
            }
        }
        List<Ladrillo> elimiarBloques = player.getBloquesAEliminar();
        for (Ladrillo elimiarBloque : elimiarBloques) {
            eliminarObj(elimiarBloque);
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

    public void eliminarObj(GameObject obj) {
        gameObjs.remove(obj);
    }

    public List<GameObject> getGameObj() {
        return gameObjs;
    }
}
