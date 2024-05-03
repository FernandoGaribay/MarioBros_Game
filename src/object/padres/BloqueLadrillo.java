package object.padres;

import object.util.GameObjeto;
import graficos.LibreriaGrafica;
import graficos.Texturas;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import object.ObjectID;
import object.bloques.BloqueLadrilloRojo;
import object.bloques.Escombros;

public class BloqueLadrillo extends GameObjeto {

    // OBJETOS
    protected Escombros escombros;

    // VARIABLES
    protected int contAnimacionGolpe = 0;
    protected boolean golpeado = false;
    protected boolean roto = false;
    
    private final String texturaNombre;
    private final ObjectID objectID;

    public BloqueLadrillo(int x, int y, ObjectID objectID, int width, int height, int xDesplasamiento, String texturaNombre) {
        super(x, y, objectID, width, height, xDesplasamiento);
        this.texturaNombre = texturaNombre;
        this.objectID = objectID;
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
            g.drawImage(Texturas.getTextura(texturaNombre), (int) (getX()), (int) (getY()));
        } else {
            escombros.render(g);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
    }

    @Override
    public GameObjeto clone() {
        return new BloqueLadrilloRojo((int) x, (int) y, (int) width, (int) height, (int) xDesplasamiento);
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
        escombros = new Escombros(x, y, width, height, objectID);
    }
}
