package org.poli.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.poli.basedatos.RepositorioPreguntas;
import org.poli.modelo.Partida;
import org.poli.modelo.Pregunta;
import org.poli.util.Constantes;
import org.poli.vista.juego.PantallaJuegoRegular;
import org.poli.vista.juego.PantallaPractica;
import org.poli.vista.menu.PantallaGameOver;
import org.poli.vista.menu.PantallaInstrucciones;
import org.poli.vista.menu.PantallaMenu;
import org.poli.vista.menu.PantallaMenuPractica;
import org.poli.vista.menu.PantallaModoJuego;
import org.poli.vista.resultado.PantallaResultado;

/**
 * Controlador del juego: es el "sistema de ventanas".
 *
 * Posee una única {@link Scene} y la reutiliza cambiando su raíz para pasar de
 * una pantalla a otra. Cada método {@code irA...()} construye una vista, conecta
 * sus botones con otros métodos de navegación y la muestra.
 *
 * Concentra TODA la navegación y el cableado de botones (la lógica de control),
 * de modo que las vistas solo dibujan y el arranque ({@code App}) queda mínimo.
 *
 * En el modo regular los pisos se juegan en cadena: al superar uno se pasa al
 * siguiente acumulando los aciertos de la campaña, y al terminar el último se
 * muestra el resultado final con el total acumulado.
 */
public class Navegador {

    private final Stage escenario;
    private final List<Pregunta> todasLasPreguntas;
    private final Scene escena;

    /**
     * @param escenario   ventana principal de JavaFX
     * @param repositorio fuente de preguntas del juego
     */
    public Navegador(Stage escenario, RepositorioPreguntas repositorio) {
        this.escenario = escenario;

        // Cargamos las preguntas una sola vez: filtrar por piso desde memoria
        // evita releer y re-parsear el CSV en cada cambio de piso o reintento.
        this.todasLasPreguntas = repositorio.obtenerPreguntas();

        // Una sola Scene para toda la app; se navega cambiando su raíz.
        // Tamaño fijo acordado para que el layout de las pantallas no se recorte.
        this.escena = new Scene(new Pane(), 1000, 650);

        this.escenario.setTitle(Constantes.TITULO_JUEGO);
        this.escenario.setScene(escena);
        this.escenario.setResizable(false);
    }

    /** Arranca la aplicación mostrando el menú principal. */
    public void iniciar() {
        irAlMenu();
        escenario.show();
    }

    // =====================================================================
    // PANTALLAS DE MENÚ
    // =====================================================================

    /** Muestra el menú principal (Jugar / Instrucciones / Salir). */
    public void irAlMenu() {
        PantallaMenu menu = new PantallaMenu();
        menu.getBotonJugar().setOnAction(e -> irAModos());
        menu.getBotonInstrucciones().setOnAction(e -> irAInstrucciones());
        // botonSalir ya cierra la aplicación desde la propia vista.
        mostrar(menu);
    }

    /** Muestra la selección de modo de juego (Regular / Práctica / Héroe). */
    public void irAModos() {
        PantallaModoJuego modos = new PantallaModoJuego();
        modos.getBotonRegular().setOnAction(e -> irAJuegoRegular(1));
        modos.getBotonPractica().setOnAction(e -> irAMenuPractica());
        modos.getBotonHeroe().setOnAction(e -> avisarModoHeroePendiente());
        modos.getBotonVolver().setOnAction(e -> irAlMenu());
        mostrar(modos);
    }

    /** Muestra el pergamino de instrucciones. */
    public void irAInstrucciones() {
        PantallaInstrucciones instrucciones = new PantallaInstrucciones();
        instrucciones.getBotonVolver().setOnAction(e -> irAlMenu());
        mostrar(instrucciones);
    }

    /** Muestra el selector de piso del modo práctica. */
    public void irAMenuPractica() {
        PantallaMenuPractica menuPractica = new PantallaMenuPractica();
        menuPractica.getBotonPiso1().setOnAction(e -> irAPractica(1));
        menuPractica.getBotonPiso2().setOnAction(e -> irAPractica(2));
        menuPractica.getBotonPiso3().setOnAction(e -> irAPractica(3));
        menuPractica.getBotonPiso4().setOnAction(e -> irAPractica(4));
        menuPractica.getBotonPiso5().setOnAction(e -> irAPractica(5));
        menuPractica.getBotonVolver().setOnAction(e -> irAModos());
        mostrar(menuPractica);
    }

    // =====================================================================
    // PANTALLAS DE JUEGO
    // =====================================================================

