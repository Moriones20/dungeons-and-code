package org.poli.vista.juego;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.util.Duration;
import java.util.List;
import java.util.function.Consumer;
import org.poli.modelo.Partida;
import org.poli.modelo.Pregunta;
import org.poli.util.Constantes;

/**
 * Pantalla de combate del modo regular: muestra la pregunta y sus opciones
 * como botones y reacciona a la respuesta del jugador.
 *
 * Toda la lógica del juego (vidas, daño, victoria/derrota) vive en el modelo
 * {@link Partida}. Esta clase es VISTA: refleja el estado de la partida, da
 * feedback visual de los golpes y le reenvía las pulsaciones. Cuando la partida
 * termina, avisa al controlador (Navegador) mediante los callbacks
 * {@code alPerder} y {@code alGanar}.
 */
public class PantallaJuegoRegular extends BorderPane {

    // 1. Componentes de la Zona Superior (Estado de la Batalla)
    private Label labelPiso;
    private Label labelVidasJugador;
    private Label labelNombreEnemigo;
    private ProgressBar barraVidaEnemigo;

    // 2. Componentes de la Zona Central (Campo de Batalla)
    private ImageView avatarJugador;
    private ImageView avatarEnemigo;

    // 3. Componentes de la Zona Inferior (Consola de Código e Interfaz de Opciones)
    private Label labelEnunciadoPregunta;
    private Button botonOpcionA;
    private Button botonOpcionB;
    private Button botonOpcionC;
    private Button botonOpcionD;
    private Button botonRendirse;

    // Estado del juego (modelo) y avisos al controlador
    private final Partida partida;
    private final Runnable alPerder;
    private final Consumer<Partida> alGanar;

    /**
     * Crea la pantalla de combate.
     *
     * @param preguntas preguntas del piso a jugar
     * @param piso      número de piso (para el rótulo superior)
     * @param alPerder  acción a ejecutar cuando el jugador se queda sin vidas
     * @param alGanar   acción a ejecutar cuando el jugador supera el piso
     *                  (recibe la partida terminada para mostrar el resultado)
     */
    public PantallaJuegoRegular(List<Pregunta> preguntas, int piso, Runnable alPerder, Consumer<Partida> alGanar) {
        this.partida = new Partida(preguntas, Constantes.VIDAS_INICIALES);
        this.alPerder = alPerder;
        this.alGanar = alGanar;

        // ---- APLICAR EL FONDO DE LA BATALLA DEL PISO 1 ----
        try {
            Image imagenFondo = new Image(getClass().getResourceAsStream("/imagenes/FondoEnemigoPiso1.png"));
            BackgroundImage fondoRPG = new BackgroundImage(
                imagenFondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
            );
            this.setBackground(new Background(fondoRPG));
        } catch (Exception e) {
            this.setStyle("-fx-background-color: #121212;");
        }

        // =====================================================================
        // ZONA SUPERIOR
        // =====================================================================
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setSpacing(50);
        topBar.setAlignment(Pos.CENTER);
        topBar.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-border-color: #ffcc00; -fx-border-width: 0 0 2 0;");

        // Rótulo del piso: usa el número recibido y el tema de las preguntas.
        String tema = (preguntas != null && !preguntas.isEmpty()) ? preguntas.get(0).getTema() : "";
        labelPiso = new Label("PISO " + piso + ": " + tema.toUpperCase());
        labelPiso.setStyle("-fx-text-fill: #ffcc00; -fx-font-size: 16px; -fx-font-weight: bold;");

        labelVidasJugador = new Label();
        labelVidasJugador.setStyle("-fx-text-fill: #ff4444; -fx-font-size: 16px; -fx-font-weight: bold;");

        VBox infoEnemigo = new VBox(5);
        infoEnemigo.setAlignment(Pos.CENTER);
        labelNombreEnemigo = new Label("GOLEM DE SINTAXIS");
        labelNombreEnemigo.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 12px; -fx-font-weight: bold;");

        barraVidaEnemigo = new ProgressBar(1.0);
        barraVidaEnemigo.setPrefWidth(220);
        barraVidaEnemigo.setStyle("-fx-accent: #33cc33;");
        infoEnemigo.getChildren().addAll(labelNombreEnemigo, barraVidaEnemigo);

        topBar.getChildren().addAll(labelPiso, labelVidasJugador, infoEnemigo);
        this.setTop(topBar);

        // =====================================================================
        // ZONA CENTRAL
        // =====================================================================
        HBox campoBatalla = new HBox();
        campoBatalla.setAlignment(Pos.CENTER);
        campoBatalla.setSpacing(120);
        campoBatalla.setPadding(new Insets(10, 20, 10, 20));

        VBox cajaJugador = new VBox(8);
        cajaJugador.setAlignment(Pos.CENTER);
        Label lblHeroe = new Label("HÉROE");
        lblHeroe.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, black, 5, 0, 0, 0);");
        avatarJugador = new ImageView();
        avatarJugador.setFitWidth(220);
        avatarJugador.setPreserveRatio(true);
        cargarImagenComponente(avatarJugador, "/imagenes/Heroe.png");
        cajaJugador.getChildren().addAll(lblHeroe, avatarJugador);

