package object.util;

import graficos.LibreriaGrafica;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import main.Game;
import object.bloques.Ladrillo;
import object.player.Player;

public class HandlerBloques {

    // VARIABLES
    private int renderIzquierda;
    private int renderDerecha;
    
    private List<GameObjeto> gameObjs;
    private Player player;

    public HandlerBloques() {
        this.gameObjs = new ArrayList<GameObjeto>();
    }

    public void tick() {
        renderIzquierda = (int) (player.getX() - Game.getMAX_RENDERIZADO());
        renderDerecha = (int) (player.getX() + Game.getMAX_RENDERIZADO());

        for (GameObjeto obj : gameObjs) { 
            // Se pausa el bloque si esta a fuera del render derecho
            if (obj.getX() > renderDerecha) {
                continue;
            }
            // Se elimina el bloque si esa fuera del render izquierdo
            if (obj.getX() < renderIzquierda) {
                eliminarObj(obj);
                return;
            }
            obj.tick();
        }
        List<Ladrillo> elimiarBloques = player.getBloquesAEliminar();
        for (Ladrillo elimiarBloque : elimiarBloques) {
            eliminarObj(elimiarBloque);
        }

    }

    public void render(LibreriaGrafica g) {
        for (GameObjeto obj : gameObjs) {
            if (obj.getX() < renderDerecha) {
                obj.render(g);
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addObj(GameObjeto obj) {
        gameObjs.add(obj);
    }

    public void eliminarObj(GameObjeto obj) {
        gameObjs.remove(obj);
    }

    public List<GameObjeto> getGameObj() {
        return gameObjs;
    }
}
