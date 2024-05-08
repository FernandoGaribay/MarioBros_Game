package graficos;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import main.Game;

public class Ventana {

    private JFrame frame;
    private Dimension tamano;

    public Ventana(int width, int height, String title) {
        this.tamano = new Dimension(width, height);
        this.frame = new JFrame(title);

        this.frame.setUndecorated(true);
        this.frame.setPreferredSize(tamano);
        this.frame.setMaximumSize(tamano);
        this.frame.setMinimumSize(tamano);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void setCanvas(Canvas canvas) {
        this.frame.getContentPane().removeAll();
        this.frame.add(canvas);
        this.frame.repaint();
        this.frame.revalidate();
    }

}
