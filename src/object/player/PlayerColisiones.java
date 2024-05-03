package object.player;

import java.util.ArrayList;
import main.Game;
import static object.EntidadID.Goomba;
import static object.EntidadID.HongoRojo;
import static object.EntidadID.HongoVerde;
import static object.EntidadID.KoopaCaparazon;
import static object.ObjectID.Bandera;
import static object.ObjectID.BarreraJugador;
import static object.ObjectID.Bloque;
import static object.ObjectID.BloqueHongoRojo;
import static object.ObjectID.BloqueHongoVerde;
import static object.ObjectID.TuberiaCabeza;
import object.bloques.BloqueEnigma;
import object.bloques.BloqueMoneda;
import object.bloques.Ladrillo;
import object.bloques.LadrilloMonedas;
import object.entidades.EntidadGoomba;
import object.entidades.EntidadHongoRojo;
import object.entidades.EntidadKoopa;
import object.entidades.EntidadKoopaCaparazon;
import object.util.EstadoPlayer;
import object.util.GameEntidad;
import object.util.GameObjeto;
import object.util.HandlerBloques;
import object.util.HandlerEntidades;

public class PlayerColisiones {

    // Objeto jugador
    private Player player;

    // HANDLER OBJETOS  
    private HandlerBloques handlerBloques;
    private HandlerEntidades handlerEntidades;

    // LISTAS DE OBJETOS A ELIMINAR
    private ArrayList<Ladrillo> colaBloquesEliminados;
    private ArrayList<BloqueEnigma> colaBloquesDrops;
    private ArrayList<GameEntidad> colaEntidadesDrops;

    // VARIABLES
    private int inmunidad;

    public PlayerColisiones(Player player, HandlerBloques handlerBloques, HandlerEntidades handlerEntidades) {
        this.player = player;
        this.handlerBloques = handlerBloques;
        this.handlerEntidades = handlerEntidades;
        this.colaBloquesEliminados = new ArrayList<>();
        this.colaBloquesDrops = new ArrayList<>();
        this.colaEntidadesDrops = new ArrayList<>();
        this.inmunidad = 0;
    }

    public void aplicarColisiones() {
        int renderIzquierda = (int) (player.getX() - Game.getMAX_RENDERIZADO());
        int renderDerecha = (int) (player.getX() + Game.getMAX_RENDERIZADO());

        // Si el jugador esta en una animacion, no se vera afectado por las colisiones
        if (player.isIgnorarColisiones()) {
            return;
        }

        for (int i = 0; i < handlerBloques.getGameObj().size(); i++) {
            GameObjeto temp = handlerBloques.getGameObj().get(i);

            if (colaBloquesEliminados.contains(temp)) {
                continue;
            } else if (colaBloquesDrops.contains(temp)) {
                continue;
            } else {
                if (temp.getX() < renderDerecha && temp.getX() > renderIzquierda) {
                    switch (temp.getID()) {
                        case Bloque:
                        case BarreraJugador:
                        case TuberiaCabeza:
                        case BloqueMoneda:
                        case BloqueHongoRojo:
                        case BloqueHongoVerde:
                        case Ladrillo:
                        case LadrilloMonedas:
                            handleColicionSolida(temp);
                            break;
                        case Bandera:
                            handleColisionBandera(temp, i);
                            break;
                    }
                }
            }
        }

        for (int i = 0; i < handlerEntidades.getGameEntidades().size(); i++) {
            GameEntidad temp = handlerEntidades.getGameEntidades().get(i);

            switch (temp.getID()) {
                case HongoRojo:
                case HongoVerde:
                case Goomba:
                case Koopa:
                case KoopaCaparazon:
                    handlerColisionEntidad(temp);
                    break;
            }
        }
    }

    private void handleColicionSolida(GameObjeto temp) {
        // Bounding Box de los pies
        if (player.getBounds().intersects(temp.getBounds())) {
            player.setY(temp.getY() - player.getHeight());
            player.setVelY(0);
            player.setSaltando(false);
        }
        // Bounding Box de la cabeza
        if (player.getBoundsTop().intersects(temp.getBounds())) {
            player.setY(temp.getY() + temp.getHeight());
            player.setVelY(0);

            BloqueEnigma bloque;
            switch (temp.getID()) {
                case BloqueHongoRojo:
                case BloqueHongoVerde:
                    bloque = ((BloqueEnigma) temp);
                    if (!bloque.isGolpeado()) {
                        bloque.golpeado();
                        colaBloquesDrops.add(bloque);
                    }
                    break;
                case BloqueMoneda:
                    ((BloqueMoneda) temp).golpeado();
                    break;
                case LadrilloMonedas:
                    ((LadrilloMonedas) temp).golpeado();
                    break;
                case Ladrillo:
                    if (player.getEstadoPlayer() == EstadoPlayer.Chico) {
                        ((Ladrillo) temp).golpear();
                    } else if (player.getEstadoPlayer() == EstadoPlayer.Grande) {
                        ((Ladrillo) temp).romper();
                        colaBloquesEliminados.add(((Ladrillo) temp));
                    }
                    break;
            }
        }
        // Bounding Box de la derecha
        if (player.getBoundsRight().intersects(temp.getBounds())) {
            player.setX(temp.getX() - player.getWidth());
            if (!player.isSaltando()) {
                player.setVelX(0);
            }
        }
        // Bounding Box de la izquierda
        if (player.getBoundsLeft().intersects(temp.getBounds())) {
            player.setX(temp.getX() + temp.getWidth());
            if (!player.isSaltando()) {
                player.setVelX(0);
            }
        }

        // Detectamos si el jugador esta callendo para evitar saltar en el aire
        if (!(player.getVelY() >= 0 && player.getVelY() <= 2.0)) {
            player.setSaltando(true);
        }
    }

