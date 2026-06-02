package org.poli.modelo.entidad;

/**
 * Enemigo que habita la mazmorra.
 * Responsable: Persona 3 (Combate & enemigos).
 */
public class Enemigo extends Entidad {

    private int danioAtaque;

    public Enemigo(String nombre, int vida, int danioAtaque, int posicionX, int posicionY) {
        super(nombre, vida, posicionX, posicionY);
        this.danioAtaque = danioAtaque;
    }

    public int getDanioAtaque() {
        return danioAtaque;
    }
}
