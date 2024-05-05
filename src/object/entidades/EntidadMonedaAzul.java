package object.entidades;

import graficos.Animacion;
import main.Game;
import object.util.GameEntidad;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.EntidadID;

public class EntidadMonedaAzul extends EntidadMoneda {

    public EntidadMonedaAzul(float x, float y, int width, int height) {
        super(x, y, EntidadID.Moneda, width, height, "entidadMonedaAzul");
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
        return new EntidadMonedaAzul((int) x, (int) y, (int) width, (int) height);
    }
}
