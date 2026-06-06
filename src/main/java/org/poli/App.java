package org.poli;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.poli.vista.menu.PantallaMenu;
import org.poli.vista.menu.PantallaModoJuego;
import org.poli.vista.menu.PantallaMenuPractica;

public class App extends Application {

    private Stage ventanaPrincipal;
    private Scene escena;

    @Override
    public void start(Stage stage) {
        this.ventanaPrincipal = stage;

        PantallaMenu menuInicio = new PantallaMenu();
        escena = new Scene(menuInicio, 1000, 650);

        // NAVEGACIÓN
        menuInicio.getBotonJugar().setOnAction(e -> {
            PantallaModoJuego pantallaModos = new PantallaModoJuego();

            pantallaModos.getBotonVolver().setOnAction(ev -> {
                escena.setRoot(menuInicio);
            });

            // Acción del Modo Práctica con el nuevo nombre semántico
            pantallaModos.getBotonPractica().setOnAction(ev -> {
                PantallaMenuPractica pantallaPisosPractica = new PantallaMenuPractica();

                pantallaPisosPractica.getBotonVolver().setOnAction(evVolver -> {
                    escena.setRoot(pantallaModos); // Regresa a la selección de modos
                });

                escena.setRoot(pantallaPisosPractica);
            });

            menuInicio.getBotonJugar().requestFocus();
            escena.setRoot(pantallaModos);
        });

        ventanaPrincipal.setTitle("Dungeons & Code");
        ventanaPrincipal.setScene(escena);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
