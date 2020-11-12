package controller;

import View.Background;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Background bg = new Background();
        GameLoop game = new GameLoop(bg);
        Scene scene = new Scene(bg);
        scene.setOnKeyPressed(e->bg.setAction(e.getCode()));
        scene.setOnKeyReleased(e->bg.unset(e.getCode()));
        stage.setTitle("PKVL");
        stage.setScene(scene);
        stage.show();
        new Thread(game).start();
    }
    public static void main(String[] args){
        launch(args);
    }
}
