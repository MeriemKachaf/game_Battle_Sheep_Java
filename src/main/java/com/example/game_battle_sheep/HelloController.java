package com.example.game_battle_sheep;

import com.example.game_battle_sheep.model.Grid;
import com.example.game_battle_sheep.model.ShotResult;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class HelloController {

    private static final int MAX_SHOTS = 30;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label statusLabel;

    private Grid gameGrid;
    private int shots;

    private Image greenImage;
    private Image sheepImage;

    @FXML
    public void initialize() {

        greenImage = new Image(
                getClass().getResourceAsStream("/images/green.png")
        );

        sheepImage = new Image(
                getClass().getResourceAsStream("/images/sheep.jpg")
        );

        resetGame();
    }

    private void createGrid() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Button button = new Button();
                button.setPrefSize(40, 40);
                button.setFocusTraversable(false);

                ImageView imageView = new ImageView(greenImage);
                imageView.setFitWidth(32);
                imageView.setFitHeight(32);
                imageView.setPreserveRatio(true);

                button.setGraphic(imageView);

                int r = row;
                int c = col;

                button.setOnAction(e -> handleShot(button, r, c));
                gridPane.add(button, col, row);
            }
        }
    }

    private void handleShot(Button button, int row, int col) {

        if (shots >= MAX_SHOTS || gameGrid.isGameOver()) {
            return;
        }

        ShotResult result = gameGrid.shoot(row, col);
        shots++;

        // Afficher le mouton uniquement si on touche
        if (result == ShotResult.TOUCHE || result == ShotResult.TOUCHE_COULE) {
            ImageView imageView = new ImageView(sheepImage);
            imageView.setFitWidth(32);
            imageView.setFitHeight(32);
            imageView.setPreserveRatio(true);
            button.setGraphic(imageView);
        }

        button.setDisable(true);

        // Victoire
        if (gameGrid.isGameOver()) {
            statusLabel.setText(
                    "🎉 VICTOIRE en " + shots + " tirs | Bateaux restants : "
                            + gameGrid.getRemainingShips()
            );
            disableAllButtons();
            return;
        }

        // Défaite (30 tirs)
        if (shots >= MAX_SHOTS) {
            statusLabel.setText(
                    "💥 DÉFAITE ! 30 tirs utilisés | Bateaux restants : "
                            + gameGrid.getRemainingShips()
            );
            disableAllButtons();
            return;
        }

        statusLabel.setText(
                "Tirs : " + shots + "/" + MAX_SHOTS + " | " + result
        );
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
        statusLabel.setText("Nouvelle partie – Tirs : 0 / 30");
        gridPane.getChildren().clear();
        createGrid();
    }
}
