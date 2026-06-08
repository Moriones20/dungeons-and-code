package org.poli.util;

/**
 * Constantes globales del juego (configuración ajustable).
 *
 * OJO: este archivo lo comparten los 5. Añade tus constantes en tu sección
 * y no reordenes las de los demás, para evitar conflictos de merge.
 */
public final class Constantes {

    private Constantes() {
        // Clase de utilidad: no se instancia.
    }

    // --- Ventana ---
    public static final String TITULO_JUEGO = "Dungeons & Code";
    public static final int ANCHO_VENTANA = 800;
    public static final int ALTO_VENTANA = 600;

    // --- Reglas del juego (ajustad estos valores a vuestro gusto) ---
    public static final int VIDAS_INICIALES = 3;
    public static final int PUNTOS_POR_ACIERTO = 10;
    public static final int TOTAL_PISOS_REGULAR = 5;
}
