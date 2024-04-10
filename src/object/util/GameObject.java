package object.util;

import graficos.LibreriaGrafica;
import java.awt.Rectangle;
import object.util.ObjectID;

public abstract class GameObject {

    protected float x;
    protected float y;
    protected ObjectID id;
    private float velX, velY;
    protected float width, height;
    private boolean golpeado;
    private int scale;

    public GameObject(float x, float y, ObjectID id, float width, float height, int scale) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.golpeado = false;
    }

    public abstract void tick();

    public abstract void render(LibreriaGrafica g);

    public abstract Rectangle getBounds();

    public void applyGravity() {
        velY += 0.5f;
    }

    public void setX(float x) {
        this.x = x;
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

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isGolpeado() {
        return golpeado;
    }

    public void setGolpeado(boolean golpeado) {
        this.golpeado = golpeado;
    }

    public abstract GameObject clone();
}
