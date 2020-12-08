package Business.Plot;

import Business.DrawArea.AreaUnit;
import Business.Report.Chart;
import Business.Simulation.OnePathogenSimu;
import Business.Simulation.PageSimu;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.Date;

import static Business.Plot.PlotAbstract.*;
import static Business.Utilities.DateFormat.*;

public class Plot {

    public static void drawLineChartInfectNum(PageSimu pageSimu, String title) {
        LineChart lineChart = new LineChart(title + "LineChart", "Time", "Total Infect Population");
        XYSeriesCollection dataset = getXYSeriesDataSetInfectNum(pageSimu.getOnePathogenSimuList());
        JFreeChart chart = lineChart.Plot(dataset);

        Date date = new Date();
        String fileName = lineChart.getTitle() + "_" + dateFormat3(date);
        Chart outputChart = new Chart(date, fileName);
        /* add outputChart to ChartDirectory*/
        pageSimu.addChart(outputChart);
        saveChartAsJPG(chart, lineChart.getTitle(), fileName);
    }

    public static void drawLineChartInfectNum(OnePathogenSimu pathSimu, String title) {
        LineChart lineChart = new LineChart(title + "LineChart", "Time", "Total Infect Population");
        XYSeriesCollection dataset = getXYSeriesDataSetInfectNum(pathSimu);
        JFreeChart chart = lineChart.Plot(dataset);

        Date date = new Date();
        String fileName = lineChart.getTitle() + "_" + dateFormat3(date);
        Chart outputChart = new Chart(date, fileName);
        /* add outputChart to ChartDirectory*/
//        pageSimu.addChart(outputChart);
        saveChartAsJPG(chart, lineChart.getTitle(), fileName);
    }

    public static void drawLineChartInfectUnits(PageSimu pageSimu, String title) {
        LineChart lineChart = new LineChart(title + "LineChart", "Time", "Infect Number of Areas");
        XYSeriesCollection dataset = getXYSeriesDataSetInfectUnits(pageSimu.getOnePathogenSimuList());
        JFreeChart chart = lineChart.Plot(dataset);

        Date date = new Date();
        String fileName = lineChart.getTitle() + "_" + dateFormat3(date);
        Chart outputChart = new Chart(date, fileName);
        /* add outputChart to ChartDirectory*/
        pageSimu.addChart(outputChart);
        saveChartAsJPG(chart, lineChart.getTitle(), fileName);
    }

    // For Multiple Simulation
    public static void drawBarChartInfectNum(PageSimu pageSimu, String title) {
        BarChart barChart = new BarChart(title + "BarChart", "Time", "Total Infect Population");
        CategoryDataset dataset = getDataSetInfectNum(pageSimu.getOnePathogenSimuList());
        JFreeChart chart = barChart.Plot(dataset);

        Date date = new Date();
        String fileName = barChart.getTitle() + "_" +  dateFormat3(date);
        Chart outputChart = new Chart(date, fileName);
        pageSimu.addChart(outputChart);
        saveChartAsJPG(chart, barChart.getTitle(), fileName);
    }

    // For One Pathogen Simulation
    public static void drawScatterPlot(PageSimu pageSimu, String title) {
        ScatterPlot scatterPlot = new ScatterPlot(title + "ScatterPlot", "", "");
        DefaultXYDataset dataset = getDatasetEpiArea(pageSimu.getOnePathogenSimuList());
        JFreeChart chart = scatterPlot.Plot(dataset);

        // Add annotation for scatter plot
        XYPlot xyplot = (XYPlot) chart.getPlot();
        for (OnePathogenSimu pathogenSimu : pageSimu.getOnePathogenSimuList()) {
            AreaUnit[][] areaUnits = pathogenSimu.getAreaUnitArray();
            for (int i = 0; i < pathogenSimu.AREA_WIDTH; i++) {
                for (int j = 0; j < pathogenSimu.AREA_LENGTH; j++) {
                    if (areaUnits[i][j].getInfectNum() / areaUnits[i][j].getHeadcount() > 0.3) {
                        XYTextAnnotation text = new XYTextAnnotation(Double.toString(areaUnits[i][j].getInfectNum()), i, j);
                        xyplot.addAnnotation(text);
                    }
                }
            }
        }

        Date date = new Date();
        String fileName = scatterPlot.getTitle() + "_" +  dateFormat3(date);
        Chart outputChart = new Chart(date, fileName);
        pageSimu.addChart(outputChart);
        saveChartAsJPG(chart, scatterPlot.getTitle(), fileName);
    }

}
