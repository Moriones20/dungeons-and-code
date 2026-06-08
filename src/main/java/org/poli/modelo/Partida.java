package org.poli.modelo;

import java.util.List;

/**
 * Representa el estado y las reglas de una partida (un piso) del juego.
 *
 * Esta clase concentra TODA la lógica del combate: vidas del jugador,
 * progreso contra el enemigo, validación de respuestas y condiciones de
 * victoria o derrota. Forma parte del MODELO y por tanto NO importa JavaFX:
 * puede probarse sin abrir una ventana.
 *
 * La vista (PantallaJuegoRegular) solo refleja este estado y le reenvía las
 * pulsaciones del jugador; las decisiones del juego se toman aquí.
 */
public class Partida {

    /** Estados posibles de la partida. */
    public enum Estado { EN_CURSO, VICTORIA, DERROTA }

    private final List<Pregunta> preguntas;
    private int indiceActual;
    private int vidas;
    private int aciertos;
    private Estado estado;

    /**
     * Crea una partida con su lista de preguntas y las vidas iniciales.
     *
     * @param preguntas      preguntas del piso a jugar
     * @param vidasIniciales número de vidas con las que empieza el jugador
     */
    public Partida(List<Pregunta> preguntas, int vidasIniciales) {
        this.preguntas = preguntas;
        this.vidas = vidasIniciales;
        this.indiceActual = 0;
        this.aciertos = 0;
        this.estado = (preguntas == null || preguntas.isEmpty())
                ? Estado.VICTORIA
                : Estado.EN_CURSO;
    }

    /**
     * Devuelve la pregunta que el jugador debe responder ahora.
     *
     * @return pregunta actual
     */
    public Pregunta getPreguntaActual() {
        return preguntas.get(indiceActual);
    }

    /**
     * Procesa la respuesta del jugador y actualiza el estado de la partida.
     *
     * Un acierto daña al enemigo; un fallo cuesta una vida. Si las vidas
     * llegan a 0 hay derrota; si se responden todas las preguntas con vida
     * restante hay victoria.
     *
     * @param opcion índice de la opción elegida (0=A, 1=B, 2=C, 3=D)
     * @return true si la respuesta fue correcta
     */
    public boolean responder(int opcion) {
        if (estado != Estado.EN_CURSO) {
            return false;
        }

        boolean acierto = getPreguntaActual().esCorrecta(opcion);

        if (acierto) {
            aciertos++;
        } else {
            vidas--;
        }

        if (vidas <= 0) {
            estado = Estado.DERROTA;
            return acierto;
        }

        indiceActual++;
        if (indiceActual >= preguntas.size()) {
            estado = Estado.VICTORIA;
        }
        return acierto;
    }

    public boolean estaEnCurso() {
        return estado == Estado.EN_CURSO;
    }

    public boolean hayVictoria() {
        return estado == Estado.VICTORIA;
    }

    public boolean hayDerrota() {
        return estado == Estado.DERROTA;
    }

    public Estado getEstado() {
        return estado;
    }

    public int getVidas() {
        return vidas;
    }

    public int getAciertos() {
        return aciertos;
    }

    public int getTotalPreguntas() {
        return preguntas == null ? 0 : preguntas.size();
    }

    /**
     * Fracción de vida restante del enemigo, para la barra de progreso.
     *
     * @return valor entre 0.0 (derrotado) y 1.0 (intacto)
     */
    public double getVidaEnemigo() {
        if (preguntas == null || preguntas.isEmpty()) {
            return 0.0;
        }
        double restante = 1.0 - ((double) aciertos / preguntas.size());
        return restante < 0 ? 0.0 : restante;
    }
}
