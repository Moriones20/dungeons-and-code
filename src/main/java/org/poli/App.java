package org.poli;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.poli.util.Constantes;

/**
 * Punto de entrada de la aplicación JavaFX.
 *
 * Esta clase configura la ventana principal (Stage) y la escena inicial.
 * Idealmente NADIE la modifica sin acordarlo con el equipo, porque la tocan todos.
 */
public class App extends Application {

    @Override
    public void start(Stage escenarioPrincipal) {
        // Pantalla provisional: cada equipo irá reemplazando esto por sus vistas.
        Label titulo = new Label("Dungeons & Code");
        StackPane raiz = new StackPane(titulo);

        Scene escena = new Scene(raiz, Constantes.ANCHO_VENTANA, Constantes.ALTO_VENTANA);

        escenarioPrincipal.setTitle(Constantes.TITULO_JUEGO);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
