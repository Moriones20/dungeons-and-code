package org.poli.vista.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

public class PantallaInstrucciones extends VBox {

    private Label titulo;
    private ScrollPane panelTexto;
    private VBox contenedorReglas;
    private Button botonVolver;

    public PantallaInstrucciones() {
        //Configurar el contenedor principal
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        //Aplicar Fondo
        try {
            Image imagenFondo = new Image(getClass().getResourceAsStream("/imagenes/FondoMenu.png"));
            BackgroundImage fondoRPG = new BackgroundImage(
                imagenFondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
            );
            this.setBackground(new Background(fondoRPG));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen de fondo en PantallaInstrucciones.");
            this.setStyle("-fx-background-color: #1a1a1a;");
        }

        //Título de la pantalla
        titulo = new Label("PERGAMINO DE INSTRUCCIONES");
        titulo.setStyle("-fx-text-fill: #ffcc00; -fx-font-size: 26px; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

        //Crear bloque oscuro semi-transparente para contener las reglas
        contenedorReglas = new VBox();
        contenedorReglas.setSpacing(15);
        contenedorReglas.setAlignment(Pos.TOP_LEFT);
        contenedorReglas.setStyle("-fx-background-color: rgba(26, 26, 26, 0.85); -fx-padding: 25; -fx-background-radius: 10; -fx-border-color: #ffcc00; -fx-border-width: 2;");
        contenedorReglas.setPrefWidth(700);
        contenedorReglas.setMaxWidth(700);

        // Instrucciones (sin emojis: la fuente por defecto de JavaFX los pinta
        // como un cuadro vacío).
        agregarRegla("OBJETIVO DEL JUEGO",
            "Adéntrate en la torre 'Dungeons & Code' y supera los 5 pisos malditos. Cada piso representa una temática crucial de Java: Variables, Operadores, Condicionales, Ciclos y Arreglos.", true);

        agregarRegla("DINÁMICA DE COMBATE",
            "En cada piso te enfrentarás a un enemigo. Para atacarlo o defenderte, deberás responder preguntas de programación de opción múltiple. ¡Si respondes bien, golpeas; si fallas, recibes daño!", false);

        agregarRegla("MODOS DE JUEGO",
            "• Modo Regular: Juega la campaña balanceada salvando tu progreso.\n" +
                "• Modo Héroe: Un reto extremo para programadores veteranos. ¡Un solo fallo y es Game Over!\n" +
                "• Modo Práctica: Ideal para repasar un piso específico sin presiones.", false);


        //Envolver las reglas en un ScrollPane por si el texto crece a futuro
        panelTexto = new ScrollPane(contenedorReglas);
        panelTexto.setFitToWidth(true);
        panelTexto.setMaxHeight(400);
        panelTexto.setPrefHeight(400);
        panelTexto.setMaxWidth(720);

        // Estilo CSS para ocultar los bordes feos por defecto del ScrollPane de JavaFX
        panelTexto.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");

        //Botón Volver
        botonVolver = new Button("VOLVER AL MENÚ");
        botonVolver.setStyle("-fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 12px; -fx-background-color: #444444; -fx-text-fill: white; -fx-background-radius: 5; -fx-font-weight: bold;");

        this.getChildren().addAll(titulo, panelTexto, botonVolver);
    }

    // Método auxiliar para formatear los textos del pergamino de forma limpia
    private void agregarRegla(String subtitulo, String contenido, boolean esPrimero) {
        Label sub = new Label(subtitulo);
        sub.setStyle("-fx-text-fill: #ffcc00; -fx-font-size: 16px; -fx-font-weight: bold;");

        Label cuerpo = new Label(contenido);
        cuerpo.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 13px; -fx-wrap-text: true; -fx-line-spacing: 4;");

        if (!esPrimero) {
            // Añadir un espacio de separación antes de la nueva sección
            Label espacio = new Label("");
            espacio.setPrefHeight(5);
            contenedorReglas.getChildren().add(espacio);
        }

        contenedorReglas.getChildren().addAll(sub, cuerpo);
    }

    // Getter para capturar la acción en App.java
    public Button getBotonVolver() { return botonVolver; }
}
