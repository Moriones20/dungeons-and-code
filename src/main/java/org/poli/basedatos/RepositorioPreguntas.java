package org.poli.basedatos;

import java.util.List;

import org.poli.modelo.Pregunta;

/**
 * Define las operaciones necesarias para obtener preguntas del juego.
 * 
 * Esta interfaz permite separar la fuente de datos de la lógica principal.
 * Las preguntas podrían obtenerse desde un archivo CSV, una base de datos
 * u otra fuente sin afectar el resto del programa.
 */
public interface RepositorioPreguntas {

    /**
     * Obtiene la lista de preguntas disponibles para el juego.
     *
     * @return lista de preguntas cargadas desde la fuente de datos
     */
    List<Pregunta> obtenerPreguntas();
}
