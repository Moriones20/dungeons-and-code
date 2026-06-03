package org.poli.basedatos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.poli.modelo.Pregunta;

/**
 * Clase de pruebas unitarias para validar el funcionamiento del cargador
 * de preguntas del juego.
 *
 * Estas pruebas verifican que el archivo preguntas.csv pueda leerse
 * correctamente desde src/main/resources y que los datos cargados se
 * conviertan en objetos Pregunta válidos.
 */
public class CargadorPreguntasTest {

    /**
     * Verifica que el cargador de preguntas pueda leer el archivo CSV
     * y retornar una lista no nula con preguntas cargadas.
     */
    @Test
    public void testCargaPreguntasDesdeCSV() {
        RepositorioPreguntas repositorio = new CargadorPreguntas();

        List<Pregunta> preguntas = repositorio.obtenerPreguntas();

        assertNotNull(preguntas);
        assertFalse(preguntas.isEmpty());
    }

    /**
     * Verifica que la primera pregunta cargada desde el CSV tenga
     * un enunciado válido.
     */
    @Test
    public void testPrimeraPreguntaTieneEnunciado() {
        RepositorioPreguntas repositorio = new CargadorPreguntas();

        List<Pregunta> preguntas = repositorio.obtenerPreguntas();

        Pregunta pregunta = preguntas.get(0);

        assertNotNull(pregunta.getEnunciado());
        assertFalse(pregunta.getEnunciado().isEmpty());
    }

    /**
     * Verifica que la primera pregunta cargada tenga exactamente
     * cuatro opciones de respuesta.
     */
    @Test
    public void testPrimeraPreguntaTieneCuatroOpciones() {
        RepositorioPreguntas repositorio = new CargadorPreguntas();

        List<Pregunta> preguntas = repositorio.obtenerPreguntas();

        Pregunta pregunta = preguntas.get(0);

        assertNotNull(pregunta.getOpciones());
        assertEquals(4, pregunta.getOpciones().length);
    }

    /**
     * Verifica que el método esCorrecta funcione correctamente al comparar
     * la respuesta seleccionada por el usuario con la respuesta correcta.
     */
    @Test
    public void testValidacionDeRespuestaCorrecta() {
        String[] opciones = {
                "String",
                "int",
                "boolean",
                "double"
        };

        Pregunta pregunta = new Pregunta(
                1,
                "Variables y tipos de datos",
                "¿Qué tipo de dato se usa para almacenar números enteros?",
                opciones,
                1
        );

        assertTrue(pregunta.esCorrecta(1));
        assertFalse(pregunta.esCorrecta(0));
    }

    /**
     * Verifica que los datos básicos de una pregunta creada manualmente
     * se almacenen correctamente.
     */
    @Test
    public void testDatosBasicosDePregunta() {
        String[] opciones = {
                "if",
                "for",
                "while",
                "switch"
        };

        Pregunta pregunta = new Pregunta(
                3,
                "Condicionales",
                "¿Qué palabra se usa para evaluar una condición?",
                opciones,
                0
        );

        assertEquals(3, pregunta.getPiso());
        assertEquals("Condicionales", pregunta.getTema());
        assertEquals("¿Qué palabra se usa para evaluar una condición?", pregunta.getEnunciado());
        assertEquals("if", pregunta.getOpciones()[0]);
    }
}