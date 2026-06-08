package org.poli;

import javafx.application.Application;
import javafx.stage.Stage;

import org.poli.basedatos.CargadorPreguntas;
import org.poli.basedatos.RepositorioPreguntas;
import org.poli.controlador.Navegador;

/**
 * Punto de entrada de la aplicación JavaFX.
 *
 * Su única responsabilidad es montar las piezas (fuente de preguntas y
 * controlador) y arrancar. Toda la navegación vive en {@link Navegador}.
 */
public class App extends Application {

    @Override
    public void start(Stage escenarioPrincipal) {
        RepositorioPreguntas repositorio = new CargadorPreguntas();
        Navegador navegador = new Navegador(escenarioPrincipal, repositorio);
        navegador.iniciar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
