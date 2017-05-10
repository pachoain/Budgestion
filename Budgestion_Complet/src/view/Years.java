package view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Account;

public class Years {

    public boolean check = false;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Years(Stage primaryStage, Account account) {
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

        menuMonths.setOnAction((ActionEvent event) -> {
            Months months = new Months(primaryStage, account);
        });

        menuYears.setDisable(true);

        Label choicelabel = new Label("Select a year :");
        choicelabel.setStyle("-fx-font: 20 arial;");
        choicelabel.setLayoutX(20);
        choicelabel.setLayoutY(44);

        ObservableList dfx = FXCollections.observableArrayList();
        ArrayList<Integer> date = account.getPossibleYear();
        ChoiceBox cy = new ChoiceBox();
        for (int d : date) {
            dfx.add(d);
        }
        cy.setItems(dfx);
        cy.setTooltip(new Tooltip("Select the month"));
        cy.setLayoutY(40);
        cy.setLayoutX(200);

        Button val = new Button("Validate");
        val.setStyle("-fx-font: 20 arial;");
        val.setLayoutX(300);
        val.setLayoutY(42);
        val.setDefaultButton(true);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Month");
        yAxis.setLabel("€");

        final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setPrefSize(1250, 600);
        lineChart.setLayoutY(80);

        val.setOnAction((ActionEvent event) -> {
            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Spendings");
            XYChart.Series series2 = new XYChart.Series();
            series2.setName("Earnings");
             XYChart.Series series3 = new XYChart.Series();
            series3.setName("Total");
            if (cy.getValue() != null) {
                double[] spending = account.getSpendingsByYear(Integer.parseInt(cy.getValue().toString()));
                double[] income = account.getIncomesByYear(Integer.parseInt(cy.getValue().toString()));
                double[] total = account.getTotalOperationByYear(Integer.parseInt(cy.getValue().toString()));
                series1.getData().add(new XYChart.Data("Jan", spending[0]));
                series1.getData().add(new XYChart.Data("Feb", spending[1]));
                series1.getData().add(new XYChart.Data("Mar", spending[2]));
                series1.getData().add(new XYChart.Data("Apr", spending[3]));
                series1.getData().add(new XYChart.Data("May", spending[4]));
                series1.getData().add(new XYChart.Data("Jun", spending[5]));
                series1.getData().add(new XYChart.Data("Jul", spending[6]));
                series1.getData().add(new XYChart.Data("Aug", spending[7]));
                series1.getData().add(new XYChart.Data("Sep", spending[8]));
                series1.getData().add(new XYChart.Data("Oct", spending[9]));
                series1.getData().add(new XYChart.Data("Nov", spending[10]));
                series1.getData().add(new XYChart.Data("Dec", spending[11]));

                series2.getData().add(new XYChart.Data("Jan", income[0]));
                series2.getData().add(new XYChart.Data("Feb", income[1]));
                series2.getData().add(new XYChart.Data("Mar", income[2]));
                series2.getData().add(new XYChart.Data("Apr", income[3]));
                series2.getData().add(new XYChart.Data("May", income[4]));
                series2.getData().add(new XYChart.Data("Jun", income[5]));
                series2.getData().add(new XYChart.Data("Jul", income[6]));
                series2.getData().add(new XYChart.Data("Aug", income[7]));
                series2.getData().add(new XYChart.Data("Sep", income[8]));
                series2.getData().add(new XYChart.Data("Oct", income[9]));
                series2.getData().add(new XYChart.Data("Nov", income[10]));
                series2.getData().add(new XYChart.Data("Dec", income[11]));
                
                series3.getData().add(new XYChart.Data("Jan", total[0]));
                series3.getData().add(new XYChart.Data("Feb", total[1]));
                series3.getData().add(new XYChart.Data("Mar", total[2]));
                series3.getData().add(new XYChart.Data("Apr", total[3]));
                series3.getData().add(new XYChart.Data("May", total[4]));
                series3.getData().add(new XYChart.Data("Jun", total[5]));
                series3.getData().add(new XYChart.Data("Jul", total[6]));
                series3.getData().add(new XYChart.Data("Aug", total[7]));
                series3.getData().add(new XYChart.Data("Sep", total[8]));
                series3.getData().add(new XYChart.Data("Oct", total[9]));
                series3.getData().add(new XYChart.Data("Nov", total[10]));
                series3.getData().add(new XYChart.Data("Dec", total[11]));

                if (check) {
                    lineChart.getData().remove(0, 3);
                }
                lineChart.getData().addAll(series1, series3, series2);
                lineChart.setTitle("Spendings & Earnings, " + cy.getValue());
                setCheck(true);
            }
        });

              
        root.getChildren().add(menuBar);
        root.getChildren().add(choicelabel);
        root.getChildren().add(cy);
        root.getChildren().add(val);
        root.getChildren().add(lineChart);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
