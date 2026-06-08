package org.poli.vista.resultado;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Pantalla final de la campaña: indica si el jugador conquistó la torre o cayó,
 * y resume su desempeño acumulado (aciertos totales y vidas con las que terminó
 * el último piso).
 *
 * Es una VISTA pura: solo construye los componentes y expone sus botones
 * mediante getters. La navegación (volver al menú, reintentar) la decide
 * el controlador {@code Navegador}, igual que en el resto de pantallas.
 */
public class PantallaResultado extends VBox {

    private final Button botonReintentar;
    private final Button botonMenuPrincipal;

    /**
     * Construye la pantalla de resultado de la campaña.
     *
     * @param victoria       true si el jugador superó todos los pisos
     * @param aciertos       respuestas correctas acumuladas en toda la campaña
     * @param totalPreguntas total de preguntas respondidas en toda la campaña
     * @param vidasRestantes vidas con las que terminó el último piso
     */
    public PantallaResultado(boolean victoria, int aciertos, int totalPreguntas, int vidasRestantes) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(25);
        this.setPadding(new Insets(50));

        String colorAcento = victoria ? "#ffcc00" : "#b71c1c";
        this.setStyle("-fx-background-color: #0a0a0a;");

        // Título (sin emojis: la fuente por defecto de JavaFX no los renderiza).
        Label labelTitulo = new Label(victoria ? "¡TORRE CONQUISTADA!" : "DERROTA");
        labelTitulo.setStyle("-fx-text-fill: " + colorAcento + "; -fx-font-size: 42px; -fx-font-weight: bold; "
            + "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");

        // Subtítulo
        Label labelSubtitulo = new Label(victoria
            ? "Has superado todos los pisos de la torre."
            : "Tu código ha colapsado ante los peligros de la torre.");
        labelSubtitulo.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 16px; -fx-font-family: 'Consolas';");

        // Resumen de la partida
        Label labelAciertos = new Label("Respuestas correctas: " + aciertos + " de " + totalPreguntas);
        labelAciertos.setStyle("-fx-text-fill: #00ff00; -fx-font-size: 18px; -fx-font-family: 'Consolas';");

        Label labelVidas = new Label("Vidas restantes: " + vidasRestantes);
        labelVidas.setStyle("-fx-text-fill: #ff4444; -fx-font-size: 18px; -fx-font-family: 'Consolas';");

        // Botones
        String estiloBoton = "-fx-pref-width: 250px; -fx-pref-height: 45px; -fx-font-size: 14px; -fx-font-weight: bold; "
            + "-fx-background-color: #1a1a1a; -fx-text-fill: #ffffff; -fx-border-color: " + colorAcento
            + "; -fx-border-radius: 5; -fx-background-radius: 5;";

        botonReintentar = crearBotonConHover("JUGAR DE NUEVO", estiloBoton);
        botonMenuPrincipal = crearBotonConHover("VOLVER AL MENÚ", estiloBoton);

        this.getChildren().addAll(labelTitulo, labelSubtitulo, labelAciertos, labelVidas,
                botonReintentar, botonMenuPrincipal);
    }

    /** Crea un botón con su estilo base y el efecto de oscurecerse al pasar el ratón. */
    private Button crearBotonConHover(String texto, String estiloBase) {
        Button boton = new Button(texto);
        boton.setStyle(estiloBase);
        boton.setOnMouseEntered(e -> boton.setStyle(estiloBase + "-fx-background-color: #333333;"));
        boton.setOnMouseExited(e -> boton.setStyle(estiloBase));
        return boton;
    }

    /** @return botón para volver a jugar la campaña desde el primer piso */
    public Button getBotonReintentar() {
        return botonReintentar;
    }

    /** @return botón para regresar al menú principal */
    public Button getBotonMenuPrincipal() {
        return botonMenuPrincipal;
    }
}
