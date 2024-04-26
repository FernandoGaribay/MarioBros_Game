package object;

import object.util.GameObject;
import graficos.Animacion;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import object.util.HandlerBloques;
import main.Game;
import static object.util.EntidadID.Hongo;
import object.util.HandlerEntidades;
import object.util.ObjectID;
import object.util.EstadoPlayer;
import object.util.GameEntidad;
import static object.util.ObjectID.Bandera;
import static object.util.ObjectID.Bloque;
import static object.util.ObjectID.BloqueHongo;
import static object.util.ObjectID.BloqueMoneda;
import static object.util.ObjectID.Ladrillo;
import static object.util.ObjectID.TuberiaCabeza;

public class Player extends GameObject {

    // OBJETOS
    private HandlerBloques handlerBloques;
    private HandlerEntidades handlerEntidades;
    private Animacion animacionCaminando;
    private Animacion animacionEstado;
    private ArrayList<Ladrillo> colaBloquesEliminados;
    private ArrayList<BloqueEnigma> colaBloquesDrops;

    // VARIABLES
    private EstadoPlayer estadoPlayer;
    private String prefijoTextura;
    private int inmunidad;
    private int hp;

    private boolean mirarAdelante = true;
    private boolean saltando = false;
    private boolean caminarAdelante = false;
    private boolean caminarAtras = false;

    private float velocidadAnterior = 0.0f;
    private boolean animacionEnCurso = false;
    private int contAnimacionMuerte;

    public Player(float x, float y, HandlerBloques handlerBloques, HandlerEntidades handlerEntidades) {
        super(x, y, ObjectID.Player, 32, 32, 0);
        this.handlerBloques = handlerBloques;
        this.handlerEntidades = handlerEntidades;
        this.colaBloquesEliminados = new ArrayList<>();
        this.colaBloquesDrops = new ArrayList<>();
        this.cambiarEstado(1);
    }

    @Override
    public void tick() {
        aplicarMovimiendo();
        aplicarGravedad();
        aplicarColisiones();
        aplicarAnimaciones();
        aplicarInmunidadMomentanea();
        animacionCaminando.runAnimacion();

    }

    @Override
    public void render(LibreriaGrafica g) {

        // Si hay una animacion en curso, se mostrarar los sprites correspondiantes
        if (animacionEnCurso) {
            animacionEstado.drawSprite(g, (int) getX(), (int) getY());
            return;
        }

        if (saltando) {
            if (getVelX() > 0) {
                g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioSaltando"), (int) getX(), (int) getY());
            } else if (getVelX() < 0) {
                g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioSaltando"), (int) (getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight());
            } else {
                if (mirarAdelante) {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioSaltando"), (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
                } else {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioSaltando"), (int) (getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight());
                }
            }
        } else {
            if (getVelX() == 4.0f || getVelX() >= 2.0f) {
                animacionCaminando.drawSprite(g, (int) (getX()), (int) (getY()));
            } else if (getVelX() == -4.0f || getVelX() <= -2.0f) {
                animacionCaminando.drawSpriteInverso(g, (int) (getX()), (int) (getY()));
            } else if (getVelX() == 0 && mirarAdelante) {
                g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_mario"), (int) getX(), (int) getY());
            } else if (getVelX() == 0 && !mirarAdelante) {
                g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_mario"), (int) (getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight());
            } else if (getVelX() > velocidadAnterior) {
                g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioDerrapando"), (int) getX(), (int) getY());
            } else if (getVelX() < velocidadAnterior) {
                g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioDerrapando"), (int) (getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight());
            }
        }
        velocidadAnterior = getVelX();

        // Cajas de colisiones -------------------------------------------------
//        showBounds(g);
    }

    @Override
    public void aplicarGravedad() {
        // Si el jugador esta en una animacion no sera afectado por la gravedad
        if (animacionEnCurso) {
            return;
        }
        setVelY(getVelY() + 0.5f);
    }

