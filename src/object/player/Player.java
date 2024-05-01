package object.player;

import object.bloques.Ladrillo;
import object.bloques.Tuberia;
import object.bloques.BloqueEnigma;
import object.util.GameObjeto;
import graficos.Animacion;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import object.util.HandlerBloques;
import main.Game;
import object.ObjectID;
import object.util.HandlerEntidades;
import object.util.EstadoPlayer;

public class Player extends GameObjeto {

    // OBJETOS
    private PlayerMovimiento playerMovimiento;
    private PlayerColisiones playerColisiones;
    private PlayerAnimaciones playerAnimaciones;

    // ANIMACIONES
    private Animacion animacionCaminando;
    private Animacion animacionEstado;

    // VARIABLES
    private EstadoPlayer estadoPlayer;
    private String prefijoTextura;
    private int hp;

    // BANDERAS
    private boolean mirarAdelante = true;
    private boolean saltando = false;
    private boolean ignorarColisiones = false;
    private boolean ignorarInput = false;
    private boolean animacionMuerte = false;
    private boolean animacionDano = false;

    private float velocidadAnterior = 0.0f;
    private int contAnimacion;

    public Player(float x, float y, HandlerBloques handlerBloques, HandlerEntidades handlerEntidades) {
        super(x, y, ObjectID.Player, 32, 32, 0);
        this.playerMovimiento = new PlayerMovimiento(this);
        this.playerAnimaciones = new PlayerAnimaciones(this);
        this.playerColisiones = new PlayerColisiones(this, handlerBloques, handlerEntidades);
        this.cambiarEstado(1);
    }

    @Override
    public void tick() {
        playerMovimiento.aplicarMovimiento();
        playerColisiones.aplicarColisiones();
        playerColisiones.aplicarInmunidadMomentanea();
        aplicarGravedad();
        aplicarAnimaciones();
    }

    @Override
    public void render(LibreriaGrafica g) {

        // Si hay una animacion en curso, se mostrarar los sprites correspondiantes
        if (animacionMuerte || animacionDano) {
            animacionEstado.drawSprite(g, (int) getX(), (int) getY());
            return;
        }

        if (saltando) {
            if (mirarAdelante) {
                if (getVelX() > 0) {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioSaltando"), (int) getX(), (int) getY());
                } else {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioSaltando"), (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
                }
            } else {
                if (getVelX() < 0) {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioSaltando"), (int) (getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight());
                } else {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioSaltando"), (int) (getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight());
                }
            }
        } else {
            if (mirarAdelante) {
                if (getVelX() <= 5.0f && getVelX() > 0.6f) {
                    animacionCaminando.drawSprite(g, (int) (getX()), (int) (getY()));
                } else if (getVelX() <= 0.5f && getVelX() >= 0.0f) {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_mario"), (int) getX(), (int) getY());
                } else if ((getVelX() > velocidadAnterior) || (getVelX() == -5.0f)) {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioDerrapando"), (int) getX(), (int) getY());
                } else {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_mario"), (int) getX(), (int) getY());
                }
            } else if (!mirarAdelante) {
                if (getVelX() >= -5.0f && getVelX() < -0.6f) {
                    animacionCaminando.drawSpriteInverso(g, (int) (getX()), (int) (getY()));
                } else if (getVelX() >= -0.5f && getVelX() <= 0.0f) {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_mario"), (int) (getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight());
                } else if ((getVelX() < velocidadAnterior) || getVelX() == 5.0f) {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_marioDerrapando"), (int) (getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight());
                } else {
                    g.drawImage(Texturas.getMarioTextura(prefijoTextura + "_mario"), (int) (getX() + getWidth()), (int) getY(), (int) -getWidth(), (int) getHeight());
                }
            } else {
                System.out.println("FALTA DE SPRIITE");
                System.out.println(getVelX());
                System.out.println("mirar adelante = " + isMirarAdelante());
                System.out.println("velocidad anterior = " + velocidadAnterior);
            }
        }
        velocidadAnterior = getVelX();
        // Cajas de colisiones -------------------------------------------------
//        showBounds(g);
    }

    @Override
    public void aplicarGravedad() {
        playerMovimiento.aplicarGravedad();
    }

    public void aplicarAnimaciones() {
        animacionCaminando.runAnimacion();

        if (animacionMuerte) {
            if (contAnimacion == 0) {
                ignorarColisiones = true;
                ignorarInput = true;
                animacionEstado = new Animacion(1, Texturas.getMarioTextura(prefijoTextura + "_marioMuerte"));
            } else if (contAnimacion <= 20) {
                setVelY(0);
            } else if (contAnimacion <= 40) {
                setVelY(getVelY() - 0.4f);
            } else if (contAnimacion <= 120) {
                setVelY(getVelY() + 0.7f);
            } else {
                ignorarColisiones = false;
                animacionMuerte = false;
                contAnimacion = 0;
                return;
            }
            animacionEstado.runAnimacion();
            contAnimacion++;
        } else if (animacionDano) {
            if (contAnimacion == 0) {
                ignorarInput = true;
                animacionEstado = new Animacion(3, Texturas.getMarioTextura(prefijoTextura + "_mario"),
                        Texturas.getMarioTextura(""));
            } else if (contAnimacion == 30) {
                ignorarInput = false;
            } else if (contAnimacion == 60) {
                animacionDano = false;
                contAnimacion = 0;
                return;
            }
            animacionEstado.runAnimacion();
            contAnimacion++;
        }
    }

    public void cambiarEstado(int hp) {
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

    public ArrayList<Ladrillo> getBloquesAEliminar() {
        ArrayList<Ladrillo> output = playerColisiones.getBloquesAEliminar();
        return output;
    }

    public ArrayList<BloqueEnigma> getBloquesDrops() {
        ArrayList<BloqueEnigma> output = playerColisiones.getBloquesDrops();
        return output;
    }

    public EstadoPlayer getEstadoPlayer() {
        return estadoPlayer;
    }

    public void setEstadoPlayer(EstadoPlayer estadoPlayer) {
        this.estadoPlayer = estadoPlayer;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isMirarAdelante() {
        return mirarAdelante;
    }

    public void setMirarAdelante(boolean mirarAdelante) {
        this.mirarAdelante = mirarAdelante;
    }

    public boolean isSaltando() {
        return saltando;
    }

    public void setSaltando(boolean hasJumped) {
        saltando = hasJumped;
    }

    public boolean isIgnorarColisiones() {
        return ignorarColisiones;
    }

    public void setIgnorarColisiones(boolean animacionEnCurso) {
        this.ignorarColisiones = animacionEnCurso;
    }

    public boolean isIgnorarInput() {
        return ignorarInput;
    }

    public void setIgnorarInput(boolean ignorarInput) {
        this.ignorarInput = ignorarInput;
    }

    public boolean isAnimacionMuerte() {
        return animacionMuerte;
    }

    public void setAnimacionMuerte(boolean animacionMuerte) {
        this.animacionMuerte = animacionMuerte;
    }

    public boolean isAnimacionDano() {
        return animacionDano;
    }

    public void setAnimacionDano(boolean animacionDano) {
        this.animacionDano = animacionDano;
    }

    @Override
    public GameObjeto clone() {
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
