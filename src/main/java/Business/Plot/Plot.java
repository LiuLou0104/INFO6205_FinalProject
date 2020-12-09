package Business.Plot;

import Business.DrawArea.AreaUnit;
import Business.Report.Chart;
import Business.Simulation.OnePathogenSimu;
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

    public static void drawLineChartInfectNum(OnePathogenSimu pathSimu, String title) {
        LineChart lineChart = new LineChart(title + " LineChart", "Time", "Total Infect Population");
        XYSeriesCollection dataset = getXYSeriesDataSetInfectNum(pathSimu.getInfectNumList());
        JFreeChart chart = lineChart.Plot(dataset);

        Date date = new Date();
        String fileName = lineChart.getTitle() + "_" + dateFormat3(date);
        Chart outputChart = new Chart(date, fileName);
        /* add outputChart to ChartDirectory*/
        pathSimu.addChart(outputChart);
        saveChartAsJPG(chart, lineChart.getTitle(), fileName);
    }

    public static void drawLineChartInfectUnits(OnePathogenSimu pathSimu, String title) {
        LineChart lineChart = new LineChart(title + " LineChart", "Time", "Number of Infect Areas");
        XYSeriesCollection dataset = getXYSeriesDataSetInfectUnits(pathSimu.getInfectUnitsList());
        JFreeChart chart = lineChart.Plot(dataset);

        Date date = new Date();
        String fileName = lineChart.getTitle() + "_" + dateFormat3(date);
        Chart outputChart = new Chart(date, fileName);
        /* add outputChart to ChartDirectory*/
        pathSimu.addChart(outputChart);
        saveChartAsJPG(chart, lineChart.getTitle(), fileName);
    }

//    // For Multiple Simulation
//    public static void drawBarChartInfectNum(PageSimu pageSimu, String title) {
//        BarChart barChart = new BarChart(title + "BarChart", "Time", "Total Infect Population");
//        CategoryDataset dataset = getDataSetInfectNum(pageSimu.getOnePathogenSimuList());
//        JFreeChart chart = barChart.Plot(dataset);
//
//        Date date = new Date();
//        String fileName = barChart.getTitle() + "_" +  dateFormat3(date);
//        Chart outputChart = new Chart(date, fileName);
//        pageSimu.addChart(outputChart);
//        saveChartAsJPG(chart, barChart.getTitle(), fileName);
//    }

    // For One Pathogen Simulation
    public static void drawScatterPlot(OnePathogenSimu pathSimu, String title) {
        ScatterPlot scatterPlot = new ScatterPlot(title + " ScatterPlot", "", "");
        DefaultXYDataset dataset = getDatasetEpiArea(pathSimu);
        JFreeChart chart = scatterPlot.Plot(dataset);

        // Add annotation for scatter plot
//        XYPlot xyplot = (XYPlot) chart.getPlot();

//        AreaUnit[][] areaUnits = pathSimu.getAreaUnitArray();
//        for (int i = 0; i < pathSimu.AREA_WIDTH; i++) {
//            for (int j = 0; j < pathSimu.AREA_LENGTH; j++) {
//                if (areaUnits[i][j].getInfectNum() / areaUnits[i][j].getHeadcount() > 0.3) {
//                    XYTextAnnotation text = new XYTextAnnotation(Double.toString(areaUnits[i][j].getInfectNum()), i, j);
//                    xyplot.addAnnotation(text);
//                }
//            }
//        }

        Date date = new Date();
        String fileName = scatterPlot.getTitle() + "_" +  dateFormat3(date);
        Chart outputChart = new Chart(date, fileName);
        pathSimu.addChart(outputChart);
        saveChartAsJPG(chart, scatterPlot.getTitle(), fileName);
    }

}
