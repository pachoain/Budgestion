package view;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Account;

public class Inscription {

    public Inscription(Stage primaryStage) {
        Group root = new Group();

        Scene scene = new Scene(root, 1300, 680, Color.LIGHTSTEELBLUE);

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

        personal_info.setStyle("-fx-font: 30 arial");
        personal_info.setLayoutX(200);
        personal_info.setLayoutY(100);

        lastname_label.setStyle("-fx-font: 20 arial");
        lastname_label.setLayoutX(200);
        lastname_label.setLayoutY(175);

        lastname.setStyle("-fx-font: 20 arial");
        lastname.setLayoutX(200);
        lastname.setLayoutY(200);

        firstname_label.setStyle("-fx-font: 20 arial");
        firstname_label.setLayoutX(200);
        firstname_label.setLayoutY(275);

        firstname.setStyle("-fx-font: 20 arial");
        firstname.setLayoutX(200);
        firstname.setLayoutY(300);

        user_label.setStyle("-fx-font: 20 arial;");
        user_label.setLayoutX(800);
        user_label.setLayoutY(175);

        username.setStyle("-fx-font: 20 arial;");
        username.setPrefSize(250, 25);
        username.setLayoutX(800);
        username.setLayoutY(200);

        password_label.setStyle("-fx-font: 20 arial;");
        password_label.setLayoutX(800);
        password_label.setLayoutY(275);

        password.setStyle("-fx-font: 20 arial;");
        password.setPrefSize(250, 25);
        password.setLayoutX(800);
        password.setLayoutY(300);

        confirmation_label.setStyle("-fx-font: 20 arial;");
        confirmation_label.setLayoutX(800);
        confirmation_label.setLayoutY(375);

        confirmation_password_label.setStyle("-fx-font: 20 arial;");
        confirmation_password_label.setPrefSize(250, 25);
        confirmation_password_label.setLayoutX(800);
        confirmation_password_label.setLayoutY(400);

        validate.setStyle("-fx-font: 20 arial;");
        validate.setPrefSize(200, 100);
        validate.setLayoutX(850);
        validate.setLayoutY(500);

        back.setStyle("-fx-font: 20 arial;");
        back.setPrefSize(200, 100);
        back.setLayoutX(50);
        back.setLayoutY(550);

        validate.setOnAction((ActionEvent event) -> {
            if (username.getText().equals("") || password.getText().equals("") || confirmation_password_label.getText().equals("") || firstname.getText().equals("") || lastname.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR !");
                alert.setHeaderText("At least one field is missing !");
                alert.setContentText("You must complete all fields !");
                
                alert.showAndWait();
            } else {
                if (password.getText().equals(confirmation_password_label.getText())) {
                    
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
                    
                    Account account = new Account(firstname.getText(), lastname.getText(), username.getText(), hashString.toString());
                    if (account.getID() == -1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR !");
                        alert.setHeaderText("This username already exists !");
                        alert.setContentText("Check the details !");
                        
                        alert.showAndWait();
                    } else {
                        Home home = new Home(primaryStage, account);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR !");
                    alert.setHeaderText("The passwords do not match !");
                    alert.setContentText("Password and confirmation password must be the same !");
                    
                    alert.showAndWait();
                }
            }
        });

        validate.setDefaultButton(true);

        back.setOnAction((ActionEvent event) -> {
            Launch launch = new Launch(primaryStage);
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