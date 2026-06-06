package org.poli.navegador;

import javafx.stage.Stage;

/**
 * El "sistema de ventanas": cambia la pantalla que se ve (menú, juego, resultado).
 *
 * BASE para implementar. Responsable: Persona 1 (Sistema de ventanas y botones).
 *
 * Idea: guardar una sola Scene y métodos como irAlMenu(), irAlJuego(),
 * irAResultado(...) que cambien el contenido. Cada botón de las pantallas
 * llamará a estos métodos.
 */
public class Navegador {

    private final Stage escenario;

    public Navegador(Stage escenario) {
        this.escenario = escenario;
    }

    // TODO (Persona 1): crear la Scene y los métodos para cambiar de pantalla.
}
