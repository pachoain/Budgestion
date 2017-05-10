package view;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.event.ActionEvent;
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
        
        Scene scene = new Scene(root, 1300, 680, Color.LIGHTSTEELBLUE);
        
        Label user_label = new Label("Username");
        TextField username = new TextField();
        Label password_label = new Label("Password");
        PasswordField password = new PasswordField();
        Button log_in = new Button("Log In"); 
        Button back = new Button("Back");
        
        user_label.setStyle("-fx-font: 20 arial;");
        user_label.setLayoutX(scene.getWidth()/2-125);
        user_label.setLayoutY(100);
        
        username.setStyle("-fx-font: 20 arial;");
        username.setPrefSize(250,25);
        username.setLayoutX(scene.getWidth()/2-125);
        username.setLayoutY(125);
        
        password_label.setStyle("-fx-font: 20 arial;");
        password_label.setLayoutX(scene.getWidth()/2-125);
        password_label.setLayoutY(225);
        
        password.setStyle("-fx-font: 20 arial;");
        password.setPrefSize(250,25);
        password.setLayoutX(scene.getWidth()/2-125);
        password.setLayoutY(250);
        
        log_in.setStyle("-fx-font: 20 arial;");
        log_in.setPrefSize(200, 100);
        log_in.setLayoutX(scene.getWidth()/2-100);
        log_in.setLayoutY(400);
        
        back.setStyle("-fx-font: 20 arial;");
        back.setPrefSize(200, 100);
        back.setLayoutX(50);
        back.setLayoutY(550);
            
        log_in.setOnAction((ActionEvent event) -> {
            
            byte[] uniqueKey = password.getText().getBytes();
            byte[] hash = null;

            try {
                hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
            } catch (NoSuchAlgorithmException e) {
                throw new Error("No MD5 support in this VM.");
            }

            StringBuilder hashString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(hash[i]);
                if (hex.length() == 1) {
                    hashString.append('0');
                    hashString.append(hex.charAt(hex.length() - 1));
                } else {
                    hashString.append(hex.substring(hex.length() - 2));
                }
            }
            
            Account account = new Account(username.getText(), hashString.toString() );
            
            if (account.getID() == -1){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR !");
                alert.setHeaderText("The username or the password are wrong !");
                alert.setContentText("Check the details !");
                
                alert.showAndWait();
            }else{
                Home home = new Home(primaryStage, account);
            }
    });
        
        log_in.setDefaultButton(true);
               
        back.setOnAction((ActionEvent event) -> {
            Launch launch = new Launch(primaryStage);
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