package view;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Budgestion extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Launch budgestion = new Launch(primaryStage); 
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}