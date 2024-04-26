package object.util;

import graficos.LibreriaGrafica;
import java.awt.Rectangle;

public abstract class GameEntidad {

    protected EntidadID id;

    protected float x;
    protected float y;
    protected float width, height;
    protected HandlerBloques handler;
    private float velX, velY;

    public GameEntidad(float x, float y, EntidadID id, float width, float height, HandlerBloques handler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(LibreriaGrafica g);

    public abstract Rectangle getBounds();

    public void aplicarGravedad() {
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

    public void setId(EntidadID id) {
        this.id = id;
    }

    public EntidadID getID() {
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

    public void setHandler(HandlerBloques handler) {
        this.handler = handler;
    }

    public abstract GameEntidad clone();
}
