package org.poli;

import javafx.application.Application;

/**
 * Clase lanzadora del juego.
 *
 * NO extiende Application a propósito: cuando se ejecuta desde el classpath
 * (como hace IntelliJ), la JVM no sabe arrancar directamente una clase que
 * extiende Application. Esta clase intermedia evita ese problema.
 *
 * >>> Ejecuta ESTA clase (org.poli.Launcher), no App. <<<
 */
public class Launcher {

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
