package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Account;

public class Years {

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

        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Launch budgestion = new Launch(primaryStage);
            }
        });

        menuHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Home home = new Home(primaryStage, account);
            }
        });

        menuMonths.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Months months = new Months(primaryStage, account);
            }
        });

        menuYears.setDisable(true);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Month");
        yAxis.setLabel("€");

        final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("Spendings & Earnings, 2016");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Spending");

        series1.getData().add(new XYChart.Data("Jan", 23));
        series1.getData().add(new XYChart.Data("Feb", 14));
        series1.getData().add(new XYChart.Data("Mar", 15));
        series1.getData().add(new XYChart.Data("Apr", 24));
        series1.getData().add(new XYChart.Data("May", 34));
        series1.getData().add(new XYChart.Data("Jun", 36));
        series1.getData().add(new XYChart.Data("Jul", 22));
        series1.getData().add(new XYChart.Data("Aug", 45));
        series1.getData().add(new XYChart.Data("Sep", 43));
        series1.getData().add(new XYChart.Data("Oct", 17));
        series1.getData().add(new XYChart.Data("Nov", 29));
        series1.getData().add(new XYChart.Data("Dec", 25));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Earning");
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

        lineChart.getData().addAll(series1, series2);
        lineChart.setPrefSize(960, 500);
        lineChart.setLayoutY(40);

        final double SCALE_DELTA = 1.1;
        lineChart.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                event.consume();

                if (event.getDeltaY() == 0) {
                    return;
                }

                double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;

                lineChart.setScaleX(lineChart.getScaleX() * scaleFactor);
                lineChart.setScaleY(lineChart.getScaleY() * scaleFactor);
            }
        });

        lineChart.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    lineChart.setScaleX(1.0);
                    lineChart.setScaleY(1.0);
                }
            }
        });

        root.getChildren().add(menuBar);
        root.getChildren().add(lineChart);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
