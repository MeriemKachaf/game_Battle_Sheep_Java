package com.example.game_battle_sheep;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                HelloApplication.class.getResource(
                        "/com/example/game_battle_sheep/hello-view.fxml"
                )
        );

        Scene scene = new Scene(loader.load(), 500, 550);

        scene.getStylesheets().add(
                HelloApplication.class.getResource(
                        "/com/example/game_battle_sheep/style.css"
                ).toExternalForm()
        );

        stage.setTitle("JavaFleet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
