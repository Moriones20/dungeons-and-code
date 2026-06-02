package org.poli.modelo.combate;

import org.poli.modelo.entidad.Enemigo;
import org.poli.modelo.entidad.Jugador;

/**
 * Resuelve los combates entre el jugador y los enemigos.
 * Responsable: Persona 3 (Combate & enemigos).
 */
public class SistemaCombate {

    /** El enemigo ataca al jugador. */
    public void atacar(Enemigo atacante, Jugador objetivo) {
        objetivo.recibirDanio(atacante.getDanioAtaque());
    }
}
