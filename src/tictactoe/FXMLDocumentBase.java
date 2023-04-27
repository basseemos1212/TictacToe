package tictactoe;

import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.BorderPane;

public class FXMLDocumentBase extends BorderPane {

    protected final NumberAxis numberAxis;
    protected final NumberAxis numberAxis0;
    protected final BubbleChart bubbleChart;

    public FXMLDocumentBase() {

        numberAxis = new NumberAxis();
        numberAxis0 = new NumberAxis();
        bubbleChart = new BubbleChart(numberAxis, numberAxis0);

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        numberAxis.setSide(javafx.geometry.Side.BOTTOM);

        numberAxis0.setSide(javafx.geometry.Side.LEFT);
        BorderPane.setAlignment(bubbleChart, javafx.geometry.Pos.CENTER);
        setCenter(bubbleChart);

    }
}
