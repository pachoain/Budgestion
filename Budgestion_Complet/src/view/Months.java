package view;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
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
        choicelabel.setStyle("-fx-font: 20 arial;");
        choicelabel.setLayoutX(20);
        choicelabel.setLayoutY(44);
        ArrayList<Date> date = account.getPossibleDate();
        ObservableList<String> dfx = FXCollections.observableArrayList();
        ChoiceBox cm = new ChoiceBox();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul","Aug", "Sep", "Oct","Nov","Dec"};
        Calendar c = Calendar.getInstance();
        for(Date d : date){
            c.setTime(d);
            String s = months[c.get(Calendar.MONTH)] + " " + c.get(Calendar.YEAR);
            dfx.add(s);
        }
        cm.setItems(dfx);
        cm.setTooltip(new Tooltip("Select the month"));
        cm.setLayoutY(40);
        cm.setLayoutX(200);
        
        Button val = new Button("Validate");
        val.setStyle("-fx-font: 20 arial;");
        val.setLayoutX(350);
        val.setLayoutY(42);
        val.setDefaultButton(true);
        
        final PieChart chart = new PieChart();
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        chart.setPrefSize(1250, 600);
        chart.setLayoutY(80);
        val.setOnAction((ActionEvent event) -> {
            if(cm.getValue()!= null){
                String[] s = cm.getValue().toString().split(" ");
                int month = -1;
                int year = Integer.parseInt(s[1]);
                for(int i = 0; i < 12; i++){
                    if(s[0].equals(months[i])){
                        month = i;
                        break;
                    }
                }
                double[] monthsSpending = account.getMonth(month, year);
                System.out.println(monthsSpending[1]);
                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList(
                                new PieChart.Data("Food", monthsSpending[1]),
                                new PieChart.Data("Multimedia", monthsSpending[2]),
                                new PieChart.Data("Withdraw", monthsSpending[3]),
                                new PieChart.Data("Hobbies", monthsSpending[4]),
                                new PieChart.Data("Transports", monthsSpending[5]),
                                new PieChart.Data("Other", monthsSpending[6]));
                
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