package object.entidades;

import main.Game;
import object.util.GameEntidad;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.EntidadID;
import object.util.GameObjeto;
import object.util.HandlerBloques;
import static object.ObjectID.Bloque;
import static object.ObjectID.BloqueHongo;
import static object.ObjectID.BloqueMoneda;
import static object.ObjectID.Ladrillo;
import static object.ObjectID.TuberiaCabeza;

public class EntidadHongo extends GameEntidad {

    // Variables
    private int countAnimacion;
    private boolean animacionInicioCompletada = false;

    private float maskX;
    private float maskY;

    public EntidadHongo(float x, float y, int width, int height, HandlerBloques handler) {
        super(x, y, EntidadID.Hongo, width, height, handler);
        countAnimacion = 0;

        maskX = x;
        maskY = y;
        setVelX(2f);
    }

    @Override
    public void tick() {
        if (animacionInicioCompletada) {
            aplicarMovimiento();
            aplicarGravedad();
            aplicarColisiones();
        } else {
            animacionDeInicio();
        }
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(Texturas.getEntidadesTextura("entidadHongo"), (int) getX(), (int) getY());
        g.drawImage(Texturas.getTextura("bloqueMonedaHit"), (int) maskX, (int) maskY);
//        showBounds(g);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX() + getWidth() / 4), (int) (getY() + getWidth() / 2), (int) (getWidth() / 2), (int) (getHeight() / 2));
    }

    public Rectangle getBoundsSides() {
        return new Rectangle((int) getX(),
                (int) getY(),
                (int) getWidth(),
                (int) getHeight() / 2);
    }

    @Override
    public GameEntidad clone() {
        return new EntidadHongo((int) x, (int) y, (int) width, (int) height, handler);
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
                    case TuberiaCabeza:
                    case BloqueMoneda:
                    case BloqueHongo:
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

    private void animacionDeInicio() {
        if (countAnimacion == 32) {
            animacionInicioCompletada = true;
        } else if (countAnimacion <= 32) {
            setY(getY() - 1f);
        }
        countAnimacion++;
    }

    private void showBounds(LibreriaGrafica g) {
        g.drawRectangle(getBounds(), Color.red);
        g.drawRectangle(getBoundsSides(), Color.red);
    }
}
