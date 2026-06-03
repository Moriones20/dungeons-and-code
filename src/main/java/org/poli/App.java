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
 * Por ahora solo abre una ventana con el título: es la BASE funcional.
 * Persona 1 cambiará esto para arrancar el Navegador en la pantalla de menú.
 */
public class App extends Application {

    @Override
    public void start(Stage escenarioPrincipal) {
        // TODO (Persona 1): reemplazar esta pantalla provisional por:
        //   Navegador navegador = new Navegador(escenarioPrincipal, repositorio);
        //   navegador.irAlMenu();
        Label titulo = new Label(Constantes.TITULO_JUEGO);
        StackPane raiz = new StackPane(titulo);

        Scene escena = new Scene(raiz, Constantes.ANCHO_VENTANA, Constantes.ALTO_VENTANA);

        escenarioPrincipal.setTitle(Constantes.TITULO_JUEGO);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
    }
}
