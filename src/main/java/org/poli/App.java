package org.poli;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.poli.basedatos.CargadorPreguntas;
import org.poli.modelo.Pregunta;
import org.poli.vista.menu.PantallaMenu;
import org.poli.vista.menu.PantallaModoJuego;
import org.poli.vista.menu.PantallaMenuPractica;
import org.poli.vista.menu.PantallaInstrucciones;
import org.poli.vista.juego.PantallaJuegoRegular;
import org.poli.vista.juego.PantallaPractica;

public class App extends Application {

    private Stage ventanaPrincipal;
    private Scene escena;

    @Override
    public void start(Stage stage) {
        this.ventanaPrincipal = stage;

        // Inicializar la pantalla de inicio con la resolución fija del proyecto
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

            // ----------------------------------------------------
            // JUGAR EN MODO REGULAR
            // ----------------------------------------------------
            pantallaModos.getBotonRegular().setOnAction(ev -> {
                // Instanciamos el escenario general de batalla
                PantallaJuegoRegular combatePiso = new PantallaJuegoRegular();

                // Acción para salir o rendirse y volver a la selección de modos
                combatePiso.getBotonRendirse().setOnAction(evVolver -> {
                    escena.setRoot(pantallaModos);
                });

                // Simulaciones de clics de prueba para el equipo
                combatePiso.getBotonOpcionA().setOnAction(click -> {
                    // Si eligen la A (Correcta de prueba), golpeamos al Golem 25%
                    combatePiso.infligirDanioEnemigo(0.25);
                });

                combatePiso.getBotonOpcionB().setOnAction(click -> {
                    // Si eligen la B (Incorrecta de prueba), el héroe pierde una vida
                    combatePiso.reducirVidaJugador();
                });

                escena.setRoot(combatePiso); // Desplegar el campo de batalla
            });

            // ----------------------------------------------------
            // MODO PRÁCTICA
            // ----------------------------------------------------
            pantallaModos.getBotonPractica().setOnAction(ev -> {
                PantallaMenuPractica pantallaPisosPractica = new PantallaMenuPractica();

                // BOTONES DE LOS PISOS
                pantallaPisosPractica.getBotonPiso1().setOnAction(e1 -> {
                    abrirPractica(1, pantallaPisosPractica);
                });

                pantallaPisosPractica.getBotonPiso2().setOnAction(e2 -> {
                    abrirPractica(2, pantallaPisosPractica);
                });

                pantallaPisosPractica.getBotonPiso3().setOnAction(e3 -> {
                    abrirPractica(3, pantallaPisosPractica);
                });

                pantallaPisosPractica.getBotonPiso4().setOnAction(e4 -> {
                    abrirPractica(4, pantallaPisosPractica);
                });

                pantallaPisosPractica.getBotonPiso5().setOnAction(e5 -> {
                    abrirPractica(5, pantallaPisosPractica);
                });

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
            PantallaInstrucciones pantallaReglas = new PantallaInstrucciones();

            // Configurar acción para regresar al menú principal
            pantallaReglas.getBotonVolver().setOnAction(ev -> {
                escena.setRoot(menuInicio);
            });

            menuInicio.getBotonInstrucciones().requestFocus(); // Limpieza de foco
            escena.setRoot(pantallaReglas); // Cambia el escenario a las instrucciones
        });

        // Configuración y despliegue de la ventana principal de JavaFX
        ventanaPrincipal.setTitle("Dungeons & Code");
        ventanaPrincipal.setScene(escena);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.show();
    }

    /**
     * Abre el modo práctica para el piso seleccionado.
     */
    private void abrirPractica(int piso, PantallaMenuPractica pantallaPisosPractica) {
        List<Pregunta> preguntas = obtenerPreguntasPorPiso(piso);
        PantallaPractica pantalla = new PantallaPractica(preguntas);

        // Volver de la pantalla de juego de práctica al selector de pisos
        pantalla.getBtnVolver().setOnAction(ev -> {
            escena.setRoot(pantallaPisosPractica);
        });

        escena.setRoot(pantalla);
    }

    /**
     * Obtiene todas las preguntas de un piso específico.
     */
    private List<Pregunta> obtenerPreguntasPorPiso(int piso) {
        CargadorPreguntas cargador = new CargadorPreguntas();
        List<Pregunta> resultado = new ArrayList<>();

        for (Pregunta p : cargador.obtenerPreguntas()) {
            if (p.getPiso() == piso) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
