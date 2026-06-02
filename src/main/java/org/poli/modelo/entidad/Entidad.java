package org.poli.modelo.entidad;

/**
 * Clase base de todo lo que vive en la mazmorra (jugador, enemigos...).
 *
 * Pertenece al paquete modelo: NO debe importar nada de JavaFX.
 * Así la lógica se puede probar con JUnit sin abrir una ventana.
 */
public abstract class Entidad {

    private String nombre;
    private int vida;
    private int posicionX;
    private int posicionY;

    protected Entidad(String nombre, int vida, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.vida = vida;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    /** Aplica daño a la entidad. La vida nunca baja de 0. */
    public void recibirDanio(int cantidad) {
        this.vida = Math.max(0, this.vida - cantidad);
    }

    public boolean estaViva() {
        return vida > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicion(int x, int y) {
        this.posicionX = x;
        this.posicionY = y;
    }
}
