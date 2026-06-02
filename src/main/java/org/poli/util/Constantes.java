package org.poli.util;

/**
 * Constantes globales del juego.
 *
 * OJO: este archivo lo comparten los 5. Para evitar conflictos de merge,
 * añade tus constantes en tu propia sección y no reordenes las de los demás.
 */
public final class Constantes {

    private Constantes() {
        // Clase de utilidad: no se instancia.
    }

    // --- Ventana ---
    public static final String TITULO_JUEGO = "Dungeons & Code";
    public static final int ANCHO_VENTANA = 1280;
    public static final int ALTO_VENTANA = 720;

    // --- Mapa / Mazmorra ---
    public static final int TAMANO_CASILLA = 32;

    // --- Jugador ---
    public static final int VIDA_INICIAL_JUGADOR = 100;
}
