package org.poli.vista.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

public class PantallaMenu extends VBox {

    // Componentes visuales
    private ImageView logoView;
    private Label titulo;
    private Label lema;
    private Button botonJugar;
    private Button botonInstrucciones;
    private Button botonSalir;

    public PantallaMenu() {
        //Configurar el contenedor principal (VBox)
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);

        //Cargar y Aplicar la Imagen de Fondo Dinámica
        try {
            Image imagenFondo = new Image(getClass().getResourceAsStream("/imagenes/FondoMenu.png"));
            BackgroundImage fondoRPG = new BackgroundImage(
                imagenFondo,
                BackgroundRepeat.NO_REPEAT,  // No repetir en mosaico
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,   // Centrar la imagen
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true) // Ajuste elástico (Cover)
            );
            this.setBackground(new Background(fondoRPG));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen de fondo, usando color por defecto.");
            this.setStyle("-fx-background-color: #1a1a1a;");
        }

        //Cargar el Logo del Juego
        try {
            Image imagenLogo = new Image(getClass().getResourceAsStream("/imagenes/logo.png"));
            logoView = new ImageView(imagenLogo);
            logoView.setFitWidth(320);
            logoView.setPreserveRatio(true); // Mantiene la proporción original para que no se deforme
        } catch (Exception e) {
            System.out.println("No se pudo cargar el logo.");
        }

        //Crear el Título y Lema
        titulo = new Label("DUNGEONS & CODE");
        titulo.setStyle("-fx-text-fill: #ffcc00; -fx-font-size: 34px; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

        lema = new Label("Aprende Java superando pisos, enemigos y desafíos de programación.");
        lema.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-font-style: italic; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.9), 5, 0, 0, 0);");

        //Crear y estilar los botones principales
        botonJugar = new Button("JUGAR");
        botonInstrucciones = new Button("INSTRUCCIONES");
        botonSalir = new Button("SALIR");

        //Estilo adaptado para que los botones resalten del fondo del paisaje
        String estiloBotonesMenu = "-fx-pref-width: 220px; -fx-pref-height: 45px; -fx-font-size: 14px; -fx-font-weight: bold; "
            + "-fx-background-color: #2a52be; -fx-text-fill: white; -fx-background-radius: 5;";

        botonJugar.setStyle(estiloBotonesMenu);
        botonInstrucciones.setStyle(estiloBotonesMenu);

        botonSalir.setStyle("-fx-pref-width: 220px; -fx-pref-height: 45px; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-color: #d32f2f; -fx-text-fill: white; -fx-background-radius: 5;");

        //Configurar la acción de cierre en el botón salir
        botonSalir.setOnAction(e -> System.exit(0));

        //Añadir al contenedor en forma vertical
        if (logoView != null) {
            this.getChildren().add(logoView);
        }
        this.getChildren().addAll(titulo, lema, botonJugar, botonInstrucciones, botonSalir);
    }

    // Métodos Getters para mantener la navegación en App.java
    public Button getBotonJugar() { return botonJugar; }
    public Button getBotonInstrucciones() { return botonInstrucciones; }
    public Button getBotonSalir() { return botonSalir; }
}
