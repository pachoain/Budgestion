package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Months {
    public Months(Stage primaryStage){
        Group root = new Group();
        
        Scene scene = new Scene(root, 960, 540, Color.LIGHTSTEELBLUE);
        
        primaryStage.setTitle("Home");
    
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem menuHome = new MenuItem("Home");
        MenuItem menuYears = new MenuItem("Years");
        MenuItem menuMonths = new MenuItem("Months");
        MenuItem logOut = new MenuItem("Log Out");
        menu.getItems().addAll(menuHome, menuYears, menuMonths, logOut);
        menuBar.getMenus().addAll(menu);
        menuBar.setPrefWidth(960);
        
        logOut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Launch budgestion = new Launch(primaryStage);
            }
        });
        
        menuHome.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Home home = new Home(primaryStage);
            }
        });
        
        menuYears.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Years years = new Years(primaryStage);
            }
        });
        
        menuMonths.setDisable(true);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Hobbies", 15),
                new PieChart.Data("Food", 25),
                new PieChart.Data("Clothes", 10),
                new PieChart.Data("Other", 17),
                new PieChart.Data("Rent", 33));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Spendings on Months");
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        chart.setPrefSize(960, 500);
        chart.setLayoutY(40);

        root.getChildren().add(menuBar);
        root.getChildren().add(chart);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}