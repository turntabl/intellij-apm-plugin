package io.turntabl.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class CpuGraph {
    private ChartPanel chartPanel;
    private String title;

    public CpuGraph(XYDataset dataSet, String title, String xAxisLabel, String yAxisLabel) {
//        XYDataset dataset = createDataset();
//        XYDataset dataset = createDataset();
        this.title = title;

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                xAxisLabel,
                yAxisLabel,
                dataSet,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));

        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));

        renderer.setSeriesPaint(2, Color.green);
        renderer.setSeriesStroke(2, new BasicStroke(1.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.BLACK);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.RED);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.RED);

        chart.getLegend().setFrame(BlockBorder.NONE);
        chart.getTitle().setPaint(Color.white);
        chart.setTitle(new TextTitle(this.title, new Font("Serif", java.awt.Font.BOLD, 18)));

        chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.BLACK);
    }

    private XYDataset createDataset() {

        XYSeries series = new XYSeries("2016");
        series.add(18, 567);
        series.add(20, 612);
        series.add(25, 800);
        series.add(30, 980);
        series.add(40, 1410);
        series.add(50, 2350);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    public ChartPanel getChart() {
        return this.chartPanel;
    }
}
