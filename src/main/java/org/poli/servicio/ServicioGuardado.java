package org.poli.servicio;

import org.poli.modelo.EstadoJuego;

/**
 * Guarda y carga partidas (a archivo, base de datos, etc.).
 * Responsable: Persona 5 (Persistencia).
 */
public class ServicioGuardado {

    /** Guarda la partida actual. Implementación pendiente. */
    public void guardar(EstadoJuego estado) {
        // TODO: serializar el estado a disco.
    }

    /** Carga una partida guardada. Implementación pendiente. */
    public EstadoJuego cargar() {
        // TODO: leer el estado desde disco.
        return new EstadoJuego();
    }
}
