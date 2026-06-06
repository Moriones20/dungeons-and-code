package org.poli.vista.juego;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import org.poli.modelo.Pregunta;

public class PantallaPractica extends VBox {

    private Label lblTitulo;
    private Label lblPregunta;

    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private Button btnVolver;

    private List<Pregunta> preguntas;
    private int indiceActual = 0;
    private int respuestasCorrectas = 0;

    public PantallaPractica(List<Pregunta> preguntas) {

        this.preguntas = preguntas;

        setAlignment(Pos.CENTER);
        setSpacing(20);

        lblTitulo = new Label("MODO PRÁCTICA");
        lblTitulo.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        lblPregunta = new Label();
        lblPregunta.setWrapText(true);
        lblPregunta.setMaxWidth(700);

        btnA = new Button();
        btnB = new Button();
        btnC = new Button();
        btnD = new Button();

        btnVolver = new Button("Volver");
        btnVolver.setVisible(false);

        btnA.setPrefWidth(400);
        btnB.setPrefWidth(400);
        btnC.setPrefWidth(400);
        btnD.setPrefWidth(400);
        btnVolver.setPrefWidth(200);

        btnA.setOnAction(e -> responder(0));
        btnB.setOnAction(e -> responder(1));
        btnC.setOnAction(e -> responder(2));
        btnD.setOnAction(e -> responder(3));

        getChildren().addAll(
                lblTitulo,
                lblPregunta,
                btnA,
                btnB,
                btnC,
                btnD,
                btnVolver
        );

        cargarPregunta();
    }

    private void cargarPregunta() {

        Pregunta pregunta = preguntas.get(indiceActual);

        lblPregunta.setText(
                "Pregunta " +
                (indiceActual + 1) +
                " de " +
                preguntas.size() +
                "\n\n" +
                pregunta.getEnunciado()
        );

        String[] opciones = pregunta.getOpciones();

        btnA.setText("A) " + opciones[0]);
        btnB.setText("B) " + opciones[1]);
        btnC.setText("C) " + opciones[2]);
        btnD.setText("D) " + opciones[3]);
    }

    private void responder(int opcion) {

        Pregunta actual = preguntas.get(indiceActual);

        if (actual.esCorrecta(opcion)) {
            respuestasCorrectas++;
        }

        indiceActual++;

        if (indiceActual < preguntas.size()) {
            cargarPregunta();
        } else {
            mostrarResultado();
        }
    }

    private void mostrarResultado() {

    lblPregunta.setText(
            "¡Práctica terminada!\n\n" +
            "Respuestas correctas: " +
            respuestasCorrectas +
            " de " +
            preguntas.size()
    );

    btnA.setVisible(false);
    btnB.setVisible(false);
    btnC.setVisible(false);
    btnD.setVisible(false);

    btnVolver.setVisible(true);
}

public Button getBtnVolver() {
    return btnVolver;
}
}