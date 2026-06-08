package org.poli.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias del modelo {@link Partida}.
 *
 * Verifican la lógica de combate (aciertos, vidas, barra del enemigo y las
 * condiciones de victoria/derrota) sin necesidad de abrir una ventana JavaFX.
 */
public class PartidaTest {

    /** Crea una lista de {@code cantidad} preguntas cuya opción correcta es la 0. */
    private List<Pregunta> preguntasDePrueba(int cantidad) {
        List<Pregunta> preguntas = new ArrayList<>();
        String[] opciones = {"correcta", "mala1", "mala2", "mala3"};
        for (int i = 0; i < cantidad; i++) {
            preguntas.add(new Pregunta(1, "Tema", "Pregunta " + i, opciones, 0));
        }
        return preguntas;
    }

    @Test
    public void partidaNuevaEstaEnCurso() {
        Partida partida = new Partida(preguntasDePrueba(3), 3);

        assertTrue(partida.estaEnCurso());
        assertEquals(3, partida.getVidas());
        assertEquals(0, partida.getAciertos());
        assertEquals(3, partida.getTotalPreguntas());
        assertEquals(1.0, partida.getVidaEnemigo(), 0.0001);
    }

    @Test
    public void aciertoSumaYDaniaAlEnemigoSinPerderVidas() {
        Partida partida = new Partida(preguntasDePrueba(3), 3);

        boolean acierto = partida.responder(0); // opción correcta

        assertTrue(acierto);
        assertEquals(1, partida.getAciertos());
        assertEquals(3, partida.getVidas());
        assertTrue(partida.estaEnCurso());
        // 1 de 3 aciertos => al enemigo le queda ~2/3 de vida
        assertEquals(1.0 - (1.0 / 3.0), partida.getVidaEnemigo(), 0.0001);
    }

    @Test
    public void falloRestaUnaVida() {
        Partida partida = new Partida(preguntasDePrueba(3), 3);

        boolean acierto = partida.responder(1); // opción incorrecta

        assertFalse(acierto);
        assertEquals(0, partida.getAciertos());
        assertEquals(2, partida.getVidas());
        assertTrue(partida.estaEnCurso());
    }

    @Test
    public void responderTodasBienEsVictoria() {
        Partida partida = new Partida(preguntasDePrueba(3), 3);

        partida.responder(0);
        partida.responder(0);
        partida.responder(0);

        assertTrue(partida.hayVictoria());
        assertFalse(partida.estaEnCurso());
        assertEquals(3, partida.getAciertos());
        assertEquals(0.0, partida.getVidaEnemigo(), 0.0001);
    }

    @Test
    public void sobrevivirHastaElFinalEsVictoriaAunqueSeFalle() {
        Partida partida = new Partida(preguntasDePrueba(3), 3);

        partida.responder(0); // bien
        partida.responder(1); // mal (queda 1 vida menos)
        partida.responder(0); // bien -> fin de las preguntas

        assertTrue(partida.hayVictoria());
        assertEquals(2, partida.getAciertos());
        assertEquals(2, partida.getVidas());
    }

    @Test
    public void quedarseSinVidasEsDerrota() {
        Partida partida = new Partida(preguntasDePrueba(3), 3);

        partida.responder(1); // mal -> 2 vidas
        partida.responder(1); // mal -> 1 vida
        partida.responder(1); // mal -> 0 vidas

        assertTrue(partida.hayDerrota());
        assertFalse(partida.estaEnCurso());
        assertEquals(0, partida.getVidas());
    }

    @Test
    public void unicaVidaYFalloEsDerrotaInmediata() {
        Partida partida = new Partida(preguntasDePrueba(3), 1);

        partida.responder(1); // mal con una sola vida

        assertTrue(partida.hayDerrota());
        assertEquals(0, partida.getVidas());
    }

    @Test
    public void noSePuedeResponderTrasTerminarLaPartida() {
        Partida partida = new Partida(preguntasDePrueba(1), 3);

        partida.responder(0); // termina en victoria
        assertTrue(partida.hayVictoria());

        boolean resultado = partida.responder(0); // intento extra
        assertFalse(resultado);
        assertEquals(1, partida.getAciertos()); // no cambia el estado
    }

    @Test
    public void partidaSinPreguntasNoSeQuedaEnCurso() {
        Partida partida = new Partida(new ArrayList<>(), 3);

        assertFalse(partida.estaEnCurso());
        assertEquals(0, partida.getTotalPreguntas());
        assertEquals(0.0, partida.getVidaEnemigo(), 0.0001);
    }
}
