package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
        
        earning.setStyle("-fx-font: 22 arial;");
        earning.setPrefSize(300, 150);
        earning.setLayoutX(70);
        earning.setLayoutY(100);
        
        spending.setStyle("-fx-font: 22 arial;");
        spending.setPrefSize(300, 150);
        spending.setLayoutX(70);
        spending.setLayoutY(300);
        
        earning.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage earning = new Stage();
                
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
                
                RadioButton undefined = new RadioButton();
                undefined.setLayoutX(60);
                undefined.setLayoutY(300);
                Label undefined_label = new Label("Undefined");
                undefined_label.setStyle("-fx-font: 13 arial;");
                undefined_label.setLayoutX(80);
                undefined_label.setLayoutY(300);
                
                ToggleGroup categories = new ToggleGroup();
                
                food.setToggleGroup(categories);
                multimedia.setToggleGroup(categories);
                withdraw.setToggleGroup(categories);
                hobbies.setToggleGroup(categories);
                transports.setToggleGroup(categories);
                undefined.setToggleGroup(categories);
                        
                food.setFocusTraversable(false);
                multimedia.setFocusTraversable(false);
                withdraw.setFocusTraversable(false);
                hobbies.setFocusTraversable(false);
                transports.setFocusTraversable(false);  
                undefined.setFocusTraversable(false);
                        
                food.setSelected(true);
                
                Button validate = new Button("Validate");
                validate.setStyle("-fx-font: 15 arial;");
                validate.setLayoutX(200);
                validate.setLayoutY(300);
                
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
                spending_group.getChildren().add(undefined);
                spending_group.getChildren().add(undefined_label);
                spending_group.getChildren().add(validate);
                
                spending.setTitle("Spending");
                spending.setScene(spending_scene);
                spending.show();
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
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
