package object;

import object.util.GameObject;
import graficos.Animacion;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import object.util.ObjectID;

public class BloqueMoneda extends GameObject {

    // OBJETOS
    private Texturas texturas;
    private BufferedImage[] bloquesMoneda;
    private Animacion animacion;
    
    // Variables
    private boolean animacionCompletada = false;
    private int contAnimacionGolpe = 0;
    
    public BloqueMoneda(int x, int y, int width, int height, int scale) {
        super(x, y, ObjectID.BloqueMoneda, width, height, scale);
        this.texturas = new Texturas();
        
        bloquesMoneda = texturas.getBloquesMoneda();
        animacion = new Animacion(12, bloquesMoneda);
    }
    
    @Override
    public void tick() {
        animacion.runAnimacion();
        
        if (isGolpeado() && !animacionCompletada) {
            runAnimacionGolpe();
        }
    }
    
    @Override
    public void render(LibreriaGrafica g) {
        if (!isGolpeado()) {
            animacion.drawSprite(g, (int) getX(), (int) getY());
        } else {
            g.drawImage(texturas.getTextura("bloqueMonedaHit"), (int) (getX()), (int) (getY()));
        }
//        g.drawRectangle(getBounds(), Color.white);
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }
    
    @Override
    public GameObject clone() {
        return new BloqueMoneda((int) x, (int) y, (int) width, (int) height, 1);
    }
    
    public void runAnimacionGolpe() {
        if (contAnimacionGolpe == 16) {
            contAnimacionGolpe = 0;
            animacionCompletada = true;
            
            return;
        }

        if (contAnimacionGolpe < 8) {
            setY(getY() - 3);
        } else if (contAnimacionGolpe < 16 && contAnimacionGolpe >= 8) {
            setY(getY() + 3);
        }
        contAnimacionGolpe++;
    }
}
