package org.poli.modelo;

import org.poli.modelo.entidad.Jugador;
import org.poli.modelo.mazmorra.Mapa;

/**
 * Contiene el estado completo de una partida (jugador, mapa actual, etc.).
 * Es el "pegamento" del modelo. Responsable: Persona 5 (Coordinación).
 */
public class EstadoJuego {

    private Jugador jugador;
    private Mapa mapaActual;

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Mapa getMapaActual() {
        return mapaActual;
    }

    public void setMapaActual(Mapa mapaActual) {
        this.mapaActual = mapaActual;
    }
}
