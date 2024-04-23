package herramientaLevelCreator;

import utils.EscritorLector_Niveles;
import graficos.Texturas;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

public class LevelCreatorFrame extends JFrame {

    // CONSTANTES
    private final LevelCreatorMatriz CREADOR_NIVELES_MATRIZ = new LevelCreatorMatriz();
    private static final Component ESPACIADO = Box.createHorizontalGlue();
    private static final JMenu MOVER_PINTAR = new JMenu();

    public LevelCreatorFrame() {
        initComponents();
        inicializarBarraObjetos();

        MOVER_PINTAR.setText("Pintando");
        menuBar.add(ESPACIADO);
        menuBar.add(MOVER_PINTAR);

        CREADOR_NIVELES_MATRIZ.setSize(1000, 600);
        add(CREADOR_NIVELES_MATRIZ);

        this.requestFocus();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!CREADOR_NIVELES_MATRIZ.isMoviendo()) {
                    if (e.getKeyCode() == 32) {
                        CREADOR_NIVELES_MATRIZ.setMoviendo(true);
                        MOVER_PINTAR.setText("Movimiento");
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (CREADOR_NIVELES_MATRIZ.isMoviendo()) {
                    if (e.getKeyCode() == 32) {
                        CREADOR_NIVELES_MATRIZ.setMoviendo(false);
                        MOVER_PINTAR.setText("Pintando");
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        levelCreatorMatriz = new herramientaLevelCreator.LevelCreatorMatriz();
        panelContenedorObjetos = new javax.swing.JPanel();
        scrollPanel = new javax.swing.JScrollPane();
        panelBarraObjetos = new javax.swing.JPanel();
        panelSeparador = new javax.swing.JPanel();
        lblPaletaObjetos = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuNuevo = new javax.swing.JMenuItem();
        menuGuardar = new javax.swing.JMenuItem();
        menuAbrir = new javax.swing.JMenuItem();
        menuOpciones = new javax.swing.JMenu();
        menuCentrar = new javax.swing.JMenuItem();
        menuListaVelocidad = new javax.swing.JMenu();
        menuX1 = new javax.swing.JMenuItem();
        menuX2 = new javax.swing.JMenuItem();
        menuX3 = new javax.swing.JMenuItem();
        menuX4 = new javax.swing.JMenuItem();
        menuX5 = new javax.swing.JMenuItem();
        menuCanva = new javax.swing.JMenu();
        menuMoverMatriz = new javax.swing.JMenu();
        menuArriba = new javax.swing.JMenuItem();
        menuIzquierda = new javax.swing.JMenuItem();
        menuDerecha = new javax.swing.JMenuItem();
        menuAbajo = new javax.swing.JMenuItem();
        addColumna = new javax.swing.JMenuItem();
        removeColumna = new javax.swing.JMenuItem();
        addFila = new javax.swing.JMenuItem();
        removeFila = new javax.swing.JMenuItem();

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
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        levelCreatorMatrizLayout.setVerticalGroup(
            levelCreatorMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(levelCreatorMatriz);
        levelCreatorMatriz.setBounds(0, 0, 1000, 600);

        panelContenedorObjetos.setForeground(new java.awt.Color(255, 255, 255));
        panelContenedorObjetos.setLayout(null);

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

        panelContenedorObjetos.add(scrollPanel);
        scrollPanel.setBounds(10, 40, 180, 550);

        lblPaletaObjetos.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lblPaletaObjetos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaletaObjetos.setText("Paleta de elementos");
        panelContenedorObjetos.add(lblPaletaObjetos);
        lblPaletaObjetos.setBounds(10, 10, 180, 20);

        getContentPane().add(panelContenedorObjetos);
        panelContenedorObjetos.setBounds(1000, 0, 200, 600);

        menuArchivo.setText("Archivo");
        menuArchivo.setToolTipText("");

        menuNuevo.setText("Nuevo");
        menuNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevoActionPerformed(evt);
            }
        });
        menuArchivo.add(menuNuevo);

        menuGuardar.setText("Guardar");
        menuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGuardarActionPerformed(evt);
            }
        });
        menuArchivo.add(menuGuardar);

        menuAbrir.setText("Abrir");
        menuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(menuAbrir);

        menuBar.add(menuArchivo);

        menuOpciones.setText("Opciones");

        menuCentrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuCentrar.setText("Centrar Vista");
        menuCentrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCentrarActionPerformed(evt);
            }
        });
        menuOpciones.add(menuCentrar);

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

        menuOpciones.add(menuListaVelocidad);

        menuBar.add(menuOpciones);

        menuCanva.setText("Canva");

        menuMoverMatriz.setText("Mover Matriz");

        menuArriba.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_UP, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuArriba.setText("Arriba");
        menuArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArribaActionPerformed(evt);
            }
        });
        menuMoverMatriz.add(menuArriba);

        menuIzquierda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_LEFT, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuIzquierda.setText("Izquierda");
        menuIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIzquierdaActionPerformed(evt);
            }
        });
        menuMoverMatriz.add(menuIzquierda);

        menuDerecha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_RIGHT, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuDerecha.setText("Derecha");
        menuDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDerechaActionPerformed(evt);
            }
        });
        menuMoverMatriz.add(menuDerecha);

        menuAbajo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DOWN, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuAbajo.setText("Abajo");
        menuAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbajoActionPerformed(evt);
            }
        });
        menuMoverMatriz.add(menuAbajo);

        menuCanva.add(menuMoverMatriz);

        addColumna.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_RIGHT, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        addColumna.setText("Add Columna");
        addColumna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addColumnaActionPerformed(evt);
            }
        });
        menuCanva.add(addColumna);

        removeColumna.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_RIGHT, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        removeColumna.setText("Remove Columna");
        removeColumna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeColumnaActionPerformed(evt);
            }
        });
        menuCanva.add(removeColumna);

        addFila.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DOWN, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        addFila.setText("add Fila");
        addFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFilaActionPerformed(evt);
            }
        });
        menuCanva.add(addFila);

        removeFila.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DOWN, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        removeFila.setText("Remove Fila");
        removeFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFilaActionPerformed(evt);
            }
        });
        menuCanva.add(removeFila);

        menuBar.add(menuCanva);

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevoActionPerformed
        int columnas = Integer.parseInt(JOptionPane.showInputDialog("Dijite el numero de columnas"));
        int filas = Integer.parseInt(JOptionPane.showInputDialog("Dijite el numero de filas"));

        if (columnas > 0 && filas > 0) {
            CREADOR_NIVELES_MATRIZ.limpiarMatriz(columnas, filas);
        }
    }//GEN-LAST:event_menuNuevoActionPerformed

    private void menuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGuardarActionPerformed
        String archivo = obtenerUbicacionArchivo();
        if (archivo != null) {
            EscritorLector_Niveles.guardarMatrizComoArchivo(CREADOR_NIVELES_MATRIZ.getMatrizElementos(), archivo);
        }
    }//GEN-LAST:event_menuGuardarActionPerformed

    private void menuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirActionPerformed
        String archivo = obtenerUbicacionArchivo();
        if (archivo != null) {
            CREADOR_NIVELES_MATRIZ.setMatrizElementos(EscritorLector_Niveles.cargarMatrizDesdeArchivo(archivo));
        }
    }//GEN-LAST:event_menuAbrirActionPerformed

    private void menuX1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuX1ActionPerformed
        CREADOR_NIVELES_MATRIZ.setVelocidad(1);
    }//GEN-LAST:event_menuX1ActionPerformed

    private void menuX2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuX2ActionPerformed
        CREADOR_NIVELES_MATRIZ.setVelocidad(2);
    }//GEN-LAST:event_menuX2ActionPerformed

    private void menuX3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuX3ActionPerformed
        CREADOR_NIVELES_MATRIZ.setVelocidad(3);
    }//GEN-LAST:event_menuX3ActionPerformed

    private void menuX4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuX4ActionPerformed
        CREADOR_NIVELES_MATRIZ.setVelocidad(4);
    }//GEN-LAST:event_menuX4ActionPerformed

    private void menuX5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuX5ActionPerformed
        CREADOR_NIVELES_MATRIZ.setVelocidad(5);
    }//GEN-LAST:event_menuX5ActionPerformed

    private void menuCentrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCentrarActionPerformed
        CREADOR_NIVELES_MATRIZ.centrar();
    }//GEN-LAST:event_menuCentrarActionPerformed

    private void addColumnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addColumnaActionPerformed
        CREADOR_NIVELES_MATRIZ.addColumna();
    }//GEN-LAST:event_addColumnaActionPerformed

    private void addFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFilaActionPerformed
        CREADOR_NIVELES_MATRIZ.addFila();
    }//GEN-LAST:event_addFilaActionPerformed

    private void removeFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFilaActionPerformed
        CREADOR_NIVELES_MATRIZ.removeFila();
    }//GEN-LAST:event_removeFilaActionPerformed

    private void removeColumnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeColumnaActionPerformed
        CREADOR_NIVELES_MATRIZ.removeColumna();
    }//GEN-LAST:event_removeColumnaActionPerformed

    private void menuAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbajoActionPerformed
        CREADOR_NIVELES_MATRIZ.recorrerMatriz("abajo");
    }//GEN-LAST:event_menuAbajoActionPerformed

    private void menuArribaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArribaActionPerformed
        CREADOR_NIVELES_MATRIZ.recorrerMatriz("arriba");
    }//GEN-LAST:event_menuArribaActionPerformed

    private void menuIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIzquierdaActionPerformed
        CREADOR_NIVELES_MATRIZ.recorrerMatriz("izquierda");
    }//GEN-LAST:event_menuIzquierdaActionPerformed

    private void menuDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDerechaActionPerformed
        CREADOR_NIVELES_MATRIZ.recorrerMatriz("derecha");
    }//GEN-LAST:event_menuDerechaActionPerformed

    private void inicializarBarraObjetos() {
        for (Map.Entry<String, BufferedImage> elemento : Texturas.getImagenesMap().entrySet()) {
            String key = elemento.getKey();
            BufferedImage imgElemento = elemento.getValue();

            // Se crea el elemento para la barra y se me asigna su interfaz
            PanelElemento panelElementoTemp = new PanelElemento(imgElemento, key, CREADOR_NIVELES_MATRIZ);
            panelBarraObjetos.add(panelElementoTemp);
        }
    }

    public static String obtenerUbicacionArchivo() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setDialogTitle("Seleccionar archivo");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            var archivoSeleccionado = fileChooser.getSelectedFile();
            return archivoSeleccionado.getAbsolutePath();
        } else {
            return null;
        }
    }

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
    private javax.swing.JMenuItem addColumna;
    private javax.swing.JMenuItem addFila;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel lblPaletaObjetos;
    private herramientaLevelCreator.LevelCreatorMatriz levelCreatorMatriz;
    private javax.swing.JMenuItem menuAbajo;
    private javax.swing.JMenuItem menuAbrir;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem menuArriba;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCanva;
    private javax.swing.JMenuItem menuCentrar;
    private javax.swing.JMenuItem menuDerecha;
    private javax.swing.JMenuItem menuGuardar;
    private javax.swing.JMenuItem menuIzquierda;
    private javax.swing.JMenu menuListaVelocidad;
    private javax.swing.JMenu menuMoverMatriz;
    private javax.swing.JMenuItem menuNuevo;
    private javax.swing.JMenu menuOpciones;
    private javax.swing.JMenuItem menuX1;
    private javax.swing.JMenuItem menuX2;
    private javax.swing.JMenuItem menuX3;
    private javax.swing.JMenuItem menuX4;
    private javax.swing.JMenuItem menuX5;
    private javax.swing.JPanel panelBarraObjetos;
    private javax.swing.JPanel panelContenedorObjetos;
    private javax.swing.JPanel panelSeparador;
    private javax.swing.JMenuItem removeColumna;
    private javax.swing.JMenuItem removeFila;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables
}
