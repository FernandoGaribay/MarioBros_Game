package object.util;

import graficos.LibreriaGrafica;
import java.util.ArrayList;
import java.util.List;
import main.Game;
import static object.ObjectID.BloqueHongoRojo;
import object.bloques.BloqueEnigma;
import object.entidades.EntidadHongoRojo;
import object.entidades.EntidadHongoVerde;
import object.entidades.EntidadKoopaCaparazon;
import object.player.Player;

public class HandlerEntidades {

    // VARIABLES
    private int renderIzquierda;
    private int renderDerecha;

    private List<GameEntidad> gameEntidades;
    private Player player;

    public HandlerEntidades() {
        this.gameEntidades = new ArrayList<GameEntidad>();
    }

    public void tick() {
        renderIzquierda = (int) (player.getX() - Game.getMAX_RENDERIZADO());
        renderDerecha = (int) (player.getX() + Game.getMAX_RENDERIZADO());

        for (GameEntidad obj : gameEntidades) {
            // Se pausa la entidad si esta a fuera del render derecho
            if (obj.getX() > renderDerecha) {
                continue;
            }
            // Se elimina la entidad si esa fuera del render izquierdo
            if (obj.getX() < renderIzquierda) {
                eliminarEntidad(obj);
                return;
            }
            obj.tick();
        }
        List<BloqueEnigma> elimiarBloques = player.getBloquesDrops();
        for (BloqueEnigma elimiarBloque : elimiarBloques) {
            switch (elimiarBloque.getID()) {
                case BloqueHongoRojo:
                    addEntidad(new EntidadHongoRojo(elimiarBloque.getX(), elimiarBloque.getY(), 32, 32));
                    break;
                case BloqueHongoVerde:
                    addEntidad(new EntidadHongoVerde(elimiarBloque.getX(), elimiarBloque.getY(), 32, 32));
                    break;
            }
        }
        List<GameEntidad> elimiarEntidades = player.getEntidadesDrops();
        for (GameEntidad elimiarEntidad : elimiarEntidades) {
            switch (elimiarEntidad.getID()) {
                case Koopa:
                    eliminarEntidad(elimiarEntidad);
                    addEntidad(new EntidadKoopaCaparazon(elimiarEntidad.getX(), elimiarEntidad.getY(), 32, 26));
                    break;
            }
        }
        List<GameEntidad> entidadesBorrar = GameEntidad.getEntidadesBorrar();
        for (GameEntidad eliminarEntidad : entidadesBorrar) {
            eliminarEntidad(eliminarEntidad);
        }
        List<GameEntidad> aniadirEntidades = GameEntidad.getEntidadesAniadir();
        for (GameEntidad addEntidad : aniadirEntidades) {
            addEntidad(new EntidadKoopaCaparazon(addEntidad.getX(), addEntidad.getY(), 32, 26));
        }
    }

    public void render(LibreriaGrafica g) {
        for (GameEntidad obj : gameEntidades) {
            if (obj.getX() < renderDerecha) {
                obj.render(g);
            }
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
