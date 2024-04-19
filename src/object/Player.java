package object;

import object.util.GameObject;
import graficos.Animacion;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.Handler;
import object.util.ObjectID;
import object.util.EstadoPlayer;

public class Player extends GameObject {

    // OBJETOS
    private Handler handler;
    private Animacion animacionCaminando;

    // VARIABLES
    private EstadoPlayer estadoPlayer;
    private String prefijoTextura;
    private int hp;

    private boolean mirarAdelante = true;
    private boolean saltando = false;
    private boolean caminarAdelante = false;
    private boolean caminarAtras = false;

    private float velocidadAnterior = 0.0f;

    public Player(float x, float y, Handler handler) {
        super(x, y, ObjectID.Player, 32, 32, 1);
        this.handler = handler;
        this.cambiarEstado(2);
    }

    @Override
    public void tick() {
        aplicarMovimiendo();
        aplicarGravedad();
        aplicarColisiones();

        animacionCaminando.runAnimacion();
    }

    @Override
    public void render(LibreriaGrafica g) {
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
//        g.fillRect((int) (getX()), (int) (getY()), (int) (getX() + getWidth()), (int) (getY() + getHeight()), Color.yellow);
//        showBounds(g);
    }

    private void aplicarMovimiendo() {
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
            }

            if (getVelX() > 0) {
                setVelX(getVelX() - 0.2f);
            } else if (getVelX() < 0) {
                setVelX(getVelX() + 0.2f);
            }
        }

        setX(getVelX() + getX());
        setY(getVelY() + getY());
    }

    private void aplicarColisiones() {
        for (int i = 0; i < handler.getGameObj().size(); i++) {
            GameObject temp = handler.getGameObj().get(i);

            switch (temp.getID()) {
                case Bloque:
                case TuberiaCabeza:
                case BloqueMoneda:
                case Ladrillo:
                    handleColisionSolida(temp);
                    break;
                case Bandera:
                    handleColisionBandera(temp, i);
                    break;
            }
        }
    }

    private void handleColisionSolida(GameObject temp) {
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

            if (temp.getID() == ObjectID.BloqueMoneda) {
                temp.setGolpeado(true);
            }

            if (temp.getID() == ObjectID.Ladrillo) {
                if (estadoPlayer == EstadoPlayer.Chico) {
                    temp.setGolpeado(true);
                } else if (estadoPlayer == EstadoPlayer.Grande) {
                    handler.removeObj(temp);
                }
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
    }

    private void handleColisionBandera(GameObject temp, int i) {
        if (getBoundsRight().intersects(temp.getBounds())) {
            handler.getGameObj().get(i).setVelY(2);
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
        g.drawRectangle(getBounds(), Color.red);
        g.drawRectangle(getBoundsTop(), Color.red);
        g.drawRectangle(getBoundsRight(), Color.red);
        g.drawRectangle(getBoundsLeft(), Color.red);
    }
}
