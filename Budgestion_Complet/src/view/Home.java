package view;

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

public class Home{
    public Home(Stage primaryStage, Account account) {
        Group root = new Group();
        
        Scene scene = new Scene(root, 960, 540, Color.LIGHTSTEELBLUE);
        
        primaryStage.setTitle("Budgestion");
    
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem menuHome = new MenuItem("Home");
        MenuItem menuYears = new MenuItem("Years");
        MenuItem menuMonths = new MenuItem("Months");
        MenuItem logOut = new MenuItem("Log Out");
        menu.getItems().addAll(menuHome, menuYears, menuMonths, logOut);
        menuBar.getMenus().addAll(menu);
        menuBar.setPrefWidth(960);
        
        Button earning = new Button("Earning");
        Button spending = new Button("Spending");
        
        Button import_button = new Button("Import");
        Button export_button = new Button("Export");
        
        import_button.setStyle("-fx-font: 22 arial;");
        import_button.setPrefSize(300, 150);
        import_button.setLayoutX(550);
        import_button.setLayoutY(100);
        
        export_button.setStyle("-fx-font: 22 arial;");
        export_button.setPrefSize(300, 150);
        export_button.setLayoutX(550);
        export_button.setLayoutY(300);
        
        earning.setStyle("-fx-font: 22 arial;");
        earning.setPrefSize(300, 150);
        earning.setLayoutX(120);
        earning.setLayoutY(100);
        
        spending.setStyle("-fx-font: 22 arial;");
        spending.setPrefSize(300, 150);
        spending.setLayoutX(120);
        spending.setLayoutY(300);
        
        earning.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage earning = new Stage();
                
                earning.getIcons().add(new Image(getClass().getResourceAsStream("up.png")));
                
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
                
                validate.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        if(date.getValue() == null || amount.getText().equals("")){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("ERROR !");
                                alert.setHeaderText("At least one field is missing !");
                                alert.setContentText("Check the details !");

                                alert.showAndWait();
                        } else {
                            if(this.isValidFloat(amount.getText()) && Double.parseDouble(amount.getText()) != 0){
                                //TODO
                            
                                earning.close();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("ERROR !");
                                alert.setHeaderText("The amount must be a real !");
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
                                
                earning.setTitle("Earning");
                earning.setScene(earning_scene);
                earning.show();
            }
        });
        
        spending.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage spending = new Stage();
                
                spending.getIcons().add(new Image(getClass().getResourceAsStream("down.jpg")));
                
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
                              
                RadioButton food = new RadioButton();
                food.setLayoutX(60);
                food.setLayoutY(200);
                Label food_label = new Label("Food");
                food_label.setStyle("-fx-font: 13 arial;");
                food_label.setLayoutX(80);
                food_label.setLayoutY(200);
                
                RadioButton multimedia = new RadioButton();
                multimedia.setLayoutX(60);
                multimedia.setLayoutY(220);
                Label multimedia_label = new Label("Multimedia");
                multimedia_label.setStyle("-fx-font: 13 arial;");
                multimedia_label.setLayoutX(80);
                multimedia_label.setLayoutY(220);
                
                RadioButton withdraw = new RadioButton();
                withdraw.setLayoutX(60);
                withdraw.setLayoutY(240);
                Label withdraw_label = new Label("Withdraw");
                withdraw_label.setStyle("-fx-font: 13 arial;");
                withdraw_label.setLayoutX(80);
                withdraw_label.setLayoutY(240);
                
                RadioButton hobbies = new RadioButton();
                hobbies.setLayoutX(60);
                hobbies.setLayoutY(260);
                Label hobbies_label = new Label("Hobbies");
                hobbies_label.setStyle("-fx-font: 13 arial;");
                hobbies_label.setLayoutX(80);
                hobbies_label.setLayoutY(260);
                
                RadioButton transports = new RadioButton();
                transports.setLayoutX(60);
                transports.setLayoutY(280);
                Label transports_label = new Label("Transports");
                transports_label.setStyle("-fx-font: 13 arial;");
                transports_label.setLayoutX(80);
                transports_label.setLayoutY(280);
                
                RadioButton other = new RadioButton();
                other.setLayoutX(60);
                other.setLayoutY(300);
                Label other_label = new Label("Other");
                other_label.setStyle("-fx-font: 13 arial;");
                other_label.setLayoutX(80);
                other_label.setLayoutY(300);
                
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
                
                validate.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        if(date.getValue() == null || amount.getText().equals("")){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("ERROR !");
                                alert.setHeaderText("At least one field is missing !");
                                alert.setContentText("Check the details !");

                                alert.showAndWait();
                        } else {
                            if(this.isValidFloat(amount.getText()) && Double.parseDouble(amount.getText()) != 0){
                                // TODO
                            
                                spending.close();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("ERROR !");
                                alert.setHeaderText("The amount must be a real and different from 0!");
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
                spending_group.getChildren().add(food_label);
                spending_group.getChildren().add(multimedia);
                spending_group.getChildren().add(multimedia_label);
                spending_group.getChildren().add(withdraw);
                spending_group.getChildren().add(withdraw_label);
                spending_group.getChildren().add(hobbies);
                spending_group.getChildren().add(hobbies_label);
                spending_group.getChildren().add(transports);
                spending_group.getChildren().add(transports_label);
                spending_group.getChildren().add(other);
                spending_group.getChildren().add(other_label);
                spending_group.getChildren().add(validate);
                
                spending.setTitle("Spending");
                spending.setScene(spending_scene);
                spending.show();
            }
        });
        
        import_button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ready to import");
            }
        });
        
        export_button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ready to export");
            }
        });
        
        logOut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Launch budgestion = new Launch(primaryStage);
            }
        });
        
        menuYears.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Years years = new Years(primaryStage, account);
            }
        });
        
        menuMonths.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Months months = new Months(primaryStage, account);
            }
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