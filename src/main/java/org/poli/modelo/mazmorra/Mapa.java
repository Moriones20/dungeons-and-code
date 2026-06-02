package org.poli.modelo.mazmorra;

/**
 * Representa la mazmorra: una cuadrícula de casillas.
 * Responsable: Persona 1 (Mapa/Mazmorra).
 */
public class Mapa {

    private final int ancho;
    private final int alto;

    public Mapa(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    public boolean dentroDeLimites(int x, int y) {
        return x >= 0 && x < ancho && y >= 0 && y < alto;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
