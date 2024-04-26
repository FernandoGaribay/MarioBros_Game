package object;

import main.Game;
import object.util.GameEntidad;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.EntidadID;
import object.util.GameObject;
import object.util.HandlerBloques;
import static object.util.ObjectID.Bloque;
import static object.util.ObjectID.BloqueHongo;
import static object.util.ObjectID.BloqueMoneda;
import static object.util.ObjectID.Ladrillo;
import static object.util.ObjectID.TuberiaCabeza;

public class EntidadGoomba extends GameEntidad {

    public EntidadGoomba(float x, float y, int width, int height, HandlerBloques handler) {
        super(x, y, EntidadID.Goomba, width, height, handler);
        setVelX(-1.2f);
    }

    @Override
    public void tick() {
        aplicarMovimiento();
        aplicarGravedad();
        aplicarColisiones();
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(Texturas.getEntidadesTextura("entidadGoomba"), (int) getX(), (int) getY());
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
        return new EntidadGoomba((int) x, (int) y, (int) width, (int) height, handler);
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
            GameObject temp = handler.getGameObj().get(i);

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

    private void handleColisionSolida(GameObject temp) {
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

    private void showBounds(LibreriaGrafica g) {
        g.drawRectangle(getBounds(), Color.red);
        g.drawRectangle(getBoundsSides(), Color.red);
    }
}
