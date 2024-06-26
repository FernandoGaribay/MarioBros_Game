package object.entidades;

import graficos.Animacion;
import main.Game;
import object.util.GameEntidad;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.EntidadID;

public class EntidadMoneda extends GameEntidad {

    // OBJETOS
    protected Animacion animacion;
    private EntidadID entidadID;

    public EntidadMoneda(float x, float y, EntidadID entidadID, int width, int height, String texturaNombre) {
        super(x, y, entidadID, width, height);
        this.entidadID = entidadID;
        this.animacion = new Animacion(10,
                Texturas.getEntidadesTextura(texturaNombre + "1"),
                Texturas.getEntidadesTextura(texturaNombre + "2"),
                Texturas.getEntidadesTextura(texturaNombre + "3")
        );
    }

    @Override
    public void tick() {
        animacion.runAnimacion();
    }

    @Override
    public void render(LibreriaGrafica g) {
        animacion.drawSprite(g, (int) getX() + 6, (int) getY());
//        showBounds(g);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX() + 6, (int) getY(), (int) getWidth(), (int) getHeight());
    }

    @Override
    public GameEntidad clone() {
        return new EntidadMonedaRoja((int) x, (int) y, (int) width, (int) height);
    }

    public void showBounds(LibreriaGrafica g) {
        g.drawRectangle(getBounds(), Color.red);
    }
}
