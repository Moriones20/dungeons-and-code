package org.poli;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.poli.vista.menu.PantallaMenu;
import org.poli.vista.menu.PantallaModoJuego;
import org.poli.vista.menu.PantallaMenuPractica;
import org.poli.vista.menu.PantallaInstrucciones;

public class App extends Application {

    private Stage ventanaPrincipal;
    private Scene escena;

    @Override
    public void start(Stage stage) {
        this.ventanaPrincipal = stage;

        //Inicializar la pantalla de inicio con la resolución fija del proyecto
        PantallaMenu menuInicio = new PantallaMenu();
        escena = new Scene(menuInicio, 1000, 650);

        // ==========================================
        // EVENTO 1: BOTÓN JUGAR (FLUJO DE JUEGO)
        // ==========================================
        menuInicio.getBotonJugar().setOnAction(e -> {
            PantallaModoJuego pantallaModos = new PantallaModoJuego();

            // Volver desde la selección de modos al menú de inicio
            pantallaModos.getBotonVolver().setOnAction(ev -> {
                escena.setRoot(menuInicio);
            });

            // Ir al Modo Práctica (Selección de Pisos)
            pantallaModos.getBotonPractica().setOnAction(ev -> {
                PantallaMenuPractica pantallaPisosPractica = new PantallaMenuPractica();

                // Volver desde el menú de práctica a la selección de modos
                pantallaPisosPractica.getBotonVolver().setOnAction(evVolver -> {
                    escena.setRoot(pantallaModos);
                });

                escena.setRoot(pantallaPisosPractica);
            });

            menuInicio.getBotonJugar().requestFocus();
            escena.setRoot(pantallaModos); // Cambiar a la pantalla de modos de juego
        });

        // ==========================================
        // EVENTO 2: BOTÓN INSTRUCCIONES
        // ==========================================
        menuInicio.getBotonInstrucciones().setOnAction(e -> {
            // Instanciamos el pergamino medieval de reglas
            PantallaInstrucciones pantallaReglas = new PantallaInstrucciones();

            // Configurar acción para regresar al menú principal
            pantallaReglas.getBotonVolver().setOnAction(ev -> {
                escena.setRoot(menuInicio);
            });

            menuInicio.getBotonInstrucciones().requestFocus(); // Limpieza de foco
            escena.setRoot(pantallaReglas); // Cambia el escenario a las instrucciones
        });

        // 2. Configuración y despliegue de la ventana principal de JavaFX
        ventanaPrincipal.setTitle("Dungeons & Code");
        ventanaPrincipal.setScene(escena);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
