package org.poli.controlador;

import org.poli.modelo.EstadoJuego;

/**
 * Conecta el modelo (lógica) con la vista (JavaFX).
 * Aquí vivirá el bucle del juego y la coordinación de turnos.
 * Responsable: Persona 5 (Coordinación / integración).
 */
public class ControladorJuego {

    private final EstadoJuego estado;

    public ControladorJuego(EstadoJuego estado) {
        this.estado = estado;
    }

    public EstadoJuego getEstado() {
        return estado;
    }
}
