package view;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Launch {
    
    public Launch(Stage primaryStage){
    Group root = new Group();
        
        Scene scene = new Scene(root, 1300, 680, Color.LIGHTSTEELBLUE);
        
        Button sign_up = new Button("Sign Up");        
        Button log_in = new Button("Log In");
        
        sign_up.setStyle("-fx-font: 22 arial;");
        sign_up.setPrefSize(500, 250);
        sign_up.setLayoutX(scene.getWidth()/2-250);
        sign_up.setLayoutY(50);
        
        log_in.setStyle("-fx-font: 22 arial;");
        log_in.setPrefSize(500, 250);
        log_in.setLayoutX(scene.getWidth()/2-250);
        log_in.setLayoutY(350);
        
        sign_up.setOnAction((ActionEvent event) -> {
            Inscription inscription = new Inscription(primaryStage);
    });
        
        log_in.setOnAction((ActionEvent event) -> {
            Identificator identificator = new Identificator(primaryStage);
    });
        
        root.getChildren().add(sign_up);
        root.getChildren().add(log_in);
              
        primaryStage.setTitle("Budgestion");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
}
