package org.poli.vista.juego;

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
import java.util.List;
import org.poli.modelo.Pregunta;
import javafx.event.ActionEvent;

public class PantallaJuegoRegular extends BorderPane {

    // 1. Componentes de la Zona Superior (Estado de la Batalla)
    private Label labelPiso;
    private Label labelVidasJugador;
    private Label labelNombreEnemigo;
    private ProgressBar barraVidaEnemigo;
    private double vidaEnemigoActual = 1.0;
    private int vidasJugadorActual = 3;

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

    //VARIABLES PARA CONTROLAR LAS PREGUNTAS DINÁMICAS
    private List<Pregunta> listaPreguntas;
    private int indicePreguntaActual = 0;
    private Runnable alTerminarGameOver;

    // Constructor que recibe las preguntas
    public PantallaJuegoRegular(List<Pregunta> preguntas, Runnable alTerminarGameOver) {
        this.listaPreguntas = preguntas;
        this.alTerminarGameOver = alTerminarGameOver;

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

        labelPiso = new Label("PISO 1: VARIABLES Y DATOS");
        labelPiso.setStyle("-fx-text-fill: #ffcc00; -fx-font-size: 16px; -fx-font-weight: bold;");

        labelVidasJugador = new Label("VIDAS: ❤️ ❤️ ❤️");
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

        filaOpciones1.getChildren().addAll(botonOpcionA, botonOpcionB);
        filaOpciones2.getChildren().addAll(botonOpcionC, botonOpcionD);

        botonRendirse = new Button("RENDIRSE / VOLVER");
        botonRendirse.setStyle("-fx-background-color: #b71c1c; -fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: bold; -fx-padding: 5 15 5 15;");

        seccionPregunta.getChildren().addAll(labelEnunciadoPregunta, filaOpciones1, filaOpciones2, botonRendirse);
        this.setBottom(seccionPregunta);


        if (listaPreguntas != null && !listaPreguntas.isEmpty()) {
            mostrarPreguntaActual();
        }
    }

// =====================================================================
    // MÉTODOS DE CONTROL DINÁMICOS 🚀
    // =====================================================================

    //Este método toma la pregunta del índice actual y la "dibuja" en la pantalla
    private void mostrarPreguntaActual() {
        Pregunta pregunta = listaPreguntas.get(indicePreguntaActual);

        // 1. Seteamos el enunciado
        labelEnunciadoPregunta.setText("// Pregunta de compilación:\n" + pregunta.getEnunciado());

        // 2. Extraemos el arreglo de opciones que hizo tu compañero
        String[] opciones = pregunta.getOpciones();

        // 3. Pintamos cada opción usando su posición en el arreglo [0, 1, 2, 3]
        botonOpcionA.setText("A) " + opciones[0]);
        botonOpcionB.setText("B) " + opciones[1]);
        botonOpcionC.setText("C) " + opciones[2]);
        botonOpcionD.setText("D) " + opciones[3]);

        // 4. Configuramos la acción pasando el número de opción (0=A, 1=B, 2=C, 3=D)
        botonOpcionA.setOnAction((javafx.event.ActionEvent e) -> verificarRespuesta(0));
        botonOpcionB.setOnAction((javafx.event.ActionEvent e) -> verificarRespuesta(1));
        botonOpcionC.setOnAction((javafx.event.ActionEvent e) -> verificarRespuesta(2));
        botonOpcionD.setOnAction((javafx.event.ActionEvent e) -> verificarRespuesta(3));
    }

    // 🚀 Valida si el usuario acertó o falló usando el método .esCorrecta() de tu compañero
    private void verificarRespuesta(int indiceSeleccionado) {
        Pregunta pregunta = listaPreguntas.get(indicePreguntaActual);

        // Usamos el método que él creó para validar pasándole el número
        if (pregunta.esCorrecta(indiceSeleccionado)) {
            // ¡Correcto! Golpeamos al Golem un ~33.4% (3 respuestas para ganar)
            infligirDanioEnemigo(0.334);
        } else {
            // ¡Incorrecto! El héroe pierde una vida
            reducirVidaJugador();
        }

        //SI EL JUGADOR SE QUEDA SIN VIDAS (AQUÍ ESTÁ EL CAMBIO 🚀)
        if (vidasJugadorActual <= 0) {
            System.out.println("¡Game Over!");

            if (alTerminarGameOver != null) {
                alTerminarGameOver.run(); // 🌟 Esto saca al jugador de aquí y abre la PantallaGameOver
            }
            return;
        }

        //SI EL ENEMIGO MUERE
        if (vidaEnemigoActual <= 0) {
            System.out.println("¡Derrotaste al Golem de este piso!");
            return;
        }

        //SI LA PARTIDA SIGUE (Pasamos a la siguiente pregunta, máximo 3)
        indicePreguntaActual++;
        if (indicePreguntaActual < listaPreguntas.size() && indicePreguntaActual < 3) {
            mostrarPreguntaActual(); // Redibujar la pantalla con la nueva pregunta
        } else {
            System.out.println("Fin de las preguntas de este piso.");
        }
    }

    public void actualizarPregunta(String enunciado, String a, String b, String c, String d) {
        labelEnunciadoPregunta.setText(enunciado);
        botonOpcionA.setText(a);
        botonOpcionB.setText(b);
        botonOpcionC.setText(c);
        botonOpcionD.setText(d);
    }


    public void reducirVidaJugador() {
        if (vidasJugadorActual > 0) {
            vidasJugadorActual--;
            StringBuilder corazones = new StringBuilder("VIDAS: ");
            for (int i = 0; i < vidasJugadorActual; i++) {
                corazones.append("❤️ ");
            }
            if(vidasJugadorActual == 0) {
                corazones.append("☠️ GAME OVER");
            }
            labelVidasJugador.setText(corazones.toString());
        }
    }

    public void infligirDanioEnemigo(double porcentajeDanio) {
        vidaEnemigoActual -= porcentajeDanio;
        if (vidaEnemigoActual < 0) vidaEnemigoActual = 0;
        barraVidaEnemigo.setProgress(vidaEnemigoActual);

        if (vidaEnemigoActual <= 0.3) {
            barraVidaEnemigo.setStyle("-fx-accent: #ff3333;");
        }
    }

    public void cambiarConfiguracionPiso(int numPiso, String temaPiso, String rutaImagenMonstruo, String rutaFondo) {
        this.labelPiso.setText("PISO " + numPiso + ": " + temaPiso.toUpperCase());
        this.vidaEnemigoActual = 1.0;
        this.barraVidaEnemigo.setProgress(1.0);
        this.barraVidaEnemigo.setStyle("-fx-accent: #33cc33;");

        cargarImagenComponente(this.avatarEnemigo, rutaImagenMonstruo);

        try {
            Image nuevoFondo = new Image(getClass().getResourceAsStream(rutaFondo));
            this.setBackground(new Background(new BackgroundImage(
                nuevoFondo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
            )));
        } catch (Exception e) {
            System.out.println("No se pudo mapear el fondo dinámico.");
        }
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
    public int getVidasJugadorActual() { return vidasJugadorActual; }
}
