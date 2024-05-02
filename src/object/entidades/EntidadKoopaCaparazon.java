package object.entidades;

import graficos.Animacion;
import main.Game;
import object.util.GameEntidad;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import object.EntidadID;
import static object.ObjectID.BarreraEntidades;
import static object.ObjectID.Bloque;
import static object.ObjectID.BloqueHongoRojo;
import object.util.GameObjeto;
import object.util.HandlerBloques;
import static object.ObjectID.BloqueMoneda;
import static object.ObjectID.Ladrillo;
import static object.ObjectID.TuberiaCabeza;

public class EntidadKoopaCaparazon extends GameEntidad {

    // OBJETOS
    private BufferedImage img;

    public EntidadKoopaCaparazon(float x, float y, int width, int height, HandlerBloques handler) {
        super(x, y, EntidadID.KoopaCaparazon, width, height, handler);
        img = Texturas.getEntidadesTextura("entidadKoopaCaparazon");
        detenerMovimiento();
    }

    @Override
    public void tick() {
        aplicarMovimiento();
        aplicarGravedad();
        aplicarColisiones();
        aplicarInmunidad();
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(img, (int) getX(), (int) getY());
        showBounds(g);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX() + getWidth() / 4), (int) (getY() + getHeight() / 2), (int) (getWidth() / 2), (int) (getHeight() / 2));
    }

    public Rectangle getBoundsSides() {
        return new Rectangle((int) getX(),
                (int) getY(),
                (int) getWidth(),
                (int) getHeight() / 2);
    }

    @Override
    public GameEntidad clone() {
        return new EntidadKoopaCaparazon((int) x, (int) y, (int) width, (int) height, handler);
    }

    public void aplicarMovimiento() {
        setX(getVelX() + getX());
        setY(getVelY() + getY());
    }

    private void aplicarColisiones() {
        int size = handler.getGameObj().size() - 1;
        int renderIzquierda = (int) (getX() - Game.getMAX_RENDERIZADO());
        int renderDerecha = (int) (getX() + Game.getMAX_RENDERIZADO());

        for (int i = 0; i < size; i++) {
            GameObjeto temp = handler.getGameObj().get(i);

            if (temp.getX() < renderDerecha && temp.getX() > renderIzquierda) {
                switch (temp.getID()) {
                    case Bloque:
                    case BarreraEntidades:
                    case TuberiaCabeza:
                    case BloqueMoneda:
                    case BloqueHongoRojo:
                    case Ladrillo:
                        handleColisionSolida(temp);
                        break;
                }
            }
        }
    }

    private void handleColisionSolida(GameObjeto temp) {
        // Bounding Box de los pies
        if (getBounds().intersects(temp.getBounds())) {
            setY(temp.getY() - getHeight());
            setVelY(0);
        }
        // Bounding Box de los lados
        if (getBoundsSides().intersects(temp.getBounds())) {
            setVelX(getVelX() * -1);
        }
    }

    public void detenerMovimiento() {
        setVelX(0);
    }

    public void iniciarMovimiento(boolean adelante) {
        if (adelante) {
            setVelX(4.0f);
        } else {
            setVelX(-4.0f);
        }
    }

    private void aplicarInmunidad() {
        if (getInmunidad() != 0) {
            setInmunidad(getInmunidad() - 1);
        }
    }

    private void showBounds(LibreriaGrafica g) {
        g.drawRectangle(getBounds(), Color.red);
        g.drawRectangle(getBoundsSides(), Color.red);
    }
}
