package view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Account;

public class Home {

    public Home(Stage primaryStage, Account account) {
        Group root = new Group();

        Scene scene = new Scene(root, 1300, 680, Color.LIGHTSTEELBLUE);

        primaryStage.setTitle("Budgestion");

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem menuHome = new MenuItem("Home");
        MenuItem menuYears = new MenuItem("Years");
        MenuItem menuMonths = new MenuItem("Months");
        MenuItem logOut = new MenuItem("Log Out");
        menu.getItems().addAll(menuHome, menuYears, menuMonths, logOut);
        menuBar.getMenus().addAll(menu);
        menuBar.setPrefWidth(1300);

        Button earning = new Button("Earning");
        Button spending = new Button("Spending");

        Button import_button = new Button("Import");
        Button export_button = new Button("Export");

        import_button.setStyle("-fx-font: 30 arial;");
        import_button.setPrefSize(400, 200);
        import_button.setLayoutX(790);
        import_button.setLayoutY(100);

        export_button.setStyle("-fx-font: 30 arial;");
        export_button.setPrefSize(400, 200);
        export_button.setLayoutX(790);
        export_button.setLayoutY(400);

        earning.setStyle("-fx-font: 30 arial;");
        earning.setPrefSize(400, 200);
        earning.setLayoutX(130);
        earning.setLayoutY(100);

        spending.setStyle("-fx-font: 30 arial;");
        spending.setPrefSize(400, 200);
        spending.setLayoutX(130);
        spending.setLayoutY(400);

        earning.setOnAction((ActionEvent event) -> {
            Stage earning1 = new Stage();
            earning1.getIcons().add(new Image(getClass().getResourceAsStream("up.png")));
            Group earning_group = new Group();
            Scene earning_scene = new Scene(earning_group, 300, 250, Color.LIGHTGREEN);
            Label date_label = new Label("Date");
            date_label.setStyle("-fx-font: 15 arial;");
            date_label.setLayoutX(50);
            date_label.setLayoutY(20);
            DatePicker date = new DatePicker();
            date.setLayoutX(50);
            date.setLayoutY(40);
            Label amount_label = new Label("Amount");
            amount_label.setStyle("-fx-font: 15 arial;");
            amount_label.setLayoutX(50);
            amount_label.setLayoutY(90);
            TextField amount = new TextField();
            amount.setLayoutX(50);
            amount.setLayoutY(110);
            Button validate = new Button("Validate");
            validate.setStyle("-fx-font: 15 arial;");
            validate.setLayoutX(200);
            validate.setLayoutY(200);
            validate.setDefaultButton(true);
            validate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (date.getValue() == null || amount.getText().equals("")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR !");
                        alert.setHeaderText("At least one field is missing !");
                        alert.setContentText("Check the details !");
                        
                        alert.showAndWait();
                    } else {
                        if (this.isValidFloat(amount.getText()) && Double.parseDouble(amount.getText()) > 0) {
                            try {
                                account.addOperation(Float.parseFloat(amount.getText()), java.sql.Date.valueOf(date.getValue()), 0);
                                earning1.close();
                            }catch (SQLException ex) {
                                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR !");
                            alert.setHeaderText("The amount must be a real and positive!");
                            alert.setContentText("Check the details !");

                            alert.showAndWait();
                        }
                    }
                }

                private boolean isValidFloat(String str) {
                    try {
                        Double.parseDouble(str);
                    } catch (NumberFormatException nfe) {
                        return false;
                    }
                    return true;
                }
            });
            earning_group.getChildren().add(date_label);
            earning_group.getChildren().add(date);
            earning_group.getChildren().add(amount_label);
            earning_group.getChildren().add(amount);
            earning_group.getChildren().add(validate);
            earning1.setTitle("Earning");
            earning1.setScene(earning_scene);
            earning1.show();
        });

        spending.setOnAction((ActionEvent event) -> {
            Stage spending1 = new Stage();
            spending1.getIcons().add(new Image(getClass().getResourceAsStream("down.jpg")));
            Group spending_group = new Group();
            Scene spending_scene = new Scene(spending_group, 300, 350, Color.LIGHTSALMON);
            Label date_label = new Label("Date");
            date_label.setStyle("-fx-font: 15 arial;");
            date_label.setLayoutX(50);
            date_label.setLayoutY(20);
            DatePicker date = new DatePicker();
            date.setLayoutX(50);
            date.setLayoutY(40);
            Label amount_label = new Label("Amount");
            amount_label.setStyle("-fx-font: 15 arial;");
            amount_label.setLayoutX(50);
            amount_label.setLayoutY(90);
            TextField amount = new TextField();
            amount.setLayoutX(50);
            amount.setLayoutY(110);
            Label category_label = new Label("Categories");
            category_label.setStyle("-fx-font: 15 arial;");
            category_label.setLayoutX(50);
            category_label.setLayoutY(160);
            RadioButton food = new RadioButton("Food");
            food.setUserData(1);
            food.setLayoutX(60);
            food.setLayoutY(200);
            RadioButton multimedia = new RadioButton("Multimedia");
            multimedia.setUserData(2);
            multimedia.setLayoutX(60);
            multimedia.setLayoutY(220);
            RadioButton withdraw = new RadioButton("Withdraw");
            withdraw.setUserData(3);
            withdraw.setLayoutX(60);
            withdraw.setLayoutY(240);
            RadioButton hobbies = new RadioButton("Hobbies");
            hobbies.setUserData(4);
            hobbies.setLayoutX(60);
            hobbies.setLayoutY(260);
            RadioButton transports = new RadioButton("Transports");
            transports.setUserData(5);
            transports.setLayoutX(60);
            transports.setLayoutY(280);
            RadioButton other = new RadioButton("Other");
            other.setUserData(6);
            other.setLayoutX(60);
            other.setLayoutY(300);
            ToggleGroup categories = new ToggleGroup();
            food.setToggleGroup(categories);
            multimedia.setToggleGroup(categories);
            withdraw.setToggleGroup(categories);
            hobbies.setToggleGroup(categories);
            transports.setToggleGroup(categories);
            other.setToggleGroup(categories);
            food.setFocusTraversable(false);
            multimedia.setFocusTraversable(false);
            withdraw.setFocusTraversable(false);
            hobbies.setFocusTraversable(false);
            transports.setFocusTraversable(false);
            other.setFocusTraversable(false);
            food.setSelected(true);
            Button validate = new Button("Validate");
            validate.setStyle("-fx-font: 15 arial;");
            validate.setLayoutX(200);
            validate.setLayoutY(300);
            validate.setDefaultButton(true);
            validate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (date.getValue() == null || amount.getText().equals("")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR !");
                        alert.setHeaderText("At least one field is missing !");
                        alert.setContentText("Check the details !");
                        
                        alert.showAndWait();
                    } else {
                        if (this.isValidFloat(amount.getText()) && Double.parseDouble(amount.getText()) > 0) {
                            try {
                                float spending_amount = - Float.parseFloat(amount.getText());
                                account.addOperation(spending_amount, java.sql.Date.valueOf(date.getValue()), (int)(categories.getSelectedToggle().getUserData()));
                                spending1.close();
                            }catch (SQLException ex) {
                                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR !");
                            alert.setHeaderText("The amount must be a real and positive !");
                            alert.setContentText("Check the details !");

                            alert.showAndWait();
                        }
                    }
                }

                private boolean isValidFloat(String str) {
                    try {
                        Double.parseDouble(str);
                    } catch (NumberFormatException nfe) {
                        return false;
                    }
                    return true;
                }
            });
            spending_group.getChildren().add(date_label);
            spending_group.getChildren().add(date);
            spending_group.getChildren().add(amount_label);
            spending_group.getChildren().add(amount);
            spending_group.getChildren().add(category_label);
            spending_group.getChildren().add(food);
            spending_group.getChildren().add(multimedia);
            spending_group.getChildren().add(withdraw);
            spending_group.getChildren().add(hobbies);
            spending_group.getChildren().add(transports);
            spending_group.getChildren().add(other);
            spending_group.getChildren().add(validate);
            spending1.setTitle("Spending");
            spending1.setScene(spending_scene);
            spending1.show();
        });

        import_button.setOnAction((ActionEvent event) -> {
            System.out.println("Ready to import");
        });

        export_button.setOnAction((ActionEvent event) -> {
            System.out.println("Ready to export");
        });

        logOut.setOnAction((ActionEvent event) -> {
            Launch budgestion = new Launch(primaryStage);
        });

        menuYears.setOnAction((ActionEvent event) -> {
            Years years = new Years(primaryStage, account);
        });

        menuMonths.setOnAction((ActionEvent event) -> {
            Months months = new Months(primaryStage, account);
        });

        menuHome.setDisable(true);

        root.getChildren().add(menuBar);
        root.getChildren().add(earning);
        root.getChildren().add(spending);
        root.getChildren().add(import_button);
        root.getChildren().add(export_button);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
