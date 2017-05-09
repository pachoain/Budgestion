package view;

import javafx.collections.FXCollections;
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

        menuMonths.setOnAction((ActionEvent event) -> {
            Months months = new Months(primaryStage, account);
        });

        menuYears.setDisable(true);

        Label choicelabel = new Label("Select a year :");
        choicelabel.setStyle("-fx-font: 17 arial;");
        choicelabel.setLayoutX(20);
        choicelabel.setLayoutY(44);

        ChoiceBox cy = new ChoiceBox(FXCollections.observableArrayList(
                2014, 2015, 2016, 2017)
        );
        cy.setTooltip(new Tooltip("Select the month"));
        cy.setLayoutY(40);
        cy.setLayoutX(150);

        Button val = new Button("Validate");
        val.setStyle("-fx-font: 15 arial;");
        val.setLayoutX(280);
        val.setLayoutY(42);
        val.setDefaultButton(true);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Month");
        yAxis.setLabel("€");

        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setPrefSize(960, 460);
        lineChart.setLayoutY(80);

        val.setOnAction((ActionEvent event) -> {
            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Spending");
            XYChart.Series series2 = new XYChart.Series();
            series2.setName("Earning");
            if (cy.getValue() != null) {
                
                series1.getData().add(new XYChart.Data("Jan", 13));
                series1.getData().add(new XYChart.Data("Feb", 24));
                series1.getData().add(new XYChart.Data("Mar", 25));
                series1.getData().add(new XYChart.Data("Apr", 14));
                series1.getData().add(new XYChart.Data("May", 24));
                series1.getData().add(new XYChart.Data("Jun", 26));
                series1.getData().add(new XYChart.Data("Jul", 42));
                series1.getData().add(new XYChart.Data("Aug", 35));
                series1.getData().add(new XYChart.Data("Sep", 63));
                series1.getData().add(new XYChart.Data("Oct", 47));
                series1.getData().add(new XYChart.Data("Nov", 39));
                series1.getData().add(new XYChart.Data("Dec", 15));
                
                series2.getData().add(new XYChart.Data("Jan", 33));
                series2.getData().add(new XYChart.Data("Feb", 34));
                series2.getData().add(new XYChart.Data("Mar", 25));
                series2.getData().add(new XYChart.Data("Apr", 44));
                series2.getData().add(new XYChart.Data("May", 39));
                series2.getData().add(new XYChart.Data("Jun", 16));
                series2.getData().add(new XYChart.Data("Jul", 55));
                series2.getData().add(new XYChart.Data("Aug", 54));
                series2.getData().add(new XYChart.Data("Sep", 48));
                series2.getData().add(new XYChart.Data("Oct", 27));
                series2.getData().add(new XYChart.Data("Nov", 37));
                series2.getData().add(new XYChart.Data("Dec", 29));
                
                if (check) {
                    lineChart.getData().remove(0, 2);
                }
                lineChart.getData().addAll(series1, series2);
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
