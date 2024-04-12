package utils;

import graficos.Texturas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorLector_Niveles {

    public static void guardarMatrizComoArchivo(CasillaNivel[][] matrizElementos, String nombreArchivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            escritor.write("Coordenadas del nivel -> " + nombreArchivo + ".txt\n\n");
            escritor.write("Dimensiones:\n" + matrizElementos.length + "," + matrizElementos[0].length + "\n\n");

            StringBuilder contenido = new StringBuilder();
            for (CasillaNivel[] arrayElementos : matrizElementos) {
                for (CasillaNivel elemento : arrayElementos) {
                    // Solo si contiene una imagen
                    if (elemento.getImagenElemento() != null) {
                        int xElemento = elemento.getX();
                        int yElemento = elemento.getY();
                        String nombreElemento = elemento.getNombreElemento();

                        // Añadir a la String la informacion de la casilla
                        contenido.append("Coordenada:\n").append(xElemento).append(",").append(yElemento).append("\n");
                        contenido.append("Elemento:\n").append(nombreElemento).append("\n\n");
                    }
                }
            }
            // Escribir todo el contenido al archivo
            escritor.write(contenido.toString());

            System.out.println("Matriz guardada correctamente en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static CasillaNivel[][] cargarMatrizDesdeArchivo(String nombreArchivo) {
        CasillaNivel[][] matrizElementos = null;
        String lineaEscaneada;

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            while ((lineaEscaneada = lector.readLine()) != null) {
                if (lineaEscaneada.equals("Coordenada:")) {
                    lineaEscaneada = lector.readLine();
                    String[] partes = lineaEscaneada.split(",");

                    int columnaArchivo = Integer.parseInt(partes[0]);
                    int filaArchivo = Integer.parseInt(partes[1]);

                    lineaEscaneada = lector.readLine(); // Leer siguiente linea (Elemento:)
                    String nombreElemento = lector.readLine();

                    // Crear la casilla temporal y agregarla a la Matriz
                    CasillaNivel casilla = new CasillaNivel();
                    casilla.setCoordenadas(columnaArchivo, filaArchivo);
                    casilla.setNombreElemento(nombreElemento);
                    casilla.setImagenElemento(Texturas.getTextura(nombreElemento));
                    matrizElementos[columnaArchivo][filaArchivo] = casilla;
                }
                
                // Se ejecuta 1 vez para inicializar las dimensiones de la matriz
                if (lineaEscaneada.equals("Dimensiones:")) {
                    lineaEscaneada = lector.readLine();
                    String[] partes = lineaEscaneada.split(",");

                    int columnas = Integer.parseInt(partes[0]);
                    int filas = Integer.parseInt(partes[1]);

                    matrizElementos = new CasillaNivel[columnas][filas];
                    for (int i = 0; i < matrizElementos.length; i++) {
                        for (int j = 0; j < matrizElementos[0].length; j++) {
                            matrizElementos[i][j] = new CasillaNivel();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el Pixel Art: " + e.getMessage());
        }

        return matrizElementos;
    }

}
