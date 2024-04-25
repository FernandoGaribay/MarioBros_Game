package herramientasPixelArt;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class PixelArtCreatorFrame extends JFrame {

    private static int WIDTH = 786;
    private static int HEIGHT = 802;

    private static PixelArtCreatorFrame frame;
    private static PixelArtCreator panelCreator;
    private List<Color> coloresRecientesList;

    JMenuBar menuBar = new JMenuBar();
    Component espaciado = Box.createHorizontalGlue();
    JMenu rellenoActivo = new JMenu("Relleno Activo");

    public PixelArtCreatorFrame(String titulo, int filas, int columnas) {
        this.coloresRecientesList = new ArrayList<>();

        setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        initEventos();
    }

    private void initEventos() {
        JMenu menuOptions = new JMenu("Opciones");
        JMenu opcionesArchivo = new JMenu("Archivo");
        JMenu coloresRecientes = new JMenu("Colores Recientes");

        // MENU SELECCIONAR COLOR ----------------------------------------------
        JMenuItem menuItemSelectColor = new JMenuItem("Seleccionar Color");
        menuItemSelectColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = showColorChooserDialog();
                if (color != null) {
                    panelCreator.setColor(color);
                    agregarColorReciente(color);
                    actualizarMenuColoresRecientes(coloresRecientes);
                }
            }
        });
        menuOptions.add(menuItemSelectColor);

        // MENU ACTIVAR RELLENO ------------------------------------------------
        JMenuItem menuItemRelleno = new JMenuItem("Activar Relleno");
        menuItemRelleno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panelCreator.isFuncionRelleno()) {
                    panelCreator.setFuncionRelleno(false);
                    removeIndicadorRelleno();
                } else {
                    panelCreator.setFuncionRelleno(true);
                    menuBar.add(espaciado);
                    menuBar.add(rellenoActivo);
                }
                menuBar.revalidate();
                menuBar.repaint();
            }
        });
        menuOptions.add(menuItemRelleno);

        // MENU CAMBIAR BACKGROUND ---------------------------------------------
        JMenuItem menuItemBackground = new JMenuItem("Cambiar Color Background");
        menuItemBackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = showColorChooserDialog();
                if (color != null) {
                    panelCreator.setBackground(color);
                    panelCreator.repaint();
                }
            }
        });
        menuOptions.add(menuItemBackground);

        // MENU NUEVO ARCHIVO --------------------------------------------------
        JMenuItem menuNuevo = new JMenuItem("Nuevo");
        menuNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filas = Integer.valueOf(JOptionPane.showInputDialog("Dijite el numero de filas"));
                int columnas = Integer.valueOf(JOptionPane.showInputDialog("Dijite el numero de columnas"));

                resetDibujo(filas, columnas);
            }
        });
        opcionesArchivo.add(menuNuevo);

        // MENU GUARDAR ARCHIVO ------------------------------------------------
        JMenuItem menuGuardar = new JMenuItem("Guardar");
        menuGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = obtenerUbicacionArchivo();
                panelCreator.guardarMatrizComoArchivo(nombre);
            }
        });
        opcionesArchivo.add(menuGuardar);

        // MENU ABRIR ARCHIVO --------------------------------------------------
        JMenuItem menuAbrir = new JMenuItem("Abrir");
        menuAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = obtenerUbicacionArchivo();
                panelCreator.cargarMatrizDesdeArchivo(nombre);
            }
        });
        opcionesArchivo.add(menuAbrir);

        menuBar.add(opcionesArchivo);
        menuBar.add(menuOptions);
        menuBar.add(coloresRecientes);

        setJMenuBar(menuBar);
    }

    private void agregarColorReciente(Color color) {
        coloresRecientesList.add(0, color);

        if (coloresRecientesList.size() > 10) {
            coloresRecientesList = coloresRecientesList.subList(0, 5);
        }
    }

    private void actualizarMenuColoresRecientes(JMenu coloresRecientes) {
        JMenu menuTemp = coloresRecientes;
        menuTemp.removeAll();

        for (Color color : coloresRecientesList) {
            JMenuItem menuItem = new JMenuItem("R: " + color.getRed() + " G: " + color.getGreen() + " B: " + color.getBlue());
            menuItem.setBackground(color);
            menuItem.setOpaque(true);
            menuItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        panelCreator.setColor(color);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        coloresRecientesList.remove(color);
                        actualizarMenuColoresRecientes(coloresRecientes);
                    }
                }
            });
            menuTemp.add(menuItem);
        }
    }

    private static void resetDibujo(int filas, int columnas) {
        panelCreator = new PixelArtCreator(filas, columnas);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panelCreator);
        frame.repaint();
        frame.revalidate();
    }

    public void removeIndicadorRelleno() {
        menuBar.remove(espaciado);
        menuBar.remove(rellenoActivo);
        menuBar.revalidate();
        menuBar.repaint();
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

    private Color showColorChooserDialog() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);

        JColorChooser colorChooser = new JColorChooser();

        dialog.add(colorChooser);
        dialog.pack();
        dialog.setSize(650, 350);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        Color selectedColor = colorChooser.getColor();
        dialog.dispose();

        return selectedColor;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new PixelArtCreatorFrame("PixelArt Creator", 16, 16);
            resetDibujo(16, 16);
        });
    }
}
