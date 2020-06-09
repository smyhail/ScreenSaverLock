package com.sub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;
    public  Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.stage=primaryStage;
        FXMLLoader loader = new FXMLLoader( Main.class.getResource( "/app.fxml" ));
        AnchorPane pane = loader.load();
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable( false );
       // stage.initStyle( StageStyle.DECORATED.UNDECORATED );
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