    private void aplicarMovimiendo() {
        setX(getVelX() + getX());
        setY(getVelY() + getY());

        // si el jugador esta en una animacion no se puede mover
        if (animacionEnCurso) {
            setVelX(0);
            setX(getX());
            return;
        }

        if (caminarAdelante || caminarAtras) {
            if (caminarAdelante) {
                setVelX(getVelX() + 0.2f);
                if (getVelX() > 4) {
                    setVelX(4);
                }
            } else if (caminarAtras) {
                setVelX(getVelX() - 0.2f);
                if (getVelX() < -4) {
                    setVelX(-4);
                }
            }
        } else if (getVelX() != 0) {
            if (Math.abs(getVelX()) < 0.01f) {
                setVelX(0);
            } else if (getVelX() > 0) {
                setVelX(getVelX() - 0.1f);
            } else if (getVelX() < 0) {
                setVelX(getVelX() + 0.1f);
            }
        }
    }

    private void aplicarInmunidadMomentanea() {
        if (inmunidad != 0) {
            inmunidad--;
        }
    }

    private void aplicarColisiones() {
        int renderIzquierda = (int) (getX() - Game.getMAX_RENDERIZADO());
        int renderDerecha = (int) (getX() + Game.getMAX_RENDERIZADO());

        // Si el jugador esta en una animacion, no se vera afectado por las colisiones
        if (animacionEnCurso) {
            return;
        }

        for (int i = 0; i < handlerBloques.getGameObj().size(); i++) {
            GameObject temp = handlerBloques.getGameObj().get(i);

            if (colaBloquesEliminados.contains(temp)) {
                continue;
            } else if (colaBloquesDrops.contains(temp)) {
                continue;
            } else {
                if (temp.getX() < renderDerecha && temp.getX() > renderIzquierda) {
                    switch (temp.getID()) {
                        case Bloque:
                        case TuberiaCabeza:
                        case BloqueMoneda:
                        case BloqueHongo:
                        case Ladrillo:
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
                case Hongo:
                case Goomba:
                    handlerColisionEntidad(temp);
                    break;
            }
        }
    }

    private void handleColicionSolida(GameObject temp) {
        // Bounding Box de los pies
        if (getBounds().intersects(temp.getBounds())) {
            setY(temp.getY() - getHeight());
            setVelY(0);
            saltando = false;
        }
        // Bounding Box de la cabeza
        if (getBoundsTop().intersects(temp.getBounds())) {
            setY(temp.getY() + temp.getHeight());
            setVelY(0);

            switch (temp.getID()) {
                case BloqueMoneda:
                    ((BloqueMoneda) temp).golpeado();
                    break;
                case BloqueHongo:
                    BloqueEnigma bloque = ((BloqueEnigma) temp);
                    if (!bloque.isGolpeado()) {
                        bloque.golpeado();
                        colaBloquesDrops.add(bloque);
                    }
                    break;
                case Ladrillo:
                    if (estadoPlayer == EstadoPlayer.Chico) {
                        ((Ladrillo) temp).golpear();
                    } else if (estadoPlayer == EstadoPlayer.Grande) {
                        ((Ladrillo) temp).romper();
                        colaBloquesEliminados.add(((Ladrillo) temp));
                    }
                    break;
            }
        }
        // Bounding Box de la derecha
        if (getBoundsRight().intersects(temp.getBounds())) {
            setX(temp.getX() - getWidth());
        }
        // Bounding Box de la izquierda
        if (getBoundsLeft().intersects(temp.getBounds())) {
            setX(temp.getX() + temp.getWidth());
        }

        // Detectamos si el jugador esta callendo para evitar saltar en el aire
        if (!(getVelY() >= 0 && getVelY() <= 2.0)) {
            saltando = true;
        }
    }

    private void handleColisionBandera(GameObject temp, int i) {
        if (getBoundsRight().intersects(temp.getBounds())) {
            handlerBloques.getGameObj().get(i).setVelY(2);
        }
    }

    private void handlerColisionEntidad(GameEntidad temp) {
        // Bounding Box de los pies
        if (getBounds().intersects(temp.getBounds())) {
            switch (temp.getID()) {
                case Hongo:
                    handlerEntidades.eliminarEntidad(temp);
                    cambiarEstado(2);
                    break;
                case Goomba:
                    handlerEntidades.eliminarEntidad(temp);
                    setVelY(-6f);
                    System.out.println("te pise");
                    break;
            }
        }
        // Bounding Box de la derecha e izquierda
        if (getBoundsRight().intersects(temp.getBounds())
                || getBoundsLeft().intersects(temp.getBounds())) {

            // Si hay fotogramas de inmunidad al daño, se sale del metodo
            if (inmunidad != 0) {
                return;
            }
            switch (temp.getID()) {
                case Goomba:
                    if (hp == 1) {
                        hp = 0;
                        inmunidad = 120;
                        break;
                    }
                    inmunidad = 120;
                    cambiarEstado(1);
                    break;
            }
        }
    }

    private void cambiarEstado(int hp) {
        if (hp == 2) {
            this.estadoPlayer = EstadoPlayer.Grande;
            this.prefijoTextura = "L";
            this.hp = 2;
            super.setHeight(64);
            super.setWidth(32);
        } else if (hp == 1) {
            this.estadoPlayer = EstadoPlayer.Chico;
            this.prefijoTextura = "S";
            this.hp = 1;
            super.setHeight(32);
            super.setWidth(32);
        }

        this.animacionCaminando = new Animacion(5,
                Texturas.getMarioTextura(prefijoTextura + "_marioCaminando1"),
                Texturas.getMarioTextura(prefijoTextura + "_marioCaminando2"),
                Texturas.getMarioTextura(prefijoTextura + "_marioCaminando3"));
    }

    public void aplicarAnimaciones() {
        if (hp == 0) {
            if(contAnimacionMuerte == 0){
                animacionEstado = new Animacion(1, Texturas.getMarioTextura(prefijoTextura + "_marioMuerte"));
            }
            if (contAnimacionMuerte <= 20) {
                animacionEnCurso = true;
                setVelY(0);
            } else if (contAnimacionMuerte <= 40) {
                setVelY(getVelY() - 0.4f);
            } else if (contAnimacionMuerte <= 120) {
                setVelY(getVelY() + 0.7f);
            } else {
                animacionEnCurso = false;
            }
            animacionEstado.runAnimacion();
            contAnimacionMuerte++;
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

    public boolean hasJumped() {
        return saltando;
    }

    public void setJumped(boolean hasJumped) {
        saltando = hasJumped;
    }

    public int getHp() {
        return hp;
    }

    public void setAdelante(boolean adelante) {
        this.caminarAdelante = adelante;
    }

    public void setAtras(boolean atras) {
        this.caminarAtras = atras;
    }

    public void setMirarAdelante(boolean mirarAdelante) {
        this.mirarAdelante = mirarAdelante;
    }

    @Override
    public GameObject clone() {
        return new Tuberia((int) x, (int) y, (int) width, (int) height, 1);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX() + getWidth() / 2 - getWidth() / 4),
                (int) (getY() + getHeight() / 2),
                (int) (getWidth() / 2),
                (int) (getHeight() / 2));
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) (getX() + getWidth() / 2 - getWidth() / 4),
                (int) (getY()),
                (int) (getWidth() / 2),
                (int) (getHeight() / 2));
    }

    public Rectangle getBoundsRight() {
        return new Rectangle(
                (int) (getX() + getWidth() - 8),
                (int) (getY() + 4),
                8,
                (int) (getHeight() - 8));
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) (getX()),
                (int) (getY() + 4),
                8,
                (int) (getHeight() - 8));
    }

    private void showBounds(LibreriaGrafica g) {
//        g.fillRect((int) (getX()), (int) (getY()), (int) (getX() + getWidth()), (int) (getY() + getHeight()), Color.yellow);

        g.drawRectangle(getBounds(), Color.red);
        g.drawRectangle(getBoundsTop(), Color.red);
        g.drawRectangle(getBoundsRight(), Color.red);
        g.drawRectangle(getBoundsLeft(), Color.red);

        g.drawLine((int) (getX() + Game.getMAX_RENDERIZADO()), 0, (int) (getX() + Game.getMAX_RENDERIZADO()), 600, Color.yellow);
        g.drawLine((int) (getX() - Game.getMAX_RENDERIZADO()), 0, (int) (getX() - Game.getMAX_RENDERIZADO()), 600, Color.yellow);
    }
}
