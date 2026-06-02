package org.poli.modelo.entidad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Ejemplo de prueba unitaria con JUnit 5.
 * Fíjate en que NO necesita JavaFX: probamos solo la lógica del modelo.
 */
class JugadorTest {

    @Test
    void elJugadorEmpiezaVivo() {
        Jugador jugador = new Jugador("Héroe", 0, 0);
        assertTrue(jugador.estaViva());
        assertEquals(100, jugador.getVida());
    }

    @Test
    void laVidaNoBajaDeCero() {
        Jugador jugador = new Jugador("Héroe", 0, 0);
        jugador.recibirDanio(150);
        assertEquals(0, jugador.getVida());
        assertFalse(jugador.estaViva());
    }
}
