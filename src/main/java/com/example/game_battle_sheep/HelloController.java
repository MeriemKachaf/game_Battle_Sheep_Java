package com.example.game_battle_sheep;

import com.example.game_battle_sheep.model.Grid;
import com.example.game_battle_sheep.model.ShotResult;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label statusLabel;

    private Grid gameGrid = new Grid();
    private int shots = 0;

    @FXML
    public void initialize() {
        createGrid();
        statusLabel.setText("Tirs : 0");
    }

    private void createGrid() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Button button = new Button(" ");
                button.setPrefSize(40, 40);

                int r = row;
                int c = col;

                button.setOnAction(e -> handleShot(button, r, c));
                gridPane.add(button, col, row);
            }
        }
    }

    private void handleShot(Button button, int row, int col) {
        ShotResult result = gameGrid.shoot(row, col);
        shots++;

        button.setDisable(true);
        button.getStyleClass().removeAll("plouf", "touche", "touche_coule");

        switch (result) {
            case PLOUF -> {
                button.setText("🌊");
                button.getStyleClass().add("plouf");
            }
            case TOUCHE -> {
                button.setText("🔥");
                button.getStyleClass().add("touche");
            }
            case TOUCHE_COULE -> {
                button.setText("💥");
                button.getStyleClass().add("touche_coule");
            }
        }

        statusLabel.setText("Tirs : " + shots + " | " + result);

        if (gameGrid.isGameOver()) {
            statusLabel.setText("🎉 VICTOIRE en " + shots + " tirs !");
            disableAllButtons();
        }
    }

    private void disableAllButtons() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        }
    }

    @FXML
    private void resetGame() {
        gameGrid = new Grid();
        shots = 0;
        statusLabel.setText("Nouvelle partie ! Tirs : 0");
        gridPane.getChildren().clear();
        createGrid();
    }
}
