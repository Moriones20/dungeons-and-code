package org.poli.vista.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

public class PantallaMenuPractica extends VBox {

    private Label titulo;
    private Label subtitulo;
    private Button botonPiso1;
    private Button botonPiso2;
    private Button botonPiso3;
    private Button botonPiso4;
    private Button botonPiso5;
    private Button botonVolver;

    public PantallaMenuPractica() {
        //Configurar el contenedor principal
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        //Aplicar el mismo Fondo RPG adaptativo
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
            System.out.println("No se pudo cargar la imagen de fondo del menu de practica");
            this.setStyle("-fx-background-color: #1a1a1a;");
        }

        //Crear los títulos con sombras de alta legibilidad
        titulo = new Label("SELECCIÓN DE PISO (MODO PRÁCTICA)");
        titulo.setStyle("-fx-text-fill: #ffcc00; -fx-font-size: 24px; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

        subtitulo = new Label("ELIGE EL PISO QUE QUIERES PRACTICAR");
        subtitulo.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.9), 5, 0, 0, 0);");

        //Crear los botones de los pisos
        botonPiso1 = new Button("PISO 1\nVariables y Datos");
        botonPiso2 = new Button("PISO 2\nOperadores");
        botonPiso3 = new Button("PISO 3\nCondicionales");
        botonPiso4 = new Button("PISO 4\nCiclos");
        botonPiso5 = new Button("PISO 5\nArreglos");
        botonVolver = new Button("VOLVER AL MENÚ");

        //Estilo unificado para los botones de los pisos
        String estiloPisos = "-fx-pref-width: 180px; -fx-pref-height: 80px; -fx-font-size: 14px; -fx-font-weight: bold; "
            + "-fx-background-color: #2a52be; -fx-text-fill: white; -fx-background-radius: 5; -fx-text-alignment: center;";

        botonPiso1.setStyle(estiloPisos);
        botonPiso2.setStyle(estiloPisos);
        botonPiso3.setStyle(estiloPisos);
        botonPiso4.setStyle(estiloPisos);
        botonPiso5.setStyle(estiloPisos);

        botonVolver.setStyle("-fx-pref-width: 200px; -fx-pref-height: 40px; -fx-font-size: 12px; -fx-background-color: #444444; -fx-text-fill: white; -fx-background-radius: 5;");

        //Organizar en la cuadrícula
        GridPane contenedorPisos = new GridPane();
        contenedorPisos.setAlignment(Pos.CENTER);
        contenedorPisos.setHgap(15);
        contenedorPisos.setVgap(15);

        contenedorPisos.add(botonPiso1, 0, 0);
        contenedorPisos.add(botonPiso2, 1, 0);
        contenedorPisos.add(botonPiso3, 2, 0);
        contenedorPisos.add(botonPiso4, 3, 0);
        contenedorPisos.add(botonPiso5, 4, 0);

        // 6. Agregar al contenedor principal
        this.getChildren().addAll(titulo, subtitulo, contenedorPisos, botonVolver);
    }

    // Getters
    public Button getBotonPiso1() { return botonPiso1; }
    public Button getBotonPiso2() { return botonPiso2; }
    public Button getBotonPiso3() { return botonPiso3; }
    public Button getBotonPiso4() { return botonPiso4; }
    public Button getBotonPiso5() { return botonPiso5; }
    public Button getBotonVolver() { return botonVolver; }
}
