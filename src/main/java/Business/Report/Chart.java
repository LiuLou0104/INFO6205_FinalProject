package Business.Report;

import Business.Plot.LineChart;
import Business.Plot.ScatterPlot;
import Business.Simulation.PageSimu;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.Date;

import static Business.Plot.PlotAbstract.*;

public class Chart {
    //Date、File
    private Date date;
    private String title;
    private String fileName;

    public Chart() {
        this.date = new Date();
    }

    public Chart(String title) {
        this.date = new Date();
        this.title = title;
    }

    public void drawLineChart(PageSimu pageSimu) {
        LineChart lineChart = new LineChart(this.title + "LineChart", "Time", "Infect Population");
        XYSeriesCollection dataset = getXYSeriesDataSet(pageSimu.getOnePathogenSimuList());
        JFreeChart chart = lineChart.Plot(dataset);

        this.fileName = lineChart.getTitle() + "_" + date;
        saveChartAsJPG(chart, lineChart.getTitle(), this.fileName);
    }

    public void drawScatterPlot() {
        ScatterPlot chart = new ScatterPlot(this.title + "ScatterPlot", "", "");
    }
}
