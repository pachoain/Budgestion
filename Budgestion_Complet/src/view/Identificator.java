package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Account;

public class Identificator {
    
    public Identificator(Stage primaryStage){
    Group root = new Group();
        
        Scene scene = new Scene(root, 960, 540, Color.LIGHTSTEELBLUE);
        
        Label user_label = new Label("Username");
        TextField username = new TextField();
        Label password_label = new Label("Password");
        PasswordField password = new PasswordField();
        Button log_in = new Button("Log In"); 
        Button back = new Button("Back");
        
        user_label.setStyle("-fx-font: 15 arial;");
        user_label.setLayoutX(scene.getWidth()/2-125);
        user_label.setLayoutY(80);
        
        username.setStyle("-fx-font: 15 arial;");
        username.setPrefSize(250,25);
        username.setLayoutX(scene.getWidth()/2-125);
        username.setLayoutY(100);
        
        password_label.setStyle("-fx-font: 15 arial;");
        password_label.setLayoutX(scene.getWidth()/2-125);
        password_label.setLayoutY(180);
        
        password.setStyle("-fx-font: 15 arial;");
        password.setPrefSize(250,25);
        password.setLayoutX(scene.getWidth()/2-125);
        password.setLayoutY(200);
        
        log_in.setStyle("-fx-font: 15 arial;");
        log_in.setPrefSize(150, 75);
        log_in.setLayoutX(scene.getWidth()/2-75);
        log_in.setLayoutY(350);
        
        back.setStyle("-fx-font: 15 arial;");
        back.setPrefSize(150, 75);
        back.setLayoutX(50);
        back.setLayoutY(450);
            
        log_in.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Account account = new Account(username.getText(), password.getText() );
                    
                if (account.getID() == -1){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR !");
                    alert.setHeaderText("The username or the password are wrong !");
                    alert.setContentText("Check the details !");

                    alert.showAndWait();
               }else{
                    Home home = new Home(primaryStage, account);
                }
           }
        });
        
        log_in.setDefaultButton(true);
               
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Launch launch = new Launch(primaryStage); 
            }
        });
        
        
        root.getChildren().add(user_label);
        root.getChildren().add(username);
        root.getChildren().add(password_label);
        root.getChildren().add(password);
        root.getChildren().add(log_in);
        root.getChildren().add(back);
              
        primaryStage.setTitle("Budgestion");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
}