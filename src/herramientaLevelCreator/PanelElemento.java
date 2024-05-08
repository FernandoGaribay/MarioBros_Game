package herramientaLevelCreator;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PanelElemento extends JPanel {

    private final BufferedImage imgObjeto;
    private final String nombreElemento;
    private final int WIDTH_IMG;
    private final int HEIGHT_IMG;

    private InterfazLevelCreator clickListener;

    public PanelElemento(BufferedImage imgObjeto, String nombreElemento, InterfazLevelCreator clickListener) {
        initComponents();
        initEventos();

        this.imgObjeto = imgObjeto;
        this.nombreElemento = nombreElemento;
        this.WIDTH_IMG = imgObjeto.getWidth();
        this.HEIGHT_IMG = imgObjeto.getHeight();
        this.clickListener = clickListener;

        setPreferredSize(new Dimension(160, 100));
        add(new PanelObjeto(imgObjeto));

        this.lblNombreElemento.setText(nombreElemento + " - " + (WIDTH_IMG / 2) + "x" + (HEIGHT_IMG / 2));
    }

    public void initEventos() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clickListener.elementoSeleccionado(nombreElemento);
            }
        });
    }

    public String getNombreElemento() {
        return nombreElemento;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombreElemento = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        lblNombreElemento.setBackground(new java.awt.Color(0, 0, 0));
        lblNombreElemento.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreElemento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreElemento.setText("Bloque Suelo");
        add(lblNombreElemento);
        lblNombreElemento.setBounds(0, 0, 160, 17);
    }// </editor-fold>//GEN-END:initComponents

    class PanelObjeto extends JPanel {

        private final int WIDTH = 145;
        private final int HEIGHT = 70;

        public PanelObjeto(BufferedImage imgObjeto) {
            setLocation(8, 20);
            setSize(new Dimension(145, 70));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.drawImage(imgObjeto, (int) ((WIDTH - WIDTH_IMG) / 2), (int) ((HEIGHT - HEIGHT_IMG) / 2), this);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblNombreElemento;
    // End of variables declaration//GEN-END:variables
}
