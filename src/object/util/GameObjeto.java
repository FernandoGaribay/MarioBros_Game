package object.util;

import graficos.LibreriaGrafica;
import java.awt.Rectangle;
import object.ObjectID;

public abstract class GameObjeto {

    protected ObjectID id;

    protected float x;
    protected float y;
    protected float width, height;
    private float velX, velY;

    protected int xDesplasamiento;

    public GameObjeto(float x, float y, ObjectID id, float width, float height, int xDesplasamiento) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
        this.xDesplasamiento = xDesplasamiento;
    }

    public abstract void tick();

    public abstract void render(LibreriaGrafica g);

    public abstract Rectangle getBounds();

    public void aplicarGravedad() {
        velY += 0.5f;
    }

    public void setX(float x) {
        this.x = x - xDesplasamiento;
    }

    public float getX() {
        return this.x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return this.y;
    }

    public void setId(ObjectID id) {
        this.id = id;
    }

    public ObjectID getID() {
        return this.id;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getxDesplasamiento() {
        return xDesplasamiento;
    }

    public void setxDesplasamiento(int xDesplasamiento) {
        this.xDesplasamiento = xDesplasamiento;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public abstract GameObjeto clone();
}
