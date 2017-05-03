package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Inscription {
    
    public Inscription(Stage primaryStage){
    Group root = new Group();
        
        Scene scene = new Scene(root, 960, 540, Color.LIGHTSTEELBLUE);
        
        Label user_label = new Label("Username");
        TextField username = new TextField();
        Label password_label = new Label("Password");
        PasswordField password = new PasswordField();
        Label confirmation_label = new Label("Password Confirmation");
        PasswordField confirmation_password_label = new PasswordField();
        Label personal_info = new Label("Personal Informations");
        Label lastname_label = new Label("Last Name");
        TextField lastname = new TextField();
        Label firstname_label = new Label("First Name");
        TextField firstname = new TextField();
        Button validate = new Button("Validate"); 
        Button back = new Button("Back");
        
        personal_info.setStyle("-fx-font: 22 arial");
        personal_info.setLayoutX(75);
        personal_info.setLayoutY(70);
        
        lastname_label.setStyle("-fx-font: 15 arial");
        lastname_label.setLayoutX(100);
        lastname_label.setLayoutY(120);
        
        lastname.setStyle("-fx-font: 15 arial");
        lastname.setLayoutX(100);
        lastname.setLayoutY(140);
        
        firstname_label.setStyle("-fx-font: 15 arial");
        firstname_label.setLayoutX(100);
        firstname_label.setLayoutY(195);
        
        firstname.setStyle("-fx-font: 15 arial");
        firstname.setLayoutX(100);
        firstname.setLayoutY(215);
        
        user_label.setStyle("-fx-font: 15 arial;");
        user_label.setLayoutX(600);
        user_label.setLayoutY(75);
        
        username.setStyle("-fx-font: 15 arial;");
        username.setPrefSize(250,25);
        username.setLayoutX(600);
        username.setLayoutY(95);
        
        password_label.setStyle("-fx-font: 15 arial;");
        password_label.setLayoutX(600);
        password_label.setLayoutY(150);
        
        password.setStyle("-fx-font: 15 arial;");
        password.setPrefSize(250,25);
        password.setLayoutX(600);
        password.setLayoutY(170);
        
        confirmation_label.setStyle("-fx-font: 15 arial;");
        confirmation_label.setLayoutX(600);
        confirmation_label.setLayoutY(225);
        
        confirmation_password_label.setStyle("-fx-font: 15 arial;");
        confirmation_password_label.setPrefSize(250,25);
        confirmation_password_label.setLayoutX(600);
        confirmation_password_label.setLayoutY(245);
        
        validate.setStyle("-fx-font: 15 arial;");
        validate.setPrefSize(150, 75);
        validate.setLayoutX(650);
        validate.setLayoutY(350);
        
        back.setStyle("-fx-font: 15 arial;");
        back.setPrefSize(150, 75);
        back.setLayoutX(50);
        back.setLayoutY(450);
            
        validate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Home home = new Home(primaryStage);
                System.out.println("Validate");
            }
        });
        
        validate.setDefaultButton(true);
        
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Launch launch = new Launch(primaryStage); 
            }
        });
        
        
        root.getChildren().add(personal_info);
        root.getChildren().add(lastname_label);
        root.getChildren().add(lastname);
        root.getChildren().add(firstname_label);
        root.getChildren().add(firstname);
        root.getChildren().add(user_label);
        root.getChildren().add(username);
        root.getChildren().add(password_label);
        root.getChildren().add(password);
        root.getChildren().add(confirmation_label);
        root.getChildren().add(confirmation_password_label);
        root.getChildren().add(validate);
        root.getChildren().add(back);
              
        primaryStage.setTitle("Budgestion");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
}