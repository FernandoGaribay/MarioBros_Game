package object.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import main.Game;
import static object.EntidadID.Goomba;
import static object.EntidadID.HongoRojo;
import static object.EntidadID.HongoVerde;
import static object.EntidadID.Koopa;
import static object.EntidadID.KoopaCaparazon;
import static object.EntidadID.Moneda;
import object.ObjectID;
import static object.ObjectID.Bandera;
import static object.ObjectID.BarreraJugador;
import static object.ObjectID.Bloque;
import static object.ObjectID.BloqueHongoRojo;
import static object.ObjectID.BloqueHongoVerde;
import static object.ObjectID.Entrada;
import static object.ObjectID.LadrilloAzul;
import static object.ObjectID.LadrilloRojo;
import static object.ObjectID.Salida;
import static object.ObjectID.TuberiaCabeza;
import object.padres.BloqueEnigma;
import object.bloques.BloqueLadrilloAzul;
import object.bloques.BloqueMoneda;
import object.bloques.BloqueLadrilloRojo;
import object.bloques.BloqueLadrilloMonedas;
import object.entidades.EntidadGoomba;
import object.entidades.EntidadHongoRojo;
import object.padres.BloqueLadrillo;
import object.entidades.EntidadKoopaCaparazon;
import object.util.EstadoPlayer;
import object.util.GameEntidad;
import object.util.GameObjeto;
import object.util.HandlerBloques;
import object.util.HandlerEntidades;
import object.util.HandlerSonidos;
import utils.ReproductorMP3;

public class PlayerColisiones {

    // Objeto jugador
    private Player player;

    // HANDLER OBJETOS  
    private HandlerBloques handlerBloques;
    private HandlerEntidades handlerEntidades;

    // LISTAS DE OBJETOS A ELIMINAR
    private ArrayList<BloqueLadrillo> colaBloquesEliminados;
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
                        case Entrada:
                            handdleColisionInteraccion(temp);
                            break;
                        case Bloque:
                        case BarreraJugador:
                        case TuberiaCabeza:
                        case BloqueMoneda:
                        case BloqueHongoRojo:
                        case BloqueHongoVerde:
                        case LadrilloRojo:
                        case LadrilloAzul:
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
                case Moneda:
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
                    ((BloqueLadrilloMonedas) temp).golpeado();
                    break;
                case LadrilloRojo:
                case LadrilloAzul:
                    if (player.getEstadoPlayer() == EstadoPlayer.Chico) {
                        HandlerSonidos.playSound("BumpSound.wav");
                        ((BloqueLadrillo) temp).golpear();
                    } else if (player.getEstadoPlayer() == EstadoPlayer.Grande) {
                        HandlerSonidos.playSound("BlockBreakSound.wav");
                        ((BloqueLadrillo) temp).romper();
                        colaBloquesEliminados.add(((BloqueLadrillo) temp));
                    }
                    break;
            }
        }
        // Bounding Box de la derecha
        if (player.getBoundsRight().intersects(temp.getBounds())) {
            player.setBloquearAdelante(true);
            player.setX(temp.getX() - player.getWidth());
            if (!player.isSaltando()) {
                player.setVelX(0);
            }
        }
        // Bounding Box de la izquierda
        if (player.getBoundsLeft().intersects(temp.getBounds())) {
            player.setBloquearAtras(true);
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

    private void handdleColisionInteraccion(GameObjeto temp) {
        if (player.getBounds().intersects(temp.getBounds()) && player.isDecenderTuberia()) {
            List<Integer> entrada = new ArrayList<>();
            List<Integer> salida = new ArrayList<>();

            switch (temp.getID()) {
                case Entrada:
                    HandlerSonidos.playSound("TuberiaSound.wav");
                    entrada.add((int) (temp.getX() / 32));
                    entrada.add((int) (temp.getY() / 32));

                    salida = Game.ENTRADAS_SALIDAS.get(entrada);
                    player.cambiarEscena(salida.get(0), salida.get(1));
                    break;
            }
        }
    }

    private void handleColisionBandera(GameObjeto temp, int i) {
        if (player.getBoundsRight().intersects(temp.getBounds())) {
            handlerBloques.getGameObj().get(i).setVelY(2);
            HandlerSonidos.stopAllSound();
            HandlerSonidos.playSound("WinSound.wav");
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
                    HandlerSonidos.playSound("CrecerSound.wav");
                    handlerEntidades.eliminarEntidad(temp);
                    player.cambiarEstado(2);
                    break;
                case HongoVerde:
                    HandlerSonidos.playSound("1UPSound.wav");
                    handlerEntidades.eliminarEntidad(temp);
                    System.out.println("Hongo verde recogido");
                    break;
                case Goomba:
                    HandlerSonidos.playSound("AplastadoSound.wav");
                    ((EntidadGoomba) temp).setAnimacionAplastado(true);
                    colaEntidadesDrops.add(temp);
                    player.setVelY(-8f);
                    break;
                case Koopa:
                    HandlerSonidos.playSound("AplastadoSound.wav");
                    temp.setAnimacionCompletada(true);
                    colaEntidadesDrops.add(temp);
                    player.setVelY(-8f);
                    break;
                case KoopaCaparazon:
                    HandlerSonidos.playSound("AplastadoSound.wav");
                    EntidadKoopaCaparazon caparazon = ((EntidadKoopaCaparazon) temp);

                    if (caparazon.getVelX() != 0) {
                        caparazon.detenerMovimiento();
                    } else {
                        caparazon.iniciarMovimiento(true);
                    }
                    player.setVelY(-8f);
                    break;
                case Moneda:
                    HandlerSonidos.playSound("CoinSound.wav");
                    handlerEntidades.eliminarEntidad(temp);
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
                        HandlerSonidos.playSound("KickShellSound.wav");
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

    public ArrayList<BloqueLadrillo> getBloquesAEliminar() {
        ArrayList<BloqueLadrillo> output = new ArrayList<>();

        for (BloqueLadrillo removeBlock : colaBloquesEliminados) {
            if (!removeBlock.sePuedeEliminar()) {
                continue;
            }
            output.add(removeBlock);
        }

        for (BloqueLadrillo bloqueEliminar : output) {
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
            if (entidadDrop.isAnimacionCompletada()) {
                output.add(entidadDrop);
            }
        }

        for (GameEntidad bloqueEliminar : output) {
            colaEntidadesDrops.remove(bloqueEliminar);
        }

        return output;
    }

}