    /**
     * Abre el modo práctica de un piso concreto (preguntas sueltas, sin vidas).
     *
     * @param piso número de piso a practicar
     */
    public void irAPractica(int piso) {
        PantallaPractica practica = new PantallaPractica(obtenerPreguntasPorPiso(piso));
        practica.getBtnVolver().setOnAction(e -> irAMenuPractica());
        mostrar(practica);
    }

    /**
     * Inicia la campaña del modo regular desde el piso indicado, partiendo de
     * cero aciertos acumulados.
     *
     * @param piso piso por el que empieza la campaña (normalmente 1)
     */
    public void irAJuegoRegular(int piso) {
        jugarPisoRegular(piso, 0, 0);
    }

    /**
     * Juega un piso del modo regular arrastrando el desempeño de los pisos ya
     * superados, para poder mostrar el total de la campaña al final.
     *
     * @param piso             piso actual a jugar
     * @param aciertosPrevios  aciertos acumulados en los pisos anteriores
     * @param preguntasPrevias preguntas respondidas en los pisos anteriores
     */
    private void jugarPisoRegular(int piso, int aciertosPrevios, int preguntasPrevias) {
        // Al perder se mantienen los totales previos para que, si reintenta este
        // piso y completa la campaña, no se pierdan los aciertos anteriores.
        Runnable alPerder = () -> mostrarGameOver(piso, aciertosPrevios, preguntasPrevias);

        // Al superar el piso: suma su desempeño y avanza, o cierra la campaña.
        Consumer<Partida> alGanar = partida -> {
            int aciertos = aciertosPrevios + partida.getAciertos();
            int preguntas = preguntasPrevias + partida.getTotalPreguntas();
            if (piso < Constantes.TOTAL_PISOS_REGULAR) {
                jugarPisoRegular(piso + 1, aciertos, preguntas);
            } else {
                mostrarResultadoFinal(aciertos, preguntas, partida.getVidas());
            }
        };

        PantallaJuegoRegular combate =
                new PantallaJuegoRegular(obtenerPreguntasPorPiso(piso), piso, alPerder, alGanar);
        combate.getBotonRendirse().setOnAction(e -> irAModos());
        mostrar(combate);
    }

    // =====================================================================
    // PANTALLAS DE FIN DE PARTIDA
    // =====================================================================

    /**
     * Muestra la pantalla de Game Over. Reintentar repite el piso perdido
     * conservando los aciertos de los pisos ya superados.
     *
     * @param piso             piso en el que se perdió
     * @param aciertosPrevios  aciertos acumulados antes de este piso
     * @param preguntasPrevias preguntas respondidas antes de este piso
     */
    private void mostrarGameOver(int piso, int aciertosPrevios, int preguntasPrevias) {
        PantallaGameOver gameOver = new PantallaGameOver();
        gameOver.getBotonMenuPrincipal().setOnAction(e -> irAlMenu());
        gameOver.getBotonReintentar().setOnAction(e -> jugarPisoRegular(piso, aciertosPrevios, preguntasPrevias));
        mostrar(gameOver);
    }

    /**
     * Muestra el resultado final tras superar todos los pisos, con el total
     * acumulado de aciertos de la campaña. Reintentar reinicia desde el piso 1.
     *
     * @param aciertosTotales aciertos sumados de todos los pisos
     * @param totalPreguntas  preguntas respondidas en toda la campaña
     * @param vidasRestantes  vidas con las que se terminó el último piso
     */
    private void mostrarResultadoFinal(int aciertosTotales, int totalPreguntas, int vidasRestantes) {
        PantallaResultado resultado = new PantallaResultado(
                true,
                aciertosTotales,
                totalPreguntas,
                vidasRestantes
        );
        resultado.getBotonMenuPrincipal().setOnAction(e -> irAlMenu());
        resultado.getBotonReintentar().setOnAction(e -> irAJuegoRegular(1));
        mostrar(resultado);
    }

    // =====================================================================
    // UTILIDADES INTERNAS
    // =====================================================================

    /** Cambia la pantalla visible reutilizando la misma Scene. */
    private void mostrar(Parent pantalla) {
        escena.setRoot(pantalla);
    }

    /** Informa que el Modo Héroe aún no está disponible. */
    private void avisarModoHeroePendiente() {
        Alert aviso = new Alert(AlertType.INFORMATION);
        aviso.setTitle("Modo Héroe");
        aviso.setHeaderText(null);
        aviso.setContentText("El Modo Héroe estará disponible próximamente.");
        aviso.showAndWait();
    }

    /** Filtra de la caché en memoria las preguntas de un piso concreto. */
    private List<Pregunta> obtenerPreguntasPorPiso(int piso) {
        List<Pregunta> resultado = new ArrayList<>();
        for (Pregunta p : todasLasPreguntas) {
            if (p.getPiso() == piso) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
