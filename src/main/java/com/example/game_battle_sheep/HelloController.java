package com.example.game_battle_sheep;

import com.example.game_battle_sheep.model.Grid;
import com.example.game_battle_sheep.model.ShotResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    private GridPane grid;

    private Grid gameGrid = new Grid();
    private int shotsLeft = 30;

    @FXML
    public void initialize() {

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Button button = new Button();
                button.setPrefSize(40, 40);

                int r = row;
                int c = col;

                button.setOnAction(e -> {
                    if (shotsLeft <= 0) return;

                    ShotResult result = gameGrid.shoot(r, c);
                    shotsLeft--;

                    switch (result) {
                        case PLOUF -> button.setText("🌊");
                        case TOUCHE -> button.setText("🔥");
                        case TOUCHE_COULE -> button.setText("💥");
                    }

                    button.setDisable(true);
                });

                grid.add(button, col, row);
            }
        }
    }
}
