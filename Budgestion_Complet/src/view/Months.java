package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Account;

public class Months {
    public Months(Stage primaryStage, Account account){
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
        
        logOut.setOnAction((ActionEvent event) -> {
            Launch budgestion = new Launch(primaryStage);
        });
        
        menuHome.setOnAction((ActionEvent event) -> {
            Home home = new Home(primaryStage, account);
        });
        
        menuYears.setOnAction((ActionEvent event) -> {
            Years years = new Years(primaryStage, account);
        });
        
        menuMonths.setDisable(true);
        
        Label choicelabel = new Label("Select a month :");
        choicelabel.setStyle("-fx-font: 17 arial;");
        choicelabel.setLayoutX(20);
        choicelabel.setLayoutY(44);
        
        ChoiceBox cm = new ChoiceBox(FXCollections.observableArrayList(
            "Jan 2017", "Feb 2017", "Mar 2017", "Apr 2017", "May 2017")
        );
        cm.setTooltip(new Tooltip("Select the month"));
        cm.setLayoutY(40);
        cm.setLayoutX(150);
        
        Button val = new Button("Validate");
        val.setStyle("-fx-font: 15 arial;");
        val.setLayoutX(280);
        val.setLayoutY(42);
        val.setDefaultButton(true);
        
        final PieChart chart = new PieChart();
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        chart.setPrefSize(960, 460);
        chart.setLayoutY(80);
        
        val.setOnAction((ActionEvent event) -> {
            if(cm.getValue()!= null){
                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList(
                                new PieChart.Data("Food", 15),
                                new PieChart.Data("Multimedia", 25),
                                new PieChart.Data("Withdraw", 10),
                                new PieChart.Data("Hobbies", 17),
                                new PieChart.Data("Transports", 33),
                                new PieChart.Data("Other", 20));
                
                chart.setData(pieChartData);
                chart.setTitle("Spendings on "+cm.getValue());
            }
        });

        root.getChildren().add(menuBar);
        root.getChildren().add(choicelabel);
        root.getChildren().add(cm);
        root.getChildren().add(val);
        root.getChildren().add(chart);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}