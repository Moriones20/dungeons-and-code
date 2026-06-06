package org.poli;

import java.util.ArrayList;
import java.util.List;

import org.poli.basedatos.CargadorPreguntas;
import org.poli.modelo.Pregunta;
import org.poli.vista.juego.PantallaPractica;
import org.poli.vista.menu.PantallaInstrucciones;
import org.poli.vista.menu.PantallaMenu;
import org.poli.vista.menu.PantallaMenuPractica;
import org.poli.vista.menu.PantallaModoJuego;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

            // Volver desde selección de modos al menú principal
            pantallaModos.getBotonVolver().setOnAction(ev -> {
                escena.setRoot(menuInicio);
            });

            // Modo práctica
            pantallaModos.getBotonPractica().setOnAction(ev -> {

                PantallaMenuPractica pantallaPisosPractica =
                        new PantallaMenuPractica();

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

                // Volver al menú de modos
                pantallaPisosPractica.getBotonVolver().setOnAction(evVolver -> {
                    escena.setRoot(pantallaModos);
                });

                escena.setRoot(pantallaPisosPractica);
            });

            menuInicio.getBotonJugar().requestFocus();
            escena.setRoot(pantallaModos);
        });

        // INSTRUCCIONES
        menuInicio.getBotonInstrucciones().setOnAction(e -> {

            PantallaInstrucciones pantallaReglas =
                    new PantallaInstrucciones();

            pantallaReglas.getBotonVolver().setOnAction(ev -> {
                escena.setRoot(menuInicio);
            });

            menuInicio.getBotonInstrucciones().requestFocus();
            escena.setRoot(pantallaReglas);
        });

        ventanaPrincipal.setTitle("Dungeons & Code");
        ventanaPrincipal.setScene(escena);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.show();
    }

    /**
     * Abre el modo práctica para el piso seleccionado.
     */
    private void abrirPractica(
            int piso,
            PantallaMenuPractica pantallaPisosPractica) {

        List<Pregunta> preguntas = obtenerPreguntasPorPiso(piso);

        PantallaPractica pantalla =
                new PantallaPractica(preguntas);

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