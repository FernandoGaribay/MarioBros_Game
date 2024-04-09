package object;

import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.Handler;
import object.util.ObjectID;

public class Player extends GameObject {

    // CONSTANTES
    private static final float WIDTH = 24;
    private static final float HEIGHT = 32;

    // OBJETOS
    private Handler handler;
    private Texturas texturas;

    // VARIABLES
    private boolean jumped = false;

    public Player(float x, float y, int scale, Handler handler) {
        super(x, y, ObjectID.Player, WIDTH, HEIGHT, scale);
        this.handler = handler;
        this.texturas = new Texturas();

    }

    @Override
    public void tick() {
        setX(getVelX() + getX());
        setY(getVelY() + getY());
        applyGravity();

        collision();
    }

    @Override
    public void render(LibreriaGrafica g) {
        g.drawImage(texturas.getTextura("marioDerecha"), (int) (getX()), (int) (getY()));
//        g.fillRectangle((int) (getX()), (int) (getY()), (int) (getX() + WIDTH), (int) (getY() + HEIGHT), Color.yellow);
//        showBounds(g);
    }

    private void collision() {
        for (int i = 0; i < handler.getGameObj().size(); i++) {
            GameObject temp = handler.getGameObj().get(i);

            // CON COLISION SOLIDA
            if (temp.getID() == ObjectID.Block || temp.getID() == ObjectID.PipeHead || temp.getID() == ObjectID.CoinBlock || temp.getID() == ObjectID.Ladrillo) {
                if (getBounds().intersects(temp.getBounds())) {
                    setY(temp.getY() - getHeight());
                    setVelY(0);
                    jumped = false;
                }
                if (getBoundsTop().intersects(temp.getBounds())) {
                    setY(temp.getY() + temp.getHeight());
                    setVelY(0);

                    if (temp.getID() == ObjectID.CoinBlock) {
                        temp.setGolpeado(true);
                    }
                    if (temp.getID() == ObjectID.Ladrillo) {
                        handler.removeObj(temp);
                    }
                }
                if (getBoundsRight().intersects(temp.getBounds())) {
                    setX(temp.getX() - getWidth());
                }
                if (getBoundsLeft().intersects(temp.getBounds())) {
                    setX(temp.getX() + temp.getWidth());
                }
            }

            // SIN COLISION SOLIDA
            if (temp.getID() == ObjectID.Bandera) {

                if (getBoundsRight().intersects(temp.getBounds())) {
                    if (temp.getID() == ObjectID.Bandera) {
                        handler.getGameObj().get(i).setVelY(2);
                    }
                }
            }

        }
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
        return new Rectangle((int) (getX() + getWidth() - 5),
                (int) (getY() + 5),
                5,
                (int) (getHeight() - 10));
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) (getX()),
                (int) (getY() + 5),
                5,
                (int) (getHeight() - 10));
    }

    private void showBounds(LibreriaGrafica g) {
        g.drawRectangle(getBounds(), Color.red);
        g.drawRectangle(getBoundsTop(), Color.red);
        g.drawRectangle(getBoundsRight(), Color.red);
        g.drawRectangle(getBoundsLeft(), Color.red);
    }

    public boolean hasJumped() {
        return jumped;
    }

    public void setJumped(boolean hasJumped) {
        jumped = hasJumped;
    }

    @Override
    public GameObject clone() {
        return new Tuberia((int) x, (int) y, (int) width, (int) height, 1);
    }
}
