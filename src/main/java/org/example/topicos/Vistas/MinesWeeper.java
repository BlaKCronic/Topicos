package org.example.topicos.Vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class MinesWeeper extends Stage{
    private int gridSize = 10;  // Tamaño del tablero (10x10)
    private Button[][] buttons;
    private int[][] field;  // Almacena el estado de cada celda (-1 es bomba, otros son contadores)
    private boolean[][] revealed;  // Indica si una celda ha sido revelada
    private boolean[][] flagged;   // Indica si una celda ha sido marcada con una bandera
    private int bombCount;
    private TextField bombInput;
    private GridPane grid;
    private VBox vbox;
    private Scene scene;
    private boolean gameOver;

    private Image flagImage;
    private Image bombImage;

    public MinesWeeper() {
        flagImage = new Image(getClass().getResource("/images/flag.png").toExternalForm());
        bombImage = new Image(getClass().getResource("/images/bomb.png").toExternalForm());

        CrearUI();
        this.setTitle("Buscaminas");
        this.setScene(scene);
        this.show();
    }

    private void CrearUI() {
        bombInput = new TextField();
        bombInput.setPromptText("Número de bombas");
        bombInput.setAlignment(Pos.CENTER);

        Button generateButton = new Button("Generar Campo Minado");
        generateButton.setOnAction(actionEvent -> {
            try {
                bombCount = Integer.parseInt(bombInput.getText());
                iniciarCampos();
                restablecerBotones();
                gameOver = false;
            } catch (NumberFormatException e) {
                bombInput.setText("Número inválido");
            }
        });

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        buttons = new Button[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(40, 40);  // Tamaño de la celda: 40x40 píxeles
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnMouseClicked(event -> {
                    if (!gameOver) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            revelarCelda(finalI, finalJ);
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            bandera(finalI, finalJ);
                        }
                    }
                });
                grid.add(buttons[i][j], j, i);
            }
        }

        vbox = new VBox(10, bombInput, generateButton, grid);
        vbox.setAlignment(Pos.CENTER);
        scene = new Scene(vbox, 500, 600);
    }

    private void iniciarCampos() {
        field = new int[gridSize][gridSize];
        revealed = new boolean[gridSize][gridSize];
        flagged = new boolean[gridSize][gridSize];

        Random random = new Random();
        int bombasColocadas = 0;

        while (bombasColocadas < bombCount) {
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);
            if (field[row][col] != -1) {
                field[row][col] = -1;  // -1 representa una bomba
                bombasColocadas++;
                celdasAdyacentes(row, col);
            }
        }
    }

    private void celdasAdyacentes(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (dentroDeLimites(newRow, newCol) && field[newRow][newCol] != -1) {
                    field[newRow][newCol]++;
                }
            }
        }
    }

    private void restablecerBotones() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setGraphic(null);  // Eliminar cualquier imagen previa
                buttons[i][j].setDisable(false);
            }
        }
    }

    private void revelarCelda(int row, int col) {
        if (!dentroDeLimites(row, col) || revealed[row][col] || flagged[row][col]) {
            return;
        }

        revealed[row][col] = true;

        if (field[row][col] == -1) {
            ImageView bombView = new ImageView(bombImage);
            bombView.setFitWidth(22);
            bombView.setFitHeight(30);
            buttons[row][col].setGraphic(bombView);
            gameOver = true;
            revelarTodo();
        } else {
            buttons[row][col].setText(String.valueOf(field[row][col]));
            buttons[row][col].setDisable(true);

            if (field[row][col] == 0) {
                // Si la celda es 0, revelar recursivamente las celdas que esten juntas
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        revelarCelda(row + i, col + j);
                    }
                }
            }

            if (ganoElJuego()) {
                System.out.println("¡Has ganado!");
                revelarTodo();
            }
        }
    }

    private void bandera(int row, int col) {
        if (!dentroDeLimites(row, col) || revealed[row][col]) {
            return;
        }

        if (flagged[row][col]) {
            flagged[row][col] = false;
            buttons[row][col].setGraphic(null);  // Eliminar la imagen de bandera
        } else {
            flagged[row][col] = true;
            ImageView flagView = new ImageView(flagImage);
            flagView.setFitWidth(22);
            flagView.setFitHeight(30);
            buttons[row][col].setGraphic(flagView);
        }
    }

    private boolean dentroDeLimites(int row, int col) {
        return row >= 0 && row < gridSize && col >= 0 && col < gridSize;
    }

    private boolean ganoElJuego() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (field[i][j] != -1 && !revealed[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void revelarTodo() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (field[i][j] == -1) {
                    ImageView bombView = new ImageView(bombImage);
                    bombView.setFitWidth(22);
                    bombView.setFitHeight(30);
                    buttons[i][j].setGraphic(bombView);
                } else {
                    buttons[i][j].setText(String.valueOf(field[i][j]));
                }
                buttons[i][j].setDisable(true);
            }
        }
    }
}