    private void handleColisionBandera(GameObjeto temp, int i) {
        if (player.getBoundsRight().intersects(temp.getBounds())) {
            handlerBloques.getGameObj().get(i).setVelY(2);
        }
    }

    private void handlerColisionEntidad(GameEntidad temp) {
        // Si hay fotogramas de inmunidad al daño, se sale del metodo
        if (inmunidad != 0) {
            return;
        }
        if (temp.getInmunidad() != 0) {
            return;
        }

        // Bounding Box de los pies
        if (player.getBounds().intersects(temp.getBounds())) {
            switch (temp.getID()) {
                case HongoRojo:
                    handlerEntidades.eliminarEntidad(temp);
                    player.cambiarEstado(2);
                    break;
                case HongoVerde:
                    handlerEntidades.eliminarEntidad(temp);
                    System.out.println("Hongo verde recogido");
                    break;
                case Goomba:
                    handlerEntidades.eliminarEntidad(temp);
                    player.setVelY(-8f);
                    break;
                case Koopa:
                    colaEntidadesDrops.add(temp);
                    player.setVelY(-8f);
                    break;
                case KoopaCaparazon:
                    EntidadKoopaCaparazon caparazon = ((EntidadKoopaCaparazon) temp);

                    if (caparazon.getVelX() != 0) {
                        caparazon.detenerMovimiento();
                    } else {
                        caparazon.iniciarMovimiento(true);
                    }
                    player.setVelY(-8f);
                    break;
            }
            return;
        }
        // Bounding Box de la derecha e izquierda
        if (player.getBoundsRight().intersects(temp.getBounds())
                || player.getBoundsLeft().intersects(temp.getBounds())) {

            switch (temp.getID()) {
                case Goomba:
                case Koopa:
                    if (player.getHp() == 1) {
                        player.setAnimacionMuerte(true);
                        break;
                    } else {
                        inmunidad = 120;
                        player.setAnimacionDano(true);
                        player.cambiarEstado(1);
                    }
                    break;
                case KoopaCaparazon:
                    EntidadKoopaCaparazon caparazon = ((EntidadKoopaCaparazon) temp);
                    if (caparazon.getVelX() == 0) {
                        if (player.isMirarAdelante()) {
                            caparazon.iniciarMovimiento(true);
                        } else {
                            caparazon.iniciarMovimiento(false);
                        }
                    } else {
                        if (player.getHp() == 1) {
                            player.setAnimacionMuerte(true);
                            break;
                        } else {
                            inmunidad = 120;
                            player.setAnimacionDano(true);
                            player.cambiarEstado(1);
                        }
                    }
                    break;
            }
        }
    }

    public void aplicarInmunidadMomentanea() {
        if (inmunidad != 0) {
            inmunidad--;
        }
    }

    public ArrayList<Ladrillo> getBloquesAEliminar() {
        ArrayList<Ladrillo> output = new ArrayList<>();

        for (Ladrillo removeBlock : colaBloquesEliminados) {
            if (!removeBlock.sePuedeEliminar()) {
                continue;
            }
            output.add(removeBlock);
        }

        for (Ladrillo bloqueEliminar : output) {
            colaBloquesEliminados.remove(bloqueEliminar);
        }

        return output;
    }

    public ArrayList<BloqueEnigma> getBloquesDrops() {
        ArrayList<BloqueEnigma> output = new ArrayList<>();

        for (BloqueEnigma removeBlock : colaBloquesDrops) {
            if (!removeBlock.poderGenerarDrops()) {
                continue;
            }
            output.add(removeBlock);
        }

        for (BloqueEnigma bloqueEliminar : output) {
            colaBloquesDrops.remove(bloqueEliminar);
        }

        return output;
    }
    
    public ArrayList<GameEntidad> getEntidadesDrops() {
        ArrayList<GameEntidad> output = new ArrayList<>();

        for (GameEntidad entidadDrop : colaEntidadesDrops) {
            output.add(entidadDrop);
        }

        for (GameEntidad bloqueEliminar : output) {
            colaEntidadesDrops.remove(bloqueEliminar);
        }

        return output;
    }

}
