package Business.Plot;

import Business.Report.Chart;
import Business.Simulation.PageSimu;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;

import static Business.Plot.PlotAbstract.*;

public class Plot {

    public static void drawLineChartInfectNum(PageSimu pageSimu, String title) {
        LineChart lineChart = new LineChart(title + " Line Chart", "Time", "Total Infect Population");
        XYSeriesCollection dataset = getXYSeriesDataSetInfectNum(pageSimu.getOnePathogenSimuList());
        JFreeChart chart = lineChart.Plot(dataset);

        String fileName = lineChart.getTitle() + "_"/* + date*/;
        Chart outputChart = new Chart(title, fileName);
        /* add outputChart to ChartDirectory*/
        pageSimu.addChart(outputChart);
        saveChartAsJPG(chart, lineChart.getTitle(), fileName);
    }

    public static void drawLineChartInfectUnits(PageSimu pageSimu, String title) {
        LineChart lineChart = new LineChart(title + " Line Chart", "Time", "Infect Number of Areas");
        XYSeriesCollection dataset = getXYSeriesDataSetInfectUnits(pageSimu.getOnePathogenSimuList());
        JFreeChart chart = lineChart.Plot(dataset);

        String fileName = lineChart.getTitle() + "_"/* + date*/;
        Chart outputChart = new Chart(title, fileName);
        /* add outputChart to ChartDirectory*/
        pageSimu.addChart(outputChart);
        saveChartAsJPG(chart, lineChart.getTitle(), fileName);
    }

    public static void drawScatterPlot(PageSimu pageSimu, String title) {
        ScatterPlot chart = new ScatterPlot(title + "ScatterPlot", "", "");
    }

    public static void drawTimeSeriesChart(PageSimu pageSimu, String title) {
        TimeSeriesChart chart = new TimeSeriesChart(title + "TimeSeriesChart", "", "");
    }
}
