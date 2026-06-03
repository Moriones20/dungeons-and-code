package org.poli.modelo;

/**
 * Representa una pregunta utilizada dentro del juego Dungeons & Code.
 * 
 * Cada pregunta pertenece a un piso específico de la torre y contiene
 * un tema, un enunciado, cuatro opciones de respuesta y la posición
 * de la respuesta correcta.
 * 
 * Esta clase forma parte del modelo del juego y permite almacenar
 * la información necesaria para mostrar preguntas y validar respuestas.
 */
public class Pregunta {

    /**
     * Número del piso al que pertenece la pregunta.
     */
    private int piso;

    /**
     * Tema de programación asociado a la pregunta.
     */
    private String tema;

    /**
     * Texto principal de la pregunta.
     */
    private String enunciado;

    /**
     * Opciones de respuesta disponibles para el jugador.
     */
    private String[] opciones;

    /**
     * Índice de la respuesta correcta dentro del arreglo de opciones.
     * 
     * 0 representa la opción A.
     * 1 representa la opción B.
     * 2 representa la opción C.
     * 3 representa la opción D.
     */
    private int respuestaCorrecta;

    /**
     * Crea una nueva pregunta con su información principal.
     *
     * @param piso número del piso al que pertenece la pregunta
     * @param tema tema educativo relacionado con la pregunta
     * @param enunciado texto de la pregunta
     * @param opciones arreglo con las opciones de respuesta
     * @param respuestaCorrecta índice de la opción correcta
     */
    public Pregunta(int piso, String tema, String enunciado, String[] opciones, int respuestaCorrecta) {
        this.piso = piso;
        this.tema = tema;
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    /**
     * Obtiene el número del piso al que pertenece la pregunta.
     *
     * @return número del piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * Obtiene el tema educativo de la pregunta.
     *
     * @return tema de la pregunta
     */
    public String getTema() {
        return tema;
    }

    /**
     * Obtiene el enunciado de la pregunta.
     *
     * @return texto principal de la pregunta
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * Obtiene las opciones disponibles para responder la pregunta.
     *
     * @return arreglo con las opciones de respuesta
     */
    public String[] getOpciones() {
        return opciones;
    }

    /**
     * Obtiene el índice de la respuesta correcta.
     *
     * @return índice de la respuesta correcta
     */
    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    /**
     * Verifica si la respuesta seleccionada por el jugador es correcta.
     *
     * @param respuestaUsuario índice de la respuesta seleccionada por el jugador
     * @return true si la respuesta coincide con la correcta, false en caso contrario
     */
    public boolean esCorrecta(int respuestaUsuario) {
        return respuestaUsuario == respuestaCorrecta;
    }
}