        VBox cajaEnemigo = new VBox(8);
        cajaEnemigo.setAlignment(Pos.CENTER);
        Label lblMounstruo = new Label("GUARDIÁN");
        lblMounstruo.setStyle("-fx-text-fill: #ff4444; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, black, 5, 0, 0, 0);");
        avatarEnemigo = new ImageView();
        avatarEnemigo.setFitWidth(220);
        avatarEnemigo.setPreserveRatio(true);
        cargarImagenComponente(avatarEnemigo, "/imagenes/GolemSintaxis.png");
        cajaEnemigo.getChildren().addAll(lblMounstruo, avatarEnemigo);

        campoBatalla.getChildren().addAll(cajaJugador, cajaEnemigo);
        this.setCenter(campoBatalla);

        // =====================================================================
        // ZONA INFERIOR
        // =====================================================================
        VBox seccionPregunta = new VBox(15);
        seccionPregunta.setPadding(new Insets(15, 20, 15, 20));
        seccionPregunta.setStyle("-fx-background-color: rgba(15, 15, 15, 0.92); -fx-border-color: #ffcc00; -fx-border-width: 2 0 0 0;");
        seccionPregunta.setAlignment(Pos.CENTER);

        // Inicializamos el Label vacío porque se llenará dinámicamente
        labelEnunciadoPregunta = new Label();
        labelEnunciadoPregunta.setStyle("-fx-text-fill: #00ff00; -fx-font-family: 'Consolas', 'Courier New'; -fx-font-size: 15px; -fx-wrap-text: true;");
        labelEnunciadoPregunta.setPrefWidth(880);
        labelEnunciadoPregunta.setPrefHeight(60);

        HBox filaOpciones1 = new HBox(25);
        filaOpciones1.setAlignment(Pos.CENTER);
        botonOpcionA = new Button();
        botonOpcionB = new Button();

        HBox filaOpciones2 = new HBox(25);
        filaOpciones2.setAlignment(Pos.CENTER);
        botonOpcionC = new Button();
        botonOpcionD = new Button();

        String estiloBotonCodigo = "-fx-pref-width: 410px; -fx-pref-height: 40px; -fx-font-family: 'Consolas'; -fx-font-size: 13px; "
            + "-fx-background-color: #222222; -fx-text-fill: #ffffff; -fx-border-color: #444444; -fx-border-radius: 4; -fx-background-radius: 4;";

        botonOpcionA.setStyle(estiloBotonCodigo);
        botonOpcionB.setStyle(estiloBotonCodigo);
        botonOpcionC.setStyle(estiloBotonCodigo);
        botonOpcionD.setStyle(estiloBotonCodigo);

        // Las opciones siempre validan la misma posición (0=A, 1=B, 2=C, 3=D);
        // basta con conectarlas una vez.
        botonOpcionA.setOnAction(e -> responder(0));
        botonOpcionB.setOnAction(e -> responder(1));
        botonOpcionC.setOnAction(e -> responder(2));
        botonOpcionD.setOnAction(e -> responder(3));

        filaOpciones1.getChildren().addAll(botonOpcionA, botonOpcionB);
        filaOpciones2.getChildren().addAll(botonOpcionC, botonOpcionD);

        botonRendirse = new Button("RENDIRSE / VOLVER");
        botonRendirse.setStyle("-fx-background-color: #b71c1c; -fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: bold; -fx-padding: 5 15 5 15;");

        seccionPregunta.getChildren().addAll(labelEnunciadoPregunta, filaOpciones1, filaOpciones2, botonRendirse);
        this.setBottom(seccionPregunta);

