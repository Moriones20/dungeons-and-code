package org.poli.vista.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PantallaGameOver extends VBox {

    private Button botonReintentar;
    private Button botonMenuPrincipal;

    public PantallaGameOver() {
        // Configuración del contenedor principal (Fondo negro y alineación al centro)
        this.setAlignment(Pos.CENTER);
        this.setSpacing(30);
        this.setPadding(new Insets(50));
        this.setStyle("-fx-background-color: #0a0a0a;"); // Un negro profundo táctico

        // Título de la pantalla
        Label labelTitulo = new Label("☠️ GAME OVER ☠️");
        labelTitulo.setStyle("-fx-text-fill: #b71c1c; -fx-font-size: 45px; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");

        // Subtítulo descriptivo
        Label labelSubtitulo = new Label("Tu código ha colapsado ante los peligros de la torre.");
        labelSubtitulo.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 16px; -fx-font-family: 'Consolas';");

        // Estilo general para los botones de la terminal
        String estiloBoton = "-fx-pref-width: 250px; -fx-pref-height: 45px; -fx-font-size: 14px; -fx-font-weight: bold; "
            + "-fx-background-color: #1a1a1a; -fx-text-fill: #ffffff; -fx-border-color: #ffcc00; -fx-border-radius: 5; -fx-background-radius: 5;";

        botonReintentar = new Button("REINTENTAR PISO");
        botonReintentar.setStyle(estiloBoton);
        // Efecto hover simple para que cambie de color al pasar el mouse
        botonReintentar.setOnMouseEntered(e -> botonReintentar.setStyle(estiloBoton + "-fx-background-color: #333333;"));
        botonReintentar.setOnMouseExited(e -> botonReintentar.setStyle(estiloBoton));

        botonMenuPrincipal = new Button("VOLVER AL MENÚ");
        botonMenuPrincipal.setStyle(estiloBoton);
        botonMenuPrincipal.setOnMouseEntered(e -> botonMenuPrincipal.setStyle(estiloBoton + "-fx-background-color: #333333;"));
        botonMenuPrincipal.setOnMouseExited(e -> botonMenuPrincipal.setStyle(estiloBoton));

        // Agregar todos los elementos a la vista
        this.getChildren().addAll(labelTitulo, labelSubtitulo, botonReintentar, botonMenuPrincipal);
    }

    // Getters para que App.java pueda controlar las acciones de los botones
    public Button getBotonReintentar() { return botonReintentar; }
    public Button getBotonMenuPrincipal() { return botonMenuPrincipal; }
}
