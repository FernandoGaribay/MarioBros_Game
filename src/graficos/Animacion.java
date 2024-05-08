package graficos;

import java.awt.image.BufferedImage;

public class Animacion {

    // IMAGENES
    private BufferedImage[] imagenes;
    private BufferedImage spriteActual;

    // VARIABLES
    private int frames = 0;
    private int velocidad = 0;
    private int velocidadIndex = 0;
    private int frameIndex = 0;

    public Animacion(int velocidad, BufferedImage... args) {
        this.velocidad = velocidad;
        this.frames = args.length;

        this.imagenes = new BufferedImage[args.length];
        for (int i = 0; i < args.length; i++) {
            imagenes[i] = args[i];
        }
    }

    public void runAnimacion() {
        velocidadIndex++;
        if (velocidadIndex > velocidad) {
            velocidadIndex = 0;
            siguienteFrame();
        }
    }

    private void siguienteFrame() {
        spriteActual = imagenes[frameIndex];
        frameIndex++;

        if (frameIndex >= frames) {
            frameIndex = 0;
        }
    }

    public void drawSprite(LibreriaGrafica g, int x, int y) {
        if (spriteActual != null) {
            g.drawImage(spriteActual, x, y);
        }
    }

    public void drawSpriteInverso(LibreriaGrafica g, int x, int y) {
        if (spriteActual != null) {
            g.drawImage(spriteActual, x + spriteActual.getWidth(), y, -spriteActual.getWidth(), spriteActual.getHeight());
        }
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
