package graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class LibreriaGrafica extends Canvas {

    private final int WIDTH;
    private final int HEIGHT;
    private BufferedImage buffer;

    // Variables para la traslación
    private int translateX = 0;
    private int translateY = 0;

    public LibreriaGrafica(int width, int height) {
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void fillRect(int x0, int y0, int x1, int y1, Color color) {
        x0 += translateX;
        y0 += translateY;
        x1 += translateX;
        y1 += translateY;

        int nx0 = Math.min(x0, x1);
        int ny0 = Math.min(y0, y1);
        int nx1 = Math.max(x0, x1);
        int ny1 = Math.max(y0, y1);

        for (int y = ny0; y <= ny1; y++) {
            int xInicio = -1;
            for (int x = nx0; x <= nx1; x++) {
                if (xInicio == -1) {
                    xInicio = x;
                }
                if (xInicio != -1) {
                    putPixel(xInicio, y, color);
                    xInicio = -1;
                }
            }
        }
    }

    public void drawRectangle(Rectangle rectangle, Color color) {
        drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y, color);
        drawLine(rectangle.x + rectangle.width, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, color);
        drawLine(rectangle.x + rectangle.width, rectangle.y + rectangle.height, rectangle.x, rectangle.y + rectangle.height, color);
        drawLine(rectangle.x, rectangle.y + rectangle.height, rectangle.x, rectangle.y, color);
    }

    public void drawRect(int x0, int y0, int x1, int y1, Color color) {
        drawLine(x0, y0, x1, y0, color);
        drawLine(x0, y1, x1, y1, color);
        drawLine(x0, y0, x0, y1, color);
        drawLine(x1, y0, x1, y1, color);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        x1 += translateX;
        y1 += translateY;
        x2 += translateX;
        y2 += translateY;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            putPixel(x1, y1, color);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    public void drawImage(BufferedImage img, int x, int y) {
        Graphics g = buffer.getGraphics();
        g.drawImage(img, x + translateX, y + translateY, null);
    }

    public void drawImage(BufferedImage img, int x, int y, int width, int height) {
        Graphics g = buffer.getGraphics();
        g.drawImage(img, x + translateX, y + translateY, width, height, null);
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void translate(int x, int y) {
        this.translateX = x;
        this.translateY = y;
    }

    public void limpiarBuffer() {
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }
}
