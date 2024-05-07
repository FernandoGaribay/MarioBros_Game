package object.util;

import object.EntidadID;
import graficos.LibreriaGrafica;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public abstract class GameEntidad {

    protected EntidadID id;

    protected float x;
    protected float y;
    protected float width, height;
    private int inmunidad;
    private float velX, velY;
    private static List<GameEntidad> entidadesBorrar;
    private static List<GameEntidad> entidadesAniadir;

    protected boolean animacionCompletada = false;
    protected boolean atropellado = false;
    protected boolean aplastado = false;
    protected int contAnimacion = 0;

    public GameEntidad(float x, float y, EntidadID id, float width, float height) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = width;
        this.height = height;

        entidadesBorrar = new ArrayList<>();
        entidadesAniadir = new ArrayList<>();
    }

    public static void addEntidadABorrar(GameEntidad entidad) {
        entidadesBorrar.add(entidad);
    }

    public static List<GameEntidad> getEntidadesBorrar() {
        return entidadesBorrar;
    }

    public static void addEntidadAAniadir(GameEntidad entidad) {
        entidadesAniadir.add(entidad);
    }

    public static List<GameEntidad> getEntidadesAniadir() {
        return entidadesAniadir;
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

    public int getInmunidad() {
        return inmunidad;
    }

    public void setInmunidad(int inmunidad) {
        this.inmunidad = inmunidad;
    }

    public boolean isAnimacionCompletada() {
        return animacionCompletada;
    }

    public void setAnimacionCompletada(boolean animacionCompletada) {
        this.animacionCompletada = animacionCompletada;
    }

    public boolean isAnimacionAtropellado() {
        return atropellado;
    }

    public void setAnimacionAtropellado(boolean atropellado) {
        this.atropellado = atropellado;
    }

    public boolean isAnimacionAplastado() {
        return aplastado;
    }

    public void setAnimacionAplastado(boolean aplastado) {
        this.aplastado = aplastado;
    }
    
    public abstract GameEntidad clone();
}