        // Estado inicial (sin animación)
        actualizarVidasLabel();
        barraVidaEnemigo.setProgress(partida.getVidaEnemigo());
        if (partida.estaEnCurso()) {
            mostrarPreguntaActual();
        } else {
            labelEnunciadoPregunta.setText("// No hay preguntas disponibles para este piso.");
        }
    }

    // =====================================================================
    // CONTROL DE LA VISTA
    // =====================================================================

    /** Dibuja en pantalla la pregunta actual del modelo. */
    private void mostrarPreguntaActual() {
        Pregunta pregunta = partida.getPreguntaActual();

        labelEnunciadoPregunta.setText("// Pregunta de compilación:\n" + pregunta.getEnunciado());

        String[] opciones = pregunta.getOpciones();
        botonOpcionA.setText("A) " + opciones[0]);
        botonOpcionB.setText("B) " + opciones[1]);
        botonOpcionC.setText("C) " + opciones[2]);
        botonOpcionD.setText("D) " + opciones[3]);
    }

    /**
     * Reenvía la respuesta al modelo, da feedback visual del golpe, refresca la
     * vista y, si la partida ha terminado, avisa al controlador.
     *
     * @param indiceSeleccionado opción elegida por el jugador
     */
    private void responder(int indiceSeleccionado) {
        double vidaEnemigoAntes = partida.getVidaEnemigo();

        boolean acierto = partida.responder(indiceSeleccionado);

        actualizarVidasLabel();
        animarBarraEnemigo(vidaEnemigoAntes, partida.getVidaEnemigo());

        if (acierto) {
            efectoGolpeEnemigo();
        } else {
            efectoDanioJugador();
        }

        if (partida.hayDerrota()) {
            if (alPerder != null) {
                alPerder.run();
            }
            return;
        }

        if (partida.hayVictoria()) {
            if (alGanar != null) {
                alGanar.accept(partida);
            }
            return;
        }

        mostrarPreguntaActual();
    }

    /** Sincroniza el texto de vidas del jugador con el modelo (corazones). */
    private void actualizarVidasLabel() {
        StringBuilder corazones = new StringBuilder("VIDAS: ");
        for (int i = 0; i < partida.getVidas(); i++) {
            corazones.append("♥ "); // ♥ corazón simple (renderiza en cualquier fuente)
        }
        if (partida.getVidas() <= 0) {
            corazones.append("GAME OVER");
        }
        labelVidasJugador.setText(corazones.toString());
    }

    // =====================================================================
    // EFECTOS VISUALES (feedback de daño)
    // =====================================================================

    /** Anima la barra del enemigo bajando suavemente de un valor a otro. */
    private void animarBarraEnemigo(double desde, double hasta) {
        barraVidaEnemigo.setProgress(desde);
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(450),
                new KeyValue(barraVidaEnemigo.progressProperty(), hasta))
        );
        timeline.setOnFinished(e ->
            barraVidaEnemigo.setStyle(hasta <= 0.3 ? "-fx-accent: #ff3333;" : "-fx-accent: #33cc33;")
        );
        timeline.play();
    }

    /** Golpe al enemigo: un breve "rebote" de su avatar al acertar. */
    private void efectoGolpeEnemigo() {
        ScaleTransition golpe = new ScaleTransition(Duration.millis(90), avatarEnemigo);
        golpe.setFromX(1.0);
        golpe.setFromY(1.0);
        golpe.setToX(1.12);
        golpe.setToY(1.12);
        golpe.setCycleCount(2);
        golpe.setAutoReverse(true);
        golpe.play();
    }

    /** Daño al jugador: sacude su avatar y parpadea el marcador de vidas. */
    private void efectoDanioJugador() {
        TranslateTransition sacudida = new TranslateTransition(Duration.millis(55), avatarJugador);
        sacudida.setFromX(0);
        sacudida.setByX(12);
        sacudida.setCycleCount(6);
        sacudida.setAutoReverse(true);
        sacudida.setOnFinished(e -> avatarJugador.setTranslateX(0));
        sacudida.play();

        FadeTransition parpadeo = new FadeTransition(Duration.millis(110), labelVidasJugador);
        parpadeo.setFromValue(1.0);
        parpadeo.setToValue(0.2);
        parpadeo.setCycleCount(4);
        parpadeo.setAutoReverse(true);
        parpadeo.setOnFinished(e -> labelVidasJugador.setOpacity(1.0));
        parpadeo.play();
    }

    private void cargarImagenComponente(ImageView view, String ruta) {
        try {
            Image img = new Image(getClass().getResourceAsStream(ruta));
            view.setImage(img);
        } catch (Exception e) {
            System.out.println("Error cargando recurso en: " + ruta);
        }
    }

    // --- GETTERS DE BOTONES ---
    public Button getBotonOpcionA() { return botonOpcionA; }
    public Button getBotonOpcionB() { return botonOpcionB; }
    public Button getBotonOpcionC() { return botonOpcionC; }
    public Button getBotonOpcionD() { return botonOpcionD; }
    public Button getBotonRendirse() { return botonRendirse; }
    public int getVidasJugadorActual() { return partida.getVidas(); }
}
