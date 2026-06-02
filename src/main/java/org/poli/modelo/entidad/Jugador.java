package org.poli.modelo.entidad;

import org.poli.util.Constantes;

/**
 * El personaje que controla quien juega.
 * Responsable: Persona 2 (Jugador & movimiento).
 */
public class Jugador extends Entidad {

    public Jugador(String nombre, int posicionX, int posicionY) {
        super(nombre, Constantes.VIDA_INICIAL_JUGADOR, posicionX, posicionY);
    }
}
