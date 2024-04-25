package object.util;

import graficos.LibreriaGrafica;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import main.Game;
import object.Ladrillo;
import object.Player;

public class EntityHandler {

    private List<GameEntidad> gameEntidades;
    private Player player;

    public EntityHandler() {
        this.gameEntidades = new ArrayList<GameEntidad>();
    }

    public void tick() {
        int renderIzquierda = (int) (player.getX() - Game.getMAX_RENDERIZADO());
        int renderDerecha = (int) (player.getX() + Game.getMAX_RENDERIZADO());

        for (GameEntidad obj : gameEntidades) {
            if (obj.getX() < renderDerecha && obj.getX() > renderIzquierda) {
                obj.tick();
            }
        }
    }

    public void render(LibreriaGrafica g) {
        int renderIzquierda = (int) (player.getX() - Game.getMAX_RENDERIZADO());
        int renderDerecha = (int) (player.getX() + Game.getMAX_RENDERIZADO());

        for (GameEntidad obj : gameEntidades) {
//            if (obj.getX() < renderDerecha && obj.getX() > renderIzquierda) {
                obj.render(g);
//            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntidad(GameEntidad entidad) {
        gameEntidades.add(entidad);
    }

    public void eliminarEntidad(GameEntidad entidad) {
        gameEntidades.remove(entidad);
    }

    public List<GameEntidad> getGameEntidades() {
        return gameEntidades;
    }
}
