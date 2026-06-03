package org.poli.basedatos;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.poli.modelo.Pregunta;

/**
 * Carga las preguntas del juego desde un archivo CSV ubicado en resources.
 * 
 * Esta clase implementa la interfaz RepositorioPreguntas y permite cargar
 * preguntas externas sin escribirlas directamente dentro del código fuente.
 */
public class CargadorPreguntas implements RepositorioPreguntas {

    /**
     * Nombre del archivo CSV que contiene las preguntas del juego.
     * 
     * El archivo debe estar ubicado en:
     * src/main/resources/preguntas.csv
     */
    private static final String ARCHIVO_PREGUNTAS = "preguntas.csv";

    /**
     * Lee el archivo CSV y convierte cada línea en un objeto Pregunta.
     *
     * @return lista de preguntas cargadas desde el archivo CSV
     */
    @Override
    public List<Pregunta> obtenerPreguntas() {
        List<Pregunta> preguntas = new ArrayList<>();

        try {
            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream(ARCHIVO_PREGUNTAS);

            if (inputStream == null) {
                System.out.println("No se encontró el archivo: " + ARCHIVO_PREGUNTAS);
                return preguntas;
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            );

            String linea;

            br.readLine(); // Salta el encabezado del CSV

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",", -1);

                int piso = Integer.parseInt(limpiarDato(datos[0]));
                String tema = limpiarDato(datos[1]);
                String enunciado = limpiarDato(datos[2]);

                String[] opciones = {
                        limpiarDato(datos[3]),
                        limpiarDato(datos[4]),
                        limpiarDato(datos[5]),
                        limpiarDato(datos[6])
                };

                int respuestaCorrecta = Integer.parseInt(limpiarDato(datos[7]));

                Pregunta pregunta = new Pregunta(
                        piso,
                        tema,
                        enunciado,
                        opciones,
                        respuestaCorrecta
                );

                preguntas.add(pregunta);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error al cargar preguntas: " + e.getMessage());
        }

        return preguntas;
    }

    /**
     * Limpia espacios y comillas innecesarias de cada dato leído del CSV.
     *
     * @param dato valor leído desde el archivo CSV
     * @return dato limpio sin comillas ni espacios externos
     */
    private String limpiarDato(String dato) {
        return dato.trim().replace("\"", "");
    }
}