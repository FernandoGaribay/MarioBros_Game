package object;

import object.util.GameObject;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import object.util.ObjectID;

public class Ladrillo extends GameObject {

    // OBJETOS
    private Escombros escombros;

    // VARIABLES
    private int contAnimacionGolpe = 0;
    private boolean golpeado = false;
    private boolean roto = false;

    public Ladrillo(int x, int y, int width, int height, int xDesplasamiento) {
        super(x, y, ObjectID.Ladrillo, width, height, xDesplasamiento);

    }

    @Override
    public void tick() {
        if (roto) {
            escombros.tick();
        }
        if (golpeado) {
            runAnimacionGolpe();
        }
    }

    @Override
    public void render(LibreriaGrafica g) {
        if (!roto) {
            g.drawImage(Texturas.getTextura("bloqueLadrillo"), (int) (getX()), (int) (getY()));
        } else {
            escombros.render(g);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObject clone() {
        return new Ladrillo((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
    }
    
    public boolean sePuedeEliminar(){
        if(escombros.sePuedeEliminar()){
            return true;
        }
        return false;
    }

    public void runAnimacionGolpe() {
        if (contAnimacionGolpe == 16) {
            contAnimacionGolpe = 0;
            golpeado = false;

            return;
        }

        if (contAnimacionGolpe < 8) {
            setY(getY() - 3);
        } else if (contAnimacionGolpe < 16 && contAnimacionGolpe >= 8) {
            setY(getY() + 3);
        }
        contAnimacionGolpe++;
    }
    
    public void golpear(){
        golpeado = true;
    }
    
    public void romper(){
        roto = true;
        escombros = new Escombros(x, y, width, height);
    }
}
