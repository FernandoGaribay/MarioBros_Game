package object.entidades;

import graficos.Animacion;
import main.Game;
import object.util.GameEntidad;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.EntidadID;
import static object.EntidadID.HongoRojo;
import object.util.GameObjeto;
import object.util.HandlerBloques;
import static object.ObjectID.Bloque;
import static object.ObjectID.BloqueMoneda;
import static object.ObjectID.Ladrillo;
import static object.ObjectID.TuberiaCabeza;
import static object.ObjectID.BloqueHongoRojo;

public class EntidadGoomba extends GameEntidad {

    // OBJETOS
    private Animacion animacion;

    public EntidadGoomba(float x, float y, int width, int height) {
        super(x, y, EntidadID.Goomba, width, height);
        setVelX(-1.2f);

        animacion = new Animacion(10,
                Texturas.getEntidadesTextura("entidadGoombaCaminando1"),
                Texturas.getEntidadesTextura("entidadGoombaCaminando2")
        );
    }

    @Override
    public void tick() {
        animacion.runAnimacion();
        aplicarMovimiento();
        aplicarGravedad();
        aplicarColisiones();
    }

    @Override
    public void render(LibreriaGrafica g) {
        animacion.drawSprite(g, (int) getX(), (int) getY());
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
        return new EntidadGoomba((int) x, (int) y, (int) width, (int) height);
    }

    public void aplicarMovimiento() {
        setX(getVelX() + getX());
        setY(getVelY() + getY());
    }

    private void aplicarColisiones() {
        int size = Game.getHandlerBloques().getGameObj().size() - 1;

        for (int i = 0; i < size; i++) {
            GameObjeto temp = Game.getHandlerBloques().getGameObj().get(i);

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

        size = Game.getHandlerEntidades().getGameEntidades().size();
        for (int i = 0; i < size; i++) {
            GameEntidad temp = Game.getHandlerEntidades().getGameEntidades().get(i);

            if (temp == this) {
                continue;
            }

            switch (temp.getID()) {
                case KoopaCaparazon:
                    handleColisionEntidad(temp);
                    break;
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

    private void handleColisionEntidad(GameEntidad temp) {
        // Bounding Box de los pies
        if (getBounds().intersects(temp.getBounds())) {
            if (temp.getVelX() != 0) {
                GameEntidad.addEntidadABorrar(this);
            }
        }
    }

    private void showBounds(LibreriaGrafica g) {
        g.drawRectangle(getBounds(), Color.red);
        g.drawRectangle(getBoundsSides(), Color.red);
    }
}
