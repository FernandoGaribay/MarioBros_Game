package object.entidades;

import graficos.Animacion;
import main.Game;
import object.util.GameEntidad;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.EntidadID;

public class EntidadMonedaRoja extends EntidadMoneda {

    public EntidadMonedaRoja(float x, float y, int width, int height) {
        super(x, y, EntidadID.Moneda, width, height);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(LibreriaGrafica g) {
        super.render(g);
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }

    @Override
    public GameEntidad clone() {
        return super.clone();
    }
}
