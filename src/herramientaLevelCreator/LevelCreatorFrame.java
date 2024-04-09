package herramientaLevelCreator;

import graficos.Texturas;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

public class LevelCreatorFrame extends JFrame {

    // Menu constantes
    private static Component espaciado = Box.createHorizontalGlue();
    private static JMenu mover_pintar = new JMenu("Movimiento");

    // Variables
    Texturas objTexturas = new Texturas();
    
    public LevelCreatorFrame() {
        initComponents();
        inicializarBarraObjetos();
        
        mover_pintar.setText("Pintando");
        menuBar.add(espaciado);
        menuBar.add(mover_pintar);
        
        this.requestFocus();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32) {
                    levelCreatorMatriz.setMoviendo(true);
                    mover_pintar.setText("Movimiento");
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 32) {
                    levelCreatorMatriz.setMoviendo(false);
                    mover_pintar.setText("Pintando");
                }
            }
        });
    }
    
    private void inicializarBarraObjetos() {
        for (Map.Entry<String, BufferedImage> elemento : objTexturas.getImagenesMap().entrySet()) {
            String key = elemento.getKey();
            BufferedImage imgElemento = elemento.getValue();

            // Se crea el elemento para la barra y se me asigna su interfaz
            PanelElemento panelElementoTemp = new PanelElemento(imgElemento, key, levelCreatorMatriz);
            panelBarraObjetos.add(panelElementoTemp);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        levelCreatorMatriz = new herramientaLevelCreator.LevelCreatorMatriz();
        jPanel1 = new javax.swing.JPanel();
        scrollPanel = new javax.swing.JScrollPane();
        panelBarraObjetos = new javax.swing.JPanel();
        panelSeparador = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuNuevo = new javax.swing.JMenuItem();
        menuGuardar = new javax.swing.JMenuItem();
        menuAbrir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuCentrar = new javax.swing.JMenuItem();
        menuListaVelocidad = new javax.swing.JMenu();
        menuX1 = new javax.swing.JMenuItem();
        menuX2 = new javax.swing.JMenuItem();
        menuX3 = new javax.swing.JMenuItem();
        menuX4 = new javax.swing.JMenuItem();
        menuX5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1200, 620));
        setMinimumSize(new java.awt.Dimension(1200, 620));
        setName("Level Creator"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 620));
        setResizable(false);
        getContentPane().setLayout(null);

        javax.swing.GroupLayout levelCreatorMatrizLayout = new javax.swing.GroupLayout(levelCreatorMatriz);
        levelCreatorMatriz.setLayout(levelCreatorMatrizLayout);
        levelCreatorMatrizLayout.setHorizontalGroup(
            levelCreatorMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1005, Short.MAX_VALUE)
        );
        levelCreatorMatrizLayout.setVerticalGroup(
            levelCreatorMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(levelCreatorMatriz);
        levelCreatorMatriz.setBounds(0, 0, 1005, 600);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        scrollPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.getVerticalScrollBar().setUnitIncrement(20);

        panelBarraObjetos.setLayout(new javax.swing.BoxLayout(panelBarraObjetos, javax.swing.BoxLayout.Y_AXIS));

        panelSeparador.setBackground(new java.awt.Color(255, 255, 255));
        panelSeparador.setMaximumSize(new java.awt.Dimension(160, 10));
        panelSeparador.setMinimumSize(new java.awt.Dimension(160, 10));
        panelSeparador.setName(""); // NOI18N
        panelSeparador.setPreferredSize(new java.awt.Dimension(160, 10));

        javax.swing.GroupLayout panelSeparadorLayout = new javax.swing.GroupLayout(panelSeparador);
        panelSeparador.setLayout(panelSeparadorLayout);
        panelSeparadorLayout.setHorizontalGroup(
            panelSeparadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        panelSeparadorLayout.setVerticalGroup(
            panelSeparadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        panelBarraObjetos.add(panelSeparador);

        scrollPanel.setViewportView(panelBarraObjetos);

        jPanel1.add(scrollPanel);
        scrollPanel.setBounds(10, 40, 180, 550);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Paleta de elementos");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 180, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(1000, 0, 200, 600);

        jMenu1.setText("Archivo");
        jMenu1.setToolTipText("");

        menuNuevo.setText("Nuevo");
        menuNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(menuNuevo);

        menuGuardar.setText("Guardar");
        menuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(menuGuardar);

        menuAbrir.setText("Abrir");
        menuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(menuAbrir);

        menuBar.add(jMenu1);

        jMenu2.setText("Opciones");

        menuCentrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuCentrar.setText("Centrar Vista");
        menuCentrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCentrarActionPerformed(evt);
            }
        });
        jMenu2.add(menuCentrar);

        menuListaVelocidad.setText("Velocidad");

        menuX1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuX1.setText("x1");
        menuX1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuX1ActionPerformed(evt);
            }
        });
        menuListaVelocidad.add(menuX1);

        menuX2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuX2.setText("x2");
        menuX2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuX2ActionPerformed(evt);
            }
        });
        menuListaVelocidad.add(menuX2);

        menuX3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuX3.setText("x3");
        menuX3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuX3ActionPerformed(evt);
            }
        });
        menuListaVelocidad.add(menuX3);

        menuX4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuX4.setText("x4");
        menuX4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuX4ActionPerformed(evt);
            }
        });
        menuListaVelocidad.add(menuX4);

        menuX5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuX5.setText("x5");
        menuX5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuX5ActionPerformed(evt);
            }
        });
        menuListaVelocidad.add(menuX5);

        jMenu2.add(menuListaVelocidad);

        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevoActionPerformed
        int columnas = Integer.parseInt(JOptionPane.showInputDialog("Dijite el numero de columnas"));
        int filas = Integer.parseInt(JOptionPane.showInputDialog("Dijite el numero de filas"));
        
        levelCreatorMatriz.limpiarMatriz(columnas, filas);
    }//GEN-LAST:event_menuNuevoActionPerformed

    private void menuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarActionPerformed
        String nombreArchivo = JOptionPane.showInputDialog("Dijite el nombre del nivel: ");
        EscritorLector_Niveles.guardarMatrizComoArchivo(levelCreatorMatriz.getMatrizElementos(), nombreArchivo);
    }//GEN-LAST:event_menuGuardarActionPerformed

    private void menuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirActionPerformed
        String nombreArchivo = JOptionPane.showInputDialog("Dijite el nombre del nivel: ");
        levelCreatorMatriz.setMatrizElementos(EscritorLector_Niveles.cargarMatrizDesdeArchivo(nombreArchivo));
    }//GEN-LAST:event_menuAbrirActionPerformed

    private void menuX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuX1ActionPerformed
        levelCreatorMatriz.setVelocidad(1);
    }//GEN-LAST:event_menuX1ActionPerformed

    private void menuX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuX2ActionPerformed
        levelCreatorMatriz.setVelocidad(2);
    }//GEN-LAST:event_menuX2ActionPerformed

    private void menuX3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuX3ActionPerformed
        levelCreatorMatriz.setVelocidad(3);
    }//GEN-LAST:event_menuX3ActionPerformed

    private void menuX4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuX4ActionPerformed
        levelCreatorMatriz.setVelocidad(4);
    }//GEN-LAST:event_menuX4ActionPerformed

    private void menuX5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuX5ActionPerformed
        levelCreatorMatriz.setVelocidad(5);
    }//GEN-LAST:event_menuX5ActionPerformed

    private void menuCentrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCentrarActionPerformed
        levelCreatorMatriz.centrar();
    }//GEN-LAST:event_menuCentrarActionPerformed
    
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LevelCreatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LevelCreatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LevelCreatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LevelCreatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LevelCreatorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private herramientaLevelCreator.LevelCreatorMatriz levelCreatorMatriz;
    private javax.swing.JMenuItem menuAbrir;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuCentrar;
    private javax.swing.JMenuItem menuGuardar;
    private javax.swing.JMenu menuListaVelocidad;
    private javax.swing.JMenuItem menuNuevo;
    private javax.swing.JMenuItem menuX1;
    private javax.swing.JMenuItem menuX2;
    private javax.swing.JMenuItem menuX3;
    private javax.swing.JMenuItem menuX4;
    private javax.swing.JMenuItem menuX5;
    private javax.swing.JPanel panelBarraObjetos;
    private javax.swing.JPanel panelSeparador;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables
}
