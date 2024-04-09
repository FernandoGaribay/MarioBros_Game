package herramientaLevelCreator;

import java.awt.image.BufferedImage;

public class CasillaNivel {
    
    private String nombreElemento;
    private int x;
    private int y;
    private BufferedImage imagenElemento;
    
    public CasillaNivel(){
        this.nombreElemento = "";
        this.x = 0;
        this.y = 0;
        this.imagenElemento = null;
    }

    public String getNombreElemento() {
        return nombreElemento;
    }

    public void setNombreElemento(String nombreElemento) {
        this.nombreElemento = nombreElemento;
    }

    public BufferedImage getImagenElemento() {
        return imagenElemento;
    }

    public void setImagenElemento(BufferedImage imagenElemento) {
        this.imagenElemento = imagenElemento;
    }

    public void setCoordenadas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    
}
