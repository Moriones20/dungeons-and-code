package org.poli.vista.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

public class PantallaModoJuego extends VBox {

    private Label titulo;
    private Button botonNormal;
    private Button botonHeroe;
    private Button botonPractica;
    private Button botonVolver;

    public PantallaModoJuego() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

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
            System.out.println("No se pudo cargar la imagen de fondo en PantallaModoJuego.");
            this.setStyle("-fx-background-color: #1a1a1a;");
        }

        titulo = new Label("SELECCIÓN DE MODO DE JUEGO");
        titulo.setStyle("-fx-text-fill: #ffcc00; -fx-font-size: 26px; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

        botonNormal = new Button("MODO NORMAL");
        botonHeroe = new Button("MODO HÉROE\n(Un fallo y pierdes)");
        botonPractica = new Button("MODO PRÁCTICA");
        botonVolver = new Button("VOLVER AL MENÚ");

        String estiloBotones = "-fx-pref-width: 250px; -fx-pref-height: 55px; -fx-font-size: 14px; -fx-font-weight: bold; "
            + "-fx-background-color: #2a52be; -fx-text-fill: white; -fx-background-radius: 5; -fx-text-alignment: center;";

        botonNormal.setStyle(estiloBotones);
        botonHeroe.setStyle(estiloBotones);
        botonPractica.setStyle(estiloBotones);

        botonVolver.setStyle("-fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 12px; -fx-background-color: #444444; -fx-text-fill: white; -fx-background-radius: 5;");

        this.getChildren().addAll(titulo, botonNormal, botonHeroe, botonPractica, botonVolver);
    }

    public Button getBotonRegular() { return botonNormal; }
    public Button getBotonHeroe() { return botonHeroe; }
    public Button getBotonPractica() { return botonPractica; }
    public Button getBotonVolver() { return botonVolver; }
}
